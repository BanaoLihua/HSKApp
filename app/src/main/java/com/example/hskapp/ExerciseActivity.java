package com.example.hskapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class ExerciseActivity extends AppCompatActivity {

    // グローバル変数を定義

    public static Integer num = 0;

    private static final String[] voc_cn = {
            "洗手间",
            "中文",
            "飞机"
    };

    private static final String[] voc_jp = {
            "トイレ",
            "中国語",
            "飛行機"
    };

    private static final String[] pinyin = {
            "xǐshǒujiān",
            "zhōngwén",
            "fēijī"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        // LevelFragmentから値を受け取る
        Intent intent = getIntent();
        String unit = intent.getStringExtra("Unit");
        TextView textView = findViewById(R.id.unit);
        textView.setText(unit);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, ExerciseFragment.newInstance(num, voc_cn, voc_jp, pinyin)).commit();

        // 「わかった」押下時の処理
        Button correctButton = findViewById(R.id.correct);
        correctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + 1;

                if(num >= voc_cn.length) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.result, ExerciseFragmentResult.newInstance(num)).commit();
                }
                else {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, ExerciseFragment.newInstance(num, voc_cn, voc_jp, pinyin)).commit();
                }
            }
        });

    }
}
