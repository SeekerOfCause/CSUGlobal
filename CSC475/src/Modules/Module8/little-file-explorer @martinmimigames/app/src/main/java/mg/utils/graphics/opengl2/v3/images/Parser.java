package mg.utils.graphics.opengl2.v3.images;

import static android.opengl.GLES20.GL_LINEAR_MIPMAP_LINEAR;
import static android.opengl.GLES20.GL_NEAREST;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glDeleteTextures;
import static android.opengl.GLES20.glGenTextures;
import static android.opengl.GLES20.glGenerateMipmap;
import static android.opengl.GLES20.glTexParameteri;
import static android.opengl.GLUtils.texImage2D;

import android.graphics.Bitmap;

import mg.utils.logger.Log;

/**
 * This is the MGGames utility dependency.
 * Image parsing for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.1 first release
 * @since about 18-02-2022 dd-mm-yyyy
 */

public class Parser {

  public static final String TAG = "Image parser";

  /**
   * put texture data into opengl,
   * and return texture id
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
    // Bind to the texture in OpenGL
    glBindTexture(GL_TEXTURE_2D, textureObjectIds[0]);

    // Set filtering: a default must be set, or the texture will be
    // black.
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

    // Load the bitmap into the bound texture.
    texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);

    // Note: Following code may cause an error to be reported in the
    // ADB log as follows: E/IMGSRV(20095): :0: HardwareMipGen:
    // Failed to generate texture mipmap levels (error=3)
    // No OpenGL error will be encountered (glGetError() will return
    // 0). If this happens, just squash the source image to be
    // square. It will look the same because of texture coordinates,
    // and mipmap generation will work.
    glGenerateMipmap(GL_TEXTURE_2D);

    // Recycle the bitmap, since its data has been loaded into
    // OpenGL.
    bitmap.recycle();

    // Unbind from the texture.
    glBindTexture(GL_TEXTURE_2D, 0);

    return textureObjectIds[0];
  }

  /**
   * delete the texture of the given id,
   * in order to free up memory
   *
   * @param textureId the id of the texture to be deleted
   */
  public static void deleteTexture(int textureId) {
    glDeleteTextures(1, new int[]{textureId}, 0);
  }
}
