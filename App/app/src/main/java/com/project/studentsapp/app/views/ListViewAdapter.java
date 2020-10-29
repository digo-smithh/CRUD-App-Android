package com.project.studentsapp.app.views;

import com.project.studentsapp.R;
import com.project.studentsapp.app.models.Student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Student> {

    Context context;
    int resource;
    List<Student> objects;

    public ListViewAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(resource, parent, false);
        }

        TextView name = (TextView) view.findViewById(R.id.studentName);
        TextView code = (TextView) view.findViewById(R.id.studentCode);

        Student student = objects.get(position);
        name.setText(student.getName());
        code.setText(student.getCode());
        return super.getView(position, convertView, parent);
    }
}
