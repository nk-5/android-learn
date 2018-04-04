package com.example.ke_nakagawa.servicestudy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private BoundService mBoundService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v("test", "onServiceConnected");
            mBoundService = ((BoundService.ServiceBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.v("test", "onServiceDisConnected");
            mBoundService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, StartedService.class);
        startService(intent);

        bindService(new Intent(MainActivity.this, BoundService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        Intent intent = new Intent(this, StartedService.class);
        stopService(intent);
        unbindService(mConnection);
        super.onStop();
    }
}
