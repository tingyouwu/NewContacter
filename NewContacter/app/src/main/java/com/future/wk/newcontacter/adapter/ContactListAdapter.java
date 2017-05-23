package com.future.wk.newcontacter.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.future.wk.newcontacter.R;
import com.future.wk.newcontacter.activity.YellowPageDetailActivity;
import com.future.wk.newcontacter.data.dalex.ContactDALEx;
import com.future.wk.newcontacter.util.CorePinYinUtil;
import com.future.wk.newcontacter.widget.roundedimageview.RoundedImageView;
import com.future.wk.newcontacter.widget.xrecyclerview.adapter.BaseRecyclerViewAdapter;
import com.future.wk.newcontacter.widget.xrecyclerview.adapter.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static android.view.View.GONE;

/**
 * 通讯录适配器
 */

public class ContactListAdapter extends BaseRecyclerViewAdapter<ContactDALEx> {

    private String TAG = "ContactListAdapter";

    //是否支持字母列表
    private boolean isAlphaSearch = true;
    //字母列表
    private Map<String, Integer> alphaIndexer;

    ImageView iv_phone;
    ImageView iv_add;
    ImageView iv_message;

    public ContactListAdapter(Context context, List<ContactDALEx> data) {
        super(context, R.layout.item_contact_list, data);
//        init();
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, final ContactDALEx contact, final int position) {
        RoundedImageView iv_icon = holder.getView(R.id.item_contact_icon);
        iv_phone = holder.getView(R.id.item_contact_phone);
        iv_add = holder.getView(R.id.item_add_contact);
        iv_message = holder.getView(R.id.item_contact_message);
        TextView tv_name = holder.getView(R.id.item_contact_name);
        TextView tv_number = holder.getView(R.id.item_contact_number);
        TextView tv_alpha = holder.getView(R.id.item_contact_alpha);

        if((mContext instanceof YellowPageDetailActivity) && (ContactDALEx.get().findYPContacterById(contact.getUserphone()).size() == 0)){
            Log.d(TAG,"set add picture");
            iv_message.setVisibility(View.GONE);
            iv_add.setVisibility(View.VISIBLE);
        }else{
            Log.d(TAG,"set message picture");
            iv_message.setVisibility(View.VISIBLE);
            iv_add.setVisibility(View.GONE);
        }

        iv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+contact.getUserphone()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("提醒");
                builder.setMessage("确认添加到常用号码中吗？");

                //监听下方button点击事件
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        contact.saveOrUpdate();
                        iv_message.setVisibility(View.VISIBLE);
                        iv_add.setVisibility(View.GONE);
                        Toast.makeText(mContext,"已添加",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(mContext, "已取消", Toast.LENGTH_SHORT).show();
                    }
                });
                //设置对话框是可取消的
                builder.setCancelable(true);
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
        tv_name.setText(contact.getUsername());
        tv_number.setText(contact.getUserphone());
        tv_alpha.setText(CorePinYinUtil.getPinyinFirstAlpha(contact.getNamepinyin()));
        iv_icon.setImageResource(R.mipmap.img_contact_default);
        if (!TextUtils.isEmpty(contact.getUsericon())) {
            if (contact.getUsericon().startsWith("content")) {
            } else {
            }
        }

        if (position > 0) {
            // 不是第一行，控制pinyin栏
            ContactDALEx lastContact = getItem(position - 1);
            String lastAlpha = CorePinYinUtil.getPinyinFirstAlpha(lastContact.getNamepinyin());
            String nowAlpha = CorePinYinUtil.getPinyinFirstAlpha(contact.getNamepinyin());
            if (lastAlpha.equals(nowAlpha)) {
                tv_alpha.setVisibility(GONE);
            } else {
                tv_alpha.setVisibility(View.VISIBLE);
            }
        } else {
            tv_alpha.setVisibility(View.VISIBLE);
        }

    }

    public void init() {

        if (isAlphaSearch) {
            //构建字母列表索引
            if (alphaIndexer == null) {
                alphaIndexer = new LinkedHashMap<>();
            } else {
                alphaIndexer.clear();
            }

            for (int index = 0; index < getData().size(); index++) {
                String alpha = CorePinYinUtil.getPinyinFirstAlpha(getData().get(index).getNamepinyin());
                if (!alphaIndexer.containsKey(alpha)) {
                    alphaIndexer.put(alpha, index);
                }
            }
        }
    }

    @Override
    public void refreshList(List<ContactDALEx> data) {
        super.refreshList(data);
        init();
    }

    public Map<String, Integer> getAlphaIndexer() {
        if (alphaIndexer == null) {
            alphaIndexer = new HashMap<String, Integer>();
        }
        return alphaIndexer;
    }

    public List<String> getAlphaList() {
        List<String> list = new ArrayList<>();
        if (alphaIndexer == null) {
            init();
        }
        list.addAll(alphaIndexer.keySet());
        return list;
    }
}
