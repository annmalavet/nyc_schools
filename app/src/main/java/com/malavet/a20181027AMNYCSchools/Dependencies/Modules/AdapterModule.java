package com.malavet.a20181027AMNYCSchools.Dependencies.Modules;

import com.malavet.a20181027AMNYCSchools.Dependencies.Scopes.ActivityScope;
import com.malavet.a20181027AMNYCSchools.Adapter.RecyclerViewAdapter;
import com.malavet.a20181027AMNYCSchools.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module(includes = {com.malavet.a20181027AMNYCSchools.Dependencies.Modules.MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getSchoolList(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MainActivity mainActivity) {
        return mainActivity;
    }
}
