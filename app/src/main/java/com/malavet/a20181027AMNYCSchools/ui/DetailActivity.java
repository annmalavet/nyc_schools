package com.malavet.a20181027AMNYCSchools.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.malavet.a20181027AMNYCSchools.APIInterface.APIInterface;
import com.malavet.a20181027AMNYCSchools.App;
import com.malavet.a20181027AMNYCSchools.Dependencies.Components.ApplicationComponent;
import com.malavet.a20181027AMNYCSchools.Dependencies.Components.DaggerDetailActivityComponent;
import com.malavet.a20181027AMNYCSchools.Dependencies.Components.DetailActivityComponent;
import com.malavet.a20181027AMNYCSchools.Dependencies.Contexts.ApplicationContext;
import com.malavet.a20181027AMNYCSchools.Objects.SATObject;
import com.malavet.a20181027AMNYCSchools.R;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    @Inject
    public APIInterface apiInterface;
    @Inject
    @ApplicationContext
    public Context mContext;
    DetailActivityComponent detailActivityComponent;
    TextView math;
    TextView reading;
    TextView writing;
    TextView schoolName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        math = findViewById(R.id.math);
        reading = findViewById(R.id.reading);
        writing = findViewById(R.id.writing);
        schoolName = findViewById(R.id.school_name);

        String dbn = getIntent().getStringExtra("dbn");

        ApplicationComponent applicationComponent = App.get(this).getApplicationComponent();
        detailActivityComponent = DaggerDetailActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .build();

        detailActivityComponent.inject(this);

        apiInterface.getSAT(dbn).enqueue(new Callback<List<SATObject>>() {
            @Override
            public void onResponse(Call<List<SATObject>> call, Response<List<SATObject>> response) {
                List<SATObject> sat = response.body();
                if(sat.get(0).getSchoolName()!=null) {
                    schoolName.setText(sat.get(0).getSchoolName());
                    math.setText("Average SAT Math score : " + sat.get(0).getSatMathAvgScore());
                    reading.setText("Average SAT Reading score : " + sat.get(0).getSatCriticalReadingAvgScore());
                    writing.setText("Average SAT Writing score : " + sat.get(0).getSatWritingAvgScore());
                }
            }

            @Override
            public void onFailure(Call<List<SATObject>> call, Throwable t) {
                Log.d("MainActivity", "FAILED Retrofit Response");
                math.setText("Sorry, there was no response.");
            }
        });
    }
}
