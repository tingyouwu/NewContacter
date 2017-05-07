package com.future.wk.newcontacter.mvp.view;

import android.os.Bundle;

import com.future.wk.newcontacter.R;
import com.future.wk.newcontacter.base.BaseFragment;
import com.future.wk.newcontacter.mvp.contract.ILocalContract;
import com.future.wk.newcontacter.mvp.contract.INetworkContract;
import com.future.wk.newcontacter.mvp.presenter.LocalPresenter;
import com.future.wk.newcontacter.mvp.presenter.NetworkPresenter;

/**
 * Created by samsung on 2017/5/3.
 */

public class NetworkContacterFragment extends BaseFragment<NetworkPresenter> implements INetworkContract.INetworkView{

    @Override
    public NetworkPresenter getPresenter(){
        return new NetworkPresenter();
    }

    @Override
    public void onInitView(Bundle savedInstanceState){
        //...
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
