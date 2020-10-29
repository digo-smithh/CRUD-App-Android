package com.project.studentsapp.data;

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
    Call<Student> getByName(@Path("code") String code);

    @POST("students/")
    void insertStudent(@Body Student student);

    @PUT("students/{code}")
    void updateStudent(@Path("code") int code, @Body Student student);

    @DELETE("students/{code}")
    void deleteStudent(@Path("code") int code);

}
