package me.zahidul_islam.smartpushnotificationreceiver.Receiver;

/**
 * Created by SSL_ZAHID on 9/4/2016.
 */
public class FCMNotification {

    public OnNotificationListener onTokenNotificationListener;
    public OnNotificationListener onMessageNotificationListener;

    private static FCMNotification ourInstance = new FCMNotification();

    public static FCMNotification getInstance() {
        return ourInstance;
    }

    private FCMNotification() {
    }

    /**
    * To get notification if the token will change. Note that : this listener will not notify every-time.
    * If the application will start for the first time and if the existing key replce with new key that
    * time this listener will execute.
    *
    * @param onTokenNotificationListener OnNotificationListener
    */
    public void getTokenNotification(OnNotificationListener onTokenNotificationListener){
        this.onTokenNotificationListener = onTokenNotificationListener;
    }

    public void getMessageNotification(OnNotificationListener onMessageNotificationListener){
        this.onMessageNotificationListener = onMessageNotificationListener;
    }

}
