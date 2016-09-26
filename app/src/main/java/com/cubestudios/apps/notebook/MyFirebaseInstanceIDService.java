package com.cubestudios.apps.notebook;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;

/**
 * Created by Rajesh-Rk on 26-Sep-16.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Token", "Refreshed token: " + refreshedToken);
    }
}
