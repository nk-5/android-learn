package com.example.ke_nakagawa.servicestudy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class StartedService extends Service {
    public static final String TAG = StartedService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestory");
        super.onDestroy();
    }
}
