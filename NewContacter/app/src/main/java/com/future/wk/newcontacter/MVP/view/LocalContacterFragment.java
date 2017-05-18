package com.future.wk.newcontacter.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.future.wk.newcontacter.adapter.ContactListAdapter;
import com.future.wk.newcontacter.data.dalex.ContactDALEx;
import com.future.wk.newcontacter.mvp.contract.ILocalContract;
import com.future.wk.newcontacter.mvp.presenter.LocalPresenter;
import com.future.wk.newcontacter.R;
import com.future.wk.newcontacter.base.BaseFragment;
import com.future.wk.newcontacter.util.CoreCommonUtil;
import com.future.wk.newcontacter.widget.common.SearchView;
import com.future.wk.newcontacter.widget.common.SideBar;
import com.future.wk.newcontacter.widget.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 本地通讯录
 **/
public class LocalContacterFragment extends BaseFragment<LocalPresenter> implements ILocalContract.ILocalView{

    @Bind(R.id.contactListView)
    XRecyclerView listview;
    @Bind(R.id.searchview)
    SearchView searchview;
    @Bind(R.id.filter_letters)
    SideBar filter_letters;
    @Bind(R.id.tv_letter)
    TextView tv_letter;

    private ContactListAdapter adapter;
    private List<ContactDALEx> mDataList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    @Override
    public LocalPresenter getPresenter(){
        return new LocalPresenter();
    }

    @Override
    public void onInitView(Bundle savedInstanceState){
        filter_letters.setTextView(tv_letter);
        adapter = new ContactListAdapter(getContext(),mDataList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        listview.setLayoutManager(linearLayoutManager);
        listview.addItemDecoration(new XRecyclerView.DivItemDecoration(2, true));
        listview.setLoadingMoreEnabled(false);
        listview.setAdapter(adapter);

        filter_letters.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                if (s != null && s.trim().length() > 0) {
                    tv_letter.setText(s);
                    if (adapter.getAlphaIndexer().get(s) != null) {
                        int position = adapter.getAlphaIndexer().get(s);
                        linearLayoutManager.scrollToPositionWithOffset(position+1, 0);
                    }
                }
            }
        });

        searchview.setOnSearchListener(searchListener);
        searchview.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    CoreCommonUtil.keyboardControl(getActivity(),false,  v);
            }
        });

        listview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                searchview.getEditText().clearFocus();
                return false;
            }
        });

        listview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                listview.refreshComplete("");
            }

            @Override
            public void onLoadMore() {

            }
        });

        mDataList.add(new ContactDALEx());
        mDataList.add(new ContactDALEx());
        mDataList.add(new ContactDALEx());
        mDataList.add(new ContactDALEx());
        mDataList.add(new ContactDALEx());
        mDataList.add(new ContactDALEx());
        mDataList.add(new ContactDALEx());
        mDataList.add(new ContactDALEx());
        mDataList.add(new ContactDALEx());

        adapter.notifyDataSetChanged();

    }

    @Override
    public void initFragmentActionBar(String title) {
        super.initFragmentActionBar(title);

        activity.getDefaultNavigation().getLeftButton().setButton(R.mipmap.female, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        activity.getDefaultNavigation().setRightButton(R.mipmap.actionbar_add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getLayoutResource(){
        return R.layout.fragment_local;
    }

    @Override
    public void showContactList(){
    }

    SearchView.OnSearchListener searchListener = new SearchView.OnSearchListener() {

        @Override
        public void onSearchEmpty() {
            filter_letters.setLettersList(adapter.getAlphaList());
        }

        @Override
        public void onSearchChange(String content) {
            filter_letters.setLettersList(adapter.getAlphaList());
        }
    };

}
