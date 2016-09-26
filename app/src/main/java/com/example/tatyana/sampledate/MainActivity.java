package com.example.tatyana.sampledate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    TextView textViewDate;
    Date currTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDate = (TextView) findViewById(R.id.textview_date);

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        Date currentLocalTime = cal.getTime();
        currTime = currentLocalTime;
        DateFormat date = new SimpleDateFormat("dd-MM-yyy HH:mm:ss z");
        date.setTimeZone(TimeZone.getDefault());
//        date.setTimeZone(TimeZone.getTimeZone("GMT"));
        String localTime = date.format(currentLocalTime);
        String message = "Current time: " + currentLocalTime + "\nGMT: " + localTime;
//        System.out.println(localTime);
        textViewDate.setText(message);

        String msg=getIntent().getStringExtra("Message");
        textViewDate.append("\nNew Timezone: "+msg);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private BroadcastReceiver broadcastReceiverChangeTimezone = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            setCurrentTime();
        }
    };

    public void setCurrentTime(){
        DateFormat date = new SimpleDateFormat("dd-MM-yyy HH:mm:ss z");
        date.setTimeZone(TimeZone.getDefault());
        String localTime = date.format(currTime);
        textViewDate.append("\n Local time: "+localTime);
    }
}
