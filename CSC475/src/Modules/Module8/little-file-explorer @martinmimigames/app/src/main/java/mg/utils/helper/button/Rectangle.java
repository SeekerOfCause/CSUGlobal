package mg.utils.helper.button;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;

import mg.utils.control.v2.Button;
import mg.utils.graphics.opengl2.v3.Draw;
import mg.utils.graphics.opengl2.v3.Drawable;
import mg.utils.graphics.opengl2.v3.Object;

/**
 * This is the MGGames utility dependency.
 * button helper for use with MGGames utility
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.0 first release
 * @since about 22-01-2021 dd-mm-yyyy
 */
public class Rectangle extends Button implements Drawable {

  public Button button;
  public Object object;

  /**
   * Creates a button.
   * No default Drawable,
   * it has to be added manually with setImage().
   */
  public Rectangle() {
    button = new Button();
    object = new Object();
  }

  /**
   * set the X coordinate of the button
   *
   * @param x X coordinate
   * @return this button
   */
  public Rectangle setX(float x) {
    button.location.x = x;
    //object.location.coor[mg.util.objects.LocationXYA.X] = x;
    object.location.x = x;
    return this;
  }

  /**
   * set the Y coordinate of the button
   *
   * @param y Y coordinate
   * @return this button
   */
  public Rectangle setY(float y) {
    button.location.y = y;
    //object.location.coor[mg.util.objects.LocationXYA.Y] = y;
    object.location.y = y;
    return this;
  }

  /**
   * set the width of the button
   *
   * @param width width of button
   * @return this button
   */
  public Rectangle setWidth(float width) {
    button.setWidth(width);
    try {
      ((mg.utils.graphics.opengl2.v3.images.shapes.Rectangle) object.drawable).setWidth(width);
    } catch (NullPointerException e) {
      throw new RuntimeException("IMAGE NOT SET\n" + e, e.getCause());
    }
    return this;
  }

  /**
   * set the height of the button
   *
   * @param height height of button
   * @return this button
   */
  public Rectangle setHeight(float height) {
    button.setHeight(height);
    try {
      ((mg.utils.graphics.opengl2.v3.images.shapes.Rectangle) object.drawable).setHeight(height);
    } catch (NullPointerException e) {
      throw new RuntimeException("IMAGE NOT SET\n" + e, e.getCause());
    }
    return this;
  }

  /**
   * set the image of button
   *
   * @param draw    draw dependency
   * @param context context of activity
   * @param Rid     image id from R.java
   * @return this button
   */
  public Rectangle setImage(Draw draw, Context context, int Rid) {
    object.drawable = new mg.utils.graphics.opengl2.v3.images.shapes.Rectangle(draw, context, Rid);
    return this;
  }

  /**
   * set the image of button
   *
   * @param draw   draw dependency
   * @param bitmap the bitmap image
   * @return this button
   */
  public Rectangle setImage(Draw draw, Bitmap bitmap) {
    object.drawable = new mg.utils.graphics.opengl2.v3.images.shapes.Rectangle(draw, bitmap);
    return this;
  }

  /**
   * set the image of button
   *
   * @param rectangle rectangle of image
   * @return this button
   */
  public Rectangle setImage(Rectangle rectangle) {
    object.drawable = rectangle;
    return this;
  }

  /**
   * crop the button image,
   * value 0 - 1.
   * does not affect width or height
   *
   * @param left   value 0 (left) - 1 (right),
   *               default = 0
   * @param right  value 0 (left) - 1 (right),
   *               default = 1
   * @param top    value 0 (top) - 1 (bottom),
   *               default = 0;
   * @param bottom value 0 (top) - 1 (bottom),
   *               default = 1;
   * @return this Vertex
   */
  public Rectangle setImageCrop(final float left,
                                final float right,
                                final float top,
                                final float bottom) {
    ((Rectangle) object.drawable).setImageCrop(left, right, top, bottom);
    return this;
  }

  /**
   * check if event about button
   *
   * @param event MotionEvent
   * @param index index of MotionEvent
   * @return if event about button
   */
  @Override
  public boolean check(MotionEvent event, int index) {
    return button.check(event, index);
  }

  /**
   * check if coordinates about button
   *
   * @param x coordinate X
   * @param y coordinate Y
   * @return if coordinates about button
   */
  @Override
  public boolean check(float x, float y) {
    return button.check(x, y);
  }

  @Override
  public void draw(Draw draw) {
    object.draw(draw);
  }

}
