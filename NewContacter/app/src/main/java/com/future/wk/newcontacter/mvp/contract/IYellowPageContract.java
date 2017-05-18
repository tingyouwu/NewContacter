package com.future.wk.newcontacter.mvp.contract;

import android.content.Context;

import com.future.wk.newcontacter.base.mvp.model.IBaseModel;
import com.future.wk.newcontacter.base.mvp.view.IBaseView;

import java.util.ArrayList;

/**
 * Created by kuangminan on 2017/5/14.
 */
public interface IYellowPageContract  {
    interface IYellowPageModel extends IBaseModel {
        void geYellowPageList();
        public ArrayList getYellowPageDetail(Context context, String category );
    }
    interface IYellowPageView extends IBaseView {
        void showYellowPageList();
        // ...
    }
}
