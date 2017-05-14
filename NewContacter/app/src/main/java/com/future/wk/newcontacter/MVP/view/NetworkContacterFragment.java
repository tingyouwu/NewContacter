package com.future.wk.newcontacter.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.future.wk.newcontacter.R;
import com.future.wk.newcontacter.adapter.ContactListAdapter;
import com.future.wk.newcontacter.base.BaseFragment;
import com.future.wk.newcontacter.data.dalex.ContactDALEx;
import com.future.wk.newcontacter.mvp.contract.ILocalContract;
import com.future.wk.newcontacter.mvp.contract.INetworkContract;
import com.future.wk.newcontacter.mvp.presenter.LocalPresenter;
import com.future.wk.newcontacter.mvp.presenter.NetworkPresenter;
import com.future.wk.newcontacter.util.CoreCommonUtil;
import com.future.wk.newcontacter.widget.common.SearchView;
import com.future.wk.newcontacter.widget.common.SideBar;
import com.future.wk.newcontacter.widget.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by samsung on 2017/5/3.
 */

public class NetworkContacterFragment extends BaseFragment<NetworkPresenter> implements INetworkContract.INetworkView{

    @Bind(R.id.netcontactListView)
    XRecyclerView netListview;
    @Bind(R.id.net_filter_letters)
    SideBar net_filter_letters;
    @Bind(R.id.net_tv_letter)
    TextView net_tv_letter;

    public ContactListAdapter netAdapter;
    private List<ContactDALEx> mNetDataList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    @Override
    public NetworkPresenter getPresenter(){
        return new NetworkPresenter();
    }

    @Override
    public void onInitView(Bundle savedInstanceState){

        net_filter_letters.setTextView(net_tv_letter);
        netAdapter = new ContactListAdapter(getContext(),mNetDataList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        netListview.setLayoutManager(linearLayoutManager);
        netListview.addItemDecoration(new XRecyclerView.DivItemDecoration(2, true));
        netListview.setLoadingMoreEnabled(false);
        netListview.setAdapter(netAdapter);

        net_filter_letters.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                if (s != null && s.trim().length() > 0) {
                    net_tv_letter.setText(s);
                    if (netAdapter.getAlphaIndexer().get(s) != null) {
                        int position = netAdapter.getAlphaIndexer().get(s);
                        linearLayoutManager.scrollToPositionWithOffset(position+1, 0);
                    }
                }
            }
        });



        netListview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                netListview.refreshComplete("");
            }

            @Override
            public void onLoadMore() {

            }
        });
        for(int i = 0; i < 15; i++) {
            mNetDataList.add(new ContactDALEx());
        }

        netAdapter.notifyDataSetChanged();
    }

    @Override
    public int getLayoutResource(){
        return R.layout.fragment_network;
    }

    @Override
    public void showNetworkContactList(){
        //...
    }

}
