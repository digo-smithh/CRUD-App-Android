package com.project.studentsapp.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.studentsapp.R;
import com.project.studentsapp.app.models.Student;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Student> implements View.OnClickListener{

    private int lastPosition = -1;
    private ArrayList<Student> dataSet;
    private Context mainContext;

    private TextView studentName;
    private TextView studentEmail;
    private ImageButton buttonEditStudent;
    private ImageButton buttonDeleteStudent;

    public CustomListAdapter(ArrayList<Student> data, Context mainContext) {
        super(mainContext, R.layout.list_item_layout, data);
        this.dataSet = data;
        this.mainContext = mainContext;
    }

    @Override
    public void onClick(View v) { }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Student student = getItem(position);

        View result = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_layout, parent, false);
            studentName = (TextView) convertView.findViewById(R.id.studentNameListItem);
            studentEmail = (TextView) convertView.findViewById(R.id.studentCodeAndEmailListItem);
            buttonEditStudent = (ImageButton) convertView.findViewById(R.id.buttonEditStudent);
            buttonDeleteStudent = (ImageButton) convertView.findViewById(R.id.buttonDeleteStudent);

            result=convertView;
        }
        else {
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mainContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        studentName.setText(student.getName());
        studentEmail.setText(student.getCode() + "\n\n" +student.getEmail());
        buttonEditStudent.setTag(position);
        buttonDeleteStudent.setTag(position);

        buttonEditStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer)v.getTag();
                Object object = getItem(position);
                Student student = (Student)object;
            }
        });
        buttonDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer)v.getTag();
                Object object = getItem(position);
                Student student = (Student)object;
            }
        });

        return convertView;
    }
}