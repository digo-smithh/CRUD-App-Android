package com.project.studentsapp.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.studentsapp.R;
import com.project.studentsapp.app.controllers.StudentsController;
import com.project.studentsapp.app.models.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        setContentOnListView();

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

    private void setContentOnListView() {
        //studentList = StudentsController.getAllStudents(mainContext);

        List<Student> studentList = new ArrayList<Student>();
        try {
            studentList.add(new Student("00000", "s", "s"));
            studentList.add(new Student("00000", "dfdfs", "s"));
            studentList.add(new Student("00000", "dss", "s"));
            studentList.add(new Student("00000", "aaas", "ssedsdsd"));
            studentList.add(new Student("00000", "s", "saaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
            studentList.add(new Student("00000", "s222222222222222222222", "sasdsds"));
            studentList.add(new Student("00000", "s", "s"));
            studentList.add(new Student("00000", "dfdfs", "s"));
            studentList.add(new Student("00000", "dss", "s"));
            studentList.add(new Student("00000", "aaas", "ssedsdsd"));
            studentList.add(new Student("00000", "s", "saaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
            studentList.add(new Student("00000", "s222222222222222222222", "sasdsds"));
            studentList.add(new Student("00000", "s", "s"));
            studentList.add(new Student("00000", "dfdfs", "s"));
            studentList.add(new Student("00000", "dss", "s"));
            studentList.add(new Student("00000", "aaas", "ssedsdsd"));
            studentList.add(new Student("00000", "s", "saaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
            studentList.add(new Student("00000", "s222222222222222222222", "sasdsds"));
        }
        catch (Exception e)
        {}

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
                    android.R.layout.simple_list_item_1,
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