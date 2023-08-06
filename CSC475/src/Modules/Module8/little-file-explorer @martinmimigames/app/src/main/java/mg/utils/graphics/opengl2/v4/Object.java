package mg.utils.graphics.opengl2.v4;

import mg.utils.objects.LocationXYA;

/**
 * This is the MGGames utility dependency.
 * Object for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 4.0.1 release
 * @since 09-05-2022 dd-mm-yyyy
 */

public class Object implements Drawable {

  /**
   * location values on the screen
   */
  public LocationXYA location;
  /**
   * the texture
   */
  public Drawable drawable;

  /**
   * creates an Object,
   * no default Drawable
   */
  public Object() {
    location = new LocationXYA();
  }

  @Override
  public void draw() {
    if (location.angle != 0) {
      Draw.translateMatrix(location.x, location.y);
      Draw.rotateMatrix(location.angle);

      drawable.draw();

      Draw.rotateMatrix(-location.angle);
      Draw.translateMatrix(-location.x, -location.y);
    } else {
      Draw.translateMatrix(location.x, location.y);

      drawable.draw();

      Draw.translateMatrix(-location.x, -location.y);
    }
  }
}
