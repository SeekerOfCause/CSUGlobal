package mg.utils.notify;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class NotificationHelper {

  public static Notification createNotification(Context context, String channelId, String category) {
    if (Build.VERSION.SDK_INT >= 26) {
      return new Notification.Builder(context, channelId)
        .setCategory(category)
        .build();
    } else if (Build.VERSION.SDK_INT >= 11) {
      return new Notification.Builder(context).getNotification();
    } else {
      return new Notification();
    }
  }

  public static NotificationChannel setupNotificationChannel(Context context, String channelId, CharSequence name, String description, int importance) {
    // Create Notification channel
    if (Build.VERSION.SDK_INT >= 26) {
      NotificationChannel channel = new NotificationChannel(channelId, name, importance);
      channel.setDescription(description);
      // Register the channel with the system; you can't change the importance
      // or other notification behaviors after this
      NotificationManager notificationManager = getNotificationManager(context);
      notificationManager.createNotificationChannel(channel);
      return channel;
    }
    return null;
  }

  public static NotificationManager getNotificationManager(Context context) {
    return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
  }

  public static void send(Context context, int id, Notification notification) {
    send(getNotificationManager(context), id, notification);
  }

  public static void send(NotificationManager notificationManager, int id, Notification notification) {
    notificationManager.notify(id, notification);
  }

  public static void unsend(Context context, int id) {
    unsend(getNotificationManager(context), id);
  }

  public static void unsend(NotificationManager notificationManager, int id) {
    notificationManager.cancel(id);
  }

  public static void setText(Notification notification, String msg) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      notification.extras.putCharSequence(Notification.EXTRA_TEXT, msg);
    }
  }

  public static void setText(Notification notification, int Rid, String msg) {
    notification.contentView.setTextViewText(Rid, msg);
  }
}
