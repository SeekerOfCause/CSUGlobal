package mg.utils.graphics.opengl2.v4.glsl;

import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glDisableVertexAttribArray;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glVertexAttribPointer;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * This is the MGGames utility dependency.
 * Vertex data processing for opengl
 *
 * @author martinmimi (from martinmimigames)
 * @version 4.0.1 release
 * @since 09-05-2022 dd-mm-yyyy
 */
public class VertexArray {
  private static final int BYTE_PER_FLOAT = 4;
  private FloatBuffer floatBuffer;

  /**
   * Create a VertexArray
   */
  public VertexArray() {
    floatBuffer = ByteBuffer
      .allocateDirect(0)
      .order(ByteOrder.nativeOrder())
      .asFloatBuffer();
  }

  /**
   * Overwrite old vertex data with new vertex data.
   * Automatically allocate new space for longer vertex data.
   *
   * @param vertexData a vertex data in form of array
   */
  public void overwrite(float[] vertexData) {
    try {
      floatBuffer.position(0);
      final int length = vertexData.length;
      for (float vertexDatum : vertexData) floatBuffer.put(vertexDatum);
    } catch (BufferOverflowException e) {
      floatBuffer = ByteBuffer
        .allocateDirect(vertexData.length * BYTE_PER_FLOAT)
        .order(ByteOrder.nativeOrder())
        .asFloatBuffer()
        .put(vertexData, 0, vertexData.length);
    }
  }

  /**
   * Enable and point to an attribute variable.
   *
   * @param dataOffset        the offset of data in the vertex array
   * @param attributeLocation the location of the attribute variable
   * @param componentCount    the number of vertex pairs
   * @param stride            the size of one vertex pair
   */
  public void setAttributePointer(int dataOffset, int attributeLocation,
                                  int componentCount, int stride) {
    floatBuffer.position(dataOffset);
    glVertexAttribPointer(attributeLocation, componentCount, GL_FLOAT,
      false, stride, floatBuffer);
    glEnableVertexAttribArray(attributeLocation);
  }

  /**
   * Disable an attribute variable.
   *
   * @param attributeLocation the location of the attribute variable
   */
  public void disableAttributePointer(int attributeLocation) {
    glDisableVertexAttribArray(attributeLocation);
  }
}
