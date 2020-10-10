package com.example.hskapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ExerciseFragmentResult extends Fragment {
    public static ExerciseFragmentResult newInstance(int num) {
        ExerciseFragmentResult fragment = new ExerciseFragmentResult();
        Bundle barg = new Bundle();
        barg.putInt("番号", num);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // 「一覧へ」ボタン押下時の処理：ExerciseActivityのfinishメソッドを呼び出してExerciseActivityを廃棄
        Button toList = view.findViewById(R.id.toList);
        toList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ExerciseActivity)getContext()).finish();

            }
        });
    }
}
