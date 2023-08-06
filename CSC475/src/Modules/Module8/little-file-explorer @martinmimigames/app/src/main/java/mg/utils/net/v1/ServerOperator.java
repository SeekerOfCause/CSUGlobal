package mg.utils.net.v1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ServerOperator implements Runnable {

  private Server server;
  private ServerSocket server_socket;
  private long timeoutTime;
  private long timeoutLength;
  private boolean timeoutEnabled;
  private boolean acceptConnection;
  private boolean isClosed;
  private int[] preferredPorts;
  private Thread running;

  public ServerOperator() {
    isClosed = true;
    timeoutEnabled = false;
    acceptConnection = true;
  }

  public void setServer(Server server) {
    this.server = server;
  }

  public void start() throws IOException {
    for (int port : preferredPorts) {
      try {
        server_socket = new ServerSocket(port);
        break;
      } catch (IOException ignore) {
      }
    }
    if (server_socket == null)
      throw new IOException("no usable port");
    timeoutTime = System.currentTimeMillis() + timeoutLength;
    running = new Thread(this);
    isClosed = false;
    running.start();
    server.onServerStart(server_socket.getLocalPort());
  }

  private void closing() {
    try {
      server_socket.close();
    } catch (IOException ignore) {
    }
    running = null;
    server.onServerClose();
  }

  public void close() {
    isClosed = true;
  }

  public void setTimeout(long timeout) {
    if (timeout < 0) {
      timeoutEnabled = false;
    } else {
      timeoutLength = timeout;
      timeoutEnabled = true;
    }
  }

  public void setPreferredPorts(int[] preferredPorts) {
    this.preferredPorts = preferredPorts;
  }

  public boolean getConnectable() {
    return acceptConnection;
  }

  public void setConnectable(boolean connectable) {
    acceptConnection = connectable;
    if (acceptConnection && running == null && isClosed) {
      running = new Thread(this);
      running.start();
    }
  }

  private void searchConnection() {
    try {
      server.onClientConnect(server_socket.accept());
    } catch (SocketTimeoutException ignore) {
    } catch (SocketException ignore) {
    } catch (IOException ignore) {
    }
  }

  @Override
  public void run() {
    while (true) {
      try {
        if (acceptConnection) {
          server_socket.setSoTimeout(10);
          searchConnection();
        }
      } catch (SocketException ignore) {
      }
      if (isClosed) {
        break;
      } else if (timeoutEnabled && timeoutTime < System.currentTimeMillis()) {
        server.onServerTimeout();
        isClosed = true;
        break;
      }
    }
    closing();
  }
}
