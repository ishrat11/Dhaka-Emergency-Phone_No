package com.example.dhakaemergencyphone_no;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{

	
	Context context;
	ArrayList<RowItem>rowitems;
	private List<RowItem >rowlist=null;

	public CustomAdapter(Context context, List<RowItem> items) {
		this.context=context;
		this.rowlist=items;
		this.rowitems=new ArrayList<RowItem>();
		this.rowitems.addAll(items);
		
	}

	private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtno;
    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//return rowitems.size();
		return rowlist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		//return rowitems.get(position);
		return rowlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		//return rowitems.indexOf(getItem(position));
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;
		 LayoutInflater mInflater = (LayoutInflater)
		            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 if(convertView==null){
			 convertView=mInflater.inflate(R.layout.text, null);
			 holder=new ViewHolder();
			holder.txtTitle=(TextView)convertView.findViewById(R.id.title);
			holder.txtno=(TextView)convertView.findViewById(R.id.police_no);
			holder.imageView=(ImageView)convertView.findViewById(R.id.icon);
			convertView.setTag(holder);
		 }
		 else {
	            holder = (ViewHolder) convertView.getTag();
	        }
		 
		// RowItem itm=(RowItem)getItem(position);
		 RowItem itm=rowlist.get(position);
		 holder.txtTitle.setText(itm.getTitle());
		 holder.txtno.setText(itm.getPolice_no());
		 holder.imageView.setImageResource(itm.getImage());
		 
		return convertView;
	}

	

	 public void filter(String charText) {
	        charText = charText.toLowerCase(Locale.getDefault());
	        rowlist.clear();
	        if (charText.length() == 0) {
	            rowlist.addAll(rowitems);
	        } else {
	            for (RowItem wp : rowitems) {
	                if (wp.getTitle().toLowerCase(Locale.getDefault())
	                        .contains(charText)) {
	                    rowlist.add(wp);
	                }
	            }
	        }
	        notifyDataSetChanged();
	    }
}
