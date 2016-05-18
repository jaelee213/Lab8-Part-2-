package com.jaelee213yahoo.user2;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.media.UnsupportedSchemeException;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Jae on 5/18/16.
 */
public class DemoService extends IntentService {

    Firebase mref;

    public DemoService() {
        super("worker");
    }

    @Override
    public int onStartCommand( Intent intent, int flags, int startId ){

        //read from firebase
        mref = new Firebase("https://lab8-110.firebaseio.com/second");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                Toast.makeText(getBaseContext(), data, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind( Intent intent ) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //create an array of string with words "hello" "from" "user" "1"
        String[] array = new String[4];
        array[0] = "hello";
        array[1] = "from";
        array[2] = "user";
        array[3] = "2";

        //write to firebase
        mref = new Firebase("https://lab8-110.firebaseio.com/first");

        for ( int x = 0; x < array.length; x++ ) {

            if (intent != null) {
                synchronized (this) {
                    try {
                        wait(4000);

                        mref.setValue(array[x]);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopService(intent);
            }

        }//end of for loop
    }

}
