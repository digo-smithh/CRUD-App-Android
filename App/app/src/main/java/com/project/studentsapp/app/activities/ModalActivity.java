package com.project.studentsapp.app.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.project.studentsapp.R;
import com.project.studentsapp.app.controllers.StudentsController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ModalActivity extends BottomSheetDialogFragment {

    Context mainContext;
    MainActivity current;
    Method method;

    public ModalActivity(Context context, MainActivity current, Method method) {
        this.mainContext = context;
        this.current = current;
        this.method = method;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavigationView navigationView = view.findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                try {
                    method.invoke(current);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    Toast.makeText(mainContext, "Unexpected error. (status: 0013)", Toast.LENGTH_SHORT).show();
                    System.exit(0013);
                }
                return true;
            }
        });
    }
}
