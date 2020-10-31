package com.project.studentsapp.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.studentsapp.R;
import com.project.studentsapp.app.config.RetrofitConfig;
import com.project.studentsapp.app.config.Server;
import com.project.studentsapp.app.controllers.StudentsController;
import com.project.studentsapp.app.models.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity {

    private Context mainContext = this;
    private int currentView;

    List<Student> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        currentView = R.layout.activity_main;
        setContentView(currentView);
        buildMainView();
    }

    private void buildMainView() {
        callGetAll();

        final BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        setSupportActionBar(bottomAppBar);

        final FloatingActionButton floatingActionButton = findViewById(R.id.button);

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ModalActivity modalActivity = new ModalActivity(MainActivity.this);
                modalActivity.show(getSupportFragmentManager(), modalActivity.getTag());
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentView = R.layout.register_student_layout;
                setContentView(currentView);
                buildRegisterView();
            }
        });
    }

    /*private void getAllStudents(final Context mainContext) {
        Call<List<Student>> call = new RetrofitConfig().getService().getAll();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(retrofit.Response<List<Student>> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    studentList = response.body();
                    setContentOnListView();
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
    }*/

    public void getAllStudents(final Context mainContext) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Server.BASE_URL + "students", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Student[] array = gson.fromJson(response,Student[].class);

                for (int i = 0; i < array.length; i++) {
                    studentList.add(array[i]);
                }

                setContentOnListView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mainContext, "Error communicating with server.", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(mainContext);
        requestQueue.add(stringRequest);
    }

    private void callGetAll() {
        getAllStudents(mainContext);
    }

    private void setContentOnListView() {
        ArrayList<Map<String,Object>> listItem = new ArrayList<>();

        for(int i = 0; i < studentList.size(); i++) {

            Student student = studentList.get(i);
            Map<String,Object> listItemMap = new HashMap<>();

            listItemMap.put("id", "student " + i);
            listItemMap.put("student", "student");
            listItem.add(listItemMap);

            SimpleAdapter listViewAdapter = new SimpleAdapter(
                    this,
                    listItem,
                    android.R.layout.simple_list_item_2,
                    new String[]{"id", "student"},
                    new int[]{android.R.id.text1, android.R.id.text2});

            ListView listView = findViewById(R.id.listView);
            listView.setAdapter(listViewAdapter);
        }
    }

    private void buildRegisterView() {
        final ImageButton imageButton = (ImageButton) findViewById(R.id.insert_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText code = (EditText) findViewById(R.id.studentCode);
                final EditText name = (EditText) findViewById(R.id.studentName);
                final EditText email = (EditText) findViewById(R.id.studentEmail);

                if (name.getText().toString().trim().length() == 0 || email.getText().toString().trim().length() == 0) {
                    Toast.makeText(mainContext, "Data cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (code.getText().toString().trim().length() < 5) {
                    Toast.makeText(mainContext, "Code have to be 5 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                StudentsController.insertStudent(mainContext, code.getText().toString().trim(), name.getText().toString().trim(), email.getText().toString().trim());

                code.setText("");
                name.setText("");
                email.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_app_bar_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (currentView == R.layout.register_student_layout) {
            currentView = R.layout.activity_main;
            setContentView(currentView);
            buildMainView();
        }
        else {
            super.onBackPressed();
        }
    }
}