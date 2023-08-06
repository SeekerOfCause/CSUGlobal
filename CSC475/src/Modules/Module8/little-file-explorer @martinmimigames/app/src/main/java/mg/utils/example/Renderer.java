package mg.utils.example;

import android.app.Activity;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import mg.utils.graphics.opengl2.v4.Draw;

public class Renderer implements GLSurfaceView.Renderer {
  private final Activity activity;

  // setting variables

  public Renderer(Activity activity) {
    this.activity = activity;
  }

  @Override
  public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
    Draw.init();
    // base color
    // not necessary
    Draw.background(0, 0, 0, 255);
  }

  @Override
  public void onSurfaceChanged(GL10 glUnused, int width, int height) {
    Draw.setScreen(width, height, Draw.ASPECT_RATIO_FOLLOW_HEIGHT);
    // setting variables that
    // requires screen size info
  }

  // everything inside will be done again and again
  @Override
  public void onDrawFrame(GL10 glUnused) {
    // start of loop
    // Clear the rendering surface.
    Draw.clean();
    // end of loop
  }
}
