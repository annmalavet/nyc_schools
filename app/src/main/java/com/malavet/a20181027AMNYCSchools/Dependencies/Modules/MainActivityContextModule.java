package com.malavet.a20181027AMNYCSchools.Dependencies.Modules;

import android.content.Context;

import com.malavet.a20181027AMNYCSchools.Dependencies.Contexts.ActivityContext;
import com.malavet.a20181027AMNYCSchools.Dependencies.Scopes.ActivityScope;
import com.malavet.a20181027AMNYCSchools.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {
    public Context context;
    private MainActivity mainActivity;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}
