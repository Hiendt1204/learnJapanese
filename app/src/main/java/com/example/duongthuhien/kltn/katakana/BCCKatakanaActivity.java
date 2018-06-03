package com.example.duongthuhien.kltn.katakana;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duongthuhien.kltn.R;

/**
 * Created by Duong Thu Hien on 5/14/2018.
 */

public class BCCKatakanaActivity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_bcckatakana,container,false);
    }
}
