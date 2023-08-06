package mg.utils.net.v1;

import java.io.IOException;
import java.net.Socket;

public class ServerTest implements Server, Runnable {

  ServerOperator server_operator;

  @Override
  public void run() {
    server_operator = new ServerOperator();
    server_operator.setServer(this);
    server_operator.setPreferredPorts(new int[]{2002, 2003, 2004});
    // server_operator.setTimeout(10);
    try {
      server_operator.start();
    } catch (IOException e) {
      System.out.println("Server : no usable ports");
    }
    server_operator.close();
  }

  @Override
  public void onClientConnect(Socket socket) {
    System.out.println("Server : client connected");
  }

  @Override
  public void onServerClose() {
    System.out.println("Server : server closed");
  }

  @Override
  public void onServerTimeout() {
    System.out.println("Server : server timeout");
  }

  @Override
  public void onServerStart(int port) {
    System.out.println("Server : server started");
    System.out.println("Server : started on port : " + port);
  }
}