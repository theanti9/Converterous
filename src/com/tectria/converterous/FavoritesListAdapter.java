package com.tectria.converterous;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FavoritesListAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private final List<String> items;
	
	public FavoritesListAdapter(Activity context, List<String> items) {
		super(context,R.layout.favrowlayout, items);
		
		this.context = context;
		this.items = items;
	}
	
	static class ViewHolder {
		public TextView textView;
	}

	public void append(List<String> items) {
		this.items.addAll(items);
	}
	
	public void add(String item) {
		this.items.add(item);
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.favrowlayout, null, true);
			holder = new ViewHolder();
			holder.textView = (TextView) rowView.findViewById(R.id.favoriteUnit);
			rowView.setTag(holder);
		} else {
			holder = (ViewHolder) rowView.getTag();
		}
		
		holder.textView.setText(items.get(position));
		
		return rowView;
	}
}
