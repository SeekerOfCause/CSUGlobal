package mg.utils.objects;

/**
 * This is the martinmimigames Rotatable rectangle object dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 0.0.0.1
 * @since about 2021/3/21
 */

@SuppressWarnings("ALL")
@Deprecated
public class RotatableRect extends Rect {

  //static final are constants
  //the followings will be used for
  //getting data in arrays []
  //example : data[CONSTANT]

  protected static final int TOTAL_FLOAT_DATA_OPTION = Rect.TOTAL_FLOAT_DATA_OPTION + 1;
  private static final int BASE_FLOAT_DATA_OPTION = Rect.TOTAL_FLOAT_DATA_OPTION;

  public static final int ANGLE = BASE_FLOAT_DATA_OPTION;

  /**
   * setup rotatable rectangle object
   */
  public RotatableRect() {
    data = new float[TOTAL_FLOAT_DATA_OPTION];
  }

  //object angle

  /**
   * get angle of rectangle in degree
   *
   * @return angle of rectangle in degree
   */
  public Float getAngle() {
    return data[ANGLE];
  }

  /**
   * set angle of rectangle in degree
   *
   * @param degree angle of rectangle in degree
   */
  public void setAngle(Float degree) {
    data[ANGLE] = degree;
  }
}
