package com.future.wk.newcontacter.mvp.contract;

import android.content.Context;

import com.future.wk.newcontacter.base.mvp.model.IBaseModel;
import com.future.wk.newcontacter.base.mvp.view.IBaseView;

import java.util.List;

/**
 * Created by samsung on 2017/5/3.
 */

public interface INetworkContract {
    interface INetworkModel extends IBaseModel{
        void getNetworkContactList();
        List getAddYellowPageList(Context mContext);
        //...
    }
    interface INetworkView extends IBaseView{
        void showNetworkContactList();
        // ...
    }
}
