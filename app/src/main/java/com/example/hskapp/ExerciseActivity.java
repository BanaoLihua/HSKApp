package com.example.hskapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
    public static ArrayList<Integer> incorrect_list = new ArrayList<>();

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
        textLevel.setText(level + "級");
        textUnit.setText("その" + unit);

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

        /**「分かった」ボタン押下時の処理**/
        //todo: 「分かった」ボタン押下後にバックキーを押すとエラーが起きる不具合

        Button correctButton = findViewById(R.id.correct);
        correctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;

                if(num >= list_voc_cn.size()) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.result, ExerciseFragmentResult.newInstance(num, incorrect_list,list_voc_cn, list_voc_jp, list_pinyin )).commit();
                }
                else {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, ExerciseFragment.newInstance(num, list_voc_cn, list_voc_jp, list_pinyin)).commit();
                    fragmentTransaction.addToBackStack(null);
                }
            }
        });
        /**「分からない」ボタン押下時の処理**/
        final Button incorrectButton = findViewById(R.id.incorrect);
        incorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incorrect_list.add(num);
                num++;

                if(num >= list_voc_cn.size()) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.result, ExerciseFragmentResult.newInstance(num, incorrect_list,list_voc_cn, list_voc_jp, list_pinyin)).commit();
                }
                else {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, ExerciseFragment.newInstance(num, list_voc_cn, list_voc_jp, list_pinyin)).commit();
                    fragmentTransaction.addToBackStack(null);
                }
            }
        });

        /**「答え」ボタン押下時の処理**/
        Button answerButton = findViewById(R.id.answer);
        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
                TextView voc_jp = fragment.getActivity().findViewById(R.id.voc_jp);
                TextView pinyin = fragment.getActivity().findViewById(R.id.pinyin);
                voc_jp.setVisibility(View.VISIBLE);
                pinyin.setVisibility(View.VISIBLE);
            }
        });
    }
    /**backキー押下時の処理・戻るを押すとnumも減るようにする**/
    @Override
    public void onBackPressed() {
        if(num > 0) {
            num--;
            incorrect_list.remove(incorrect_list.size() - 1);
        }
        super.onBackPressed();
    }
}
