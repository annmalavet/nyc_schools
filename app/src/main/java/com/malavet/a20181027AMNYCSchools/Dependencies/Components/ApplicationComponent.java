package com.malavet.a20181027AMNYCSchools.Dependencies.Components;

import android.content.Context;

import com.malavet.a20181027AMNYCSchools.APIInterface.APIInterface;
import com.malavet.a20181027AMNYCSchools.App;
import com.malavet.a20181027AMNYCSchools.Dependencies.Contexts.ApplicationContext;
import com.malavet.a20181027AMNYCSchools.Dependencies.Modules.ContextModule;
import com.malavet.a20181027AMNYCSchools.Dependencies.Modules.RetrofitModule;
import com.malavet.a20181027AMNYCSchools.Dependencies.Scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    public APIInterface getApiInterface();

    @ApplicationContext
    public Context getContext();

    public void injectApplication(App app);
}
