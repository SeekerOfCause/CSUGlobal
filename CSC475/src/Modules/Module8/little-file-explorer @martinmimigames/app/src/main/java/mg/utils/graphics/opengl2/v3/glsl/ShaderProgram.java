package mg.utils.graphics.opengl2.v3.glsl;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteProgram;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;
import static android.opengl.GLES20.glUseProgram;

import mg.utils.logger.Log;

/**
 * This is the MGGames utility dependency.
 * opengl code implementation for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.1 release
 * @since 17-02-2022 dd-mm-yyyy
 */

public abstract class ShaderProgram {
  // Uniform constants
  protected static final String U_MATRIX = "u_Matrix";
  protected static final String U_TEXTURE_UNIT = "u_TextureUnit";

  // Attribute constants
  protected static final String A_POSITION = "a_Position";
  protected static final String A_COLOR = "a_Color";
  protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";
  private static final String TAG = "ShaderHelper";
  /**
   * Shader program
   * value = 0 if incorrect
   */
  protected final int program;

  protected ShaderProgram() {

    // Compile the shaders.
    final int vertexShader =
      compileShader(GL_VERTEX_SHADER, ShaderCode.TEXTURE_VERTEX_SHADER);
    final int fragmentShader =
      compileShader(GL_FRAGMENT_SHADER, ShaderCode.TEXTURE_FRAGMENT_SHADER);

    // Link them into a shader program.

    // Create a new program object.
    // return 0 if error
    final int programObjectId = glCreateProgram();

    //run if error
    if (programObjectId == 0) {
      if (Log.ON) Log.w(TAG, "Could not create new program");
      program = 0;
      return;
    }

    // Attach the vertex shader to the program.
    glAttachShader(programObjectId, vertexShader);

    // Attach the fragment shader to the program.
    glAttachShader(programObjectId, fragmentShader);

    // Link the two shaders together into a program.
    glLinkProgram(programObjectId);

    // Get the link status.
    final int[] linkStatus = new int[1];
    glGetProgramiv(programObjectId, GL_LINK_STATUS,
      linkStatus, 0);

    // Verify the link status.
    if (linkStatus[0] == 0) {
      // If it failed, delete the program object.
      glDeleteProgram(programObjectId);

      if (Log.ON) Log.w(TAG, "Linking of program failed.");

      program = 0;
      return;
    }

    program = programObjectId;
  }

  public void useProgram() {
    // Set the current OpenGL shader program to this program.
    glUseProgram(program);
  }

  /**
   * Compiles a shader, returning the OpenGL object ID.
   * if error return 0
   */
  private int compileShader(int type, String shaderCode) {
    // Create a new shader object.
    final int shaderObjectId = glCreateShader(type);

    //if shader object id = 0
    //then shader cannot be created
    if (shaderObjectId == 0) {
      if (Log.ON) Log.w(TAG, "Could not create new shader.");
      return 0;
    }

    // Pass in the shader source.
    glShaderSource(shaderObjectId, shaderCode);

    // Compile the shader.
    glCompileShader(shaderObjectId);

    // Get the compilation status.
    final int[] compileStatus = new int[1];
    glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS,
      compileStatus, 0);

    // Verify the compile status.
    //if compile status = 0
    //an error had occurred
    if (compileStatus[0] == 0) {
      // If it failed, delete the shader object.
      glDeleteShader(shaderObjectId);

      if (Log.ON) Log.w(TAG, "Compilation of shader failed.");
      return 0;
    }

    // Return the shader object ID.
    return shaderObjectId;
  }
}
