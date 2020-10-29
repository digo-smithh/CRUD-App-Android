package com.project.studentsapp.app.config;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import com.project.studentsapp.app.services.Service;
import com.squareup.okhttp.OkHttpClient;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public  RetrofitConfig(){

        OkHttpClient okHttpClient = new OkHttpClient();
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.136:4000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public Service getService(){
        return this.retrofit.create(Service.class);
    }

}