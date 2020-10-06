package com.example.hskapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LevelFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static LevelFragment newInstance(String str,Integer voc_count, ArrayList unit_list) {
        LevelFragment fragment = new LevelFragment();
        // Bundle にパラメータを設定
        Bundle barg = new Bundle();
        barg.putString("級", str);
        barg.putInt("語彙数", voc_count);
        barg.putStringArrayList("単元", unit_list);
        fragment.setArguments(barg);
        return fragment;
    }
    // FragmentのViewを生成して返す
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_level,
                container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if(args != null ){
            // パラメータの受取
            String str = args.getString("級");
            Integer get_int = args.getInt("語彙数");
            String voc_counts = get_int.toString();
            ArrayList unit_list = args.getStringArrayList("単元");

            // 単元一覧をListViewで表示
            ListView listView = getActivity().findViewById(R.id.unit_list);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, unit_list);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(this);

            // 適当に受け取ったパラメータを表示
            TextView textView = view.findViewById(R.id.text_fragment);
            textView.setText(str + "/" + voc_counts + "語");
        }
    }
    // ListViewのアイテムを選択したら単元パラメータと共にExerciseActivityへ遷移する処理
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity().getApplicationContext(), ExerciseActivity.class);
        String selectedItem = parent.getAdapter().getItem(position).toString();
        System.out.println(selectedItem);
        intent.putExtra("Unit", selectedItem);
        startActivity(intent);
    }
}
