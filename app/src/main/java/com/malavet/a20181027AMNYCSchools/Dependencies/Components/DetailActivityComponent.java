package com.malavet.a20181027AMNYCSchools.Dependencies.Components;


import com.malavet.a20181027AMNYCSchools.Dependencies.Scopes.ActivityScope;
import com.malavet.a20181027AMNYCSchools.ui.DetailActivity;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class)
@ActivityScope
public interface DetailActivityComponent {

    void inject(DetailActivity detailActivity);
}
