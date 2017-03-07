package cm.android.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        String s="methond";
        if(s.contains("sme")){
            Log.e("ggg", "me: ");
        }else if(s.contains("methond")){
            Log.e("ggg", "method: ");
        } else if(s.contains("hon")){
            Log.e("ggg", "hod: ");
        }else {
            Log.e("ggg", "fffff: ");
        }

        Intent intent1 = new Intent();
       intent1.setPackage(this.getPackageName());
        MainActivity.this.sendBroadcast(intent1);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                MainActivity.this.startService(intent);


            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                MainActivity.this.stopService(intent);
            }
        });

    }

}
