package mg.utils.graphics.gl2d;

import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.Matrix.rotateM;
import static android.opengl.Matrix.translateM;
import static mg.utils.graphics.gl2d.Prop.crop_data;
import static mg.utils.graphics.gl2d.Prop.height;
import static mg.utils.graphics.gl2d.Prop.ratio;
import static mg.utils.graphics.gl2d.Prop.texture_data;

import java.util.ArrayList;

import mg.utils.graphics.gl2d.objects.TextureShaderProgram;
import mg.utils.objects.Rect;
import mg.utils.objects.RotatableRect;

@Deprecated
@SuppressWarnings("deprecation")
class Drawing {

  public static void drawRotatableDefault(RotatableRect objects, float[] projectionMatrix, int textureId) {

    // get the data from the storage
    float[] result = objects.getDataArray();
    ArrayList<Float> crop = crop_data.get(textureId);
    final int texture = texture_data.get(textureId);
    final float angle = result[RotatableRect.ANGLE];

    // combine the data

    final float half_w = result[RotatableRect.HALF_WIDTH] * ratio;
    final float half_h = result[RotatableRect.HALF_HEIGHT] * ratio;

    final float[] vertex_data = {
      -half_w,
      -half_h,
      crop.get(0),
      crop.get(3),
      -half_w,
      half_h,
      crop.get(0),
      crop.get(2),
      half_w,
      -half_h,
      crop.get(1),
      crop.get(3),
      half_w,
      half_h,
      crop.get(1),
      crop.get(2)
    };

    // get the drawing location
    final float x = result[RotatableRect.X] * ratio;
    final float y = result[RotatableRect.Y] * ratio;
    // the drawing process
    translateM(projectionMatrix, 0, x, -y, 0f);
    rotateM(projectionMatrix, 0, -angle, 0f, 0f, 1f);

    Drawing.drawTextureDefault(vertex_data, projectionMatrix, texture);

    rotateM(projectionMatrix, 0, angle, 0f, 0f, 1f);

    translateM(projectionMatrix, 0, -x, y, 0f);
    // the end of the drawing process
  }

  // draw an object with texture
  public static void drawNonRotatableDefault(Rect rect, float[] projectionMatrix, int textureId) {

    // get the data from the storage
    float[] result = rect.getDataArray();
    ArrayList<Float> crop = crop_data.get(textureId);
    final int texture = texture_data.get(textureId);

    // combine the data

    final float half_w = result[Rect.HALF_WIDTH] * ratio;
    final float half_h = result[Rect.HALF_HEIGHT] * ratio;

    final float[] vertex_data = {
      -half_w,
      -half_h,
      crop.get(0),
      crop.get(3),
      -half_w,
      half_h,
      crop.get(0),
      crop.get(2),
      half_w,
      -half_h,
      crop.get(1),
      crop.get(3),
      half_w,
      half_h,
      crop.get(1),
      crop.get(2)
    };

    // get the drawing location
    final float x = result[Rect.X] * ratio;
    final float y = result[Rect.Y] * ratio;

    // the drawing process
    translateM(projectionMatrix, 0, x, -y, 0f);

    Drawing.drawTextureDefault(vertex_data, projectionMatrix, texture);

    translateM(projectionMatrix, 0, -x, y, 0f);
    // the end of the drawing process
  }

  // draw an object with offset texture
  public static void drawRotatableOffsetDefault(RotatableRect objects, float[] projectionMatrix, int textureId, float rotation_x, float rotation_y) {

    // get the data from the storage
    float[] result = objects.getDataArray();
    ArrayList<Float> crop = crop_data.get(textureId);
    final int texture = texture_data.get(textureId);
    final float angle = result[RotatableRect.ANGLE];

    // combine the data

    final float half_w = result[RotatableRect.HALF_WIDTH] * ratio;
    final float half_h = result[RotatableRect.HALF_HEIGHT] * ratio;

    final float[] vertex_data = {
      -half_w,
      -half_h,
      crop.get(0),
      crop.get(3),
      -half_w,
      half_h,
      crop.get(0),
      crop.get(2),
      half_w,
      -half_h,
      crop.get(1),
      crop.get(3),
      half_w,
      half_h,
      crop.get(1),
      crop.get(2)
    };

    // get the drawing location
    final float x = result[RotatableRect.X] * ratio;
    final float y = result[RotatableRect.Y] * ratio;

    // the drawing process
    translateM(projectionMatrix, 0, x, -y, 0f);
    rotateM(projectionMatrix, 0, -angle, 0f, 0f, 1f);
    translateM(projectionMatrix, 0, -rotation_x / height, -rotation_y / height, 0f);

    Drawing.drawTextureDefault(vertex_data, projectionMatrix, texture);

    translateM(projectionMatrix, 0, rotation_x / height, rotation_y / height, 0f);
    rotateM(projectionMatrix, 0, angle, 0f, 0f, 1f);
    translateM(projectionMatrix, 0, -x, y, 0f);
    // the end of the drawing process
  }

  //draw an object with offset un-rotated
  public static void drawNonRotatableOffsetDefault(Rect objects, float[] projectionMatrix, int textureId, float rotation_x, float rotation_y) {

    // get the data from the storage
    float[] result = objects.getDataArray();
    ArrayList<Float> crop = crop_data.get(textureId);
    final int texture = texture_data.get(textureId);

    // combine the data

    final float half_w = result[Rect.HALF_WIDTH] * ratio;
    final float half_h = result[Rect.HALF_HEIGHT] * ratio;

    final float[] vertex_data = {
      -half_w,
      -half_h,
      crop.get(0),
      crop.get(3),
      -half_w,
      half_h,
      crop.get(0),
      crop.get(2),
      half_w,
      -half_h,
      crop.get(1),
      crop.get(3),
      half_w,
      half_h,
      crop.get(1),
      crop.get(2)
    };

    // get the drawing location
    final float x = result[Rect.X] * ratio;
    final float y = result[Rect.Y] * ratio;

    // the drawing process
    translateM(projectionMatrix, 0, x, -y, 0f);
    translateM(projectionMatrix, 0, -rotation_x / height, -rotation_y / height, 0f);

    Drawing.drawTextureDefault(vertex_data, projectionMatrix, texture);

    translateM(projectionMatrix, 0, rotation_x / height, rotation_y / height, 0f);
    translateM(projectionMatrix, 0, -x, y, 0f);
    // the end of the drawing process
  }

  private static void drawTextureDefault(final float[] vertex_data, final float[] projectionMatrix, final int texture) {
    final VertexArray vertexArray = new VertexArray(vertex_data);

    Prop.textureProgram.useProgram();

    Prop.textureProgram.setUniforms(projectionMatrix, texture);

    bindData(Prop.textureProgram, vertexArray);

    glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
  }


  private static void bindData(final TextureShaderProgram textureProgram, final VertexArray vertexArray) {
    vertexArray.setVertexAttribPointer(
      0, textureProgram.getPositionAttributeLocation(), Prop.POSITION_COMPONENT_COUNT, Prop.STRIDE);

    vertexArray.setVertexAttribPointer(
      Prop.POSITION_COMPONENT_COUNT,
      textureProgram.getTextureCoordinatesAttributeLocation(),
      Prop.TEXTURE_COORDINATES_COMPONENT_COUNT,
      Prop.STRIDE);
  }
}
