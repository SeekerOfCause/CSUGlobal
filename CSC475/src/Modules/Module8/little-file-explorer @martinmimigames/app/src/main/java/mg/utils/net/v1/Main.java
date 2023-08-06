package mg.utils.net.v1;

public class Main {
  public static void main(String[] args) {
    new Thread(new ServerTest()).start();
    new Thread(new ClientTest()).start();
  }
}