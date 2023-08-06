package mg.utils.graphics.opengl2.v4;

import static android.opengl.GLES20.GL_LINEAR_MIPMAP_LINEAR;
import static android.opengl.GLES20.GL_NEAREST;
import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glDeleteTextures;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glGenTextures;
import static android.opengl.GLES20.glGenerateMipmap;
import static android.opengl.GLES20.glTexParameteri;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLUtils.texImage2D;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import mg.utils.graphics.opengl2.v4.glsl.ShaderCode;
import mg.utils.graphics.opengl2.v4.helper.Vertex;
import mg.utils.logger.Log;

/**
 * This is the MGGames utility dependency.
 * Image for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 4.0.1 release
 * @since 09-05-2022 dd-mm-yyyy
 */
public class Image extends Renderable {

  public static final String TAG = "Image";

  /**
   * how many coordinate component are there.
   * default = 2
   */
  int texturePartCount = 2;

  /**
   * id of texture stored in openGL
   */
  public int textureId;

  private final int positionLocation;
  private final int textureUnitLocation;
  private final int texturePositionLocation;
  private final int matrixLocation;

  /**
   * Create Image object with image from res folder
   *
   * @param context  the context
   * @param imageRId the id in R.java
   */
  public Image(final Context context, final int imageRId) {
    this();

    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inScaled = false;
    if (Build.VERSION.SDK_INT >= 10 && Build.VERSION.SDK_INT < 24)
      options.inPreferQualityOverSpeed = true;

    // Read in the resource
    final Bitmap bitmap =
      BitmapFactory.decodeResource(context.getResources(), imageRId, options);

    if (bitmap == null) {
      if (Log.ON)
        Log.w(TAG, "Resource ID " + imageRId + " could not be decoded.");
      return;
    }

    textureId = parseTexture(bitmap);

    bitmap.recycle();
  }

  /**
   * Create Image object with a bitmap
   *
   * @param bitmap the bitmap for the image
   */
  public Image(final Bitmap bitmap) {
    this();
    textureId = parseTexture(bitmap);
  }

  private Image() {
    super();
    vertexCount = 4;

    program = Draw.defaultPrograms.textureProgram;

    positionLocation = program.getAttributeLocation(ShaderCode.A_POSITION);
    textureUnitLocation = program.getUniformLocation(ShaderCode.U_TEXTURE_UNIT);
    texturePositionLocation = program.getAttributeLocation(ShaderCode.A_TEXTURE_COORDINATES);
    matrixLocation = program.getUniformLocation(ShaderCode.U_MATRIX);
    vertex = new float[16];
    Vertex.Image.setWidth(vertex, 1);
    Vertex.Image.setHeight(vertex, 1);
    Vertex.Image.setCrop(vertex, 0, 1, 0, 1);
    updateStride();
  }

  @Override
  public void updateStride() {
    vertexStride = (vertexPartCount + texturePartCount) * vertexCount;
  }

  @Override
  public void preDraw() {
    program.use();

    // Set the active texture unit to texture unit 0.
    glActiveTexture(GL_TEXTURE0);

    // Bind the texture to this unit.
    glBindTexture(GL_TEXTURE_2D, textureId);

    // Tell the texture uniform sampler to use this texture in the shader by
    // telling it to read from texture unit 0.
    glUniform1i(textureUnitLocation, 0);

    Draw.vertexArray.overwrite(vertex);

    Draw.vertexArray.setAttributePointer(
      0,
      positionLocation,
      vertexPartCount,
      vertexStride);

    Draw.vertexArray.setAttributePointer(
      vertexPartCount,
      texturePositionLocation,
      texturePartCount,
      vertexStride);
  }

  @Override
  public void pureDraw() {
    // Pass the matrix into the shader program.
    glUniformMatrix4fv(matrixLocation, 1, false, Draw.projectionMatrix, 0);

    glDrawArrays(GL_TRIANGLE_STRIP, 0, vertexCount);
  }

  @Override
  public void postDraw() {
    Draw.vertexArray.disableAttributePointer(positionLocation);
    Draw.vertexArray.disableAttributePointer(texturePositionLocation);
  }

  /**
   * Parses a bitmap,
   * and return texture id for use in opengl.
   * If error reported in ADB log as follows: E/IMGSRV(20095): :0: HardwareMipGen:,
   * just squash texture to a square to resolve,
   * the outputted texture will stay the same because of texture cropping.
   *
   * @param bitmap image
   * @return texture id
   */
  public static int parseTexture(Bitmap bitmap) {
    final int[] textureObjectIds = new int[1];
    glGenTextures(1, textureObjectIds, 0);

    if (textureObjectIds[0] == 0) {
      if (Log.ON) Log.w(TAG, "Could not generate a new OpenGL texture object.");
      return 0;
    }

    glBindTexture(GL_TEXTURE_2D, textureObjectIds[0]);

    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

    texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);

    glGenerateMipmap(GL_TEXTURE_2D);

    glBindTexture(GL_TEXTURE_2D, 0);

    return textureObjectIds[0];
  }

  /**
   * Delete the texture of the given id,
   * in order to free up graphics memory.
   *
   * @param textureId the id of the texture to be deleted
   */
  public static void deleteTexture(int textureId) {
    glDeleteTextures(1, new int[]{textureId}, 0);
  }
}
