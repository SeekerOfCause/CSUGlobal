package mg.utils.objects;

/**
 * This is the martinmimigames Vertex object dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 0.0.0.1
 * @since about 2021/3/21
 */

@SuppressWarnings("ALL")
@Deprecated
public class Rect extends Coor {

  //static final are constants
  //the followings will be used for
  //getting data in arrays []
  //example : data[CONSTANT]

  protected static final int TOTAL_FLOAT_DATA_OPTION = Coor.TOTAL_FLOAT_DATA_OPTION + 2;

  private static final int BASE_FLOAT_DATA_OPTION = Coor.TOTAL_FLOAT_DATA_OPTION;

  public static final int HALF_WIDTH = BASE_FLOAT_DATA_OPTION;
  public static final int HALF_HEIGHT = BASE_FLOAT_DATA_OPTION + 1;

  /**
   * setup rectangle object
   */
  public Rect() {
    data = new float[TOTAL_FLOAT_DATA_OPTION];
  }

  //objects size

  /**
   * set rectangle size
   *
   * @param w width of rectangle
   * @param h height of rectangle
   */
  public void setRect(Float w, Float h) {
    data[HALF_WIDTH] = w / 2f;
    data[HALF_HEIGHT] = h / 2f;
  }

  /**
   * get width of rectangle
   *
   * @return width of rectangle
   */
  public Float getWidth() {
    return data[HALF_WIDTH] * 2;
  }

  /**
   * get height of rectangle
   *
   * @return height of rectangle
   */
  public Float getHeight() {
    return data[HALF_HEIGHT] * 2;
  }

  /**
   * get half of the width of rectangle
   *
   * @return half of the width of rectangle
   */
  public Float getHalfWidth() {
    return data[HALF_WIDTH];
  }

  /**
   * get half of the height of rectangle
   *
   * @return half of the height of rectangle
   */
  public Float getHalfHeight() {
    return data[HALF_HEIGHT];
  }

}
