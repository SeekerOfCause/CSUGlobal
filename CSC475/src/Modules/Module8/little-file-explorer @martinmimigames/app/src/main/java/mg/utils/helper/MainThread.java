package mg.utils.helper;

import android.os.Handler;
import android.os.Looper;

/**
 * A helper class for running processes in Main Thread.
 * Helpful for UI manipulations and such.
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.0
 * @since 04-03-2023 [dd-mm-yyyy]
 */
public final class MainThread {

  /**
   * A handler for the main looper thread
   */
  private static final Handler handler;

  static {
    handler = new Handler(Looper.getMainLooper());
  }

  /**
   * Queue the provided runnable in the main thread and run it.
   * Uses a handler and the main looper.
   *
   * @param runnable the runnable to be ran in the main thread
   */
  public static void run(Runnable runnable) {
    handler.post(runnable);
  }

  /**
   * Queue the provided runnable in the main thread and run it after a certain delay.
   * Uses a handler and the main looper.
   *
   * @param runnable the runnable to be ran in the main thread
   * @param delay    the delay in milliseconds for the runnable to run
   */
  public static void runDelayed(Runnable runnable, long delay) {
    handler.postDelayed(runnable, delay);
  }

  /**
   * Queue the provided runnable in the main thread and run it at a certain time.
   * Uses a handler and the main looper.
   *
   * @param runnable the runnable to be ran in the main thread
   * @param time     the time in which the runnable is ran, time-base is SystemClock.upTimeMillis
   */
  public static void runWhen(Runnable runnable, long time) {
    handler.postAtTime(runnable, time);
  }

  /**
   * Queue the provided runnable to the front in the main thread and run it as soon as possible.
   * Uses a handler and the main looper.
   * Should only be used in special circumstances,
   * as it can cause queue starvation, ordering problems and unexpected side-effects.
   *
   * @param runnable the runnable to be ran in the main thread
   */
  public static void runFirst(Runnable runnable) {
    handler.postAtFrontOfQueue(runnable);
  }

}
