package com.future.wk.newcontacter.mvp.contract;

import com.future.wk.newcontacter.base.mvp.model.IBaseModel;
import com.future.wk.newcontacter.base.mvp.view.IBaseView;

/**
 * Created by samsung on 2017/5/3.
 */

public interface ILocalContract {
    interface ILocalModel extends IBaseModel{
        void getContactList();
        //...
    }
    interface ILocalView extends IBaseView{
        void showContactList();
        // ...
    }
}
