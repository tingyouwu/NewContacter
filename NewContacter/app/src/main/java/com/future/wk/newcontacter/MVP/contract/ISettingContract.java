package com.future.wk.newcontacter.mvp.contract;

import com.future.wk.newcontacter.base.mvp.model.IBaseModel;
import com.future.wk.newcontacter.base.mvp.view.IBaseView;

/**
 * Created by samsung on 2017/5/3.
 */

public interface ISettingContract {
    interface ISettingModel extends IBaseModel{
        void getSettingList();
        //...
    }
    interface ISettingView extends IBaseView{
        void showSettingList();
        // ...
    }
}
