package com.example.duongthuhien.kltn.CaiDat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.duongthuhien.kltn.AlarmReceiver;
import com.example.duongthuhien.kltn.R;

public class CaiDatActivity extends AppCompatActivity implements View.OnClickListener {
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    CheckBox ckb_TuVung;
    CheckBox ckb_NguPhap;
    CheckBox ckb_Kanji;
    CheckBox ckb_Romaji;
    CheckBox ckb_Ngia;
    CheckBox ckb_HienHTD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);

        addControls();
        Intent intent =new Intent(CaiDatActivity.this, AlarmReceiver.class);
        intent.setAction("com.example.duongthuhien.kltn.ALARM_RECEIVER");
        pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addControls() {
        alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        ckb_HienHTD=findViewById(R.id.ckb_HienHTD);
        ckb_Kanji=findViewById(R.id.ckb_Kanji);
        ckb_Ngia=findViewById(R.id.ckb_Ngia);
        ckb_NguPhap=findViewById(R.id.ckb_NguPhap);
        ckb_Romaji=findViewById(R.id.ckb_Romaji);
        ckb_TuVung=findViewById(R.id.ckb_TuVung);

        ckb_TuVung.setOnClickListener(this);
        ckb_HienHTD.setOnClickListener(this);
        ckb_Kanji.setOnClickListener(this);
        ckb_Ngia.setOnClickListener(this);
        ckb_NguPhap.setOnClickListener(this);
        ckb_Romaji.setOnClickListener(this);


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
                if (ckb_HienHTD.isChecked()){
                    //alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,);
                }
                break;
            case R.id.ckb_Kanji:
                if (ckb_Kanji.isChecked()){
                    Toast.makeText(getApplicationContext(), "HienHocthudong", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.ckb_TuVung:
                if (ckb_TuVung.isChecked()){
                    Toast.makeText(getApplicationContext(), "HienHocthudong", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.ckb_Ngia:
                if (ckb_Ngia.isChecked()){
                    Toast.makeText(getApplicationContext(), "HienHocthudong", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.ckb_NguPhap:
                if (ckb_NguPhap.isChecked()){
                    Toast.makeText(getApplicationContext(), "HienHocthudong", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.ckb_Romaji:
                if (ckb_Romaji.isChecked()){
                    Toast.makeText(getApplicationContext(), "HienHocthudong", Toast.LENGTH_LONG).show();
                }
                break;

        }

    }
}
