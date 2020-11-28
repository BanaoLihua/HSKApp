package com.example.hskapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

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
    public static ArrayList<String> result_list = new ArrayList<>();
    public ListView listView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**リザルト画面に表示されるテキストを作成してListViewへ**/
       for(int i=0; i<getArguments().getInt("番号"); i++) {
           String result_cell = getArguments().getStringArrayList("中国語").get(i) + "(" + getArguments().getStringArrayList("拼音").get(i) + ")" + "：" + getArguments().getStringArrayList("日本語").get(i);
           if(getArguments().getIntegerArrayList("間違い").contains(i)) {
               result_cell = "❌ " + result_cell;
           }else {
               result_cell = "⭕ " + result_cell;
           }
           result_list.add(result_cell);
       }
       final ArrayAdapter adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, result_list);
       ListView listView = (ListView) view.findViewById(R.id.result_list);
       listView.setAdapter(adapter);


        /**「一覧へ」ボタン押下時の処理：ExerciseActivityのfinishメソッドを呼び出してExerciseActivityを廃棄・numとincorrect_listをリセット **/
        Button toList = view.findViewById(R.id.toList);
        toList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ExerciseActivity)getContext()).num = 0;
                ((ExerciseActivity)getContext()).incorrect_list = new ArrayList<>();
                result_list = new ArrayList<>();

                ((ExerciseActivity)getContext()).finish();
            }
        });

        // Todo: バックキーを押した際の挙動をどうするか？
        // Todo: 「次へ」ボタン押下時の処理
        // 「次へ」ボタン押下時の処理

        Button toNext = view.findViewById(R.id.toNext);
        toNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**LevelFragmentをlistViewで動かさない可能性があるので後回し**/
            }
        });
    }

}
