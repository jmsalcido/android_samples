package org.otfusion.stupidrx.presenter;

import org.otfusion.stupidrx.model.PseudoClient;
import org.otfusion.stupidrx.view.MainMvpView;

import java.util.List;
import java.util.concurrent.Callable;

import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter implements Presenter<MainMvpView> {

    private MainMvpView view;
    private final PseudoClient pseudoClient;

    private Subscription holidaySubscription;

    public MainPresenter() {
        pseudoClient = new PseudoClient();
    }

    @Override
    public void attachView(MainMvpView view) {
        this.view = view;
    }

    @Override
    public void dettach() {
        view = null;
        if (holidaySubscription != null && !holidaySubscription.isUnsubscribed()) {
            holidaySubscription.unsubscribe();
            holidaySubscription = null;
        }
    }

    public void loadHolidays() {
        view.showWaiting();
        Single<List<String>> clientCall = Single.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return pseudoClient.getListOfHolidays();
            }
        });

        holidaySubscription = clientCall.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<String>>() {
                               @Override
                               public void onSuccess(List<String> value) {
                                   showLoadedHolidays(value);
                               }

                               @Override
                               public void onError(Throwable error) {
                                   view.hideWaiting();
                                   view.showError(error.getMessage());
                               }
                           }
                );
    }

    private void showLoadedHolidays(List<String> holidays) {
        view.hideWaiting();
        view.showHolidays(holidays);
    }
}
