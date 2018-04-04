package com.example.ke_nakagawa.servicestudy;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyIntentService extends IntentService {
    public static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        this(MyIntentService.class.getSimpleName());
    }

    public MyIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "onBind");
        return super.onBind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onHandleIntent(Intent intent) {
        Log.v(TAG, "onHandleIntent");
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
    }

}
