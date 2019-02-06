package com.example.shrinidhikr.moneysaver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Intent notificationintent=new Intent(context,login_activity.class);
        TaskStackBuilder stackBuilder=TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationintent);
        PendingIntent pendingIntent=stackBuilder.getPendingIntent(100,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
        Notification notification=builder.setContentTitle("Notification from MoneySaver")
        .setContentText("Check your balance!")
         .setTicker("New message alert!")
         .setAutoCancel(true)
          .setSmallIcon(R.mipmap.money2_ic)
           .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification);

    }
}
