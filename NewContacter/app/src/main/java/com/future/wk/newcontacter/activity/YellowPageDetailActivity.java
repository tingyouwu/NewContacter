package com.future.wk.newcontacter.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;

import com.future.wk.newcontacter.R;
import com.future.wk.newcontacter.adapter.ContactListAdapter;
import com.future.wk.newcontacter.base.BaseActivity;
import com.future.wk.newcontacter.base.mvp.presenter.BasePresenter;
import com.future.wk.newcontacter.data.dalex.ContactDALEx;
import com.future.wk.newcontacter.mvp.presenter.YellowPagePresenter;
import com.future.wk.newcontacter.widget.common.SideBar;
import com.future.wk.newcontacter.widget.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by samsung on 2017/5/15.
 */

public class YellowPageDetailActivity extends BaseActivity<YellowPagePresenter>  {

    @Bind(R.id.yellowpageListView)
    XRecyclerView yellowpagelistview;
    @Bind(R.id.yp_filter_letters)
    SideBar yp_filter_letters;
    @Bind(R.id.yp_tv_letter)
    TextView yp_tv_letter;

    private String TAG = "YellowPageDetail";
    public ContactListAdapter ypAdapter;
    private List<ContactDALEx> mypDataList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    @Override
    public YellowPagePresenter getPresenter() {
        return new YellowPagePresenter();
    }
    @Override
    protected boolean isEnableStatusBar() {
        return true;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_yellowpage;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {

        yp_filter_letters.setTextView(yp_tv_letter);
        ypAdapter = new ContactListAdapter(this,mypDataList);
        linearLayoutManager = new LinearLayoutManager(this);
        yellowpagelistview.setLayoutManager(linearLayoutManager);
        yellowpagelistview.addItemDecoration(new XRecyclerView.DivItemDecoration(2, true));
        yellowpagelistview.setLoadingMoreEnabled(false);
        yellowpagelistview.setAdapter(ypAdapter);

        yp_filter_letters.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                if (s != null && s.trim().length() > 0) {
                    yp_tv_letter.setText(s);
                    if (ypAdapter.getAlphaIndexer().get(s) != null) {
                        int position = ypAdapter.getAlphaIndexer().get(s);
                        linearLayoutManager.scrollToPositionWithOffset(position+1, 0);
                    }
                }
            }
        });



        yellowpagelistview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                yellowpagelistview.refreshComplete("");
            }

            @Override
            public void onLoadMore() {

            }
        });

        mypDataList.addAll(mPresenter.getYellowPageList(this, getIntent().getStringExtra("itemID")));
        Log.d(TAG,"mypDataList length:"+mypDataList.size());

        for(int i = 0; i < mypDataList.size(); i++) {
            Log.d(TAG,"mypDataList name:"+mypDataList.get(i).getUsername());
            Log.d(TAG,"mypDataList phone:"+mypDataList.get(i).getUserphone());
        }


        ypAdapter.notifyDataSetChanged();
    }



}
