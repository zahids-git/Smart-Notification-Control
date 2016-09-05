package me.zahidul_islam.smartpushnotificationreceiver.FCM;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import me.zahidul_islam.smartpushnotificationreceiver.R;
import me.zahidul_islam.smartpushnotificationreceiver.Receiver.FCMNotification;


public class FCMTokenRegistration extends FirebaseInstanceIdService {

    private static final String TAG = "FCMTokenRegistration";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();

        // Insert it in local storage
        try{
            SharedPreferences preferences = getApplicationContext().getSharedPreferences(getString(R.string.share_pref),MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(getString(R.string.token_key),token);
            editor.commit();
        }catch (Exception e){
            Log.e(TAG, "onTokenRefresh: "+e.getMessage() );
        }

        // Push the token to registered listener
        try {
            FCMNotification.getInstance().onTokenNotificationListener.getMessage(token);
        }catch (Exception e){
            Log.e(TAG, "onTokenRefresh: "+e.getMessage() );
        }
    }

}
