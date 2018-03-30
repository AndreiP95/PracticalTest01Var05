package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var05;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var05Service extends Service {

    private ProcessingThread processingThread = null;

    public PracticalTest01Var05Service() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int suma = intent.getIntExtra("suma", -1);
        processingThread = new ProcessingThread(this, suma);
        processingThread.start();

        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }


}
