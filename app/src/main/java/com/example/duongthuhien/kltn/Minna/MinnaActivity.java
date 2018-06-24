package com.example.duongthuhien.kltn.Minna;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.duongthuhien.kltn.R;

import java.lang.reflect.Field;

public class MinnaActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner spinner_DSBaiHoc;
    Spinner spinner_KieuHoc;
    int posDSBaiHoc;
    int posKieuHoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minna);
        addControls();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] ArrayBaiHocMinna=getResources().getStringArray(R.array.arrBaihoc_Minna);
        String[] ArrayPhanHocMinna=getResources().getStringArray(R.array.arrPhanHoc_Minna);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                //android.R.layout.simple_spinner_dropdown_item,
                R.layout.item_spinner,
                ArrayBaiHocMinna);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner_DSBaiHoc.setAdapter(adapter);

        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,
                //android.R.layout.simple_spinner_dropdown_item,
                R.layout.item_spinner,
                ArrayPhanHocMinna);
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spinner_KieuHoc.setAdapter(adapter1);
        spinner_DSBaiHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posDSBaiHoc=position;
                showFragment();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_DSBaiHoc.setSelection(0);
        spinner_KieuHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posKieuHoc=position;
                showFragment();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_KieuHoc.setSelection(0);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addControls() {

        spinner_DSBaiHoc=findViewById(R.id.spinner_DSBaiHoc);
        spinner_KieuHoc=findViewById(R.id.spinner_KieuHoc);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showFragment();
    }

    private void showFragment() {
        Fragment fragment = null;
        if (posKieuHoc == 0) {
            fragment = new TuMoiFragment();
        } else if (posKieuHoc == 1) {
            fragment = new NguPhapFragment();
        }
        else if (posKieuHoc ==2 ){
            fragment=new Kaiwa_Fragment();
        }
        else if (posKieuHoc ==3 ){
            fragment=new  ThamKhaoFragment();
        }
        if (fragment == null) {
            Log.d("hiendt","fragment is null");
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("Bai",posDSBaiHoc);
        fragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                //.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .show(fragment)
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onClick(View view) {

    }
}
