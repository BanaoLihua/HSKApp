package com.example.hskapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

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
                    setLevelFragment(150, "1");
                }
            });
            // 2級
            level2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setLevelFragment(300, "2");
                }
            });
            // 3級
            level3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setLevelFragment(600, "3");
                }
            });
            // 4級
            level4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setLevelFragment(1200, "4");
                }
            });
            // 5級
            level5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setLevelFragment(2500, "5");
                }
            });
            // 6級
            level6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setLevelFragment(5000, "6");
                }
            });
        }
    }
    // LevelFragmentへ渡す関数
    public void setLevelFragment(Integer voc_count, String selected_level) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // 単元パラメータ
        ArrayList<String> unit_list = new ArrayList<>();
        int unit_num = 1;

        if(selected_level == "1") {
            Integer max_unit = 15;
            for (; unit_num <= max_unit;) {
                unit_list.add(String.valueOf(unit_num));
                unit_num++;
            }
        }
        else if(selected_level == "2") {
            Integer max_unit = 15;
            for (; unit_num <= max_unit;) {
                unit_list.add(String.valueOf(unit_num));
                unit_num++;
            }
        }
        else if(selected_level == "3") {
            Integer max_unit = 29;
            for (; unit_num <= max_unit;) {
                unit_list.add(String.valueOf(unit_num));
                unit_num++;
            }
        }
        else if(selected_level == "4") {
            Integer max_unit = 60;
            for (; unit_num <= max_unit;) {
                unit_list.add(String.valueOf(unit_num));
                unit_num++;
            }
        }
        else if(selected_level == "5") {
            Integer max_unit = 130;
            for (; unit_num <= max_unit;) {
                unit_list.add(String.valueOf(unit_num));
                unit_num++;
            }
        }
        else if(selected_level == "6") {
            Integer max_unit = 251;
            for (; unit_num <= max_unit;) {
                unit_list.add(String.valueOf(unit_num));
                unit_num++;
            }
        }

        fragmentTransaction.add(R.id.container,
                LevelFragment.newInstance(voc_count,selected_level,unit_list));
        fragmentTransaction.commit();
    }
}
