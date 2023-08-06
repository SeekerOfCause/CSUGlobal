package mg.utils.graphics.opengl2.v4;

import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glLineWidth;
import static android.opengl.GLES20.glUniform4fv;
import static android.opengl.GLES20.glUniformMatrix4fv;

import android.opengl.GLES20;

import mg.utils.graphics.opengl2.v4.glsl.ShaderCode;

/**
 * This is the MGGames utility dependency.
 * Line for opengl graphic works
 *
 * @author martinmimi (from martinmimigames)
 * @version 4.0.1 release
 * @since 09-05-2022 dd-mm-yyyy
 */
public class Line extends Renderable {

  private final int positionLocation;
  private final int colorLocation;
  private final int matrixLocation;
  private float[] color;
  private float lineWidth;

  /**
   * Create the line object.
   */
  public Line() {
    super();
    color = new float[4];

    program = Draw.defaultPrograms.solidColorProgram;

    positionLocation = program.getAttributeLocation(ShaderCode.A_POSITION);
    colorLocation = program.getUniformLocation(ShaderCode.A_COLOR);
    lineWidth = 1;
    matrixLocation = program.getUniformLocation(ShaderCode.U_MATRIX);
  }

  @Override
  public void preDraw() {
    program.use();

    Draw.vertexArray.overwrite(vertex);
    Draw.vertexArray.setAttributePointer(0, positionLocation, vertexPartCount, vertexStride);


    glUniform4fv(colorLocation, 1, color, 0);

  }

  @Override
  public void pureDraw() {

    glLineWidth(lineWidth);

    glUniformMatrix4fv(matrixLocation, 1, false, Draw.projectionMatrix, 0);

    glDrawArrays(GLES20.GL_LINES, 0, vertexCount);

  }

  @Override
  public void postDraw() {

    Draw.vertexArray.disableAttributePointer(positionLocation);
  }

  /**
   * set line width.
   *
   * @param width the width of the line
   */
  public void setWidth(float width) {
    lineWidth = width;
  }

  /**
   * set line color.
   *
   * @param red   colour red, value 0 - 255
   * @param green colour green, value 0 - 255
   * @param blue  colour blue, value 0 - 255
   * @param alpha value alpha, value 1 - 255
   */
  public void setColor(float red, float green, float blue, float alpha) {
    color[0] = red / 255f;
    color[1] = green / 255f;
    color[2] = blue / 255f;
    color[3] = alpha / 255f;
  }
}
