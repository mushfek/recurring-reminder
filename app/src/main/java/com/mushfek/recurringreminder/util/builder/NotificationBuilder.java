package com.mushfek.recurringreminder.util.builder;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;

/**
 * @author Mushfekur Rahman
 * @since 1.0
 */
public class NotificationBuilder {
    public static Notification buildReminderNotification(Context context, String contentTitle,
                                                         String contentText, int iconId, String tickerText,
                                                         PendingIntent receiverIntent) {
        return new Notification.Builder(context)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setContentIntent(receiverIntent)
                .setSmallIcon(iconId)
                .setTicker(tickerText).getNotification();
    }
}
