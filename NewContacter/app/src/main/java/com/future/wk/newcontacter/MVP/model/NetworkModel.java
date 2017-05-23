package com.future.wk.newcontacter.mvp.model;

import android.content.Context;

import com.future.wk.newcontacter.data.dalex.ContactDALEx;
import com.future.wk.newcontacter.mvp.contract.INetworkContract;

import java.util.List;

/**
 * Created by samsung on 2017/5/3.
 */

public class NetworkModel implements INetworkContract.INetworkModel {
    @Override
    public void getNetworkContactList() {
        //...
    }

    @Override
    public List getAddYellowPageList(Context mContext){
        return ContactDALEx.get().findAllYPContacter();
    }
}
