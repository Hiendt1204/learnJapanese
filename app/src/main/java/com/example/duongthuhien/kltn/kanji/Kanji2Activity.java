package com.example.duongthuhien.kltn.kanji;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Model.Kanji1;
import com.example.duongthuhien.kltn.Model.KanjiExample;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;

import java.util.ArrayList;
import java.util.List;

public class Kanji2Activity extends AppCompatActivity implements View.OnClickListener {

    List<Kanji1> kanjiList;
    LinearLayout exampleContainer;
    Button btn_playK2;
    TextView tv_Jword_K2;
    ImageView img_MoTa_K2;
    TextView tv_MoTa_K2;
    TextView tv_AmHan_K2;
    TextView tv_Nghia_K2;
    TextView tv_onyomi_K2;
    TextView tv_kunyomi_K2;
    TextView tv_Ronyomi_K2;
    TextView tv_Rkunyomi_K2;

    SoundPool soundWord;
    AudioManager audioManager;
    int pos1;


    ArrayList<String> mlistSound=new ArrayList<String>();

    // Số luồng âm thanh phát ra tối đa.
    private static final int MAX_STREAMS = 5;

    // Chọn loại luồng âm thanh để phát nhạc.
    private static final int streamType = AudioManager.STREAM_MUSIC;

    private boolean loaded;

    private int sound;
    private float volume;


    int lessionPosition;
    int wordPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Đối tượng AudioManager sử dụng để điều chỉnh âm lượng.
        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);

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

        addControls();

        Intent intent = getIntent();
        lessionPosition = intent.getIntExtra("LessionPosition", -1);
        wordPosition = intent.getIntExtra("PosK1", -1);
        exampleContainer = (LinearLayout) findViewById(R.id.exampleContainer);
        SQLiteDataController sqLiteDataController=new SQLiteDataController(Kanji2Activity.this);
        sqLiteDataController.open();
        kanjiList=sqLiteDataController.getbylessionID(lessionPosition);
        addExampleRows();

        tv_Jword_K2.setText(kanjiList.get(wordPosition).getStr_JWord_K());
        tv_MoTa_K2.setText(kanjiList.get(wordPosition).getStr_MoTa_V());
        tv_AmHan_K2.setText(kanjiList.get(wordPosition).getStr_JWord_K());
        tv_Nghia_K2.setText(kanjiList.get(wordPosition).getStr_VWord_K());
        tv_onyomi_K2.setText(kanjiList.get(wordPosition).getStr_On());
        tv_kunyomi_K2.setText(kanjiList.get(wordPosition).getStr_Kun());
        tv_Ronyomi_K2.setText(kanjiList.get(wordPosition).getStr_ronjomi());
        tv_Rkunyomi_K2.setText(kanjiList.get(wordPosition).getStr_rkunjomi());

        int resourceId = getResources().getIdentifier(kanjiList.get(wordPosition).getStr_MoTa(),
                "drawable", getPackageName());
        img_MoTa_K2.setImageResource(resourceId);

    }

    private void addControls() {
         btn_playK2=findViewById(R.id.btn_playK2);
         tv_Jword_K2=findViewById(R.id.tv_Jword_K2);
         img_MoTa_K2=findViewById(R.id.img_MoTa_K2);
         tv_MoTa_K2=findViewById(R.id.tv_MoTa_K2);
         tv_AmHan_K2=findViewById(R.id.tv_AmHan_K2);
         tv_Nghia_K2=findViewById(R.id.tv_Nghia_K2);
         tv_onyomi_K2=findViewById(R.id.tv_onyomi_K2);
         tv_kunyomi_K2=findViewById(R.id.tv_kunyomi_K2);
         tv_Ronyomi_K2=findViewById(R.id.tv_Ronyomi_K2);
         tv_Rkunyomi_K2=findViewById(R.id.tv_Rkunyomi_K2);

         btn_playK2.setOnClickListener(this);
    }

    private void addExampleRows() {
        exampleContainer.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);
        Kanji1 word = kanjiList.get(wordPosition);
        ArrayList<KanjiExample> exampleList = word.getExampleList();
        for (KanjiExample example : exampleList) {
            View exampleRow = inflater.inflate(R.layout.item_kanji_example, null);
            TextView cot1 = (TextView)exampleRow.findViewById(R.id.cot1);
            cot1.setText(example.getCot1());
            TextView cot2 = (TextView)exampleRow.findViewById(R.id.cot2);
            cot2.setText(example.getCot2());
            TextView cot3 = (TextView)exampleRow.findViewById(R.id.cot3);
            cot3.setText(example.getCot3());

            exampleContainer.addView(exampleRow);
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
        int resourceId = getResources()
                .getIdentifier(kanjiList.get(wordPosition).getSoundK(),
                        "raw", getPackageName());
        sound = soundWord.load(this, resourceId, 1);
        Log.d("hiendt","onItemClick "+resourceId);

    }
}
