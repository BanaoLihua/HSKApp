package com.example.hskapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class LevelActivity extends AppCompatActivity {

    private TextView level1,level2,level3,level4,level5,level6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        // 戻るボタン（Activityの終了）
        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // parts_levelを取得
        level1 = findViewById(R.id.level1).findViewById(R.id.level);
        level2 = findViewById(R.id.level2).findViewById(R.id.level);
        level3 = findViewById(R.id.level3).findViewById(R.id.level);
        level4 = findViewById(R.id.level4).findViewById(R.id.level);
        level5 = findViewById(R.id.level5).findViewById(R.id.level);
        level6 = findViewById(R.id.level6).findViewById(R.id.level);

        level1.setText("HSK1級");
        level2.setText("HSK2級");
        level3.setText("HSK3級");
        level4.setText("HSK4級");
        level5.setText("HSK5級");
        level6.setText("HSK6級");

        // 各級のクリック処理
        if(savedInstanceState == null) {
            // 1級
            level1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    // BackStackを設定
                    fragmentTransaction.addToBackStack(null);

                    // パラメータを設定
                    String str = "1級";
                    Integer voc_count = 150;
                    ArrayList<String> unit_list = new ArrayList<>();
                    unit_list.add("noun01");
                    unit_list.add("noun02");
                    unit_list.add("verb01");

                    // LevelFragmentに値を渡す
                    fragmentTransaction.add(R.id.container,
                            LevelFragment.newInstance(str,voc_count,unit_list));
                    fragmentTransaction.commit();
                }
            });
            /** 処理の邪魔なので、まずは1級から
            // 2級
            level2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    // BackStackを設定
                    fragmentTransaction.addToBackStack(null);

                    // パラメータを設定
                    String str = "2級";
                    Integer voc_count = 300;
                    fragmentTransaction.add(R.id.container,
                            LevelFragment.newInstance(str,voc_count,));
                    fragmentTransaction.commit();
                }
            });
            // 3級
            level3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    // BackStackを設定
                    fragmentTransaction.addToBackStack(null);

                    // パラメータを設定
                    String str = "3級";
                    Integer voc_count = 600;
                    fragmentTransaction.add(R.id.container,
                            LevelFragment.newInstance(str,voc_count));
                    fragmentTransaction.commit();
                }
            });
            // 4級
            level4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    // BackStackを設定
                    fragmentTransaction.addToBackStack(null);

                    // パラメータを設定
                    String str = "4級";
                    Integer voc_count = 1200;
                    fragmentTransaction.add(R.id.container,
                            LevelFragment.newInstance(str,voc_count));
                    fragmentTransaction.commit();
                }
            });
            // 5級
            level5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    // BackStackを設定
                    fragmentTransaction.addToBackStack(null);

                    // パラメータを設定
                    String str = "5級";
                    Integer voc_count = 2500;
                    fragmentTransaction.add(R.id.container,
                            LevelFragment.newInstance(str,voc_count));
                    fragmentTransaction.commit();
                }
            });
            // 6級
            level6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    // BackStackを設定
                    fragmentTransaction.addToBackStack(null);

                    // パラメータを設定
                    String str = "6級";
                    Integer voc_count = 5000;
                    fragmentTransaction.add(R.id.container,
                            LevelFragment.newInstance(str,voc_count));
                    fragmentTransaction.commit();
                }
            });
             */
        }
    }
}
