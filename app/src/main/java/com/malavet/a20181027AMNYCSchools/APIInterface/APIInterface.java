package com.malavet.a20181027AMNYCSchools.APIInterface;


import com.malavet.a20181027AMNYCSchools.Objects.NYCSchoolsObject;
import com.malavet.a20181027AMNYCSchools.Objects.SATObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    //get all schools
    @GET("/resource/97mf-9njv.json")
    Call<List<NYCSchoolsObject>> getSchoolfromAPI(

    );

    //get SAT from specific school
    @GET("/resource/734v-jeq5.json")
    Call<List<SATObject>> getSAT(
            @Query("dbn") String dbn
    );


}
