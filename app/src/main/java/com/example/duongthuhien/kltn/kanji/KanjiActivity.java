package com.example.duongthuhien.kltn.kanji;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
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

    SoundPool soundWord;
    AudioManager audioManager;
    int pos1;

    ArrayList<Integer> mlistSound=new ArrayList<>();
    ArrayList<Kanji1> kanjiList=new ArrayList<>();

    // Số luồng âm thanh phát ra tối đa.
    private static final int MAX_STREAMS = 5;

    // Chọn loại luồng âm thanh để phát nhạc.
    private static final int streamType = AudioManager.STREAM_MUSIC;

    private boolean loaded;

    private int sound;
    private float volume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji);

        addcontrols();


        // Đối tượng AudioManager sử dụng để điều chỉnh âm lượng.
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Chỉ số âm lượng hiện tại của loại luồng nhạc cụ thể (streamType).
        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);


        // Chỉ số âm lượng tối đa của loại luồng nhạc cụ thể (streamType).
        float maxVolumeIndex = (float) audioManager.getStreamMaxVolume(streamType);

        // Âm lượng  (0 --> 1)
        this.volume = currentVolumeIndex / maxVolumeIndex;

        // Cho phép thay đổi âm lượng các luồng kiểu 'streamType' bằng các nút
        // điều khiển của phần cứng.
        setVolumeControlStream(streamType);

        // Với phiên bản Android SDK >= 21
        if (Build.VERSION.SDK_INT >= 21) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundWord = builder.build();
        }
        // Với phiên bản Android SDK < 21
        else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            this.soundWord = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        // Sự kiện SoundPool đã tải lên bộ nhớ thành công.
        this.soundWord.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
                Log.d("hiendt","onLoadComplete");
                soundWord.play(sound, 1, 1, 0, 0, 1);
                Log.d("hiendt","played ");
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner spinner1=findViewById(R.id.spinner_DSBaiHoc);
        String[] stringArray = getResources().getStringArray(R.array.arrBaihoc_Kanji1);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,stringArray);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lessionID=position;
                kanji1List=new ArrayList<Kanji1>();
                SQLiteDataController sqLiteDataController=new SQLiteDataController(KanjiActivity.this);
                sqLiteDataController.open();
                SQLiteDatabase database=sqLiteDataController.getMyDatabase();

                kanji1List=sqLiteDataController.getbylessionID(position);
                for (Kanji1 kanji1 :kanji1List){}

                Kanji1_adapter kanji1_adapter=new Kanji1_adapter(KanjiActivity.this
                        ,R.layout.item_listview_kanji1,kanji1List, position);

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
        getMenuInflater().inflate(R.menu.main_action,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.playall:

                for (int i=0;i<mlistSound.size();i++){
                    sound = soundWord.load(this, mlistSound.get(i), 1);
                }
                Log.d("Hiendt","mlistSound"+mlistSound.size());

                break;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    public ArrayList getListSound(){
        for (int i=0;i<kanji1List.size();i++){
            int resourceId = getResources()
                    .getIdentifier(kanji1List.get(i).getSoundK(),
                            "raw", getPackageName());
            mlistSound.add(resourceId);
        }
        return mlistSound;
    }

    private void addcontrols() {
        lv_Kanji1=findViewById(R.id.lv_Kanji1);
        btn_GhepTu=findViewById(R.id.btn_GhepTu);
        btn_TracNghiem=findViewById(R.id.btn_TracNghiem);


        btn_TracNghiem.setOnClickListener(this);
        btn_GhepTu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_TracNghiem:
                Intent intent= new Intent(KanjiActivity.this,TracNghiemActivity.class);
                intent.putExtra("LESSION_ID",lessionID);
                startActivity(intent);
                break;
            case R.id.btn_GhepTu:
                Intent intent1= new Intent(KanjiActivity.this,GhepTuActivity.class);
                intent1.putExtra("LESSION_ID",lessionID);
                startActivity(intent1);
                break;
        }


    }
}
