package com.malavet.a20181027AMNYCSchools.Dependencies.Modules;

import android.content.Context;

import com.malavet.a20181027AMNYCSchools.Dependencies.Contexts.ApplicationContext;
import com.malavet.a20181027AMNYCSchools.Dependencies.Scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
