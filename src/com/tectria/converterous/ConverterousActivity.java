package com.tectria.converterous;

import kankan.wheel.widget.*;
import kankan.wheel.widget.adapters.*;
import android.app.Activity;
import android.content.Context;
import android.media.*;
import android.media.MediaPlayer.*;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ConverterousActivity extends Activity {
	
    private boolean scrolling = false;
    
    //Display display = null;
    
    private WheelView whlTo = null;
    private WheelView whlFrom = null;
    private WheelView whlType = null;
    private MediaPlayer mp = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mp = MediaPlayer.create(this, R.raw.click);
        //display = getWindowManager().getDefaultDisplay();
                
        whlTo = (WheelView)findViewById(R.id.whlTo);
        whlFrom = (WheelView)findViewById(R.id.whlFrom);
        whlType = (WheelView)findViewById(R.id.whlType);
        //TODO: Hide whlTo and whlFrom on startup
        
        whlTo.setViewAdapter(new ToUnitAdapter(this, "Mass"));
        whlFrom.setViewAdapter(new FromUnitAdapter(this, "Mass"));
        whlType.setViewAdapter(new UnitTypeAdapter(this));
        // Once whlTo and whlFrom are hidden, don't initialize them here; Only after whlType has been changed

        whlType.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldVal, int newVal) {
				updateFromUnits(newVal);
				playClick();
			}
		});
        
        whlType.addScrollingListener(new OnWheelScrollListener() {
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
                playClick();
            }
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
                updateFromUnits(whlType.getCurrentItem());
            }
        });
        
        whlType.setCurrentItem(0); //Set before defining whlFrom listeners to prevent unnecessary propagation
        
        whlFrom.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldVal, int newVal) {
				updateToUnits(whlType.getCurrentItem());
				playClick();
			}
		});
        
        whlFrom.addScrollingListener(new OnWheelScrollListener() {
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
                playClick();
            }
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
                updateToUnits(whlType.getCurrentItem());
            }
        });
        
        whlTo.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldVal, int newVal) {
			    //TODO: Update results
				playClick();
			}
		});
        
        whlTo.addScrollingListener(new OnWheelScrollListener() {
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
                playClick();
            }
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
                //TODO: Update results
            }
        });
        
        whlFrom.setCurrentItem(0);
    }
    
    public final void playClick() {
    	mp.start();
    }
    
    public final void updateFromUnits(int type) {
    	whlFrom.setViewAdapter(new FromUnitAdapter(this, UnitData.getTypeAtIndex(type)));
    	whlFrom.setCurrentItem(0);
    	updateToUnits(type);
    }
    
    public final void updateToUnits(int type) {
    	whlTo.setViewAdapter(new ToUnitAdapter(this, UnitData.getTypeAtIndex(type)));
    	whlTo.setCurrentItem(0);
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
        		++index;
        	}
        	
        	String[] item = UnitData.getUnitAtIndex(this.type, index, whlFrom.getCurrentItem());
        	
        	if(item[1] == "1") { 
        		this.avoided = true;
        	}
        	
            return item[0];
        }
    }
}