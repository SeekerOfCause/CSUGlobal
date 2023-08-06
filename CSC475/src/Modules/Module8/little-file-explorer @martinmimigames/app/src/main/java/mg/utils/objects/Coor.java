package mg.utils.objects;


/**
 * This is the martinmimigames coordination dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 0.0.0.1
 * @since about 2021/3/21
 */
@Deprecated
public class Coor {

  //static final are constants
  //the followings will be used for
  //getting data in arrays []
  //example : data[CONSTANT]

  protected static final int TOTAL_FLOAT_DATA_OPTION = 2;
  private static final int BASE_FLOAT_DATA_OPTION = 0;

  public static final int X = BASE_FLOAT_DATA_OPTION;
  public static final int Y = BASE_FLOAT_DATA_OPTION + 1;

  protected float[] data;

  public Coor() {
    data = new float[TOTAL_FLOAT_DATA_OPTION];
  }

  //objects coordination

  /**
   * set coordination
   *
   * @param x location in x axis
   * @param y location in y axis
   */
  public void setCoor(float x, float y) {
    data[X] = x;
    data[Y] = y;
  }

  /**
   * move coordination
   *
   * @param increase_in_x increase in x axis, use minus if decrease
   * @param increase_in_y increase in y axis, use minus if decrease
   */
  public void moveCoor(float increase_in_x, float increase_in_y) {
    data[X] += increase_in_x;
    data[Y] += increase_in_y;
  }

  /**
   * get coordination of x
   *
   * @return coordination of x
   */
  public float getX() {
    return data[X];
  }

  /**
   * get coordination of y
   *
   * @return coordination of y
   */
  public float getY() {
    return data[Y];
  }

  /**
   * get the entire data array used to store the values
   *
   * @return data array
   */
  //get entire data array
  public float[] getDataArray() {
    return data;
  }
}
