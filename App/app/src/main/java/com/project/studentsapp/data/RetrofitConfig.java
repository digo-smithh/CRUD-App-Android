package com.project.studentsapp.data;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public  RetrofitConfig(){

        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:4000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Service getService(){
        return this.retrofit.create(Service.class);
    }

}