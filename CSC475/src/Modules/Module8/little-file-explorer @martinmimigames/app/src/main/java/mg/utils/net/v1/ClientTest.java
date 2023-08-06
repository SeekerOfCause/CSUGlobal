package mg.utils.net.v1;

import java.net.Socket;
import java.util.Scanner;

public class ClientTest implements Client, Runnable {

  ClientOperator client_operator;
  Scanner in;

  public ClientTest() {
    in = new Scanner(System.in);
  }

  @Override
  public void run() {
    client_operator = new ClientOperator(this);
    client_operator.setHost("localhost");
    client_operator.setPort(2002);
    client_operator.connect();
  }

  @Override
  public void onHostUnknown() {
    System.out.println("Client : host unknown");
  }

  @Override
  public void onConnect(Socket socket) {
    System.out.println("Client : connected");
    System.out.println("Client :  host address = " + socket.getInetAddress());
    System.out.println("Client :  host port = " + socket.getPort());
  }

  @Override
  public void onConnectionFailed() {
    System.out.println("Client : connection failed");
  }

  @Override
  public void onClientClose() {
    System.out.println("Client : closed");
  }
}