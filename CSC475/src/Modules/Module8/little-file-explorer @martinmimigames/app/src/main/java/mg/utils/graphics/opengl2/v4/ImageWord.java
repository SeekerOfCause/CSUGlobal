package mg.utils.graphics.opengl2.v4;

import android.content.Context;

import mg.utils.graphics.opengl2.v4.helper.Vertex;
import mg.utils.objects.LocationXYA;

/**
 * This is the MGGames utility dependency.
 * Text composing for Draw dependency
 *
 * @author martinmimi (from martinmimigames)
 * @version 4.0.1 release
 * @since 09-05-2022 dd-mm-yyyy
 */

public class ImageWord extends Object {

  public static final int CENTERED = 0;
  public static final int LEFT = 1;
  private final Object object;
  public float fontSize;
  public int drawType;
  private byte[] byteList;

  public ImageWord(Context context, int imageRId) {
    location = new LocationXYA();
    object = new Object();
    object.drawable = drawable = new Image(context, imageRId);
    byteList = new byte[0];
    fontSize = 0.25f;
    drawType = CENTERED;
  }

  @Override
  public void draw() {
    final float y = location.y;
    final float string_size = byteList.length * fontSize;
    float x;
    switch (drawType) {
      case LEFT:
        x = location.x;
        break;
      case CENTERED:
      default:
        x = location.x - string_size / 2 + fontSize / 2;
        break;
    }
    Vertex.Image.setWidth(((Renderable) drawable).vertex, fontSize);
    Vertex.Image.setHeight(((Renderable) drawable).vertex, fontSize);
    for (byte value : byteList) {
      byte b = value;
      if (b < 11 || b > 36) {

        if (b == 10) {
        } else if (b < 10) {
          b += 3;
          int row = 4;
          drawChar(b, row, x, y);
        }

      } else {
        b -= 10;

        int row = 0;

        drawChar(b, row, x, y);

      }
      x += fontSize;
    }
  }

  private void drawChar(byte charByte, int row, float x, float y) {
    while (charByte > 6) {
      charByte -= 6;
      row += 1;
    }
    final float cx = charByte * 0.15625f + 0.01f;
    final float cy = row * 0.15625f + 0.01f;
    Vertex.Image.setCrop(((Image) drawable).vertex,
      //col
      cx - 0.15625f,
      cx,
      //row
      cy,
      cy + 0.15625f);
    object.location.x = x;
    object.location.y = y;
    object.draw();
  }

  public void setText(String string) {
    char[] text = string.toLowerCase().toCharArray();

    byteList = new byte[text.length];
    for (int i = 0; i < text.length; i++) {
      switch (text[i]) {

        // numbers

        case '0':
          byteList[i] = 0;
          break;
        case '1':
          byteList[i] = 1;
          break;
        case '2':
          byteList[i] = 2;
          break;
        case '3':
          byteList[i] = 3;
          break;
        case '4':
          byteList[i] = 4;
          break;
        case '5':
          byteList[i] = 5;
          break;
        case '6':
          byteList[i] = 6;
          break;
        case '7':
          byteList[i] = 7;
          break;
        case '8':
          byteList[i] = 8;
          break;
        case '9':
          byteList[i] = 9;
          break;

        // space

        case ' ':
          byteList[i] = 10;
          break;

        // letters

        case 'a':
          byteList[i] = 11;
          break;
        case 'b':
          byteList[i] = 12;
          break;
        case 'c':
          byteList[i] = 13;
          break;
        case 'd':
          byteList[i] = 14;
          break;
        case 'e':
          byteList[i] = 15;
          break;
        case 'f':
          byteList[i] = 16;
          break;
        case 'g':
          byteList[i] = 17;
          break;
        case 'h':
          byteList[i] = 18;
          break;
        case 'i':
          byteList[i] = 19;
          break;
        case 'j':
          byteList[i] = 20;
          break;
        case 'k':
          byteList[i] = 21;
          break;
        case 'l':
          byteList[i] = 22;
          break;
        case 'm':
          byteList[i] = 23;
          break;
        case 'n':
          byteList[i] = 24;
          break;
        case 'o':
          byteList[i] = 25;
          break;
        case 'p':
          byteList[i] = 26;
          break;
        case 'q':
          byteList[i] = 27;
          break;
        case 'r':
          byteList[i] = 28;
          break;
        case 's':
          byteList[i] = 29;
          break;
        case 't':
          byteList[i] = 30;
          break;
        case 'u':
          byteList[i] = 31;
          break;
        case 'v':
          byteList[i] = 32;
          break;
        case 'w':
          byteList[i] = 33;
          break;
        case 'x':
          byteList[i] = 34;
          break;
        case 'y':
          byteList[i] = 35;
          break;
        case 'z':
          byteList[i] = 36;
          break;
      }
    }
  }
}
