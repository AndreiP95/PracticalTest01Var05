package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {

    Button button;

    private IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

         savedInstanceState = getIntent().getBundleExtra("savedData");
         final Bundle bundle = savedInstanceState;
        String text = savedInstanceState.getString("textview",null);
        String val [] = text.split("\\+");
        int sum = 0;
        try {
            for (int i = 0; i < val.length; i++)
                sum = sum + Integer.parseInt(val[i]);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        final int valoare = sum;
        button = (Button) findViewById(R.id.BTNReturn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putInt("suma",valoare);
                Toast.makeText(getApplicationContext(), "Suma este " + valoare,  Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05MainActivity.class);
                intent.putExtra("savedData", bundle);
                startActivity(intent);

            }
        });


    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
