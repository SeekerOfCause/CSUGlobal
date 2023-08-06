package mg.utils.graphics.opengl2.v3.glsl;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glVertexAttribPointer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * This is the MGGames utility dependency.
 * Vertex data processing for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 1.0.2 release
 * @since 09-03-2022 dd-mm-yyyy
 */

public class VertexArray {
  private static final int BYTE_PER_FLOAT = 4;
  private final FloatBuffer floatBuffer;

  public VertexArray(float[] vertexData) {
    floatBuffer = ByteBuffer
      .allocateDirect(vertexData.length * BYTE_PER_FLOAT)
      .order(ByteOrder.nativeOrder())
      .asFloatBuffer()
      .put(vertexData, 0, vertexData.length);
  }

  public void overwrite(float[] vertexData) {
    floatBuffer.position(0);
    final int length = vertexData.length;
    for (float vertexDatum : vertexData) floatBuffer.put(vertexDatum);
  }

  public void setVertexAttribPointer(int dataOffset, int attributeLocation,
                                     int componentCount, int stride) {
    floatBuffer.position(dataOffset);
    glVertexAttribPointer(attributeLocation, componentCount, GL_FLOAT,
      false, stride, floatBuffer);
    glEnableVertexAttribArray(attributeLocation);
  }
}
