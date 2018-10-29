package com.malavet.a20181027AMNYCSchools;

import android.app.Activity;
import android.app.Application;

import com.malavet.a20181027AMNYCSchools.Dependencies.Components.ApplicationComponent;
import com.malavet.a20181027AMNYCSchools.Dependencies.Components.DaggerApplicationComponent;
import com.malavet.a20181027AMNYCSchools.Dependencies.Modules.ContextModule;

public class App extends Application {

    ApplicationComponent applicationComponent;

    public static App get(Activity activity) {
        return (App) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

