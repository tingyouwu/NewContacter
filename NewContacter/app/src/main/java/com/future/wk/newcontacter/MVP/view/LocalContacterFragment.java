package com.future.wk.newcontacter.mvp.view;

import android.os.Bundle;

import com.future.wk.newcontacter.mvp.contract.ILocalContract;
import com.future.wk.newcontacter.mvp.presenter.LocalPresenter;
import com.future.wk.newcontacter.R;
import com.future.wk.newcontacter.base.BaseFragment;

public class LocalContacterFragment extends BaseFragment<LocalPresenter> implements ILocalContract.ILocalView{

    @Override
    public LocalPresenter getPresenter(){
        return new LocalPresenter();
    }

    @Override
    public void onInitView(Bundle savedInstanceState){
    }

    @Override
    public int getLayoutResource(){
        return R.layout.fragment_local;
    }

    @Override
    public void showContactList(){
    }
}
