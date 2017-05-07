package com.future.wk.newcontacter.mvp.presenter;

import com.future.wk.newcontacter.base.mvp.presenter.BasePresenter;
import com.future.wk.newcontacter.mvp.contract.ISettingContract;
import com.future.wk.newcontacter.mvp.model.SettingModel;

/**
 * Created by samsung on 2017/5/3.
 */

public class SettingPresenter extends BasePresenter<ISettingContract.ISettingView> {
    private ISettingContract.ISettingModel mSettingModel;

    public SettingPresenter(){
        mSettingModel = new SettingModel();
    }
}
