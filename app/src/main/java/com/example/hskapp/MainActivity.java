package com.example.hskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.graphics.Typeface;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface customFont = Typeface.createFromAsset(getAssets(), "HGRGY001.ttf");
        TextView appName = findViewById(R.id.app_name);
        appName.setTypeface(customFont);

        Button levelButton = findViewById(R.id.button_level);
        blinkBtn(levelButton, 1000, 500);
        levelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLevelIntent = new Intent(getApplication(), LevelActivity.class);
                startActivity(toLevelIntent);
            }
        });

        /** My単語帳画面への遷移
        Button myvocButton = findViewById(R.id.button_myvoc);
        myvocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMyvocIntent = new Intent(getApplication(), MyvocActivity.class);
                startActivity(toMyvocIntent);
            }
        });
         **/
    }

    /**ボタンを点滅させる関数**/
    private void blinkBtn(Button btn, long duration, long offset){
        Animation anm = new AlphaAnimation(0.0f, 1.0f);
        anm.setDuration(duration);
        anm.setStartOffset(offset);
        anm.setRepeatMode(Animation.REVERSE);
        anm.setRepeatCount(Animation.INFINITE);
        btn.startAnimation(anm);
    }
}
