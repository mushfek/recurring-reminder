package com.mushfek.recurringreminder.util.reminder;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.Toast;
import com.mushfek.recurringreminder.R;
import com.mushfek.recurringreminder.activity.DataCollectionActivity;
import com.mushfek.recurringreminder.util.builder.NotificationBuilder;
import com.oneous.log4android.Logger;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mushfekur Rahman
 * @since 1.0
 */
public class ReminderBroadcastReceiver extends BroadcastReceiver {

    private static final Logger log = Logger.getLogger(ReminderBroadcastReceiver.class);

    @Override
    public void onReceive(Context context, Intent intent) {
        log.debug("onReceive: ");

        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "REC-REMINDER");

        wakeLock.acquire();

        Intent resultIntent = new Intent(context, DataCollectionActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, resultIntent, 0);

        Notification reminderNotification = NotificationBuilder.buildReminderNotification(context,
                "Ebola Vitals Entry",
                "Please enter your vitals to stay secure blah blah...",
                R.drawable.ic_reminder_notification,
                "From Ebola Tracker",
                pIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        reminderNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, reminderNotification);

        wakeLock.release();
    }

    public void setRecurringReminder(Context context) {
        log.debug("setRecurringReminder: ");

        Intent intent = new Intent(context, ReminderBroadcastReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 1, pIntent);
    }

    public void cancelReminder(Context context) {
        log.debug("cancelReminder: ");

        Intent intent = new Intent(context, ReminderBroadcastReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pIntent);
    }
}
