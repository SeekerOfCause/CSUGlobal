package mg.utils.graphics.opengl2.v3.glsl;

/**
 * This is the MGGames utility dependency.
 * opengl code for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.1 release
 * @since 17-02-2022 dd-mm-yyyy
 */

public class ShaderCode {

  public static final String TEXTURE_VERTEX_SHADER =
    "uniform mat4 u_Matrix;" +
      "attribute vec4 a_Position;" +
      "attribute vec2 a_TextureCoordinates;" +
      "varying vec2 v_TextureCoordinates;" +
      "void main()" +
      "{v_TextureCoordinates=a_TextureCoordinates;" +
      "gl_Position=u_Matrix*a_Position;}";


  public static final String TEXTURE_FRAGMENT_SHADER =
    "precision mediump float;" +
      "uniform sampler2D u_TextureUnit;" +
      "varying vec2 v_TextureCoordinates;" +
      "void main()" +
      "{gl_FragColor=texture2D(u_TextureUnit,v_TextureCoordinates);}";
}
