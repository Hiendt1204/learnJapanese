package com.example.duongthuhien.kltn.ChuCai;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Adapter.Gridview_adapter;
import com.example.duongthuhien.kltn.Model.Word;
import com.example.duongthuhien.kltn.R;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class ChuCaiActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_Hiragana;
    Button btn_Katakana;
    FloatingActionButton btn_DSBH;

    TextView tv_VwordDetail;
    TextView tv_JwordDetail;
    int trangthaibtn;
    int mtrangthaibtn;
    Button btn_Close;
    LinearLayout ll_WordDetail;
    GridView gv_Word;
    Gridview_adapter mGridview_adapter;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_cai);

        addControls();
        trangthaibtn = 1;
        mListWord = getWordList(trangthaibtn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Đối tượng AudioManager sử dụng để điều chỉnh âm lượng.
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Chỉ số âm lượng hiện tại của loại luồng nhạc cụ thể (streamType).
        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);


        // Chỉ số âm lượng tối đa của loại luồng nhạc cụ thể (streamType).
        final float maxVolumeIndex = (float) audioManager.getStreamMaxVolume(streamType);

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
        mGridview_adapter = new Gridview_adapter(ChuCaiActivity.this, mListWord);
        gv_Word.setAdapter(mGridview_adapter);

        gv_Word.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ll_WordDetail.setVisibility(View.VISIBLE);
                // Tải file âm thanh vào SoundPool.
                sound = soundWord.load(ChuCaiActivity.this, getResources().getIdentifier
                        (mlistSound.get(i), "raw", getPackageName()), 1);
                tv_JwordDetail.setText(mListWord.get(i).getJword());
                tv_VwordDetail.setText(mListWord.get(i).getVword());
            }
        }));



    }

    private void addControls() {
        btn_Hiragana=findViewById(R.id.btn_Hiragana);
        btn_Katakana=findViewById(R.id.btn_Katakana);
        gv_Word = findViewById(R.id.gv_Word);
        btn_PlayWord = findViewById(R.id.btn_PlayWord);
        ll_WordDetail = findViewById(R.id.ll_WordDetail);
        btn_Close = findViewById(R.id.btn_Close);
        tv_JwordDetail=findViewById(R.id.tv_JwordDetail);
        tv_VwordDetail=findViewById(R.id.tv_VwordDetail);
        btn_DSBH=findViewById(R.id.btn_DSBH);

        btn_PlayWord.setOnClickListener(this);
        btn_Close.setOnClickListener(this);
        btn_DSBH.setOnClickListener(this);

        btn_Katakana.setOnClickListener(this);
        btn_Hiragana.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Hiragana:
                mtrangthaibtn=2;
                getWordList(2);
                mGridview_adapter.notifyDataSetChanged();
                break;
            case R.id.btn_Katakana:
                mtrangthaibtn=1;
                getWordList(1);
                mGridview_adapter.notifyDataSetChanged();
                break;
            case R.id.btn_Close:
                ll_WordDetail.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_PlayWord:
                if (loaded) {
                    float leftVolumn = volume;
                    float rightVolumn = volume;
                    // Phát âm thanh
                    int streamId = this.soundWord.play(this.sound, leftVolumn,
                            rightVolumn, 1, 0, 1f);
                }
                break;
            case R.id.btn_DSBH:
                Intent intent=new Intent(ChuCaiActivity.this,DSBHActivity.class);
                intent.putExtra("trangthaibtn",mtrangthaibtn);
                startActivityForResult(intent,3);
                break;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public ArrayList getListSound(){
        String[] sound=getResources().getStringArray(R.array.kana_raw);
        for (int i=0;i<sound.length;i++){
            mlistSound.add(sound[i]);
        }

        return mlistSound;
    }

    public ArrayList getWordList(int trangthaibtn) {
        mListWord.clear();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }
        return super.onOptionsItemSelected(item);
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
