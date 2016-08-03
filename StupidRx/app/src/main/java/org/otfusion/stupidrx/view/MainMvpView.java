package org.otfusion.stupidrx.view;

import java.util.List;

public interface MainMvpView extends MvpView {

    void hideError();

    void showError(String errorMessage);

    void showHolidays(List<String> holidays);

    void showWaiting();

    void hideWaiting();

}
