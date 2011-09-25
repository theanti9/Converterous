package com.tectria.converterous;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FavoriteListActivity extends Activity {
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoritelist);
        ListView lv = (ListView)findViewById(R.id.lstFavorites);
        FavoritesListAdapter favlist = new FavoritesListAdapter(this, new ArrayList<String>());
        ConverterousDbAdapter database = new ConverterousDbAdapter(this);
        database.open();
        Cursor c = database.getFavorites();
        while(c.moveToNext()) {
        	favlist.add(UnitData.getName(c.getString(0))+" to "+UnitData.getName(c.getString(1)));
        }
        lv.setAdapter(favlist);
        
        
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
				String res = (String)adapter.getAdapter().getItem(position);
				String[] items = res.split(" ");
				
				Bundle b = new Bundle();
				b.putString("unit1", UnitData.getAbv(items[0]));
				b.putString("unit2", UnitData.getAbv(items[2]));
				Intent intent = new Intent(getBaseContext(), ConverterousActivity.class);
				intent.putExtras(b);
				startActivity(intent);
			}
        	
        });
        
    }
}
