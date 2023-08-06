package mg.utils.net.v1;

import java.net.Socket;

public interface Server {

  void onServerStart(int port);

  void onClientConnect(Socket socket);

  void onServerTimeout();

  void onServerClose();
}
