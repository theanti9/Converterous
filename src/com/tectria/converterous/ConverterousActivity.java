package com.tectria.converterous;

import kankan.wheel.widget.*;
import kankan.wheel.widget.adapters.*;
import android.app.Activity;
import android.content.Context;
import android.media.*;
import android.os.Bundle;
import android.text.Html;
import android.view.*;
import android.view.View.*;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView.*;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import java.text.*;

public class ConverterousActivity extends Activity {
	
    private boolean scrolling = false;
    private int savedToItem = 0;
    private int savedFromItem = 0;
    private int offset;
    private int fromTextSize;
    private int toTextSize;
    
    private LinearLayout llayout = null;
    private WheelView whlTo = null;
    private WheelView whlFrom = null;
    private WheelView whlType = null;
    private FontResizingEditText txtFromVal = null;
    private FontResizingEditText txtToVal = null;
    private TextView lblEq = null;
    private TextView lblType = null;
    private TextView lblTo = null;
    private TextView lblFrom = null;
    
    private MediaPlayer click_mp = null;
    private MediaPlayer select_mp = null;
    private ConvertTool converter = null;
    private NumberFormat dec = new DecimalFormat("#0.000");
    InputMethodManager imm = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        click_mp = MediaPlayer.create(this, R.raw.click);
        select_mp = MediaPlayer.create(this, R.raw.select);
        
        converter = new ConvertTool();
        
        llayout = (LinearLayout)findViewById(R.id.llayout);
        txtFromVal = (FontResizingEditText)findViewById(R.id.txtFromVal);
        txtToVal = (FontResizingEditText)findViewById(R.id.txtToVal);
        lblEq = (TextView)findViewById(R.id.lblEq);
        lblType = (TextView)findViewById(R.id.lblType);
        lblTo = (TextView)findViewById(R.id.lblTo);
        lblFrom = (TextView)findViewById(R.id.lblFrom);
        txtFromVal.setText("0");
        txtToVal.setText("0");
        
        whlTo = (WheelView)findViewById(R.id.whlTo);
        whlFrom = (WheelView)findViewById(R.id.whlFrom);
        whlType = (WheelView)findViewById(R.id.whlType);
        
        whlTo.setViewAdapter(new ToUnitAdapter(this, "Mass"));
        whlFrom.setViewAdapter(new FromUnitAdapter(this, "Mass"));
        whlType.setViewAdapter(new UnitTypeAdapter(this));

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
				lblFrom.setText(UnitData.getUnitAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlFrom.getCurrentItem()));
				updateToUnits(whlType.getCurrentItem());
				playClick();
			}
		});
        
        whlFrom.addScrollingListener(new OnWheelScrollListener() {
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
            }
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
                updateToUnits(whlType.getCurrentItem());
                showResults();
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
				lblTo.setText(UnitData.getUnitAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlTo.getCurrentItem()));
				playClick();
				
			}
		});
        
        whlTo.addScrollingListener(new OnWheelScrollListener() {
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
            }
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
                updateFromUnits(whlType.getCurrentItem());
                showResults();
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
            		txtFromVal.setText(Html.fromHtml(getNumeric(txtFromVal.getText().toString()) + "<small><small><sub>" + UnitData.getAbvAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlFrom.getCurrentItem()) + "</sub></small></small>"));
            		imm.hideSoftInputFromWindow(llayout.getWindowToken(), 0);
            		llayout.requestFocus();
				}
			}
        });
        
        txtFromVal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				playSelect();
				txtFromVal.setText(getNumeric(txtFromVal.getText().toString()));
				txtFromVal.setSelection(txtFromVal.getText().length());
			}
        });
        
        txtFromVal.setOnFocusChangeListener(new OnFocusChangeListener() {          
            public void onFocusChange(View v, boolean hasFocus) {
            	if(hasFocus) {
            		playSelect();
            		txtFromVal.setText(getNumeric(txtFromVal.getText().toString()));
            		txtFromVal.setSelection(txtFromVal.getText().length());
            	} else {
            		if(getNumeric(txtFromVal.getText().toString()) == "") {
            			txtFromVal.setText("0");
            		}
            		txtFromVal.setText(Html.fromHtml(getNumeric(txtFromVal.getText().toString()) + "<small><small><sub>" + UnitData.getAbvAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlFrom.getCurrentItem()) + "</sub></small></small>"));
            		imm.hideSoftInputFromWindow(llayout.getWindowToken(), 0);
            		llayout.requestFocus();
            	}
            }
        });
        
        txtFromVal.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View view, int id, KeyEvent event) {
				recalcToVal();
				return false;
			}
        });
        
        txtToVal.setOnFocusChangeListener(new OnFocusChangeListener() {          
            public void onFocusChange(View v, boolean hasFocus) {
            	if(hasFocus) {
            		playSelect();
            		txtToVal.setSelection(0, txtToVal.getText().length());
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
    		} else if(c == ',') {
    			num.append(c);
    		}
    	}
    	return num.toString();
    }
    
    public final void playClick() {
    	click_mp.release();
    	click_mp = MediaPlayer.create(this, R.raw.click);
    	click_mp.setVolume(0.1f, 0.1f);
    	click_mp.start();
    }
    
    public final void playSelect() {
    	select_mp.release();
    	select_mp = MediaPlayer.create(this, R.raw.select);
    	select_mp.setVolume(0.1f, 0.1f);
    	select_mp.start();
    }
    
    public final void playResult() {
    	select_mp.release();
    	select_mp = MediaPlayer.create(this, R.raw.result);
    	select_mp.setVolume(0.1f, 0.1f);
    	select_mp.start();
    }
    
	public final void hideResults() {
		txtFromVal.setVisibility(View.INVISIBLE);
		txtToVal.setVisibility(View.INVISIBLE);
		lblEq.setVisibility(View.INVISIBLE);
	}
	
	public final void recalcToVal() {
		offset = 0;
		if(whlTo.getCurrentItem() >= whlFrom.getCurrentItem()) {
			offset = 1;
		}
		double fromnum;
		if(getNumeric(txtFromVal.getText().toString()) == "") {
			fromnum = 0.0;
		} else {
			fromnum = Double.parseDouble(getNumeric(txtFromVal.getText().toString()));
		}
		converter.setFromUnit(UnitData.getAbvAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlFrom.getCurrentItem()));
		converter.setToUnit(UnitData.getAbvAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlTo.getCurrentItem() + offset));
		converter.setFromNum(fromnum);
		txtToVal.setText(Html.fromHtml(dec.format(converter.convert()) + "<small><small><sub>" + UnitData.getAbvAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlTo.getCurrentItem() + offset) + "</sub></small></small>"));
	}
	
	public final void showResults() {
		offset = 0;
		if(whlTo.getCurrentItem() >= whlFrom.getCurrentItem()) {
			offset = 1;
		}
		
		converter.setFromUnit(UnitData.getAbvAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlFrom.getCurrentItem()));
		converter.setToUnit(UnitData.getAbvAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlTo.getCurrentItem() + offset));
		converter.setFromNum(Double.parseDouble(getNumeric(txtFromVal.getText().toString())));
		
		txtFromVal.setText(Html.fromHtml(getNumeric(txtFromVal.getText().toString()) + "<small><small><sub>" + UnitData.getAbvAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlFrom.getCurrentItem()) + "</sub></small></small>"));
		txtToVal.setText(Html.fromHtml(dec.format(converter.convert()) + "<small><small><sub>" + UnitData.getAbvAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlTo.getCurrentItem() + offset) + "</sub></small></small>"));
		
		if(txtFromVal.getText().length() > 9) {
			
		}
		
		playResult();
		lblFrom.setText(UnitData.getUnitAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlFrom.getCurrentItem()));
		lblTo.setText(UnitData.getUnitAtIndex(UnitData.getTypeAtIndex(whlType.getCurrentItem()), whlTo.getCurrentItem() + offset));
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
        	
        	if(item[1] == "1") { 
        		this.avoided = true;
        		this.avoided_index = index;
        	}
        	
            return item[0];
        }
    }
}