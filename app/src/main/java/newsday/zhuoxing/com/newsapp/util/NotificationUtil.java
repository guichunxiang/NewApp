package newsday.zhuoxing.com.newsapp.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import newsday.zhuoxing.com.newsapp.activity.MainActivity;

/**
 * Created by Administrator on 2016/9/13.
 */
public class NotificationUtil {
    public static NotificationManager notificationManager;
    public static final int NOTIFIID=1;
    public static void OpenNotufication(Context context) {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(android.support.design.R.drawable.notification_template_icon_bg)
                .setContentTitle(" ")
                .setContentIntent(pendingIntent)
                .setShowWhen(true)
                .setVibrate(new long[]{0,1000,1000,1000})
                .build();
        notificationManager.notify(NOTIFIID,notification);
    }
    public static void CloseNotification(Context context){
        if (notificationManager == null) {
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        notificationManager.cancel(NOTIFIID);
    }
}
