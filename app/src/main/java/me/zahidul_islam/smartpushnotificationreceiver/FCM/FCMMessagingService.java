package me.zahidul_islam.smartpushnotificationreceiver.FCM;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import me.zahidul_islam.smartpushnotificationreceiver.Activity.MainActivity;
import me.zahidul_islam.smartpushnotificationreceiver.R;
import me.zahidul_islam.smartpushnotificationreceiver.Receiver.FCMNotification;

/**
 * Created by SSL_ZAHID on 9/4/2016.
 */
public class FCMMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCMMessagingService";
    private static final int REQUEST_CODE_ACTIVITY = 360;
    private static final int REQUEST_CODE_NOTIFICATION = 370;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String message = remoteMessage.getData().get("all_data");

        try{
            /*If there anyone register the listener then this will execute*/
            FCMNotification.getInstance().onMessageNotificationListener.getMessage(message);
        }catch (Exception e){

            /*If no one register the listener then notification will show on notification bar*/
            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(this, REQUEST_CODE_ACTIVITY, intent, 0);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentTitle(getString(R.string.app_name))
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pIntent)
                    .setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(message));

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(REQUEST_CODE_NOTIFICATION, mBuilder.build());

        }

    }
}
