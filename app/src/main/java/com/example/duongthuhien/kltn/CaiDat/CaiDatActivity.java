package com.example.duongthuhien.kltn.CaiDat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.duongthuhien.kltn.AlarmReceiver;
import com.example.duongthuhien.kltn.R;

public class CaiDatActivity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener {
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    CheckBox ckb_HienHTD;
    RadioButton rb_TuVung;
    RadioButton rb_NguPhap;
    RadioButton rb_Kanji;
    int interval = 60000;
    SharedPreferences pref;
    public static final String PREF_HIEN_HTD = "HienHTD";
    public static final String PREF_LUA_CHON_HTD = "LuachonHTD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        addControls();
        Intent intent =new Intent(CaiDatActivity.this, AlarmReceiver.class);
        intent.setAction("com.example.duongthuhien.kltn.ALARM_RECEIVER");
        pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addControls() {
        alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        ckb_HienHTD=findViewById(R.id.ckb_HienHTD);
        rb_Kanji=findViewById(R.id.rb_Kanji);
        rb_NguPhap=findViewById(R.id.rb_NguPhap);
        rb_TuVung=findViewById(R.id.rb_TuVung);

        rb_TuVung.setOnClickListener(this);
        ckb_HienHTD.setOnClickListener(this);
        rb_Kanji.setOnClickListener(this);
        rb_NguPhap.setOnClickListener(this);

        ckb_HienHTD.setOnCheckedChangeListener(this);
        boolean hienHTD = pref.getBoolean(PREF_HIEN_HTD, false);
        ckb_HienHTD.setChecked(hienHTD);

        rb_TuVung.setEnabled(hienHTD);
        rb_NguPhap.setEnabled(hienHTD);
        rb_Kanji.setEnabled(hienHTD);

        int luachonHTD = pref.getInt(PREF_LUA_CHON_HTD, 0);
        if (luachonHTD == 0) {
            rb_TuVung.setChecked(true);
        } else if (luachonHTD == 1) {
            rb_NguPhap.setChecked(true);
        } else if (luachonHTD == 2) {
            rb_Kanji.setChecked(true);
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ckb_HienHTD:
                //luu vao shared preference
                pref.edit().putBoolean(PREF_HIEN_HTD, ckb_HienHTD.isChecked()).commit();

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(this, AlarmReceiver.class);
                intent.setAction("com.example.duongthuhien.kltn.ALARM_RECEIVER");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0, intent, 0);
                if (ckb_HienHTD.isChecked()){
                    Log.d("hiendt","setAlarm");
                    alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,  SystemClock.elapsedRealtime(), interval, pendingIntent);
                } else {
                    if (alarmManager!= null) {
                        alarmManager.cancel(pendingIntent);
                    }
                }
                break;
            case R.id.rb_TuVung:
                if (rb_TuVung.isChecked()){
                    pref.edit().putInt(PREF_LUA_CHON_HTD, 0).commit();
                }
                break;
            case R.id.rb_NguPhap:
                if (rb_NguPhap.isChecked()){
                    pref.edit().putInt(PREF_LUA_CHON_HTD, 1).commit();
                }
                break;
            case R.id.rb_Kanji:
                if (rb_Kanji.isChecked()){
                    pref.edit().putInt(PREF_LUA_CHON_HTD, 2).commit();
                }
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.ckb_HienHTD) {
            rb_TuVung.setEnabled(isChecked);
            rb_NguPhap.setEnabled(isChecked);
            rb_Kanji.setEnabled(isChecked);
        }
    }
}
