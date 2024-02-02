import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable {
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private String name;
    private static ArrayList<ClientManager> clientsList = new ArrayList<>();

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()){
            try {
                messageFromClient = br.readLine();
                if (messageFromClient == null){
                    closeAll(socket, br, bw);
                    break;
                }
                broadcastMessage(messageFromClient);
            }catch (IOException e){
                closeAll(socket, br, bw);
                break;
            }
        }

    }

    public ClientManager(Socket socket){
        try {
            this.socket = socket;
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = br.readLine();
            clientsList.add(this);
            System.out.printf("%s has just join to chat\n", name);
            broadcastMessage("Server: " + name + " has joined to chat");

        }catch (IOException e){
            closeAll(socket, br, bw);
        }
    }
    public void broadcastMessage(String message){
        for (ClientManager client: clientsList) {
            try{
                if (!client.name.equals(name) && message != null){
                    client.bw.write(message);
                    client.bw.newLine();
                    client.bw.flush();
                }
            }catch(IOException e){
                closeAll(socket, br, bw);
            }
        }

    }
    public void removeClient(){
        clientsList.remove(this);
        System.out.printf("%s has disconnected\n", name);
        broadcastMessage("Server: " + name + " has disconnected");
    }
    public void closeAll(Socket socket, BufferedReader br, BufferedWriter bw){
        // Удаление клиента из коллекции
        removeClient();
        try {
            // Закрытие соединения с клиентским сокетом
            if(socket != null){socket.close();}
            // Завершаем работу буфера на чтение данных
            if (br != null){br.close();}
            // Завершаем работу буфера для записи данных
            if (bw != null){bw.close();}

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
