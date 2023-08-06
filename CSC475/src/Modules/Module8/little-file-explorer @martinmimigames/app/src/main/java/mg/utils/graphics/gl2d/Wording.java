package mg.utils.graphics.gl2d;

import java.util.ArrayList;

import mg.utils.objects.Rect;

@Deprecated
@SuppressWarnings({"deprecation", "StatementWithEmptyBody"})
public class Wording {

  private static int word_res;
  private static Rect word_obj;

  private static ArrayList<ArrayList<Byte>> word_data;
  private static ArrayList<Float> x_data;
  private static ArrayList<Float> y_data;
  private static ArrayList<Float> font_size;

  public static void setup(int word_file_resource) {
    word_res = Prop.loadTexture(word_file_resource);
    //final float[] size = {0f, 0f, 1f, 1f};
    word_obj = new Rect();
    word_data = new ArrayList<>();
    x_data = new ArrayList<>();
    y_data = new ArrayList<>();
    font_size = new ArrayList<>();
  }

  public static int add(String words, float size, float x, float y) {
    final byte[] string = textToInt(words);
    ArrayList<Byte> data = new ArrayList<>();
    for (byte b : string) {
      data.add(b);
    }
    word_data.add(data);
    x_data.add(x);
    y_data.add(y);
    font_size.add(size);
    return word_data.size() - 1;
  }

  public static void change(int id, String words) {
    final byte[] string = textToInt(words);
    ArrayList<Byte> data = new ArrayList<>();
    for (byte b : string) {
      data.add(b);
    }
    word_data.set(id, data);
  }

  public static void resize(int id, float size) {
    font_size.set(id, size);
  }

  public static void move(int id, float x, float y) {
    x_data.set(id, x_data.get(id) + x);
    y_data.set(id, y_data.get(id) + y);
  }

  public static void moveTo(int id, float x, float y) {
    x_data.set(id, x);
    y_data.set(id, y);
  }

  private static void drawWords(float font, byte b, int row, float x, float y, float[] projectionMatrix) {
    while (b > 6) {
      b -= 6;
      row += 1;
    }
    final float cx = b * 0.15625f + 0.01f;
    final float cy = row * 0.15625f + 0.01f;

    final float[] crop = {
      //col
      cx - 0.15625f,
      cx,
      //row
      cy,
      cy + 0.15625f
    };
    Prop.crop(word_res, crop);
    word_obj.setCoor(x, y);
    word_obj.setRect(font, font);
    Prop.draw(word_obj, projectionMatrix, word_res, DrawType.NON_ROTATABLE);

  }

  public static void clear() {
    if (word_data != null) {
      word_data.clear();
      font_size.clear();
      x_data.clear();
      y_data.clear();
    }
  }

  private static byte[] textToInt(String test_string) {
    test_string = test_string.toLowerCase();
    final char[] string_letters = test_string.toCharArray();
    byte[] string_data = new byte[string_letters.length];

    for (int i = 0; i < string_letters.length; i++) {
      byte n = 0;
      switch (string_letters[i]) {

        // numbers

        case '0':
          break;
        case '1':
          n = 1;
          break;
        case '2':
          n = 2;
          break;
        case '3':
          n = 3;
          break;
        case '4':
          n = 4;
          break;
        case '5':
          n = 5;
          break;
        case '6':
          n = 6;
          break;
        case '7':
          n = 7;
          break;
        case '8':
          n = 8;
          break;
        case '9':
          n = 9;
          break;

        // space

        case ' ':
          n = 10;
          break;

        // letters

        case 'a':
          n = 11;
          break;
        case 'b':
          n = 12;
          break;
        case 'c':
          n = 13;
          break;
        case 'd':
          n = 14;
          break;
        case 'e':
          n = 15;
          break;
        case 'f':
          n = 16;
          break;
        case 'g':
          n = 17;
          break;
        case 'h':
          n = 18;
          break;
        case 'i':
          n = 19;
          break;
        case 'j':
          n = 20;
          break;
        case 'k':
          n = 21;
          break;
        case 'l':
          n = 22;
          break;
        case 'm':
          n = 23;
          break;
        case 'n':
          n = 24;
          break;
        case 'o':
          n = 25;
          break;
        case 'p':
          n = 26;
          break;
        case 'q':
          n = 27;
          break;
        case 'r':
          n = 28;
          break;
        case 's':
          n = 29;
          break;
        case 't':
          n = 30;
          break;
        case 'u':
          n = 31;
          break;
        case 'v':
          n = 32;
          break;
        case 'w':
          n = 33;
          break;
        case 'x':
          n = 34;
          break;
        case 'y':
          n = 35;
          break;
        case 'z':
          n = 36;
          break;
      }
      string_data[i] = n;
    }
    return string_data;
  }

  public static void draw(int text_id, float[] modelMatrix) {
    ArrayList<Byte> string_data = word_data.get(text_id);
    final float font = font_size.get(text_id);
    final float y = y_data.get(text_id);
    final float string_size = string_data.size() * font;
    float x = x_data.get(text_id) - string_size / 2 + font / 2;
    for (int i = 0; i < string_data.size(); i++) {
      byte b = string_data.get(i);
      if (b < 11 || b > 36) {

        if (b == 10) {
        } else if (b < 10) {
          b += 3;
          int row = 4;
          drawWords(font, b, row, x, y, modelMatrix);
        }

      } else {
        b -= 10;

        int row = 0;

        drawWords(font, b, row, x, y, modelMatrix);

      }
      x += font;
    }
  }

  public static void drawToLeft(int text_id, float[] modelMatrix) {
    ArrayList<Byte> string_data = word_data.get(text_id);
    final float font = font_size.get(text_id);
    final float y = y_data.get(text_id);
    float x = x_data.get(text_id) + font / 2;
    for (int i = 0; i < string_data.size(); i++) {
      byte b = string_data.get(i);
      if (b < 11 || b > 36) {

        if (b == 10) {
        } else if (b < 10) {
          b += 3;
          int row = 4;
          drawWords(font, b, row, x, y, modelMatrix);
        }

      } else {
        b -= 10;

        int row = 0;

        drawWords(font, b, row, x, y, modelMatrix);

      }
      x += font;
    }
  }
}