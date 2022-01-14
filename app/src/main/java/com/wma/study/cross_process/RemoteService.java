package com.wma.study.cross_process;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.wma.study.LogUtil;

public class RemoteService extends Service {

    TestPerson.TestReceiver receiver;

    public static void start(Context context) {
        Intent intent = new Intent(context, RemoteService.class);
        context.startService(intent);
    }



    public RemoteService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("TAG_WMA", "onCreate: ");
        receiver = new TestPerson.TestReceiver();
        IntentFilter filter = new IntentFilter(TestPerson.ACTION_TEST);
        registerReceiver(receiver, filter);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d("TAG_WMA", "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}