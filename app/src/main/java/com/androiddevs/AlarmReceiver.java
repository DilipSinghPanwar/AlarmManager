package com.androiddevs;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = AlarmReceiver.class.getSimpleName();
    Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        if (intent.getExtras().containsKey("keyOne")) {
            Log.e(TAG, "AlarmReceiver Excuted Type: >>" + intent.getStringExtra("keyOne"));
            displayNotification(intent.getStringExtra("keyOne"));
        } else if (intent.getExtras().containsKey("keyTwo")) {
            Log.e(TAG, "AlarmReceiver Excuted Type: >>" + intent.getStringExtra("keyTwo"));
            displayNotification(intent.getStringExtra("keyTwo"));
        } else if (intent.getExtras().containsKey("keyThree")) {
            Log.e(TAG, "AlarmReceiver Excuted Type: >>" + intent.getStringExtra("keyThree"));
            displayNotification(intent.getStringExtra("keyThree"));
        }
    }

    public void displayNotification(String receivedValue) {
        Toast.makeText(mContext, "Alarm Excuted!", Toast.LENGTH_SHORT).show();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Alarm Action Executed")
                .setContentText(receivedValue)
                .setTicker(receivedValue)
                .setDefaults(Notification.DEFAULT_SOUND).setAutoCancel(true);
        mBuilder.setColor(Color.parseColor("#26ce61"));
        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationID = 100;
        mNotificationManager.notify(notificationID, mBuilder.build());
    }
}
