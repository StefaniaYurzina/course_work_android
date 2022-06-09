package com.mirea.course_work.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.mirea.course_work.MainActivity;
import com.mirea.course_work.R;

public class LoadingActivity extends AppCompatActivity {

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        name = findViewById(R.id.name);
        name.setOnClickListener(view -> {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
}