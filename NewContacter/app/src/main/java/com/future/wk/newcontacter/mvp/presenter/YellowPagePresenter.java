package com.future.wk.newcontacter.mvp.presenter;

import android.content.Context;

import com.future.wk.newcontacter.base.mvp.presenter.BasePresenter;
import com.future.wk.newcontacter.data.dalex.ContactDALEx;
import com.future.wk.newcontacter.mvp.contract.IYellowPageContract;
import com.future.wk.newcontacter.mvp.model.YellowPageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuangminan on 2017/5/14.
 */
public class YellowPagePresenter extends BasePresenter<IYellowPageContract.IYellowPageView> {

    private IYellowPageContract.IYellowPageModel mYellowPageModel;

    public YellowPagePresenter(){
        mYellowPageModel = new YellowPageModel();
    }

    public List<ContactDALEx> getYellowPageList(Context context, String category){
        return mYellowPageModel.getYellowPageDetail(context, category);
    }
}
