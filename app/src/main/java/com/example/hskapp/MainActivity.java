package com.example.hskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button levelButton = findViewById(R.id.button_level);
        levelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLevelIntent = new Intent(getApplication(), LevelActivity.class);
                startActivity(toLevelIntent);
            }
        });

        Button myvocButton = findViewById(R.id.button_myvoc);
        myvocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMyvocIntent = new Intent(getApplication(), MyvocActivity.class);
                startActivity(toMyvocIntent);
            }
        });
    }
}
