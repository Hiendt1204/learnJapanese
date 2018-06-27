package com.example.duongthuhien.kltn;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.duongthuhien.kltn.CaiDat.CaiDatActivity;
import com.example.duongthuhien.kltn.Model.Kanji1;
import com.example.duongthuhien.kltn.Model.Tumoi_Frag;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;

import java.util.Random;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by 84973 on 6/25/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {
    public void initChannels(Context context) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("default",
                "Channel name",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Channel description");
        notificationManager.createNotificationChannel(channel);
    }
    @TargetApi(Build.VERSION_CODES.N)
    public void sendNotification(Context context) {

        Notification.Builder builder = null;
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        int luachonHTD = pref.getInt(CaiDatActivity.PREF_LUA_CHON_HTD, 0);
        RemoteViews notificationLayout;
        Random rand = new Random();
        SQLiteDataController dbController = new SQLiteDataController(context);
        Log.d("hiendt","luachonHTD " + luachonHTD);
        if (luachonHTD == 0 || luachonHTD == 1) {
            notificationLayout = new RemoteViews(context.getPackageName(), R.layout.notification_vocabulary);
            int maxId = 260;
            Tumoi_Frag tumoi = null;
            do {
                int id = rand.nextInt(maxId);
                tumoi = dbController.getWordbyID_M(id);
            } while (tumoi == null);
            notificationLayout.setTextViewText(R.id.word, tumoi.getStrJWord_M());
            notificationLayout.setTextViewText(R.id.romaji, tumoi.getStrPhienAm_M());
            notificationLayout.setTextViewText(R.id.mean, tumoi.getStrVWord_M());
        } else { // luachonHTD == 2
            notificationLayout = new RemoteViews(context.getPackageName(), R.layout.notification_vocabulary);
            int maxId = 500;
            Kanji1 word = null;
            do {
                int id = rand.nextInt(maxId);
                word = dbController.getWord(id);
            } while (word == null);

            notificationLayout.setTextViewText(R.id.word, word.getStr_JWord_K());
            notificationLayout.setTextViewText(R.id.romaji, word.getStr_ronjomi());
            notificationLayout.setTextViewText(R.id.mean, word.getStr_VWord_K());
        }

        if (Build.VERSION.SDK_INT < 26) {
            builder = new Notification.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setStyle(new Notification.DecoratedCustomViewStyle())
                    .setCustomContentView(notificationLayout);
        } else {

            initChannels(context);
            builder = new Notification.Builder(context, "default")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setStyle(new Notification.DecoratedCustomViewStyle())
                            .setCustomContentView(notificationLayout);
        }
        //Get an instance of NotificationManager//


        NotificationManager notificationManager =(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("hiendt","onReceive " + intent.getAction());
        if ("com.example.duongthuhien.kltn.ALARM_RECEIVER".equals(intent.getAction())) {
            sendNotification(context);
        } else if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            int interval = 60000;
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            alarmIntent.setAction("com.example.duongthuhien.kltn.ALARM_RECEIVER");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0, alarmIntent, 0);
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            boolean luachonHTD = pref.getBoolean(CaiDatActivity.PREF_HIEN_HTD, false);
            if (luachonHTD){
                alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,  SystemClock.elapsedRealtime(), interval, pendingIntent);
            }
        }
    }
}
