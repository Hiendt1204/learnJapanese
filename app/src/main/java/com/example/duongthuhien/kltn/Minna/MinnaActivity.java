package com.example.duongthuhien.kltn.Minna;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.example.duongthuhien.kltn.R;

public class MinnaActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_BaiHocM;
    Button btn_TuMoiM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minna);
        addControls();
    }

    private void addControls() {

        //btn_TuMoiM.setOnClickListener(this);
        //btn_BaiHocM.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showFragment(1, 0);
    }

    private void showFragment(int bai, int option) {
        Fragment fragment = null;
        if (option == 0) {
            fragment = new TuMoiFragment();
        } else if (option == 1) {
            //fragment = new GrammarFragment();
        }
        if (fragment == null) {
            Log.d("hiendt","fragment is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("Bai",bai);
        fragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                //.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .show(fragment)
                .add(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onClick(View view) {

    }
}
