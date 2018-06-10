package com.example.duongthuhien.kltn.MCCB;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.duongthuhien.kltn.Adapter.Detail_MCCB_Adapter;
import com.example.duongthuhien.kltn.Model.NewWordMCCB;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;

import java.util.ArrayList;
import java.util.List;

public class Detail_MCCB_Activity extends AppCompatActivity  {
    private static final String TAG ="Detail_MCCB_Activity";
    Button btn_PlaySound;
    ListView ll_lvMCCB;
    List<NewWordMCCB> detail_mccbslist;
    Detail_MCCB_Adapter detail_mccb_adapter;
    int position=0;
    int pos2=0;
    SoundPool soundWord;
    AudioManager audioManager;
    ArrayList<String> mlistSound=new ArrayList<String>();

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
        setContentView(R.layout.activity_detail__mccb_);
        Intent intent = getIntent();
        position=intent.getIntExtra("POS",-1);
        addControl();

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
        this.setVolumeControlStream(streamType);

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


        ll_lvMCCB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                int resourceId = getResources()
                        .getIdentifier(detail_mccbslist.get(pos).getSound_NewWord() + "_f",
                                "raw", getPackageName());
                Log.d("hiendt","onItemClick "+resourceId);
                sound = soundWord.load(Detail_MCCB_Activity.this, resourceId, 1);
            }
        });
    }

    private void addControl() {
//        btn_PlaySound.setOnClickListener(this);
        ll_lvMCCB=findViewById(R.id.lv_detailMCCB);

        detail_mccbslist=new ArrayList<NewWordMCCB>();

        SQLiteDataController sqLiteDataController=new SQLiteDataController(this);
        sqLiteDataController.open();
        SQLiteDatabase database=sqLiteDataController.getMyDatabase();

        detail_mccbslist=sqLiteDataController.getbyCatagoryID(position);
        for(NewWordMCCB word : detail_mccbslist){
        }

        Detail_MCCB_Adapter detail_mccb_adapter=new Detail_MCCB_Adapter
                (Detail_MCCB_Activity.this, R.layout.item_detail_mccb, detail_mccbslist);
        ll_lvMCCB.setAdapter(detail_mccb_adapter);
    }


}
