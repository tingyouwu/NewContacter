package com.future.wk.newcontacter.mvp.view;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.future.wk.newcontacter.R;
import com.future.wk.newcontacter.base.BaseFragment;
import com.future.wk.newcontacter.mvp.contract.INetworkContract;
import com.future.wk.newcontacter.mvp.presenter.NetworkPresenter;
import com.future.wk.newcontacter.util.CoreCommonUtil;
import com.future.wk.newcontacter.util.NetworkFragmentPagerAdapter;
import com.future.wk.newcontacter.widget.common.SearchView;


import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by samsung on 2017/5/10.
 */

public class NetworkFragment extends BaseFragment<NetworkPresenter> implements INetworkContract.INetworkView {

    @Bind(R.id.searchview_service)
    SearchView searchviewService;

    private Resources resources;
    private ViewPager mPager;
    private ArrayList<Fragment> fragmentsList;
    private ImageView ivBottomLine;
    private TextView tvTabNew, tvTabHot;

    private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
    private int position_one;
    public final static int num = 2 ;
    NetworkContacterFragment home1;
    YellowPageFragment home2;

    private static String TAG = "NetworkFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Log.d(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_service, null);

        resources = getResources();
        InitWidth(view);
        InitTextView(view);
        InitViewPager(view);
        TranslateAnimation animation = new TranslateAnimation(position_one, offset, 0, 0);
        tvTabHot.setTextColor(resources.getColor(R.color.black));
        animation.setFillAfter(true);
        animation.setDuration(300);
        ivBottomLine.startAnimation(animation);

        return view;
    }
    @Override
    public NetworkPresenter getPresenter(){
        return null;
    }

    @Override
    public void onInitView(Bundle savedInstanceState){
        Log.d(TAG,"onInitView");
        /*searchviewService.setOnSearchListener(searchListener);
        searchviewService.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    CoreCommonUtil.keyboardControl(getActivity(),false,  v);
            }
        });
        if(home1.netListview != null) {
            home1.netListview.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    searchviewService.getEditText().clearFocus();
                    return false;
                }
            });
        }*/
    }
    @Override
    public int getLayoutResource(){
        return R.layout.fragment_service;
    }

    @Override
    public void showNetworkContactList(){
        //...
    }


    public  void clearSearchViewFocus(){
        searchviewService.getEditText().clearFocus();
    }



    private void InitTextView(View parentView) {
        tvTabNew = (TextView) parentView.findViewById(R.id.tv_tab_1);
        tvTabHot = (TextView) parentView.findViewById(R.id.tv_tab_2);

        tvTabNew.setOnClickListener(new MyOnClickListener(0));
        tvTabHot.setOnClickListener(new MyOnClickListener(1));
    }

    private void InitViewPager(View parentView) {
        Log.d(TAG,"InitViewPager");
        mPager = (ViewPager) parentView.findViewById(R.id.vPager);
        fragmentsList = new ArrayList<Fragment>();

        home1 = new NetworkContacterFragment();
        home2 = new YellowPageFragment();

        fragmentsList.add(home1);
        fragmentsList.add(home2);

        mPager.setAdapter(new NetworkFragmentPagerAdapter(getChildFragmentManager(), fragmentsList));
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
        mPager.setCurrentItem(0);

    }

    private void InitWidth(View parentView) {
        ivBottomLine = (ImageView) parentView.findViewById(R.id.iv_bottom_line);
        bottomLineWidth = ivBottomLine.getLayoutParams().width;
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (int) ((screenW / num - bottomLineWidth) / 2);
        int avg = (int) (screenW / num);
        position_one = avg + offset;


    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    };

    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
                case 0:
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(position_one, offset, 0, 0);
                        tvTabHot.setTextColor(resources.getColor(R.color.black));
                    }
                    tvTabNew.setTextColor(resources.getColor(R.color.colorBluePrimary));
                    break;
                case 1:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_one, 0, 0);
                        tvTabNew.setTextColor(resources.getColor(R.color.black));
                    }
                    tvTabHot.setTextColor(resources.getColor(R.color.colorBluePrimary));
                    break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);
            animation.setDuration(300);
            ivBottomLine.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }


    SearchView.OnSearchListener searchListener = new SearchView.OnSearchListener() {

        @Override
        public void onSearchEmpty() {
            home1.net_filter_letters.setLettersList(home1.netAdapter.getAlphaList());
        }

        @Override
        public void onSearchChange(String content) {
            home1.net_filter_letters.setLettersList(home1.netAdapter.getAlphaList());
        }
    };
}
