package com.example.ke_nakagawa.asynctasklearn;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    HandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i=0; i < 5; i++) {
            runThreadHandler();
        }
//        runThreadHandler();
//        runMultipleAsyncTask(); // Start Async Task
    }

    private void runThreadHandler() {
        if (handlerThread == null) {
            handlerThread = new HandlerThread("KeigoThread");
            handlerThread.start();
        }

        Handler handler = new Handler(handlerThread.getLooper());

        // postDelayedだと直列実行が担保されない
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("keigo is cool");
//                System.out.println(Thread.currentThread().getName());
//            }
//        }, 2000);

        // postだと直列が担保される
        handler.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("keigo is cool");
                System.out.println(Thread.currentThread().getName());
            }
        });
    }

    private void runMultipleAsyncTask() // Run Multiple Async Task
    {
        FirstAsyncTask asyncTask = new FirstAsyncTask(); // First
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        asyncTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
//        asyncTask.execute();

        SecondAsyncTask asyncTask2 = new SecondAsyncTask(); // Second
        asyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        asyncTask2.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
//        asyncTask2.execute();


        /**
         * 1.execute & 2.execute -> 直列
         * 別のAsyncTaskクラスであっても, 1の実行が完了してから2が実行される
         *
         * 1.executeOnExecutor & 2.executeOnExecutor (SERIAL_EXECUTOR) -> 直列
         * 別のAsyncTaskクラスであっても, 1の実行が完了してから2が実行される
         *
         * 1.executeOnExecutor & 2.executeOnExecutor (THREAD_POOL_EXECUTOR) -> 並列
         * 1の実行と並列で2が実行される
         */
    }
    //Start First Async Task:
    private class FirstAsyncTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            Log.i("AsyncTask" ,"FirstOnPreExecute()");
        }
        @Override
        protected Void doInBackground(Void... params)
        {
            for(int index = 0; index < 5; index++)
            {
                Log.i("AsyncTask" ,"FirstAsyncTask");
                try
                {
                    sleep(100);
                }
                catch (InterruptedException exception)
                {
                    exception.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            Log.d("AsyncTask" ,"FirstonPostExecute()");
        }
    }
    //Start Second Async Task:
    private class SecondAsyncTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            Log.i("AsyncTask" ,"SecondOnPreExecute()");
        }
        @Override
        protected Void doInBackground(Void... params)
        {
            for(int index = 0; index < 5; index++)
            {
                Log.d("AsyncTask" ,"SecondAsyncTask");
                try
                {
                    sleep(100);
                }
                catch (InterruptedException exception)
                {
                    exception.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            Log.d("AsyncTask" ,"SecondOnPostExecute()");
        }
    }
}
