package mg.utils.graphics.opengl2.v4.glsl;

/**
 * This is the MGGames utility dependency.
 * opengl code for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 4.0.1 release
 * @since 09-05-2022 dd-mm-yyyy
 */

public class ShaderCode {
  // Uniform constants
  public static final String U_MATRIX = "u_Matrix";
  public static final String U_TEXTURE_UNIT = "u_TextureUnit";

  // Attribute constants
  public static final String A_POSITION = "a_Position";
  public static final String A_COLOR = "a_Color";
  public static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";

  public static final String TEXTURE_VERTEX_SHADER =
    "uniform mat4 u_Matrix;" +
      "attribute vec4 a_Position;" +
      "attribute vec2 a_TextureCoordinates;" +
      "varying vec2 v_TextureCoordinates;" +
      "void main()" +
      "{v_TextureCoordinates=a_TextureCoordinates;" +
      "gl_Position=u_Matrix*a_Position;}";

  public static final String SOLID_VERTEX_SHADER =
    "uniform mat4 u_Matrix;" +
      "attribute vec4 a_Position;" +
      "void main()" +
      "{gl_Position=u_Matrix*a_Position;}";

  public static final String TEXTURE_FRAGMENT_SHADER =
    "precision mediump float;" +
      "uniform sampler2D u_TextureUnit;" +
      "varying vec2 v_TextureCoordinates;" +
      "void main()" +
      "{gl_FragColor=texture2D(u_TextureUnit,v_TextureCoordinates);}";

  public static final String SOLID_COLOR_FRAGMENT_SHADER =
    "precision mediump float;" +
      "uniform vec4 a_Color;" +
      "void main()" +
      "{gl_FragColor = a_Color;}";


}
