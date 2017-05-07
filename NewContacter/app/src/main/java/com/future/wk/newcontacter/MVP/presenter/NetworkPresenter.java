package com.future.wk.newcontacter.mvp.presenter;

import com.future.wk.newcontacter.base.mvp.presenter.BasePresenter;
import com.future.wk.newcontacter.mvp.contract.INetworkContract;
import com.future.wk.newcontacter.mvp.model.NetworkModel;

/**
 * Created by samsung on 2017/5/3.
 */

public class NetworkPresenter extends BasePresenter<INetworkContract.INetworkView> {
    private INetworkContract.INetworkModel mNetworkModel;

    public NetworkPresenter(){
        mNetworkModel = new NetworkModel();
    }
}
