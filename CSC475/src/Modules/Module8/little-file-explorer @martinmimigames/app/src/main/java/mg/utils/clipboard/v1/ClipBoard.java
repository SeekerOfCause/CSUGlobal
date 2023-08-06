package mg.utils.clipboard.v1;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;

/**
 * Clipboard helper.
 * Allows you to copy items to the users' clipboard.
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0
 * @since 22-01-2023 dd-mm-yyyy
 */
public class ClipBoard {

  /**
   * Copy plain text to the user's clipboard.
   * @param context your app context
   * @param text the text you want to copy
   */
  public static void copyText(Context context, CharSequence text){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      copyClipData(context, ClipData.newPlainText(text, text));
    } else {
      getService(context).setText(text);
    }
  }

  /**
   * Copy clip data to the user's clipboard.
   * Only available on Android HoneyComb (sdk 11)+
   * @param context your app context
   * @param clipData the clip data you want to copy
   */
  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
  public static void copyClipData(Context context, ClipData clipData){
    var clipboard = (android.content.ClipboardManager) getService(context);
    clipboard.setPrimaryClip(clipData);
  }

  /**
   * Get the clipboard service. (android.text.ClipboardManager)
   * @param context your app context
   * @return clipboard manager (android.text.ClipboardManager)
   */
  private static ClipboardManager getService(Context context){
    return (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
  }
}
