package com.future.wk.newcontacter.widget.common;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.future.wk.newcontacter.R;

public class CopyTextView extends TextView {

	public CopyTextView(Context context) {
		super(context);
		init();
	}
	
	public CopyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init(){
		onNormal();
		this.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				showCopyDialog();
				return false;
			}
		});
	}
	
	
	/** --------------------------- dialog -----------------------------*/
	CopyPopDialog dialog;
	private void showCopyDialog(){
		if(dialog == null){
			dialog = new CopyPopDialog(getContext());
		}
		if(!dialog.isShowing()){
			dialog.show();
		}
	}
	
	private class CopyPopDialog extends Dialog {

		public CopyPopDialog(Context context) {
			super(context, R.style.CopyTextDialogStyle);
		}
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			View content = LayoutInflater.from(getContext()).inflate(R.layout.dialog_copytext, null);
			setContentView(content);
			
			RelativeLayout layout = (RelativeLayout)content.findViewById(R.id.copytext_layout);
			
			getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			
			WindowManager.LayoutParams wlp = getWindow().getAttributes();
			wlp.gravity = Gravity.BOTTOM;
			getWindow().setAttributes(wlp);
			this.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
			this.setCancelable(true);
			setOnDismissListener(new OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					onNormal();
				}
			});
			content.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					CopyPopDialog.this.dismiss();
				}
			});
			
			layout.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					CopyPopDialog.this.dismiss();
					copyText();
				}
			});
			
		}
		
		@Override
		public void show() {
			super.show();
			onSelected();
		}
	}
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	protected void copyText(){
		try {
			ClipboardManager clipManager = (ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				ClipData clip =ClipData.newPlainText("text",getText().toString());
				clipManager.setPrimaryClip(clip);
			}else{
				clipManager.setText(getText().toString());
			}
		} catch (Throwable e) {
		}
		
	}
	
	
	private void onNormal(){
		setBackgroundResource(R.drawable.selector_alpha_white);
	}
	
	private void onSelected(){
		setBackgroundResource(R.drawable.bg_copytext);
	}
}
