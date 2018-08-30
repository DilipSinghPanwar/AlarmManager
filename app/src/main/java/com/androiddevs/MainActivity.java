package com.androiddevs;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText mEtInputSeconds, mEtInputTime, mEtInputDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnAlarmStart).setOnClickListener(this);
        findViewById(R.id.btnRepeatingAlarm).setOnClickListener(this);
        findViewById(R.id.btnAccToDateTime).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAlarmStart:
                alarmAsUserInputinSec();
                break;
            case R.id.btnRepeatingAlarm:
                startAtRepeatingAlarm();
                break;
            case R.id.btnAccToDateTime:
                startAtFutureDateTime();
                break;
            default:
                break;
        }
    }

    /*execute after specific seconds*/
    public void alarmAsUserInputinSec() {
        mEtInputSeconds = (EditText) findViewById(R.id.etInputTimeInSecond);
        if (mEtInputSeconds.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Enter Seconds", Toast.LENGTH_LONG).show();
        } else {
            int alarmInSecond = Integer.parseInt(mEtInputSeconds.getText().toString());
            Intent intent = new Intent(this, AlarmReceiver.class);
            intent.putExtra("keyOne", "This is one time alarm, executed after " + alarmInSecond + " seconds.");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 111, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (alarmInSecond * 1000), pendingIntent);
            Toast.makeText(this, "Alarm after " + alarmInSecond + " seconds.", Toast.LENGTH_LONG).show();
        }
    }

    /*excute after specific time interval (Repeating Alarm)*/
    public void startAtRepeatingAlarm() {
        mEtInputTime = (EditText) findViewById(R.id.etInputTime);
        if (mEtInputTime.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Enter Minutes", Toast.LENGTH_LONG).show();
        } else {
            int interval = Integer.parseInt(mEtInputTime.getText().toString()) * 60 * 1000;  // hour*minutes*seconds*milliSec
            Intent intent = new Intent(this, AlarmReceiver.class);
            intent.putExtra("keyTwo", "This is repeating alarm, execute after every " + interval + " milliseconds of interval.");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 222, intent, 0);
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            /* Set the alarm to start at 10:30 AM */
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
        /* Repeating on every input minutes interval */
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, pendingIntent);
            Toast.makeText(this, "Alarm execute in " + interval + " seconds interval.", Toast.LENGTH_LONG).show();
        }
    }

    public void startAtFutureDateTime() {
        mEtInputDateTime = (EditText) findViewById(R.id.etDateTime);
        int day = 6;  //1-31
        int month = Calendar.SEPTEMBER;  //first month is 0!!! January is zero!!!
        int year = 2017; //year...
        // Create a new calendar set to the date chosen
        // we set the time to midnight (i.e. the first minute of that day)
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        c.set(Calendar.HOUR_OF_DAY, 16);//HOUR 1-23
        c.set(Calendar.MINUTE, 29);   //MIN
        c.set(Calendar.SECOND, 0); //SEC
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("keyThree", "This is future Alarm, executed after specific time and date.");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 333, intent, 0);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        // Notify the user what they just did
        Toast.makeText(this, "Notification set for: " + day + "/" + (month + 1) + "/" + year + " and " + c.getTimeInMillis() + " milliseconds", Toast.LENGTH_SHORT).show();
    }
}
