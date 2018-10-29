package com.malavet.a20181027AMNYCSchools.Dependencies.Components;
import android.content.Context;
import com.malavet.a20181027AMNYCSchools.Dependencies.Contexts.ActivityContext;
import com.malavet.a20181027AMNYCSchools.Dependencies.Modules.AdapterModule;
import com.malavet.a20181027AMNYCSchools.Dependencies.Scopes.ActivityScope;
import com.malavet.a20181027AMNYCSchools.ui.MainActivity;

import dagger.Component;


@ActivityScope
@Component(modules = AdapterModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();

    void injectMainActivity(MainActivity mainActivity);
}
