package mg.utils.logger;

public class Log {

  public static boolean ON = true;

  public static void w(String tag, String information) {
    android.util.Log.w(tag, information);
  }

}
