package com.example.hskapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.widget.TextView;

public class LevelFragment extends Fragment {

    public static LevelFragment newInstance(String str,int voc_count) {
        LevelFragment fragment = new LevelFragment();
        // Bundle にパラメータを設定
        Bundle barg = new Bundle();
        barg.putString("級", str);
        barg.putInt("語彙数", voc_count);
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
            String str = args.getString("級");
            Integer get_int = args.getInt("語彙数");
            String voc_counts = get_int.toString();

            TextView textView = view.findViewById(R.id.text_fragment);
            textView.setText(str + "/" + voc_counts + "語");
        }
    }
}
