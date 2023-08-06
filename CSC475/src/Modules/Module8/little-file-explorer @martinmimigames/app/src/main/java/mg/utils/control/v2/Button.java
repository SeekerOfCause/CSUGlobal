package mg.utils.control.v2;

import android.view.MotionEvent;

import mg.utils.objects.LocationXY;

/**
 * This is the MGGames utility dependency.
 * Button utility
 *
 * @author martinmimi (from martinmimigames)
 * @version 2.0 release
 * @since 04-01-2022 dd-mm-yyyy
 */
public class Button {

  /**
   * the center of button
   */
  public LocationXY location;
  private float half_w;
  private float half_h;

  /**
   * initiation
   */
  public Button() {
    location = new LocationXY();
  }

  /**
   * initiation
   *
   * @param x      the x coordinate from the left of the screen
   * @param y      the y coordinate from the top of the screen
   * @param width  the width of the button in pixels
   * @param height the height of the button in pixels
   */
  public Button(float x, float y, float width, float height) {
    this();
    location.x = x;
    location.y = y;
    setWidth(width);
    setHeight(height);
  }

  /**
   * set the width of the button
   *
   * @param width the width of the button
   * @return this button for stacking purposes
   */
  public Button setWidth(float width) {
    half_w = width / 2f;
    return this;
  }

  /**
   * set the height of the button
   *
   * @param height the height of the button
   * @return this button for stacking purposes
   */
  public Button setHeight(float height) {
    half_h = height / 2f;
    return this;
  }

  /**
   * checks if the pointer event happened in the button
   *
   * @param event the pointer event
   * @param index the index of the pointer event
   * @return true if the pointer event happened in the button, false otherwise.
   */
  public boolean check(MotionEvent event, int index) {
    return check(event.getX(index), event.getY(index));
  }


  /**
   * checks if the x, y location is within the button
   *
   * @param x the distance from the left of the screen in pixels
   * @param y the distance from the top of the screen in pixels
   * @return true if x, y location is within the button, false otherwise.
   */
  public boolean check(float x, float y) {
    return x >= location.x - half_w
      && y <= location.y + half_h
      && x <= location.x + half_w
      && y >= location.y - half_h;
  }
}
