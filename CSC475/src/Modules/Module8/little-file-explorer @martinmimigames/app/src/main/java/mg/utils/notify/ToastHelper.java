package mg.utils.notify;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import mg.utils.helper.MainThread;

/**
 * This is the MGGames utility dependency.
 * Helper for creating and displaying toast
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.0 release
 * @since 01-07-2022 dd-mm-yyyy
 */
public class ToastHelper {

  /**
   * create and display toast for a short time
   *
   * @param context the context to display toast on (Activity, Service, etc)
   * @param msg     the message to display
   */
  public static void showShort(Context context, String msg) {
    MainThread.run(() -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show());
  }

  /**
   * create and display toast for a short time
   *
   * @param context the context to display toast on (Activity, Service, etc)
   * @param resId   the id of message to display from R.java (stored in string res)
   */
  public static void showShort(Context context, int resId) {
    MainThread.run(() -> Toast.makeText(context, resId, Toast.LENGTH_SHORT).show());
  }

  /**
   * create and display toast for a long time
   *
   * @param context the context to display toast on (Activity, Service, etc)
   * @param msg     the message to display
   */
  public static void showLong(Context context, String msg) {
    MainThread.run(() -> Toast.makeText(context, msg, Toast.LENGTH_LONG).show());
  }

  /**
   * create and display toast for a long time
   *
   * @param context the context to display toast on (Activity, Service, etc)
   * @param resId   the id of message to display from R.java (stored in string res)
   */
  public static void showLong(Context context, int resId) {
    MainThread.run(() -> Toast.makeText(context, resId, Toast.LENGTH_LONG).show());
  }
}
