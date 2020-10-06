package com.example.hskapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Intent intent = getIntent();
        String unit = intent.getStringExtra("Unit");
        System.out.println(unit);
        TextView textView = findViewById(R.id.unit);
        textView.setText(unit);
    }
}
