package mg.utils.graphics.opengl2.v3;

import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glDrawArrays;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import java.nio.BufferOverflowException;

import mg.utils.graphics.opengl2.v3.glsl.VertexArray;
import mg.utils.graphics.opengl2.v3.images.Parser;
import mg.utils.logger.Log;

/**
 * This is the MGGames utility dependency.
 * Image for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.2 release
 * @since 09-03-2022 dd-mm-yyyy
 */

public class Images implements Drawable {

  public static final String TAG = "Image";
  /**
   * how many set of points are there.
   * a set = (position + coordinate)
   */
  public int points;
  /**
   * stride
   */
  public int stride;
  /**
   * type of image.
   * use value in TYPE
   */
  public int imageType = TYPE.NONE;
  /**
   * id of texture stored in openGL
   */
  public int textureId;
  /**
   * vertex data
   */
  public float[] vertex_data;
  /**
   * how many position component are there.
   * update stride
   * default = 2
   */
  int positionComponentCount = 2;
  /**
   * how many coordinate component are there.
   * default = 2
   */
  int textureCoordinatesComponentCount = 2;

  /**
   * @param context  the context
   * @param imageRId the id in R.java
   */
  public Images(final Context context, final int imageRId) {
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inScaled = false;
    if (Build.VERSION.SDK_INT >= 10)
      options.inPreferQualityOverSpeed = true;

    // Read in the resource
    final Bitmap bitmap =
      BitmapFactory.decodeResource(context.getResources(), imageRId, options);

    if (bitmap == null) {
      if (Log.ON)
        Log.w(TAG, "Resource ID " + imageRId + " could not be decoded.");
      return;
    }
    textureId = Parser.parseTexture(bitmap);
    imageType = TYPE.IMAGES;
    bitmap.recycle();
  }

  /**
   * @param bitmap the bitmap for the image
   */
  public Images(final Bitmap bitmap) {
    textureId = Parser.parseTexture(bitmap);
    imageType = TYPE.IMAGES;
  }

  /**
   * update stride value
   */
  public void updateStride() {
    stride = (positionComponentCount + textureCoordinatesComponentCount) * points;
  }

  @Override
  public void draw(Draw draw) {
    draw.availablePrograms.textureProgram.useProgram();

    draw.availablePrograms.textureProgram.setUniforms(Draw.projectionMatrix, textureId);

    try {
      draw.vertexArray.overwrite(vertex_data);
    } catch (BufferOverflowException e) {
      draw.vertexArray = new VertexArray(vertex_data);
    } catch (NullPointerException e) {
      draw.vertexArray = new VertexArray(vertex_data);
    }
    draw.vertexArray.setVertexAttribPointer(
      0,
      draw.availablePrograms.textureProgram.getPositionAttributeLocation(),
      positionComponentCount,
      stride);

    draw.vertexArray.setVertexAttribPointer(
      positionComponentCount,
      draw.availablePrograms.textureProgram.getTextureCoordinatesAttributeLocation(),
      textureCoordinatesComponentCount,
      stride);

    glDrawArrays(GL_TRIANGLE_STRIP, 0, points);
  }

  public void deleteTexture() {
    Parser.deleteTexture(textureId);
  }

  public static final class TYPE {
    public static final int NONE = 0;
    public static final int IMAGES = 1;
  }
}
