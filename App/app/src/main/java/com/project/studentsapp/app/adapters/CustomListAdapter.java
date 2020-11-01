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

    private ArrayList<Student> dataSet;
    Context mainContext;

    TextView studentCode;
    TextView studentName;
    TextView studentEmail;
    ImageButton editButton;
    ImageButton deleteButton;

    ImageView info;

    public CustomListAdapter(ArrayList<Student> data, Context mainContext) {
        super(mainContext, R.layout.list_item_layout, data);
        this.dataSet = data;
        this.mainContext = mainContext;
    }

    @Override
    public void onClick(View v) {

        int position = (Integer)v.getTag();
        Object object = getItem(position);
        Student student = (Student)object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Student student = getItem(position);

        View result = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_layout, parent, false);
            studentCode = (TextView) convertView.findViewById(R.id.studentCodeListItem);
            studentName = (TextView) convertView.findViewById(R.id.studentNameListItem);
            studentEmail = (TextView) convertView.findViewById(R.id.studentEmailListItem);
            info = (ImageView) convertView.findViewById(R.id.item_info);

            result=convertView;
        }
        else {
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mainContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        studentCode.setText(student.getCode());
        studentName.setText(student.getName());
        studentEmail.setText(student.getEmail());
        info.setOnClickListener(this);
        info.setTag(position);

        return convertView;
    }
}