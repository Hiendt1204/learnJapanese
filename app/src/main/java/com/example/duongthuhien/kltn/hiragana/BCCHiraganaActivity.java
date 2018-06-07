package com.example.duongthuhien.kltn.hiragana;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.NoCopySpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Adapter.Gridview_adapter;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.Word;

import java.util.ArrayList;

/**
 * Created by Duong Thu Hien on 5/14/2018.
 */

public class BCCHiraganaActivity extends Activity implements View.OnClickListener {
    TextView tv_VwordDetail;
    TextView tv_JwordDetail;
    int trangthaibtn;
    Button btn_Close;
    LinearLayout ll_WordDetail;
    GridView gv_Word;
    Button btn_PlayWord;
    ArrayList<Word> mListWord = new ArrayList<>();
    SoundPool soundWord;
    AudioManager audioManager;

    ArrayList<String> mlistSound=new ArrayList<>();
    // Số luồng âm thanh phát ra tối đa.
    private static final int MAX_STREAMS = 5;

    // Chọn loại luồng âm thanh để phát nhạc.
    private static final int streamType = AudioManager.STREAM_MUSIC;

    private boolean loaded;

    private int sound;
    private float volume;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcchiragana);
        Intent intent=getIntent();
        trangthaibtn=intent.getIntExtra("A",-1);
        Log.d("hiendt","HiraganaActivity trangthaibtn " + trangthaibtn);
        mListWord = getWordList(trangthaibtn);
        addControls();

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
            }
        });

        // Tải file nhạc tiếng vật thể bị phá hủy (destroy.war) vào SoundPool.
        //this.soundIdDestroy = this.soundPool.load(this, R.raw.accommodation1, 1);


        getListSound();
        Gridview_adapter gridview_adapter = new Gridview_adapter(BCCHiraganaActivity.this, mListWord);
        gv_Word.setAdapter(gridview_adapter);

        gv_Word.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ll_WordDetail.setVisibility(View.VISIBLE);
                // Tải file âm thanh vào SoundPool.
                sound = soundWord.load(BCCHiraganaActivity.this, getResources().getIdentifier(mlistSound.get(i), "raw", getPackageName()), 1);
                tv_JwordDetail.setText(mListWord.get(i).getJword());
                tv_VwordDetail.setText(mListWord.get(i).getVword());
            }
        }));


    }

    private void addControls() {
        gv_Word = findViewById(R.id.gv_Word);
        btn_PlayWord = findViewById(R.id.btn_PlayWord);
        ll_WordDetail = findViewById(R.id.ll_WordDetail);
        btn_Close = findViewById(R.id.btn_Close);
        tv_JwordDetail=findViewById(R.id.tv_JwordDetail);
        tv_VwordDetail=findViewById(R.id.tv_VwordDetail);

        btn_PlayWord.setOnClickListener(this);
        btn_Close.setOnClickListener(this);
    }
    public ArrayList getListSound(){
        String[] sound=getResources().getStringArray(R.array.kana_raw);
        for (int i=0;i<sound.length;i++){
            mlistSound.add(sound[i]);
        }
        return mlistSound;
    }

    public ArrayList getWordList(int trangthaibtn) {
        if (trangthaibtn==2){
            String[] Tiengviet = getResources().getStringArray(R.array.roomaji);
            String[] hiragana = getResources().getStringArray(R.array.hiragana);
            for (int i = 0; i < Tiengviet.length; i++) {
                Word word = new Word();
                word.setId(i);
                word.setJword(hiragana[i]);
                word.setVword(Tiengviet[i]);
                mListWord.add(word);
            }
        }
        else if (trangthaibtn==1){
            String[] Tiengviet = getResources().getStringArray(R.array.roomaji_k);
            String[] hiragana = getResources().getStringArray(R.array.katakana);
            for (int i = 0; i < Tiengviet.length; i++) {
                Word word = new Word();
                word.setId(i);
                word.setJword(hiragana[i]);
                word.setVword(Tiengviet[i]);
                mListWord.add(word);
            }

        }
        return mListWord;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_Close:
                ll_WordDetail.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_PlayWord:
                if (loaded) {
                    float leftVolumn = volume;
                    float rightVolumn = volume;
                    // Phát âm thanh
                    int streamId = this.soundWord.play(this.sound, leftVolumn, rightVolumn, 1, 0, 1f);
                }
                break;
        }

    }

    private void hidePopup() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ll_WordDetail.setVisibility(View.INVISIBLE);
            }
        }, 1000);
    }
}
