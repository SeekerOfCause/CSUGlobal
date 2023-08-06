package mg.utils.helper;

/**
 * This is the MGGames utility dependency.
 * a fps regulator
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.0 first release
 * @since about 22-01-2021 dd-mm-yyyy
 */
public class BasicFpsRegulator {

  // default 50 fps
  private long stopTime = 20;

  public void run() throws InterruptedException {
    Thread.sleep(stopTime);
  }

  /**
   * set the time to stop in millis
   *
   * @param stopTime time to stop in millis
   */
  public void setStopTime(long stopTime) {
    this.stopTime = stopTime;
  }

  /**
   * set the time to stop by fps
   *
   * @param fps fps of loop
   */
  public void setFps(float fps) {
    this.stopTime = (long) (1000 / fps);
  }
}
