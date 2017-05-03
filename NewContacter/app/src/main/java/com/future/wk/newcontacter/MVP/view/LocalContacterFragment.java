package com.future.wk.newcontacter.MVP.view;

import android.os.Bundle;

import com.future.wk.newcontacter.MVP.contract.ILocalContract;
import com.future.wk.newcontacter.MVP.presenter.LocalPresenter;
import com.future.wk.newcontacter.R;
import com.future.wk.newcontacter.base.fragment.BaseFragment;

/**
 * Created by samsung on 2017/5/3.
 */

public class LocalContacterFragment extends BaseFragment<LocalPresenter> implements ILocalContract.ILocalView{

    @Override
    public LocalPresenter getPresenter(){
        return new LocalPresenter();
    }

    @Override
    public void onInitView(Bundle savedInstanceState){
        //...
    }

    @Override
    public int getLayoutResource(){
        return R.layout.fragment_local;
    }

    @Override
    public void showContactList(){
        //...
    }

    @Override
    public boolean checkNet() {
        return super.checkNet();
    }
}
