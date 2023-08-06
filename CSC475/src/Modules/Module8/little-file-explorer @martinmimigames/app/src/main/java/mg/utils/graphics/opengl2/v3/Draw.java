package mg.utils.graphics.opengl2.v3;

import static android.opengl.GLES20.GL_BLEND;
import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_ONE_MINUS_SRC_ALPHA;
import static android.opengl.GLES20.GL_SRC_ALPHA;
import static android.opengl.GLES20.glBlendFunc;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glViewport;
import static android.opengl.Matrix.orthoM;
import static android.opengl.Matrix.translateM;

import android.app.Activity;

import mg.utils.graphics.opengl2.v3.glsl.AvailablePrograms;
import mg.utils.graphics.opengl2.v3.glsl.VertexArray;

/**
 * This is the MGGames utility dependency.
 * Draw dependency for opengl graphic works
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.2 release
 * @since 09-03-2022 dd-mm-yyyy
 */

public class Draw {

  public static float[] projectionMatrix;
  public float ratio;
  public Integer height;
  AvailablePrograms availablePrograms;
  VertexArray vertexArray;

  /**
   * Do not use in Constructor.
   * Place in onSurfaceCreated instead.
   *
   * @param activity Activity with the screen
   */
  public Draw(Activity activity) {
    // setting up variables
    height = activity.getWindow().getDecorView().getBottom();
    projectionMatrix = new float[16];
    availablePrograms = new AvailablePrograms();
    // enable transparent texture
    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
  }

  /**
   * Setup the screen.
   * Place in onSurfaceChange.
   *
   * @param width  width of screen
   * @param height height of screen
   */
  public void setScreen(int width, int height) {
    // Set the OpenGL viewport to fill the entire surface.
    glViewport(0, 0, width, height);
    // store in aspect ratio
    final float aspectRatio = (float) width / (float) height;
    // build the matrix
    orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1, 1, -1, 1);
    // set the matrix to right position
    translateM(projectionMatrix, 0, -aspectRatio, 1, 0);
    // set variables
    this.height = height;
    ratio = 2 / (float) height;
  }

  /**
   * Set background colour.
   * Can only run in GLThread.
   *
   * @param red   colour red, value 0 - 255
   * @param green colour green, value 0 - 255
   * @param blue  colour blue, value 0 - 255
   * @param alpha value alpha, value 1 - 255
   */
  public void background(final float red, final float green, final float blue, final float alpha) {
    glClearColor(red / 255f, green / 255f, blue / 255f, alpha / 255f);
  }

  /**
   * Draw the drawable.
   * Calls the draw(draw) method.
   *
   * @param drawable the drawable to be drawn
   */
  public void draw(Drawable drawable) {
    drawable.draw(this);
  }

  /**
   * Clear the rendering surface.
   * Can only run in GLThread
   */
  public void clean() {
    // Clear the rendering surface.
    glClear(GL_COLOR_BUFFER_BIT);
  }
}
