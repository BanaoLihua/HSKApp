package com.example.hskapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ExerciseFragment extends Fragment {

    // ExerciseActivityとの架け橋
    public static ExerciseFragment newInstance(Integer num, ArrayList<String> voc_cn, ArrayList<String> voc_jp, ArrayList<String> pinyin) {
        ExerciseFragment fragment = new ExerciseFragment();
        Bundle barg = new Bundle();
        barg.putInt("問題番号", num);
        barg.putStringArrayList("中国語", voc_cn);
        barg.putStringArrayList("日本語", voc_jp);
        barg.putStringArrayList("拼音", pinyin);
        fragment.setArguments(barg);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 値を受け取って表示
        Bundle args = getArguments();
        if(args != null ){

            Integer num = args.getInt("問題番号");
            ArrayList<String> voc_cn = args.getStringArrayList("中国語");
            ArrayList<String> voc_jp = args.getStringArrayList("日本語");
            ArrayList<String> pinyin = args.getStringArrayList("拼音");

            TextView textViewCn = view.findViewById(R.id.voc_cn);
            textViewCn.setText(voc_cn.get(num));
            TextView textViewJp = view.findViewById(R.id.voc_jp);
            textViewJp.setText(voc_jp.get(num));
            TextView textViewPinyin = view.findViewById(R.id.pinyin);
            textViewPinyin.setText(pinyin.get(num));
        }
    }
}
