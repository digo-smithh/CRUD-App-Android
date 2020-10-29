package com.project.studentsapp.app.controllers;

import android.content.Context;
import android.widget.Toast;

import com.project.studentsapp.app.config.RetrofitConfig;
import com.project.studentsapp.app.models.Student;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class StudentsController {

    public static List<Student> getAllStudents(final Context mainContext) {
        Call<List<Student>> call = new RetrofitConfig().getService().getAll();
        final List<Student>[] list = new List[]{new ArrayList<Student>()};
        list[0] = null;

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Response<List<Student>> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    list[0] = response.body();
                }
                else{
                    Toast.makeText(mainContext, "Error getting students.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(mainContext, "Error communicating with server.", Toast.LENGTH_SHORT).show();
            }
        });

        return list[0];
    }

    public static void insertStudent(final Context mainContext, String code, String name, String email){

        Student student = null;

        try {
            student = new Student(code, name, email);
        }
        catch (Exception e) {
            Toast.makeText(mainContext, "Student data was entered incorrectly.", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Student> call = new RetrofitConfig().getService().insert(student);
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Response<Student> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    Toast.makeText(mainContext, "Student was include successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(mainContext, "Error inserting student.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(mainContext, "Error communicating with server.", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public static void deleteAllStudents(final Context mainContext) {

        Call<Student> call = new RetrofitConfig().getService().deleteAll();
        call.enqueue(new Callback<Student>(){
            @Override
            public void onResponse(Response<Student> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    Toast.makeText(mainContext, "All students all deleted successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(mainContext, "Error deleting students.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(mainContext, "Error communicating with server.", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}