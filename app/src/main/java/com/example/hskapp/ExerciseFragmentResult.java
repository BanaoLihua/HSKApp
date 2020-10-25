package com.example.hskapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ExerciseFragmentResult extends Fragment {
    public static ExerciseFragmentResult newInstance(int num, ArrayList<Integer> incorrect_list,ArrayList<String> voc_cn, ArrayList<String> voc_jp, ArrayList<String> pinyin) {
        ExerciseFragmentResult fragment = new ExerciseFragmentResult();
        Bundle barg = new Bundle();
        barg.putInt("番号", num);
        barg.putIntegerArrayList("間違い", incorrect_list);
        barg.putStringArrayList("中国語", voc_cn);
        barg.putStringArrayList("日本語", voc_jp);
        barg.putStringArrayList("拼音", pinyin);
        fragment.setArguments(barg);
        return fragment;
    }
    public static ArrayList<String> incorrect_list_char = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**「分からない」が選択された問題番号のリスト（incorrect_list）と対応する問題番号を照合して新たにincorrect_list_charを作って表示**/
        TextView incorrect = view.findViewById(R.id.incorrect);
        for(Integer i : getArguments().getIntegerArrayList("間違い")) {
            String incorrect_voc_cn_list = getArguments().getStringArrayList("中国語").get(i);
            incorrect_list_char.add(incorrect_voc_cn_list);
        }
        incorrect.setText(incorrect_list_char.toString());

        /**「一覧へ」ボタン押下時の処理：ExerciseActivityのfinishメソッドを呼び出してExerciseActivityを廃棄・numとincorrect_listをリセット **/
        Button toList = view.findViewById(R.id.toList);
        toList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ExerciseActivity)getContext()).num = 0;
                ((ExerciseActivity)getContext()).incorrect_list = new ArrayList<>();
                incorrect_list_char = new ArrayList<>();

                ((ExerciseActivity)getContext()).finish();
            }
        });

        // Todo: バックキーを押した際の挙動をどうするか？参考：https://qiita.com/takenori-ooba/items/030bfd6d05f783b4d33a
        // Todo: 「次へ」ボタン押下時の処理
        // 「次へ」ボタン押下時の処理

        Button toNext = view.findViewById(R.id.toNext);

    }
}
