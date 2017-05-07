package com.future.wk.newcontacter.mvp.contract;

import com.future.wk.newcontacter.base.mvp.model.IBaseModel;
import com.future.wk.newcontacter.base.mvp.view.IBaseView;

/**
 * Created by samsung on 2017/5/3.
 */

public interface INetworkContract {
    interface INetworkModel extends IBaseModel{
        void getNetworkContactList();
        //...
    }
    interface INetworkView extends IBaseView{
        void showNetworkContactList();
        // ...
    }
}
