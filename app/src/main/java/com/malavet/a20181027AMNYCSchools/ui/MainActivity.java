package com.malavet.a20181027AMNYCSchools.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.malavet.a20181027AMNYCSchools.APIInterface.APIInterface;
import com.malavet.a20181027AMNYCSchools.App;
import com.malavet.a20181027AMNYCSchools.Dependencies.Components.ApplicationComponent;
import com.malavet.a20181027AMNYCSchools.Dependencies.Components.DaggerMainActivityComponent;
import com.malavet.a20181027AMNYCSchools.Dependencies.Components.MainActivityComponent;
import com.malavet.a20181027AMNYCSchools.Dependencies.Contexts.ActivityContext;
import com.malavet.a20181027AMNYCSchools.Dependencies.Contexts.ApplicationContext;
import com.malavet.a20181027AMNYCSchools.Dependencies.Modules.MainActivityContextModule;
import com.malavet.a20181027AMNYCSchools.Objects.NYCSchoolsObject;
import com.malavet.a20181027AMNYCSchools.R;
import com.malavet.a20181027AMNYCSchools.Adapter.RecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener {
    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;
    @Inject
    public APIInterface apiInterface;
    @Inject
    @ApplicationContext
    public Context mContext;
    @Inject
    @ActivityContext
    public Context activityContext;
    MainActivityComponent mainActivityComponent;
    //  Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        ApplicationComponent applicationComponent = App.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        ///calling API right onCreate of app
        apiInterface.getSchoolfromAPI().enqueue(new Callback<List<NYCSchoolsObject>>() {
            @Override
            public void onResponse(Call<List<NYCSchoolsObject>> call, Response<List<NYCSchoolsObject>> response) {
                //send data to adapter
                toAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<NYCSchoolsObject>> call, Throwable t) {

            }
        });
    }

    private void toAdapter(List<NYCSchoolsObject> response) {
        //send data to dapater
        recyclerViewAdapter.setData(response);
    }


    @Override
    public void launchIntent(String dbn) {
        startActivity(new Intent(activityContext, DetailActivity.class).putExtra("dbn", dbn));
    }
}
