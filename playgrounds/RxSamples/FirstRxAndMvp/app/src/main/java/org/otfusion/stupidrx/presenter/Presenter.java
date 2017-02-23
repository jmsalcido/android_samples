package org.otfusion.stupidrx.presenter;

import org.otfusion.stupidrx.view.MvpView;


public interface Presenter<V extends MvpView> {

    void attachView(V view);

    void dettach();

}
