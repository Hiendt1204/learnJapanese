package com.example.duongthuhien.kltn.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Model.Kanji1;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;
import com.example.duongthuhien.kltn.kanji.Kanji2Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 84973 on 6/8/2018.
 */

public class Kanji1_adapter extends ArrayAdapter{
    Activity context;
    int resource;
    @NonNull List<Kanji1> objects;

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

    private int lessionPosition;
    public Kanji1_adapter(@NonNull Activity context, int resource, @NonNull List objects, int lessionPosition) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
        this.lessionPosition = lessionPosition;
        // Đối tượng AudioManager sử dụng để điều chỉnh âm lượng.
        audioManager = (AudioManager)context. getSystemService(context.AUDIO_SERVICE);

        // Chỉ số âm lượng hiện tại của loại luồng nhạc cụ thể (streamType).
        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);


        // Chỉ số âm lượng tối đa của loại luồng nhạc cụ thể (streamType).
        float maxVolumeIndex = (float) audioManager.getStreamMaxVolume(streamType);

        // Âm lượng  (0 --> 1)
        this.volume = currentVolumeIndex / maxVolumeIndex;

        // Cho phép thay đổi âm lượng các luồng kiểu 'streamType' bằng các nút
        // điều khiển của phần cứng.
        context.setVolumeControlStream(streamType);

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



    }
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        final Button btn_Play=row.findViewById(R.id.btn_PlayK1);
        TextView tv_Sothutu=row.findViewById(R.id.tv_SoThuTu);
        TextView tv_JWord_K=row.findViewById(R.id.tv_JWord_K);
        TextView tv_VWord_K=row.findViewById(R.id.tv_VWord_K);
        TextView tv_On=row.findViewById(R.id.tv_On);
        TextView tv_Kun=row.findViewById(R.id.tv_Kun);
        ImageView tv_MoTa=row.findViewById(R.id.img_MoTa);
        TextView tv_MoTa_V=row.findViewById(R.id.tv_MoTa_V);
        ListView lv_Kanji1=row.findViewById(R.id.lv_Kanji1);
        final ImageView btn_FavoriteK1=row.findViewById(R.id.btn_FavoriteK1);

        if (objects.get(pos).getFavorite()==1){
            //btn_FavoriteK1.setImageResource(R.drawable.ic_favorite_border_red_24dp);
            btn_FavoriteK1.setColorFilter(context.getResources().getColor(R.color.colorAccent));
        } else {
            //btn_FavoriteK1.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            btn_FavoriteK1.setColorFilter(context.getResources().getColor(R.color.Black));
        }

        btn_FavoriteK1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDataController sqLiteDataController=new SQLiteDataController(context);
                 sqLiteDataController.open();
                SQLiteDatabase database=sqLiteDataController.getMyDatabase();

                if (objects.get(pos).getFavorite()==1){
                    sqLiteDataController.update0FavoriteKanji(objects.get(pos).getId());
                    objects.get(pos).setFavorite(0);
                    //btn_FavoriteK1.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    btn_FavoriteK1.setColorFilter(context.getResources().getColor(R.color.Black));
                }else if (objects.get(pos).getFavorite()==0){
                    sqLiteDataController.update1FavoriteKanji(objects.get(pos).getId());
                    objects.get(pos).setFavorite(1);
                    //btn_FavoriteK1.setImageResource(R.drawable.ic_favorite_border_red_24dp);
                    btn_FavoriteK1.setColorFilter(context.getResources().getColor(R.color.colorAccent));
                }
                sqLiteDataController.getFavourite();
            }
        });

        btn_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resourceId = context.getResources()
                        .getIdentifier(objects.get(pos).getSoundK(),
                                "raw", context.getPackageName());
                sound = soundWord.load(context, resourceId, 1);
            }
        });

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Kanji2Activity.class);
                intent.putExtra("PosK1",pos);
                intent.putExtra("ID",objects.get(pos).getStr_Sothutu());
                intent.putExtra("LessionPosition", Kanji1_adapter.this.lessionPosition);
                context.startActivity(intent);

            }
        });


        final Kanji1 kanji1=(Kanji1) this.objects.get(pos);


        tv_JWord_K.setText(Html.fromHtml(kanji1.getStr_JWord_K()));
        tv_Sothutu.setText(String.valueOf(pos +1));
        String vWord = kanji1.getStr_VWord_K();

        tv_VWord_K.setText(vWord.replaceAll(",", ", "));
        tv_On.setText(kanji1.getStr_On());
        tv_Kun.setText(kanji1.getStr_Kun());
        int resourceId = this.context.getResources().getIdentifier(kanji1.getStr_MoTa(),
                "drawable", this.context.getPackageName());
        Log.d("hiendt","kun " + kanji1.getStr_Kun());
        tv_MoTa.setImageResource(resourceId);
        tv_MoTa_V.setText(Html.fromHtml(kanji1.getStr_MoTa_V()));



        return row;
    }


}
