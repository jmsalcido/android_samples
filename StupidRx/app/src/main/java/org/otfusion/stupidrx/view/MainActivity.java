package org.otfusion.stupidrx.view;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import org.otfusion.stupidrx.R;
import org.otfusion.stupidrx.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainMvpView {

    public static final String SAVED = "saved";
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private HolidayAdapter mAdapter;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new HolidayAdapter(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.holiday_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);

        presenter = new MainPresenter();
        presenter.attachView(this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadHolidays();
            }
        });

        if (savedInstanceState != null) {
            mAdapter.setHolidays(savedInstanceState.getStringArrayList(SAVED));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(SAVED, (ArrayList<String>) mAdapter.getData());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.dettach();
    }

    @Override
    public void hideError() {

    }

    @Override
    public void showError(String errorMessage) {
        Snackbar errorView = Snackbar.make(mSwipeRefreshLayout, errorMessage, Snackbar.LENGTH_INDEFINITE);
        errorView.setAction("Try Again", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadHolidays();
            }
        });
        errorView.show();
    }

    @Override
    public void showWaiting() {
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setEnabled(false);
    }

    @Override
    public void hideWaiting() {
        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setEnabled(true);
    }

    @Override
    public void showHolidays(List<String> holidays) {
        mAdapter.setHolidays(holidays);
    }

    @Override
    public Context getContext() {
        return null;
    }
}
