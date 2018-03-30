package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var05;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

/**
 * Created by Bunica on 3/30/2018.
 */

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;
    private int suma = 0;
    private Random random = new Random();


    public ProcessingThread(Context context, int suma) {
        this.context = context;
        this.suma = suma;

    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread started");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped");

    }

    private void sendMessage()
    {
        Intent intent = new Intent();
        intent.setAction("Action");
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + suma);
    }



    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
