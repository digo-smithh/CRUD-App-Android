package com.project.studentsapp.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.project.studentsapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        setSupportActionBar(bottomAppBar);

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetLayout bottomSheetLayout = new BottomSheetLayout(MainActivity.this);
                bottomSheetLayout.show(getSupportFragmentManager(), bottomSheetLayout.getTag());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_app_bar_menu, menu);
        return true;
    }
}