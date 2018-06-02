package com.example.fivedayforecast.home.dagger;

import com.example.fivedayforecast.dagger.AppComponent;
import com.example.fivedayforecast.home.mvp.HomeActivity;

import dagger.Component;

@HomeScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeActivity homeActivity);
}
