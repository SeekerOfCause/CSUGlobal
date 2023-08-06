package mg.utils.graphics.opengl2.v4.helper;

/**
 * This is the MGGames utility dependency.
 * Vertex helper in Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 4.0.1 release
 * @since 09-05-2022 dd-mm-yyyy
 */
public class Vertex {

  /**
   * Helper class to create / modify vertex data for rectangular shapes.
   * Works with a float array of size 8.
   */
  public static class Rectangle {
    /**
     * Set the width of a rectangular object
     *
     * @param vertex the vertex array to be modify (must be size 8)
     * @param width  the width of the object
     * @return the given array
     */
    public static float[] setWidth(float[] vertex, float width) {
      float halfWidth = width / 2f;
      vertex[0] = vertex[2] = -halfWidth;
      vertex[4] = vertex[6] = halfWidth;
      return vertex;
    }

    /**
     * Set the height of a rectangular object
     *
     * @param vertex the vertex array to be modify (must be size 8)
     * @param height the height of the object
     * @return the given array
     */
    public static float[] setHeight(float[] vertex, float height) {
      float halfHeight = height / 2f;
      vertex[1] = vertex[5] = halfHeight;
      vertex[3] = vertex[7] = -halfHeight;
      return vertex;
    }
  }

  /**
   * Helper class to create / modify vertex data for images.
   * Works with a float array of size 16.
   */
  public static class Image {
    /**
     * Set the width of a image
     *
     * @param vertex the vertex array to be modify (must be size 16)
     * @param width  the width of the object
     * @return the given array
     */
    public static float[] setWidth(float[] vertex, float width) {
      float halfWidth = width / 2f;
      vertex[0] = vertex[4] = -halfWidth;
      vertex[8] = vertex[12] = halfWidth;
      return vertex;
    }

    /**
     * Set the height of a image
     *
     * @param vertex the vertex array to be modify (must be size 16)
     * @param height the height of the object
     * @return the given array
     */
    public static float[] setHeight(float[] vertex, float height) {
      float halfHeight = height / 2f;
      vertex[1] = vertex[9] = halfHeight;
      vertex[5] = vertex[13] = -halfHeight;
      return vertex;
    }

    /**
     * Set the crop of a image
     *
     * @param vertex the vertex array to be modify (must be size 16)
     * @param left   value 0 (left) - 1 (right), default = 0
     * @param right  value 0 (left) - 1 (right), default = 1
     * @param top    value 0 (top) - 1 (top), default = 0;
     * @param bottom value 0 (top) - 1 (bottom), default = 1;
     * @return the given array
     */
    public static float[] setCrop(float[] vertex, float left, float right, float top, float bottom) {
      vertex[2] = vertex[6] = left;
      vertex[3] = vertex[11] = top;
      vertex[10] = vertex[14] = right;
      vertex[7] = vertex[15] = bottom;
      return vertex;
    }
  }
}
