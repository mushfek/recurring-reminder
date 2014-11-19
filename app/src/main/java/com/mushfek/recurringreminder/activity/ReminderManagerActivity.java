package com.mushfek.recurringreminder.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import com.mushfek.recurringreminder.R;
import com.mushfek.recurringreminder.util.reminder.ReminderBroadcastReceiver;
import com.oneous.log4android.Logger;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mushfekur Rahman
 * @since 1.0
 */
public class ReminderManagerActivity extends Activity {

    private static final Logger log = Logger.getLogger(ReminderManagerActivity.class);

    private ReminderBroadcastReceiver reminderReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        log.debug("onCreate: ");

        setContentView(R.layout.reminder_manager_layout);
        reminderReceiver = new ReminderBroadcastReceiver();

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        log.debug("onStart: ");

        super.onStart();
    }

    public void startTimer(View view) {
        log.debug("startTimer: ");

        Context context = this.getApplicationContext();
        if (reminderReceiver != null) {
            reminderReceiver.setRecurringReminder(context);

            StringBuilder sb = new StringBuilder();
            sb.append("Timer started at: ");
            Format formatter = new SimpleDateFormat("hh:mm:ss a");
            sb.append(formatter.format(new Date()));

            Toast.makeText(context, sb, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "ReminderReceiver is null", Toast.LENGTH_LONG).show();
        }
    }

    public void stopTimer(View view) {
        log.debug("stopTimer: ");

        Context context = this.getApplicationContext();
        if (reminderReceiver != null) {
            reminderReceiver.cancelReminder(context);
        } else {
            Toast.makeText(context, "ReminderReceiver is null", Toast.LENGTH_LONG).show();
        }
    }
}
