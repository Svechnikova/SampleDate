package com.example.tatyana.sampledate;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String TAG = MainActivity.class.getName();

    TextView textViewDate;
    String currTime;
    TimeZoneChangeListener mTimeZoneChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDate = (TextView) findViewById(R.id.textview_date);

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat(DATE_FORMAT); //"dd-MM-yyy HH:mm:ss z"
        date.setTimeZone(TimeZone.getTimeZone("GMT"));
        String timeInGMT = date.format(currentLocalTime);
        currTime = timeInGMT;
        String message = "Time in GMT: " + timeInGMT;
        textViewDate.setText(message);

        mTimeZoneChangeListener = new TimeZoneChangeListener(getApplicationContext()) {
            @Override
            void onTimeZoneChanged(String timezone) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Time zone changed: " + timezone, Toast.LENGTH_SHORT);
                toast.show();

                setCurrentTime();

            }
        };
        mTimeZoneChangeListener.register();

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

    @Override
    protected void onDestroy() {
        mTimeZoneChangeListener.unregister();
        mTimeZoneChangeListener = null;
        super.onDestroy();
    }

    public void setCurrentTime(){
        DateFormat date = new SimpleDateFormat(DATE_FORMAT);
        date.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date localTime = null;
        try {
            localTime = date.parse(currTime);
        } catch (ParseException e) {
            Log.e(TAG, "Could not convert date: [" + currTime + "]");
            e.printStackTrace();
        }
        textViewDate.append("\n Local time: "+localTime);
    }
}
