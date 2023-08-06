package mg.utils.graphics.gl2d;

import static android.opengl.GLES20.GL_BLEND;
import static android.opengl.GLES20.GL_ONE_MINUS_SRC_ALPHA;
import static android.opengl.GLES20.GL_SRC_ALPHA;
import static android.opengl.GLES20.glBlendFunc;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glViewport;
import static android.opengl.Matrix.multiplyMM;
import static android.opengl.Matrix.orthoM;
import static android.opengl.Matrix.setIdentityM;
import static android.opengl.Matrix.translateM;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;

import mg.utils.graphics.gl2d.objects.TextureHelper;
import mg.utils.graphics.gl2d.objects.TextureShaderProgram;
import mg.utils.objects.Rect;
import mg.utils.objects.RotatableRect;

/**
 * This is the martinmimigames. drawing dependency
 *
 * @author martinmimi (from martinmimigames.)
 * @version 0.0.0.1
 * @since about 2020/12/15
 */
@Deprecated
@SuppressLint({"StaticFieldLeak"})
@SuppressWarnings({"deprecation", "fallthrough", "cast"})
public class Prop {
  static final int POSITION_COMPONENT_COUNT = 2;
  static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;
  static final int STRIDE =
    (POSITION_COMPONENT_COUNT + TEXTURE_COORDINATES_COMPONENT_COUNT) * 4;

  // data storage
  static ArrayList<Integer> texture_data;
  static ArrayList<ArrayList<Float>> crop_data;
  static float height;
  static float ratio;

  // required files
  static Activity activity;
  static TextureShaderProgram textureProgram;


  /**
   * setup this dependency for use
   *
   * @param _activity returned by "this" in Activity
   * @param vertex    a texture vertex shader
   * @param fragment  a texture fragment shader
   */
  public static void setup(Activity _activity, int vertex, int fragment) {

    // setting up variables
    activity = _activity;
    textureProgram = new TextureShaderProgram(activity, vertex, fragment);

    // setting up storage
    texture_data = new ArrayList<>();
    crop_data = new ArrayList<>();

    // enable transparent texture
    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
  }

  /**
   * setup screen for this dependency
   *
   * @param width            screen width
   * @param screen_height    screen height
   * @param projectionMatrix the matrix format in "android.opengl.Matrix", sets it up
   */
  public static void setScreen(int width, int screen_height, float[] projectionMatrix) {
    // Set the OpenGL viewport to fill the entire surface.
    height = screen_height;
    ratio = 2 / height;
    glViewport(0, 0, width, screen_height);
    final float aspectRatio = (float) width / (float) height;
    orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1, 1, -1, 1);
    translateM(projectionMatrix, 0, (float) -(((float) width) / (float) height), 1, 0);
    float[] modelMatrix = new float[16];
    setIdentityM(modelMatrix, 0);
    final float[] temp = new float[16];
    multiplyMM(temp, 0, projectionMatrix, 0, modelMatrix, 0);
    System.arraycopy(temp, 0, projectionMatrix, 0, temp.length);
  }

  /**
   * load a texture and give an id for use
   *
   * @param resourceId id from the res folder, given in R.java
   * @return id of texture
   */
  // load a texture (jpg, png, etc)
  public static int loadTexture(int resourceId) {
    final int texture = TextureHelper.loadTexture(activity, resourceId);
    texture_data.add(texture);
    ArrayList<Float> data = new ArrayList<>();
    data.add(0f);
    data.add(1f);
    data.add(0f);
    data.add(1f);
    crop_data.add(data);

    // give a texture id
    return texture_data.size() - 1;
  }

  /**
   * change the texture to the newly given one
   *
   * @param id         id of texture given by "loadTexture"
   * @param resourceId id from the res folder, given in R.java
   */
  // load a texture (jpg, png, etc)
  public static void changeTexture(int id, int resourceId) {
    final int texture = TextureHelper.loadTexture(activity, resourceId);
    texture_data.set(id, texture);
  }

  /**
   * crops a texture
   *
   * @param textureId id of texture given by "loadTexture"
   * @param lrtb      float list of left, right, top, bottom. 0f for 0% and 1f for 100%
   */
  // crops a texture
  public static void crop(int textureId, float[] lrtb) {
    ArrayList<Float> data = new ArrayList<>();
    data.add(lrtb[0]);
    data.add(lrtb[1]);
    data.add(lrtb[2]);
    data.add(lrtb[3]);
    crop_data.set(textureId, data);
  }

  /**
   * clear stored texture
   */
  // clear
  public static void clear() {
    if (texture_data != null) {
      texture_data.clear();
      crop_data.clear();
    }
  }

  /**
   * drawing texture to screen
   *
   * @param object           uses RotatableRect / Rect from this package to give object size, coor...
   * @param projectionMatrix the matrix format in "android.opengl.Matrix"
   * @param textureId        id of texture given by "loadTexture"
   * @param draw_type        uses drawType to set draw type
   */
  //draw with rotation if available
  public static void draw(RotatableRect object, float[] projectionMatrix, int textureId, int draw_type) {
    switch (draw_type) {
      //if default
      //if rotatable
      case DrawType.DEFAULT:
      case DrawType.ROTATABLE:
        Drawing.drawRotatableDefault(object, projectionMatrix, textureId);
        break;
      case DrawType.NON_ROTATABLE:
        Drawing.drawNonRotatableDefault(object, projectionMatrix, textureId);
        break;
      default:
        if (mg.utils.logger.Log.ON) {
          Log.e("Martinmimi_Prop", "draw type \" " + draw_type + " \" is not available for choose");
        }
    }
  }

  /**
   * drawing texture to screen
   *
   * @param object           uses RotatableRect / Rect from this package to give object size, coor...
   * @param projectionMatrix the matrix format in "android.opengl.Matrix"
   * @param textureId        id of texture given by "loadTexture"
   * @param draw_type        uses drawType to set draw type
   */
  //draw without rotation if not available
  public static void draw(Rect object, float[] projectionMatrix, int textureId, int draw_type) {
    switch (draw_type) {
      //if default
      //if rotatable
      case DrawType.DEFAULT:
      case DrawType.NON_ROTATABLE:
        Drawing.drawNonRotatableDefault(object, projectionMatrix, textureId);
        break;
      case DrawType.ROTATABLE:
      default:
        if (mg.utils.logger.Log.ON) {
          Log.e("Martinmimi_Prop", "draw type \" " + draw_type + " \" is not available for choose");
        }
    }
  }


  /**
   * drawing texture to screen
   *
   * @param object           uses RotatableRect / Rect from this package to give object size, coor...
   * @param projectionMatrix the matrix format in "android.opengl.Matrix"
   * @param textureId        id of texture given by "loadTexture"
   * @param draw_type        uses drawType to set draw type
   * @param rotation_x       offset of x when not rotated
   * @param rotation_y       offset of y when not rotated
   */
  //draw with offset
  public static void draw(RotatableRect object, float[] projectionMatrix, int textureId, float rotation_x, float rotation_y, int draw_type) {
    switch (draw_type) {
      //if default
      //if rotatable
      case DrawType.DEFAULT:
      case DrawType.ROTATABLE:
        Drawing.drawRotatableOffsetDefault(object, projectionMatrix, textureId, rotation_x, rotation_y);
        break;
      //if non-rotatable
      case DrawType.NON_ROTATABLE:
        Drawing.drawNonRotatableOffsetDefault(object, projectionMatrix, textureId, rotation_x, rotation_y);
      default:
        if (mg.utils.logger.Log.ON) {
          Log.e("Martinmimi_Prop", "draw type \" " + draw_type + " \" is not available for choose");
        }
    }
  }

  /**
   * drawing texture to screen
   *
   * @param object           uses RotatableRect / Rect from this package to give object size, coor...
   * @param projectionMatrix the matrix format in "android.opengl.Matrix"
   * @param textureId        id of texture given by "loadTexture"
   * @param draw_type        uses drawType to set draw type
   * @param rotation_x       offset of x when not rotated
   * @param rotation_y       offset of y when not rotated
   */
  //draw with offset
  public static void draw(Rect object, float[] projectionMatrix, int textureId, float rotation_x, float rotation_y, int draw_type) {
    switch (draw_type) {
      //if default
      //if non-rotatable
      case DrawType.DEFAULT:
      case DrawType.NON_ROTATABLE:
        Drawing.drawNonRotatableOffsetDefault(object, projectionMatrix, textureId, rotation_x, rotation_y);
        break;
      //if rotatable
      case DrawType.ROTATABLE:
      default:
        if (mg.utils.logger.Log.ON) {
          Log.e("Martinmimi_Prop", "draw type \" " + draw_type + " \" is not available for choose");
        }
    }
  }
}
