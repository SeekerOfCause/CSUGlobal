package mg.utils.example;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class Activity extends android.app.Activity {

  private GLSurfaceView v;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    v = new GLSurfaceView(this);
    v.setEGLContextClientVersion(2);
    v.setRenderer(new Renderer(this));
    setContentView(v);
  }

  @Override
  protected void onStart() {
    super.onStart();
    v.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    v.onPause();
  }
}
