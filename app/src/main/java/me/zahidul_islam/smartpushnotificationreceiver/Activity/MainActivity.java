package me.zahidul_islam.smartpushnotificationreceiver.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import me.zahidul_islam.smartpushnotificationreceiver.R;
import me.zahidul_islam.smartpushnotificationreceiver.Receiver.FCMNotification;
import me.zahidul_islam.smartpushnotificationreceiver.Receiver.OnNotificationListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ArrayList<String> allMessage;
    private ListView messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /*Initialize the view*/
    private void init() {
        messageList = (ListView) findViewById(R.id.messageList);
    }


    @Override
    protected void onResume() {
        super.onResume();

        /*Make the list adapter where the message will show*/
        allMessage = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,allMessage);
        messageList.setAdapter(adapter);

        /*Register The Listener*/
        FCMNotification.getInstance().getMessageNotification(new OnNotificationListener() {
            @Override
            public void getMessage(final String message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        allMessage.add(message);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        /*UnRegister The Listener*/
        FCMNotification.getInstance().getMessageNotification(null);
    }
}
