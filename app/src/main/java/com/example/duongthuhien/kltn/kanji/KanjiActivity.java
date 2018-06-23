package com.example.duongthuhien.kltn.kanji;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.duongthuhien.kltn.Adapter.DanhSachBaiHoc_k1_adapter;
import com.example.duongthuhien.kltn.Adapter.Kanji1_adapter;
import com.example.duongthuhien.kltn.GhepTuActivity;
import com.example.duongthuhien.kltn.Model.BaiHocH;
import com.example.duongthuhien.kltn.Model.Kanji1;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;
import com.example.duongthuhien.kltn.TracNghiemActivity;
import com.example.duongthuhien.kltn.Tuvungyeuthich.TuvungyeuthichActivity;

import java.util.ArrayList;
import java.util.List;

public class KanjiActivity extends AppCompatActivity implements View.OnClickListener {
    List<Kanji1> kanji1List;
    ListView lv_Kanji1;
    ListView lv_BaiHoc_Kanji1;
    ArrayList<BaiHocH> dsBaiHoc;
    DanhSachBaiHoc_k1_adapter danhSachBaiHoc_k1_adapter;
    Kanji1_adapter kanji1_adapter;
    ImageButton btn_GhepTu;
    ImageButton btn_TracNghiem;
    int lessionID;

    MediaPlayer mediaPlayer;


    ArrayList<Integer> mlistSound = new ArrayList<>();
    ArrayList<Kanji1> kanjiList = new ArrayList<>();

    private int currentTrack = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji);

        addcontrols();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner1 = findViewById(R.id.spinner_DSBaiHoc);
        String[] stringArray = getResources().getStringArray(R.array.arrBaihoc_Kanji1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, stringArray);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lessionID = position;
                kanji1List = new ArrayList<Kanji1>();
                SQLiteDataController sqLiteDataController = new SQLiteDataController(KanjiActivity.this);
                sqLiteDataController.open();
                SQLiteDatabase database = sqLiteDataController.getMyDatabase();

                kanji1List = sqLiteDataController.getbylessionID(position);
                for (Kanji1 kanji1 : kanji1List) {
                }

                Kanji1_adapter kanji1_adapter = new Kanji1_adapter(KanjiActivity.this
                        , R.layout.item_listview_kanji1, kanji1List, position);

                lv_Kanji1.setAdapter(kanji1_adapter);
                getListSound();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_action, menu);
        return true;
    }
    public void playAll(){
        try {
            Uri file = Uri.parse("android.resource://com.example.duongthuhien.kltn/" + mlistSound.get(currentTrack));
            Log.d("hiendt","Uri  "+file);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(this, file);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (currentTrack==(mlistSound.size()-1)){
                        return;
                    }
                    currentTrack = (currentTrack + 1) % mlistSound.size();
                    Uri nextTrack = Uri.parse("android.resource://com.example.duongthuhien.kltn/"
                            + mlistSound.get(currentTrack));
                    Log.d("hiendt","PlayComplete  "+nextTrack);
                    try {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(KanjiActivity.this,nextTrack);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        Log.d("hiendt","start done  ");
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

            });
            Log.d("hiendt","play done  ");
            mediaPlayer.start();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.playall:
                playAll();
                break;
            case R.id.favorite:
                Intent intent=new Intent(KanjiActivity.this, TuvungyeuthichActivity.class);
                intent.putExtra("posF",1);
                startActivity(intent);
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public ArrayList getListSound() {
        for (int i = 0; i < kanji1List.size(); i++) {
            int resourceId = getResources()
                    .getIdentifier(kanji1List.get(i).getSoundK(),
                            "raw", getPackageName());
            mlistSound.add(resourceId);
        }
        return mlistSound;
    }

    private void addcontrols() {
        lv_Kanji1 = findViewById(R.id.lv_Kanji1);
        btn_GhepTu = findViewById(R.id.btn_GhepTu);
        btn_TracNghiem = findViewById(R.id.btn_TracNghiem);
        btn_TracNghiem.setOnClickListener(this);
        btn_GhepTu.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_TracNghiem:
                Intent intent = new Intent(KanjiActivity.this, TracNghiemActivity.class);
                intent.putExtra("LESSION_ID", lessionID);
                Log.d("hiendt","lesion_Id_Kanji"+lessionID);
                startActivityForResult(intent,1);
                break;
            case R.id.btn_GhepTu:
                Intent intent1 = new Intent(KanjiActivity.this, GhepTuActivity.class);
                intent1.putExtra("LESSION_ID", lessionID);
                startActivityForResult(intent1,2);
                break;
        }


    }
}
