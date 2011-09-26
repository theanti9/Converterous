package com.tectria.converterous;

import kankan.wheel.widget.*;
import kankan.wheel.widget.adapters.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.media.*;
import android.os.Bundle;
import android.text.*;
import android.view.*;
import android.view.View.*;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView.*;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.util.TypedValue;

import java.text.*;

public class ConverterousActivity extends Activity {
	
    private boolean scrolling = false;
    private int savedToItem = 0;
    private int savedFromItem = 0;
    private int offset;
    private float initialTextSize;
    private int lastFromLen = 0;
    private int lastToLen = 0;
    
    private LinearLayout llayout = null;
    private WheelView whlTo = null;
    private WheelView whlFrom = null;
    private WheelView whlType = null;
    private EditText txtFromVal = null;
    private EditText txtToVal = null;
    private TextView lblEq = null;
    private TextView lblType = null;
    private TextView lblTo = null;
    private TextView lblFrom = null;
    
    private AlertDialog.Builder dialog = null;
    private TextView dialogText = null;
    
    private MediaPlayer click_mp = null;
    private MediaPlayer select_mp = null;
    private ConvertTool converter = null;
    private NumberFormat dec = new DecimalFormat("###.#######");
    final Paint textMeasure = new Paint();
    InputMethodManager imm = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        click_mp = MediaPlayer.create(this, R.raw.click);
        click_mp.setVolume(0.01f, 0.01f);
        select_mp = MediaPlayer.create(this, R.raw.select);
        select_mp.setVolume(0.01f, 0.01f);
        
        converter = new ConvertTool();
        
        llayout = (LinearLayout)findViewById(R.id.llayout);
        txtFromVal = (EditText)findViewById(R.id.txtFromVal);
        txtToVal = (EditText)findViewById(R.id.txtToVal);
        lblEq = (TextView)findViewById(R.id.lblEq);
        lblType = (TextView)findViewById(R.id.lblType);
        lblTo = (TextView)findViewById(R.id.lblTo);
        lblFrom = (TextView)findViewById(R.id.lblFrom);
        txtFromVal.setText("0");
        txtToVal.setText("0");
        initialTextSize = txtFromVal.getTextSize();
        
        whlTo = (WheelView)findViewById(R.id.whlTo);
        whlFrom = (WheelView)findViewById(R.id.whlFrom);
        whlType = (WheelView)findViewById(R.id.whlType);
        
        whlTo.setViewAdapter(new ToUnitAdapter(this, "Mass"));
        whlFrom.setViewAdapter(new FromUnitAdapter(this, "Mass"));
        whlType.setViewAdapter(new UnitTypeAdapter(this));
        
       lblTo.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
		        String unitname = lblTo.getText().toString();
				String unitinfo;
				try {
					unitinfo = (String)getResources().getText(getResources().getIdentifier(unitname.toUpperCase(), "string", "com.tectria.converterous"));
				} catch(Resources.NotFoundException e) {
					unitinfo = "No information availible.";
				}
				
				makeDialog(unitname, unitinfo);
				return false;
			}
        });
        
        lblFrom.setOnTouchListener(new OnTouchListener() {
        	@Override
			public boolean onTouch(View v, MotionEvent event) {
		        String unitname = lblFrom.getText().toString();
				String unitinfo;
				try {
					unitinfo = (String)getResources().getText(getResources().getIdentifier(unitname.toUpperCase(), "string", "com.tectria.converterous"));
				} catch(Resources.NotFoundException e) {
					unitinfo = "No information availible.";
				}
				
				makeDialog(unitname, unitinfo);
				return false;
			}
        });
        
        whlType.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldVal, int newVal) {
				lblType.setText("Converting " + UnitData.getTypeAtIndex(whlType.getCurrentItem()));
				updateFromUnits(newVal);
				playClick();
			}
		});
        
        whlType.addScrollingListener(new OnWheelScrollListener() {
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
                hideResults();
            }
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
                updateFromUnits(whlType.getCurrentItem());
                showResults();
            }
        });

        whlType.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					playSelect();
				}
				return false;
			}
        });
        
        whlFrom.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldVal, int newVal) {
				lblFrom.setText(casecor(UnitData.getUnitAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlFrom.getCurrentItem())));
				updateToUnits(whlType.getCurrentItem());
				showResults();
				playClick();
			}
		});
        
        whlFrom.addScrollingListener(new OnWheelScrollListener() {
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
            }
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
                recalcToVal();
            }
        });
        
        whlFrom.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					playSelect();
				}
				return false;
			}
        });
        
        whlTo.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldVal, int newVal) {
				recalcToVal();
				playClick();
				
			}
		});
        
        whlTo.addScrollingListener(new OnWheelScrollListener() {
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
            }
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
                recalcToVal();
            }
        });
        
        whlTo.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN) {
					playSelect();
				}
				return false;
			}
        });
                
        //Set up the result EditText view events
        llayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(v != txtFromVal) {
            		txtFromVal.setText(Html.fromHtml(getNumeric(txtFromVal.getText().toString()) + "<small><small>" + UnitData.getAbvAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlFrom.getCurrentItem()) + "</small></small>"));
            		imm.hideSoftInputFromWindow(llayout.getWindowToken(), 0);
            		llayout.requestFocus();
				}
			}
        });
        
        txtFromVal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				playSelect();
				String text = getNumeric(txtFromVal.getText().toString());
				if(text.equals("0")) {
					txtFromVal.setText("");
				} else {
					txtFromVal.setText(getNumeric(txtFromVal.getText().toString()));
					//txtFromVal.setSelection(txtFromVal.getText().length());
					txtFromVal.setSelection(0, txtFromVal.getText().length());
				}
				
			}
        });
        
        txtFromVal.setOnFocusChangeListener(new OnFocusChangeListener() {          
            public void onFocusChange(View v, boolean hasFocus) {
            	if(hasFocus) {
            		playSelect();
            		String text = getNumeric(txtFromVal.getText().toString());
    				if(text.equals("0")) {
    					txtFromVal.setText("");
    				} else {
    					txtFromVal.setText(getNumeric(txtFromVal.getText().toString()));
    					txtFromVal.setSelection(txtFromVal.getText().length());
    				}
            	} else {
            		if(getNumeric(txtFromVal.getText().toString()).equals("")) {
            			txtFromVal.setText("0");
            		}
            		txtFromVal.setText(Html.fromHtml(getNumeric(txtFromVal.getText().toString()) + "<small><small>" + UnitData.getAbvAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlFrom.getCurrentItem()) + "</small></small>"));
            		imm.hideSoftInputFromWindow(llayout.getWindowToken(), 0);
            		if(txtFromVal.getLineCount() > 1) {
						txtFromVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtFromVal.getTextSize()/1.3f);
					}
            		llayout.requestFocus();
            	}
            }
        });
        
        txtFromVal.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View view, int id, KeyEvent event) {
				
				//If string length increased
				if(txtFromVal.getText().length() > lastFromLen) {
					//Decrease font size until it only spans one line
					if(txtFromVal.getLineCount() > 1) {
						txtFromVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtFromVal.getTextSize()/1.3f);
					}
				}
				else if(txtFromVal.getText().length() < lastFromLen) {
					//txtFromVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, initialTextSize);
					if(txtFromVal.getLineCount() == 1 && txtFromVal.getTextSize() < initialTextSize) {
						txtFromVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, chooseSize(txtFromVal));
					}
				}
				
				lastFromLen = txtFromVal.getText().length();
				recalcToVal();
				return false;
			}
        });
        
        txtFromVal.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				//Decrease font size until it only spans one line
				if(txtFromVal.getLineCount() > 1) {
					txtFromVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtFromVal.getTextSize()/1.3f);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				//Decrease font size until it only spans one line
				if(txtFromVal.getLineCount() > 1) {
					txtFromVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtFromVal.getTextSize()/1.3f);
				}
			}
        	
        });
        
        txtToVal.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				//Decrease font size until it only spans one line
				if(txtToVal.getLineCount() > 1) {
					txtToVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtToVal.getTextSize()/1.3f);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				//Decrease font size until it only spans one line
				if(txtToVal.getLineCount() > 1) {
					txtToVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtToVal.getTextSize()/1.3f);
				}
			}
        	
        });
        
        txtToVal.setOnFocusChangeListener(new OnFocusChangeListener() {          
            public void onFocusChange(View v, boolean hasFocus) {
            	if(hasFocus) {
            		playSelect();
            		txtToVal.setSelection(0, txtToVal.getText().length());
            		imm.hideSoftInputFromWindow(llayout.getWindowToken(), 0);
            	}
            }
        });
        
        whlType.setCurrentItem(0); //Set before defining whlFrom listeners to prevent unnecessary propagation
        whlFrom.setCurrentItem(0);
        
		showResults();
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	txtFromVal.setOnEditorActionListener(new OnEditorActionListener() {
			public boolean onEditorAction(TextView view, int id, KeyEvent event) {
				if(id == EditorInfo.IME_NULL) {
					imm.hideSoftInputFromWindow(llayout.getWindowToken(), 0);
            		llayout.requestFocus();
				}
				return true;
    		}
    	});
    }
    
    public final String casecor(String text) {
    	text = String.valueOf(text.charAt(0)).toUpperCase() + text.substring(1, text.length());
    	return text;
    }
    
    public final void makeDialog(String title, String text) {
    	dialog = new AlertDialog.Builder(this);
        dialogText = new TextView(this);
        dialogText.setText(text);
        dialogText.setPadding(10, 10, 10, 10);
        dialog.setView(dialogText);
        dialog.setTitle(title);
        dialog.create();
        dialog.show();
    }
    
    public final String getNumeric(String str) {
    	StringBuffer num = new StringBuffer();
    	char c;
    	int i;
    	for(i=0;i<str.length();i++) {
    		c = str.charAt(i);
    		if(Character.isDigit(c)) {
    			num.append(c);
    		} else if(c == '.') {
    			num.append(c);
    		} else if(c == '-') {
    			num.append(c);
    		}
    	}
    	String result = num.toString();
    	if(result.equals(".") || result.equals("-")) {
    		result = "0.0";
    	}
    	return result.replace("-.", "0.0");
    }
    
    public final void playClick() {
    	click_mp.start();
    }
    
    public final void playSelect() {
    	select_mp.start();
    }
    
	public final void hideResults() {
		txtFromVal.setVisibility(View.INVISIBLE);
		txtToVal.setVisibility(View.INVISIBLE);
		lblEq.setVisibility(View.INVISIBLE);
	}
	
	public final float chooseSize(EditText view) {
		final float scaled = view.getTextSize()*1.1f;
		if(scaled < initialTextSize) {
			return scaled;
		} else {
			return initialTextSize;
		}
	}
	
	public final void recalcToVal() {
		offset = 0;
		if(whlTo.getCurrentItem() >= whlFrom.getCurrentItem()) {
			offset = 1;
		}
		double fromnum;
		if(getNumeric(txtFromVal.getText().toString()).equals("")) {
			fromnum = 0.0;
		} else {
			fromnum = Double.parseDouble(getNumeric(txtFromVal.getText().toString()));
		}
		lblTo.setText(casecor(UnitData.getUnitAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlTo.getCurrentItem() + offset)));
		converter.setFromUnit(UnitData.getUnitAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlFrom.getCurrentItem()));
		converter.setToUnit(UnitData.getUnitAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlTo.getCurrentItem() + offset));
		converter.setFromNum(fromnum);
		converter.setFromSI(UnitData.getIsSIFor(whlType.getCurrentItem(), whlFrom.getCurrentItem()));
		converter.setToSI(UnitData.getIsSIFor(whlType.getCurrentItem(), whlTo.getCurrentItem() + offset));
		txtToVal.setText(Html.fromHtml(dec.format(converter.convert()) + "<small><small>" + UnitData.getAbvAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlTo.getCurrentItem() + offset) + "</small></small>"));
		//If string length increased
		if(txtToVal.getText().length() > lastToLen) {
			//Decrease font size until it only spans one line
			if(txtToVal.getLineCount() > 1) {
				txtToVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtToVal.getTextSize()/1.3f);
			}
		}
		else if(txtToVal.getText().length() < lastToLen) {
			if(txtToVal.getLineCount() == 1 && txtToVal.getTextSize() < initialTextSize) {
				txtToVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, chooseSize(txtToVal));
			}
		}
		lastToLen = txtToVal.getText().length();
	}
	
	public final void showResults() {
		offset = 0;
		if(whlTo.getCurrentItem() >= whlFrom.getCurrentItem()) {
			offset = 1;
		}
		
		converter.setFromUnit(UnitData.getUnitAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlFrom.getCurrentItem()));
		converter.setToUnit(UnitData.getUnitAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlTo.getCurrentItem() + offset));
		converter.setFromNum(Double.parseDouble(getNumeric(txtFromVal.getText().toString())));
		converter.setFromSI(UnitData.getIsSIFor(whlType.getCurrentItem(), whlFrom.getCurrentItem()));
		converter.setToSI(UnitData.getIsSIFor(whlType.getCurrentItem(), whlTo.getCurrentItem() + offset));
		
		txtFromVal.setText(Html.fromHtml(getNumeric(txtFromVal.getText().toString()) + "<small><small>" + UnitData.getAbvAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlFrom.getCurrentItem()) + "</small></small>"));
		txtToVal.setText(Html.fromHtml(dec.format(converter.convert()) + "<small><small>" + UnitData.getAbvAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlTo.getCurrentItem() + offset) + "</small></small>"));
		
		if(txtFromVal.getLineCount() > 1) {
			txtFromVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtFromVal.getTextSize()/1.3f);
		}
		
		if(txtToVal.getText().length() > lastToLen) {
			//Decrease font size until it only spans one line
			if(txtToVal.getLineCount() > 1) {
				txtToVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtToVal.getTextSize()/1.3f);
			}
		}
		else if(txtToVal.getText().length() < lastToLen) {
			if(txtToVal.getLineCount() == 1 && txtToVal.getTextSize() < initialTextSize) {
				txtToVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, initialTextSize);
			}
		} else {
			txtToVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, initialTextSize);
		}
		
		lblFrom.setText(casecor(UnitData.getUnitAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlFrom.getCurrentItem())));
		lblTo.setText(casecor(UnitData.getUnitAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlTo.getCurrentItem() + offset)));
		lblType.setText("Converting " + UnitData.getTypeAtIndex(whlType.getCurrentItem()));
		txtFromVal.setPadding(txtFromVal.getPaddingLeft(), 2, txtFromVal.getPaddingRight(), 6);
		txtFromVal.setVisibility(View.VISIBLE);
		txtToVal.setVisibility(View.VISIBLE);
		lblEq.setVisibility(View.VISIBLE);
	}
    
    public final void updateFromUnits(int type) {
    	savedFromItem = whlFrom.getCurrentItem();
    	whlFrom.setViewAdapter(new FromUnitAdapter(this, UnitData.getTypeAtIndex(type)));
    	whlFrom.setCurrentItem(savedFromItem);
    	updateToUnits(type);
    }
    
    public final void updateToUnits(int type) {
    	savedToItem = whlTo.getCurrentItem();
    	whlTo.setViewAdapter(new ToUnitAdapter(this, UnitData.getTypeAtIndex(type)));
    	whlTo.setCurrentItem(savedToItem);
    	txtToVal.setTextSize(TypedValue.COMPLEX_UNIT_PX, initialTextSize);
    	if(!scrolling) {
    		showResults();
    	}
    }
    
    /*
     * Unit Type Adapter
     */
    private class UnitTypeAdapter extends AbstractWheelTextAdapter {
        
    	protected UnitTypeAdapter(Context context) {
			super(context, R.layout.wheelitem, NO_RESOURCE);
			setItemTextResource(R.id.wheelitem);
		}

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }
        
        @Override
        public int getItemsCount() {
            return UnitData.getLength("Units");
        }
        
        @Override
        protected CharSequence getItemText(int index) {
        	return UnitData.getTypeAtIndex(index);
        }
    }
    
    /*
     * From Unit Select Adapter
     */
    private class FromUnitAdapter extends AbstractWheelTextAdapter {
        
    	public String type = null;
    	
    	protected FromUnitAdapter(Context context, String type) {
			super(context, R.layout.wheelitem, NO_RESOURCE);
			setItemTextResource(R.id.wheelitem);
			this.type = type;
		}

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }
        
        @Override
        public int getItemsCount() {
            return UnitData.getLength(this.type);
        }
        
        @Override
        protected CharSequence getItemText(int index) {
            return UnitData.getUnitAtIndex(this.type, index);
        }
    }
    
    /*
     * To Unit Select Adapter
     */
    private class ToUnitAdapter extends AbstractWheelTextAdapter {
        
    	public String type = null;
    	public boolean avoided = false;
    	public int avoided_index = 0;
    	
    	protected ToUnitAdapter(Context context, String type) {
			super(context, R.layout.wheelitem, NO_RESOURCE);
			setItemTextResource(R.id.wheelitem);
			this.type = type;
		}

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }
        
        @Override
        public int getItemsCount() {
            return UnitData.getLength(this.type) - 1;
        }
        
        @Override
        protected CharSequence getItemText(int index) {
        	
        	if(this.avoided) {
        		if(index < avoided_index) {
        			this.avoided = false;
        		} else {
        			++index;
        		}
        	}
        	
        	String[] item = UnitData.getUnitAtIndex(this.type, index, whlFrom.getCurrentItem());
        	
        	if(item[1].equals("1")) { 
        		this.avoided = true;
        		this.avoided_index = index;
        	}
        	
            return item[0];
        }
    }
}