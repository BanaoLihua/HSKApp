package com.example.hskapp;

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

public class ExerciseFragmentResult extends Fragment {
    public static ExerciseFragmentResult newInstance(int num, ArrayList<Integer> incorrect_list,ArrayList<String> voc_cn, ArrayList<String> voc_jp, ArrayList<String> pinyin, String unit, String level) {
        ExerciseFragmentResult fragment = new ExerciseFragmentResult();
        Bundle barg = new Bundle();
        barg.putInt("番号", num);
        barg.putIntegerArrayList("間違い", incorrect_list);
        barg.putStringArrayList("中国語", voc_cn);
        barg.putStringArrayList("日本語", voc_jp);
        barg.putStringArrayList("拼音", pinyin);
        barg.putString("単元", unit);
        barg.putString("級", level);
        fragment.setArguments(barg);
        return fragment;
    }
    public static ArrayList<String> result_list = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView resultTitle = view.findViewById(R.id.result_title);
        resultTitle.setText("演習結果・その" + getArguments().getString("単元"));

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

       result_list = new ArrayList<>();


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

        /**「次へ」ボタン押下時の処理**/
        Integer level = new Integer(getArguments().getString("級")).intValue();
        Integer unit = new Integer(getArguments().getString("単元")).intValue();
        Button toNext = view.findViewById(R.id.toNext);

        makeLastUnitDeleteNext(level, unit, toNext);

        toNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer current_unit = new Integer(getArguments().getString("単元")).intValue();
                Integer next_unit = current_unit + 1;
                ((ExerciseActivity)getContext()).goToNext(next_unit.toString());
            }
        });

    }

    /**最後の単元で「次へ」ボタンを表示させない関数**/
    public void makeLastUnitDeleteNext(Integer level, Integer unit, Button toNext) {
        if((level == 1) && (unit == 15)) {
            toNext.setVisibility((View.GONE));
        }
        else if((level == 2) && (unit == 15)) {
            toNext.setVisibility((View.GONE));
        }
        else if((level == 3) && (unit == 29)) {
            toNext.setVisibility((View.GONE));
        }
        else if((level == 4) && (unit == 60)) {
            toNext.setVisibility((View.GONE));
        }
        else if((level == 5) && (unit == 130)) {
            toNext.setVisibility((View.GONE));
        }
        else if((level == 6) && (unit == 251)) {
            toNext.setVisibility((View.GONE));
        }
    }
}
