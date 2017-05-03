package com.future.wk.newcontacter.MVP.presenter;

import com.future.wk.newcontacter.MVP.contract.ILocalContract;
import com.future.wk.newcontacter.MVP.model.LocalModel;
import com.future.wk.newcontacter.base.mvp.presenter.BasePresenter;

/**
 * Created by samsung on 2017/5/3.
 */

public class LocalPresenter extends BasePresenter<ILocalContract.ILocalView> {
    private ILocalContract.ILocalModel mLocalModel;

    public LocalPresenter(){
        mLocalModel = new LocalModel();
    }
}
