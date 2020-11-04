package com.project.studentsapp.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.project.studentsapp.R;
import com.project.studentsapp.app.activities.MainActivity;
import com.project.studentsapp.app.models.Student;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Student> implements View.OnClickListener{

    private ArrayList<Student> dataSet;
    private Context mainContext;
    private MainActivity current;

    private TextView studentName;
    private TextView studentCodeAndEmail;
    private ImageButton buttonEditStudent;
    private ImageButton buttonDeleteStudent;

    public CustomListAdapter(ArrayList<Student> data, Context mainContext, MainActivity current) {
        super(mainContext, R.layout.list_item_layout, data);
        this.dataSet = data;
        this.mainContext = mainContext;
        this.current = current;
    }

    @Override
    public void onClick(View v) { }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Student student = getItem(position);

        View result;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.list_item_layout, parent, false);
        studentName = (TextView) convertView.findViewById(R.id.studentNameListItem);
        studentCodeAndEmail = (TextView) convertView.findViewById(R.id.studentCodeAndEmailListItem);
        buttonEditStudent = (ImageButton) convertView.findViewById(R.id.buttonEditStudent);
        buttonDeleteStudent = (ImageButton) convertView.findViewById(R.id.buttonDeleteStudent);
        
        result = convertView;

        Animation animation = AnimationUtils.loadAnimation(mainContext, R.anim.slide_animation);
        result.startAnimation(animation);

        if (student.getName().length() <= 24)
            studentName.setText(student.getName());
        else
            studentName.setText(student.getName().substring(0, 22) + "...");

        studentCodeAndEmail.setText(student.getCode() + "\n\n" +student.getEmail());
        buttonEditStudent.setTag(position);
        buttonDeleteStudent.setTag(position);

        buttonEditStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer)v.getTag();
                Object object = getItem(position);
                Student student = (Student)object;
                current.setCurrentView(R.layout.update_student_layout);
                current.setContentView(current.getCurrentView());
                current.buildUpdateView(student);
            }
        });
        buttonDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer)v.getTag();
                Object object = getItem(position);
                Student student = (Student)object;
                current.showDeleteDialog(student);
            }
        });

        return convertView;
    }
}