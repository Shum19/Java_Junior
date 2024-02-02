import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type your name: ");
        String name = scanner.nextLine();

        try (Socket socket = new Socket(address, 8888);) {
            Client client = new Client(socket, name);

            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("Inet Address: " + inetAddress);
            String remoteIP = inetAddress.getHostAddress();
            System.out.println("Remote IP: " + remoteIP);
            System.out.println("Local port: " + socket.getLocalPort());
            client.listenMessage();
            client.sendMessage();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
