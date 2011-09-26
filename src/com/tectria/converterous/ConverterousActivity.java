package com.tectria.converterous;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class ConverterousActivity extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    Resources res = getResources();
	    TabHost tabHost = getTabHost();
	    TabHost.TabSpec spec;
	    Intent intent;
	    
	    intent = new Intent().setClass(this, FavoriteListActivity.class);
	    
	    spec = tabHost.newTabSpec("favorites").setIndicator("Favorites",
	                      res.getDrawable(R.drawable.tab_icons_favorites))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, ConverterActivity.class);
	    spec = tabHost.newTabSpec("converter").setIndicator("Converter",
	                      res.getDrawable(R.drawable.tab_icons_converter))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(0);
	}
}