package com.future.wk.newcontacter.base.mvp.presenter;


import com.future.wk.newcontacter.base.mvp.view.IBaseView;

/**
 * @author wty
 * IBasePresenter是所有Presenter的基类
 */
public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);

    void detachView();
}
