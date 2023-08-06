package mg.utils.net.v1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientOperator {

  private final Client client;
  private Socket socket;
  private InetAddress host;
  private int port;

  public ClientOperator(Client client) {
    this.client = client;
  }

  public Socket getSocket() {
    return socket;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }

  public void setHost(String host) {
    try {
      this.host = InetAddress.getByName(host);
    } catch (UnknownHostException e) {
      client.onHostUnknown();
    }
  }

  public String getHostName() {
    return host.getHostName();
  }

  public String getHostIp() {
    return host.getHostAddress();
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public void connect() {
    try {
      socket = new Socket(host, port);
      client.onConnect(socket);
    } catch (IOException e) {
      client.onConnectionFailed();
    }
  }

  public void close() throws IOException {
    client.onClientClose();
    socket.close();
  }
}
