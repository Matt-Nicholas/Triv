package com.example.guest.triv;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.example.guest.triv.ui.MainActivity;

import java.util.Random;


/**
 * Created by foste on 2/12/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    String messageTitle = "We've got questions.";
    String messageContent = "You'v got answers!";




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MATT ", " onReceive");
        Random randomGenerator = new Random();
        Calendar now = GregorianCalendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int min = now.get(Calendar.MINUTE);
        if(day%5 == 0 && hour == 12 && min == 0) {
//            if(min%2 == 0) {
            int randomInt = randomGenerator.nextInt(3);
            setNotificationMessage(randomInt);

            Log.d("MATT ", " onReceive % 2");
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.coin_icon)
                            .setContentTitle(messageTitle)
                            .setContentText(messageContent)
                            .setAutoCancel(true);
            Intent resultIntent = new Intent(context, MainActivity.class);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());
        }
    }

    private void setNotificationMessage(int i){
        switch(i){
            case 0:
                messageTitle = "We've got questions.";
                messageContent = "You'v got answers!";
                break;
            case 1:
                messageTitle = "So many questions...";
                messageContent = "So little time...";
                break;
        }
    }
}
