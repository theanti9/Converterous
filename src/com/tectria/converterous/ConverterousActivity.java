package com.tectria.converterous;

import kankan.wheel.widget.*;
import kankan.wheel.widget.adapters.*;
import android.app.Activity;
import android.content.Context;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //display = getWindowManager().getDefaultDisplay();
                
        whlTo = (WheelView)findViewById(R.id.whlTo);
        whlFrom = (WheelView)findViewById(R.id.whlFrom);
        whlType = (WheelView)findViewById(R.id.whlType);
        //TODO: Hide whlTo and whlFrom on startup
        
        whlTo.setViewAdapter(new ToUnitAdapter(this, "Mass"));
        whlFrom.setViewAdapter(new FromUnitAdapter(this, "Mass"));
        whlType.setViewAdapter(new UnitTypeAdapter(this));
        // Once whlTo and whlFrom are hidden, don't initialize them here; Only after whlType has been changed
        
        whlType.setCurrentItem(1);

        whlType.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldVal, int newVal) {
			    if (!scrolling) {
			        //TODO: Update whlTo and whlFrom to have units from the selected categories
			    	// We do that by just recreating and resetting the adapter.
			    }
			}
		});
        
        whlType.addScrollingListener(new OnWheelScrollListener() {
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
            }
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
                //TODO: Update whlTo and whlFrom to have units from the selected categories
                // We do that by just recreating and resetting the adapter.
            }
        });
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
            return UnitData.getAtIndex("Units", index);
        }
    }
    
    /*
     * Unit Select Adapter
     */
    private class ToUnitAdapter extends AbstractWheelTextAdapter {
        
    	public String type = null;
    	
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
            return UnitData.getLength(this.type);
        }
        
        @Override
        protected CharSequence getItemText(int index) {
            return UnitData.getAtIndex(this.type, index);
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
            return UnitData.getAtIndex(this.type, index);
        }
    }
}