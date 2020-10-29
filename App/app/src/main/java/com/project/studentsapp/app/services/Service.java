package com.project.studentsapp.app.services;

import com.project.studentsapp.app.models.Student;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface Service {

    @GET("students/")
    Call<List<Student>> getAll();

    @GET("students/{code}")
    Call<Student> getByCode(@Path("code") String code);

    @POST("students/")
    Call<Student> insert(@Body Student student);

    @PUT("students/{code}")
    Call<Student> update(@Path("code") int code, @Body Student student);

    @DELETE("students/{code}")
    Call<Student> delete(@Path("code") int code);

    @DELETE("students/")
    Call<Student> deleteAll();
}
