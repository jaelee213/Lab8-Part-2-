package com.jaelee213yahoo.user2;

import com.firebase.client.Firebase;

/**
 * Created by Jae on 5/18/16.
 */
public class FirebaseContext extends android.app.Application {

    public void onCreate()
    {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

}
