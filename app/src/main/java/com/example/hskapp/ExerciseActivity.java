package com.example.hskapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;
import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {


    // グローバル変数を定義
    ArrayList<Integer> list_num = new ArrayList<>();
    ArrayList<String> list_voc_cn = new ArrayList<>();
    ArrayList<String> list_voc_jp = new ArrayList<>();
    ArrayList<String> list_pinyin = new ArrayList<>();
    ArrayList<Integer> list_unit = new ArrayList<>();

    private DatabaseHelper helper;

    public static Integer num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);


        // LevelFragmentから値を受け取って表示
        Intent intent = getIntent();
        String level = intent.getStringExtra("Level");
        String unit = intent.getStringExtra("Unit");
        TextView textLevel = findViewById(R.id.level);
        TextView textUnit = findViewById(R.id.unit);
        textLevel.setText("級:" + level);
        textUnit.setText("単元:" + unit);

        /** DB操作開始 **/
        helper = new DatabaseHelper(this);
        try {
            helper.createDatabase();
        }
        catch (IOException e) {
            throw new Error("Unable to create database");
        }

        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "select num, voc_cn, voc_jp, pinyin, unit" + " from " + "level"+level + " " + "where" + " unit=" + unit;
        try {
            Cursor cursor = db.rawQuery(sql, null);
            while(cursor.moveToNext()) {
               list_num.add(cursor.getInt(0));
               list_voc_cn.add(cursor.getString(1));
               list_voc_jp.add(cursor.getString(2));
               list_pinyin.add(cursor.getString(3));
               list_unit.add(cursor.getInt(4));
            }

        } finally {
            db.close();
        }
        /** DB操作終了 **/

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, ExerciseFragment.newInstance(num, list_voc_cn, list_voc_jp, list_pinyin)).commit();

        // 「わかった」押下時の処理
        Button correctButton = findViewById(R.id.correct);
        correctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;


                if(num >= list_voc_cn.size()) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.result, ExerciseFragmentResult.newInstance(num)).commit();
                }
                else {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, ExerciseFragment.newInstance(num, list_voc_cn, list_voc_jp, list_pinyin)).commit();
                    fragmentTransaction.addToBackStack(null);
                }
            }
        });
    }
    /**backキー押下時の処理・戻るを押すとnumも減るようにする**/
    @Override
    public void onBackPressed() {
        if(num > 0) {
            num--;
        }
        super.onBackPressed();
    }
}
