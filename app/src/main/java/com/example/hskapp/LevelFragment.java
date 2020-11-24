package com.example.hskapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class LevelFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static LevelFragment newInstance(Integer voc_count,String selected_level, ArrayList unit_list) {
        LevelFragment fragment = new LevelFragment();
        // Bundle にパラメータを設定
        Bundle barg = new Bundle();
        barg.putInt("語彙数", voc_count);
        barg.putString("級",selected_level );
        barg.putStringArrayList("単元", unit_list);
        fragment.setArguments(barg);
        return fragment;
    }
    // FragmentのViewを生成して返す
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_level,
                container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if(args != null ){
            // パラメータの受取
            ArrayList unit_list = args.getStringArrayList("単元");

            // 単元一覧をGridViewで表示
            GridView gridView = view.findViewById(R.id.gridview);
            GridAdapter adapter = new GridAdapter(getActivity().getApplicationContext(), R.layout.grid_items, unit_list);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(this);

        }
    }
    // ListViewのアイテムを選択したら単元パラメータと共にExerciseActivityへ遷移する処理
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedLevel = getArguments().getString("級");
        String selectedUnit = parent.getAdapter().getItem(position).toString();
        System.out.println(selectedUnit);
        Intent intent = new Intent(getActivity().getApplicationContext(), ExerciseActivity.class);
        intent.putExtra("Level", selectedLevel);
        intent.putExtra("Unit", selectedUnit);
        startActivity(intent);
    }
}
