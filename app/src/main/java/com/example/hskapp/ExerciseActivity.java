//todo: 「次へ」2周目以降のバックキーの挙動をどうにかする

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
        final String unit = intent.getStringExtra("Unit");
        TextView textLevel = findViewById(R.id.level);
        TextView textUnit = findViewById(R.id.unit);
        textLevel.setText(level + "級");
        textUnit.setText("その" + unit);

        getFromDB(unit, level);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, ExerciseFragment.newInstance(num, list_voc_cn, list_voc_jp, list_pinyin)).commit();

        pressCorrect(unit, level);

        pressIncorrect(unit, level);

        pressAnswer();
    }
    /**backキー無効化**/
    @Override
    public void onBackPressed() {

    }

    /**結果画面の「次へ」ボタン押下時の処理**/
    public void goToNext(String next_unit) {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.result);
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();

        Intent intent = getIntent();
        String level = intent.getStringExtra("Level");
        final String unit = next_unit;
        TextView textLevel = findViewById(R.id.level);
        TextView textUnit = findViewById(R.id.unit);
        textLevel.setText(level + "級");
        textUnit.setText("その" + unit);

        getFromDB(unit, level);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, ExerciseFragment.newInstance(num, list_voc_cn, list_voc_jp, list_pinyin)).commit();

        pressCorrect(unit, level);

        pressIncorrect(unit, level);

        pressAnswer();

    }

    /**DBから単語のデータを取ってくる関数**/
    public void getFromDB(String unit, String level) {
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
    }

    /**「答え」ボタン押下時の処理**/
    public void pressAnswer(){
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

    /**「分かった」ボタン押下時の処理**/
    public void pressCorrect(final String unit, final String level) {
        Button correctButton = findViewById(R.id.correct);
        correctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                if(num >= list_voc_cn.size()) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.result, ExerciseFragmentResult.newInstance(num, incorrect_list,list_voc_cn, list_voc_jp, list_pinyin, unit, level )).commit();
                    list_num = new ArrayList<>();
                    list_voc_cn = new ArrayList<>();
                    list_voc_jp = new ArrayList<>();
                    list_pinyin = new ArrayList<>();
                    incorrect_list = new ArrayList<>();
                    num = 0;
                }
                else {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, ExerciseFragment.newInstance(num, list_voc_cn, list_voc_jp, list_pinyin)).commit();
                    fragmentTransaction.addToBackStack(null);
                }
            }
        });
    }

    /**「分からなかった」ボタン押下時の処理**/
    public void pressIncorrect(final String unit, final String level) {
        final Button incorrectButton = findViewById(R.id.incorrect);
        incorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incorrect_list.add(num);
                num++;

                if(num >= list_voc_cn.size()) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.result, ExerciseFragmentResult.newInstance(num, incorrect_list,list_voc_cn, list_voc_jp, list_pinyin, unit, level)).commit();
                    list_num = new ArrayList<>();
                    list_voc_cn = new ArrayList<>();
                    list_voc_jp = new ArrayList<>();
                    list_pinyin = new ArrayList<>();
                    incorrect_list = new ArrayList<>();
                    num = 0;
                }
                else {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, ExerciseFragment.newInstance(num, list_voc_cn, list_voc_jp, list_pinyin)).commit();
                    fragmentTransaction.addToBackStack(null);
                }
            }
        });
    }
}
