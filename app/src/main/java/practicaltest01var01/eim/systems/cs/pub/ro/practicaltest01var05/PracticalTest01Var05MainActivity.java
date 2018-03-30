package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    static String value = null;
    int suma;

    Button button;
    Button compute;
    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);

        compute = (Button) findViewById(R.id.BTNCompute);
        button = (Button) findViewById(R.id.BTNAdd);
        textView = (TextView) findViewById(R.id.TV1);
        editText = (EditText) findViewById(R.id.ETNumber);

        savedInstanceState = getIntent().getBundleExtra("savedData");

        Intent intent = null;

        try {
            if (savedInstanceState.containsKey("suma"))
                suma = savedInstanceState.getInt("suma");
            if (suma > 10) {
                intent = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
                intent.putExtra("suma", suma);
                getApplicationContext().startService(intent);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textView.getText().toString();
                String toAdd = editText.getText().toString();
                if (text.compareToIgnoreCase("") == 0)
                    text = text + toAdd;
                else
                    text = text + "+" + toAdd;
                textView.setText(text);
                editText.setText("");

            }
        });

        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();

                if (textView.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Suma este " + suma + " ", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    String text = textView.getText().toString();
                    bundle.putString("textview", text);

                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);
                    intent.putExtra("savedData", bundle);
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("suma", suma);

    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("suma")) {
            int text = savedInstanceState.getInt("suma");
            Toast.makeText(getApplicationContext(), "Suma este " + text + " ", Toast.LENGTH_SHORT).show();
        } else {
            textView.setText(String.valueOf(""));
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var05Service.class);
        stopService(intent);
        super.onDestroy();
    }
}
