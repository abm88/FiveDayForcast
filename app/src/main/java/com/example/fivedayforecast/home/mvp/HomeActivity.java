package com.example.fivedayforecast.home.mvp;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fivedayforecast.R;
import com.example.fivedayforecast.Utils;
import com.example.fivedayforecast.app.App;
import com.example.fivedayforecast.home.adapter.ForecastAdapter;
import com.example.fivedayforecast.home.dagger.DaggerHomeComponent;
import com.example.fivedayforecast.home.dagger.HomeComponent;
import com.example.fivedayforecast.home.dagger.HomeModule;
import com.example.fivedayforecast.model.Day;
import com.example.fivedayforecast.model.Forecast;
import com.example.fivedayforecast.net.ForecastService;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity implements HomeContract.View{

    @BindView(R.id.rv_forecast)
    RecyclerView recyclerView;
    @BindView(R.id.pb_home_progress)
    ProgressBar progressBar;
    @BindView(R.id.tv_try_again)
    TextView tryAgain;

    @Inject
    HomeContract.Presenter presenter;

    private final ForecastAdapter forecastAdapter = new ForecastAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DaggerHomeComponent.builder()
                .appComponent(((App)getApplication()).getAppComponent())
                .homeModule(new HomeModule(this))
                .build().inject(this);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(forecastAdapter);
        presenter.getForecast(Utils.isOnline(this));
    }

    @Override
    public void showForecast(@NonNull List<Day> days) {
        forecastAdapter.setData(days);
    }

    @Override
    public void showError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(boolean shouldShow) {
        progressBar.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showTryAgain(boolean shouldShow) {
        if (shouldShow) {
            recyclerView.setVisibility(View.GONE);
            tryAgain.setVisibility(View.VISIBLE);
        } else {
            tryAgain.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setActivityTitle(String title) {
        setTitle(title);
    }

    @OnClick(R.id.tv_try_again)
    public void onTryAgainClicked() {
        presenter.getForecast(Utils.isOnline(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }
}
