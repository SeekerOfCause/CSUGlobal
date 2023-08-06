package mg.utils.control;

import java.util.ArrayList;

@Deprecated
public class Button {

  private static final ArrayList<Float> left = new ArrayList<>();
  private static final ArrayList<Float> right = new ArrayList<>();
  private static final ArrayList<Float> up = new ArrayList<>();
  private static final ArrayList<Float> down = new ArrayList<>();

  private static float current_x, current_y;

  public static int add(float x, float y, float w, float h) {
    final float half_w = w / 2f;
    final float half_h = h / 2f;
    left.add(x - half_w);
    right.add(x + half_w);
    up.add(y + half_h);
    down.add(y - half_h);
    return left.size() - 1;
  }

  public static int moveTo(int id, float x, float y) {
    final float half_w = (right.get(id) - left.get(id)) / 2f;
    final float half_h = (up.get(id) - down.get(id)) / 2f;
    left.set(id, x - half_w);
    right.set(id, x + half_w);
    up.set(id, y + half_h);
    down.set(id, y - half_h);
    return left.size() - 1;
  }

  public static int move(int id, float x, float y) {
    final float half_w = (right.get(id) - left.get(id)) / 2f;
    final float half_h = (up.get(id) - down.get(id)) / 2f;
    final float old_x = right.get(id) - half_w;
    final float old_y = up.get(id) - half_h;
    x += old_x;
    y += old_y;
    left.set(id, x - half_w);
    right.set(id, x + half_w);
    up.set(id, y + half_h);
    down.set(id, y - half_h);
    return left.size() - 1;
  }

  public static int pxadd(float l, float r, float u, float d) {
    left.add(l);
    right.add(r);
    up.add(u);
    down.add(d);
    return left.size() - 1;
  }

  public static void clear() {
    left.clear();
    right.clear();
    up.clear();
    down.clear();
  }

  public static void setPointerCoor(float x, float y) {
    current_x = x;
    current_y = y;
  }

  public static boolean use(int id) {
    return current_x >= left.get(id)
      && current_y <= up.get(id)
      && current_x <= right.get(id)
      && current_y >= down.get(id);
  }
}
