package com.example.ke_nakagawa.asynctasklearn;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static java.lang.Thread.sleep;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    HandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        crashAndExecuteAsyncTask();

//        runThreadHandler();
        runMultipleAsyncTask(); // Start Async Task

//        incrementAtomicInteger();
//        incrementIntegerAndSyncronized();
//        incrementAtomicIntegerAndSyncronized();
    }

    private void crashAndExecuteAsyncTask() {
        try {
            throw new NullPointerException();
        } catch (Exception e) {
            Log.i("NullPointerException" , e.toString());
            CrashAndAsyncTask task = new CrashAndAsyncTask();
//            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            task.execute();
            throw e;
        }
    }

    private class CrashAndAsyncTask extends AsyncTask<AtomicInteger, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            Log.i("AsyncTask" ,"crashTask");
        }
        @Override
        protected Void doInBackground(AtomicInteger... params)
        {
            for(int index = 0; index < 10000; index++)
            {
                Log.i("AsyncTask" ,"test" + String.valueOf(index));
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            Log.d("AsyncTask" ,"crashTask");
        }
    }

    private void incrementAtomicInteger() {
        IncrementAtomicIntAsyncTask task = new IncrementAtomicIntAsyncTask();
        IncrementAtomicIntAsyncTask2 task2 = new IncrementAtomicIntAsyncTask2();
        AtomicInteger atomicInt = new AtomicInteger();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, atomicInt);
        task2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, atomicInt);

        // result
        /*
        I/AsyncTask: IncrementAtomicIntOnPreExecute()
        I/AsyncTask: IncrementAtomicIntOnPreExecute2()
        I/AsyncTask: AtomicInteger = 1
        I/AsyncTask: AtomicInteger = 2
        I/AsyncTask: AtomicInteger = 3
        I/AsyncTask: AtomicInteger = 4
        I/AsyncTask: AtomicInteger = 6
        I/AsyncTask: AtomicInteger = 6
        I/AsyncTask: AtomicInteger = 7
        I/AsyncTask: AtomicInteger = 8
        I/AsyncTask: AtomicInteger = 9
        I/AsyncTask: AtomicInteger = 10
        I/AsyncTask: AtomicInteger = 12
        I/AsyncTask: AtomicInteger = 12
        I/AsyncTask: AtomicInteger = 13
        I/AsyncTask: AtomicInteger = 14
        I/AsyncTask: AtomicInteger = 15
        I/AsyncTask: AtomicInteger = 17
        I/AsyncTask: AtomicInteger = 16
        I/AsyncTask: AtomicInteger = 18
        I/AsyncTask: AtomicInteger = 19
        I/AsyncTask: AtomicInteger = 20
        D/AsyncTask: IncrementAtomicIntPostExecute()
        D/AsyncTask: IncrementAtomicIntPostExecute2()
         */
    }

    private void incrementIntegerAndSyncronized() {
        SyncronizedIncrementAsyncTask task = new SyncronizedIncrementAsyncTask();
        SyncronizedIncrementAsyncTask2 task2 = new SyncronizedIncrementAsyncTask2();
        Integer intNum = 0;
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, intNum);
        task2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, intNum);


        // result
        /*
        I/AsyncTask: SyncronizedIncrementOnPreExecute()
        I/AsyncTask: SyncronizedIncrementOnPreExecute2()
        I/AsyncTask: SyncronizedAtomicInteger = 1
        I/AsyncTask: SyncronizedAtomicInteger = 2
        I/AsyncTask: SyncronizedAtomicInteger = 3
        I/AsyncTask: SyncronizedAtomicInteger = 4
        I/AsyncTask: SyncronizedAtomicInteger = 5
        I/AsyncTask: SyncronizedAtomicInteger = 6
        I/AsyncTask: SyncronizedAtomicInteger = 7
        I/AsyncTask: SyncronizedAtomicInteger = 8
        I/AsyncTask: SyncronizedAtomicInteger = 9
        I/AsyncTask: SyncronizedAtomicInteger = 10
        I/AsyncTask: SyncronizedAtomicInteger = 1
        I/AsyncTask: SyncronizedAtomicInteger = 2
        I/AsyncTask: SyncronizedAtomicInteger = 3
        I/AsyncTask: SyncronizedAtomicInteger = 4
        I/AsyncTask: SyncronizedAtomicInteger = 5
        I/AsyncTask: SyncronizedAtomicInteger = 6
        I/AsyncTask: SyncronizedAtomicInteger = 7
        I/AsyncTask: SyncronizedAtomicInteger = 8
        I/AsyncTask: SyncronizedAtomicInteger = 9
        I/AsyncTask: SyncronizedAtomicInteger = 10
        D/AsyncTask: SyncronizedIncrementPostExecute2()
        D/AsyncTask: SyncronizedIncrementPostExecute()
         */
    }

    private void incrementAtomicIntegerAndSyncronized() {
        SyncronizedIncrementAtomicAsyncTask task = new SyncronizedIncrementAtomicAsyncTask();
        SyncronizedIncrementAtomicAsyncTask2 task2 = new SyncronizedIncrementAtomicAsyncTask2();
        AtomicInteger atomicInt = new AtomicInteger();
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, atomicInt);
        task2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, atomicInt);

        // result
        /*
        I/AsyncTask: IncrementAtomicIntOnPreExecute()
        I/AsyncTask: SyncronizedIncrementAtomicIntOnPreExecute2()
        I/AsyncTask: SyncronizedAtomicInteger = 1
        I/AsyncTask: SyncronizedAtomicInteger = 2
        I/AsyncTask: SyncronizedAtomicInteger = 3
        I/AsyncTask: SyncronizedAtomicInteger = 4
        I/AsyncTask: SyncronizedAtomicInteger = 5
        I/AsyncTask: SyncronizedAtomicInteger = 6
        I/AsyncTask: SyncronizedAtomicInteger = 7
        I/AsyncTask: SyncronizedAtomicInteger = 8
        I/AsyncTask: SyncronizedAtomicInteger = 9
        I/AsyncTask: SyncronizedAtomicInteger = 10
        I/AsyncTask: SyncronizedAtomicInteger = 11
        I/AsyncTask: SyncronizedAtomicInteger = 12
        I/AsyncTask: SyncronizedAtomicInteger = 13
        I/AsyncTask: SyncronizedAtomicInteger = 14
        I/AsyncTask: SyncronizedAtomicInteger = 15
        I/AsyncTask: SyncronizedAtomicInteger = 16
        I/AsyncTask: SyncronizedAtomicInteger = 17
        I/AsyncTask: SyncronizedAtomicInteger = 18
        I/AsyncTask: SyncronizedAtomicInteger = 19
        I/AsyncTask: SyncronizedAtomicInteger = 20
        D/AsyncTask: SyncronizedIncrementAtomicIntPostExecute2()
        D/AsyncTask: IncrementAtomicIntPostExecute()
         */
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

        for (int i = 0; i < 2; i++)
        {
            FirstAsyncTask asyncTask = new FirstAsyncTask(); // First
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

//        asyncTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
//        asyncTask.execute();

//        SecondAsyncTask asyncTask2 = new SecondAsyncTask(); // Second
//        asyncTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
            cancel(true);
        }
        @Override
        protected Void doInBackground(Void... params)
        {
//            for(int index = 0; index < 5; index++)
//            {
                Log.i("AsyncTask" ,"FirstAsyncTask");
//                try
//                {
//                    sleep(100);
//                }
//                catch (InterruptedException exception)
//                {
//                    exception.printStackTrace();
//                }
//            }
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

    private class IncrementAtomicIntAsyncTask extends AsyncTask<AtomicInteger, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            Log.i("AsyncTask" ,"IncrementAtomicIntOnPreExecute()");
        }
        @Override
        protected Void doInBackground(AtomicInteger... params)
        {
            for(int index = 0; index < 10; index++)
            {
                params[0].getAndIncrement();
                Log.i("AsyncTask" , "AtomicInteger = " + String.valueOf(params[0].get()));
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            Log.d("AsyncTask" ,"IncrementAtomicIntPostExecute()");
        }
    }

    private class IncrementAtomicIntAsyncTask2 extends AsyncTask<AtomicInteger, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            Log.i("AsyncTask" ,"IncrementAtomicIntOnPreExecute2()");
        }
        @Override
        protected Void doInBackground(AtomicInteger... params)
        {
            for(int index = 0; index < 10; index++)
            {
                params[0].getAndIncrement();
                Log.i("AsyncTask" , "AtomicInteger = " + String.valueOf(params[0].get()));
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            Log.d("AsyncTask" ,"IncrementAtomicIntPostExecute2()");
        }
    }

    /***************************************************************************/

    private class SyncronizedIncrementAsyncTask extends AsyncTask<Integer, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            Log.i("AsyncTask" ,"SyncronizedIncrementOnPreExecute()");
        }
        @Override
        protected Void doInBackground(Integer... params)
        {
            increment(params[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            Log.d("AsyncTask" ,"SyncronizedIncrementPostExecute()");
        }
    }

    private class SyncronizedIncrementAsyncTask2 extends AsyncTask<Integer, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            Log.i("AsyncTask" ,"SyncronizedIncrementOnPreExecute2()");
        }
        @Override
        protected Void doInBackground(Integer... params)
        {
            increment(params[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            Log.d("AsyncTask" ,"SyncronizedIncrementPostExecute2()");
        }
    }

    /***************************************************************************/

    private class SyncronizedIncrementAtomicAsyncTask extends AsyncTask<AtomicInteger, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            Log.i("AsyncTask" ,"IncrementAtomicIntOnPreExecute()");
        }
        @Override
        protected Void doInBackground(AtomicInteger... params)
        {
            incrementAtomic(params[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            Log.d("AsyncTask" ,"IncrementAtomicIntPostExecute()");
        }
    }

    private class SyncronizedIncrementAtomicAsyncTask2 extends AsyncTask<AtomicInteger, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            Log.i("AsyncTask" ,"SyncronizedIncrementAtomicIntOnPreExecute2()");
        }
        @Override
        protected Void doInBackground(AtomicInteger... params)
        {
            incrementAtomic(params[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            Log.d("AsyncTask" ,"SyncronizedIncrementAtomicIntPostExecute2()");
        }
    }

    synchronized void increment(int num) {
        for(int index = 0; index < 10; index++)
        {
            num++;
            Log.i("AsyncTask" , "SyncronizedAtomicInteger = " + String.valueOf(num));
        }
    }

    synchronized void incrementAtomic(AtomicInteger num) {
        for(int index = 0; index < 10; index++)
        {
            num.getAndIncrement();
            Log.i("AsyncTask" , "SyncronizedAtomicInteger = " + String.valueOf(num.get()));
        }
    }
}
