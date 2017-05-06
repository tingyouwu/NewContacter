package com.future.wk.newcontacter.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.future.wk.newcontacter.R;
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

/**
 * 通讯录适配器
 */

public class ContactListAdapter extends BaseRecyclerViewAdapter<ContactDALEx> {

    //是否支持字母列表
    private boolean isAlphaSearch = true;
    //字母列表
    private Map<String, Integer> alphaIndexer;

    public ContactListAdapter(Context context, List<ContactDALEx> data) {
        super(context, R.layout.item_contact_list, data);
//        init();
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, final ContactDALEx contact, final int position) {
//        RoundedImageView iv_icon = holder.getView(R.id.item_contact_icon);
//        ImageView iv_phone = holder.getView(R.id.item_contact_phone);
//        TextView tv_name = holder.getView(R.id.item_contact_name);
//        TextView tv_alpha = holder.getView(R.id.item_contact_alpha);
//
//        tv_name.setText(contact.getUsername());
//        tv_alpha.setText(CorePinYinUtil.getPinyinFirstAlpha(contact.getNamepinyin()));
//        iv_icon.setImageResource(R.mipmap.img_contact_default);
//        if (!TextUtils.isEmpty(contact.getUsericon())) {
//            if (contact.getUsericon().startsWith("content")) {
//            } else {
//
//            }
//        }
//
//        if (position > 0) {
//            // 不是第一行，控制pinyin栏
//            ContactDALEx lastContact = getItem(position - 1);
//            String lastAlpha = CorePinYinUtil.getPinyinFirstAlpha(lastContact.getNamepinyin());
//            String nowAlpha = CorePinYinUtil.getPinyinFirstAlpha(contact.getNamepinyin());
//            if (lastAlpha.equals(nowAlpha)) {
//                tv_alpha.setVisibility(View.GONE);
//            } else {
//                tv_alpha.setVisibility(View.VISIBLE);
//            }
//        } else {
//            tv_alpha.setVisibility(View.VISIBLE);
//        }

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
