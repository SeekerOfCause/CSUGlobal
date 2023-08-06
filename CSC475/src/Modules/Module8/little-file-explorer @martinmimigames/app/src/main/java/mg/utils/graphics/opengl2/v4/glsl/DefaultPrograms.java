package mg.utils.graphics.opengl2.v4.glsl;

import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_VERTEX_SHADER;

import android.opengl.GLES20;

/**
 * This is the MGGames utility dependency.
 * Available opengl programs for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 4.0.1 release
 * @since 09-05-2022 dd-mm-yyyy
 */

public class DefaultPrograms {

  /**
   * A program for textured obects
   */
  public Program textureProgram;
  /**
   * A program for solid colored objects
   */
  public Program solidColorProgram;

  public DefaultPrograms() {
    textureProgram = new Program()
      .addShaderProgram(GL_VERTEX_SHADER, ShaderCode.TEXTURE_VERTEX_SHADER)
      .addShaderProgram(GL_FRAGMENT_SHADER, ShaderCode.TEXTURE_FRAGMENT_SHADER)
      .complete();
    solidColorProgram = new Program()
      .addShaderProgram(GLES20.GL_VERTEX_SHADER, ShaderCode.SOLID_VERTEX_SHADER)
      .addShaderProgram(GLES20.GL_FRAGMENT_SHADER, ShaderCode.SOLID_COLOR_FRAGMENT_SHADER)
      .complete();
  }

}
