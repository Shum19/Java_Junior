import java.io.IOException;
import java.net.ServerSocket;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8882);
        Server server = new Server(serverSocket);
        server.runServer();
        server.closeSocket();
    }
}
