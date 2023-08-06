package mg.utils.graphics.opengl2.v4.glsl;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteProgram;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;
import static android.opengl.GLES20.glUseProgram;

import mg.utils.logger.Log;


/**
 * This is the MGGames utility dependency.
 * Program to store, compile, and use glsl shaders
 *
 * @author martinmimi (from martinmimigames)
 * @version 4.0.1 release
 * @since 09-05-2022 dd-mm-yyyy
 */

public class Program {


  private static final String TAG = "ShaderHelper";

  /**
   * Shader program
   * value = 0 if incorrect
   */
  protected int id;

  /**
   * Create the program.
   */
  public Program() {
    id = glCreateProgram();

    if (id == 0) {
      if (Log.ON) Log.w(TAG, "Could not create new program");
    }
  }

  /**
   * Add a shader to this program.
   *
   * @param type       the type of shader, uses values from opengl 2
   * @param shaderCode the shader code written in String
   * @return this program object for chaining commands
   */
  public Program addShaderProgram(int type, String shaderCode) {
    if (id != 0)
      glAttachShader(id, compileShader(type, shaderCode));
    return this;
  }

  /**
   * For getting a uniform location from the shader codes.
   *
   * @param name the name of the uniform variable
   * @return the location of the variable
   */
  public int getUniformLocation(String name) {
    return glGetUniformLocation(id, name);
  }

  /**
   * For getting a attribute location from the shader codes.
   *
   * @param name the name of the attribute variable
   * @return the location of the variable
   */
  public int getAttributeLocation(String name) {
    return glGetAttribLocation(id, name);
  }

  /**
   * Link the shaders together.
   * Can be used after call.
   *
   * @return this program object for chaining commands
   */
  public Program complete() {
    if (id == 0) return this;
    glLinkProgram(id);

    final int[] linkStatus = new int[1];
    glGetProgramiv(id, GL_LINK_STATUS,
      linkStatus, 0);

    if (linkStatus[0] == 0) {
      glDeleteProgram(id);
      id = 0;
      if (Log.ON) Log.w(TAG, "Linking of program failed.");
    }
    return this;
  }

  /**
   * Call to use program.
   */
  public void use() {
    glUseProgram(id);
  }

  /**
   * Get the program id for use in opengl.
   *
   * @return the program id
   */
  public int getProgramId() {
    return id;
  }

  private int compileShader(int type, String shaderCode) {
    final int shaderObjectId = glCreateShader(type);

    if (shaderObjectId == 0) {
      if (Log.ON) Log.w(TAG, "Could not create new shader.");
      return 0;
    }

    glShaderSource(shaderObjectId, shaderCode);

    glCompileShader(shaderObjectId);

    final int[] compileStatus = new int[1];
    glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS,
      compileStatus, 0);

    if (compileStatus[0] == 0) {
      glDeleteShader(shaderObjectId);

      if (Log.ON) Log.w(TAG, "Compilation of shader failed.");
      return 0;
    }
    return shaderObjectId;
  }
}
