package com.project.studentsapp.data;


import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class CRUDActivity {

    public List<Student> getAllStudents() {
        Call<List<Student>> call = new RetrofitConfig().getService().getAll();
        final List<Student>[] list = new List[]{new ArrayList<Student>()};
        list[0] = null;

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Response<List<Student>> response, Retrofit retrofit) {
                    list[0] = response.body();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });

        return list[0];
    }
}
