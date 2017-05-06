package com.future.wk.newcontacter.widget.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.future.wk.newcontacter.R;


public class ListViewEmptyLayout extends LinearLayout {

	TextView tv;
	ImageView iv;
	TextView tv_empty;
	OnEmptyListener listener;

	public ListViewEmptyLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.layout_listview_empty, this);
		tv = (TextView)findViewById(R.id.listview_empty_text);
		iv = (ImageView)findViewById(R.id.listview_empty_img);
		tv_empty = (TextView)findViewById(R.id.tv_refresh);
		tv_empty.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(listener != null) {
					setVisibility(View.GONE);
					listener.onEmptyClick();
				}
			}
		});
	}

    public void setNormalEmpty(String text,int resource){
        iv.setImageResource(resource);
        tv.setText(text);
    }

	public void setNormalEmpty(String text){
		iv.setImageResource(R.mipmap.img_search_empty);
		tv.setText(text);
	}
	
	public void setSearchEmpty(String text){
		iv.setImageResource(R.mipmap.img_search_empty);
		tv.setText(text);
	}

	public void setOnEmptyListener(OnEmptyListener listener){
		tv_empty.setVisibility(View.VISIBLE);
		this.listener = listener;
	}

	public interface OnEmptyListener {
		void onEmptyClick();
	}

}
