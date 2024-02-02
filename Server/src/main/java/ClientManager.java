import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable {
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private String name;
    private String personsName;
    private static ArrayList<ClientManager> clientsList = new ArrayList<>();

    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()){
            try {
                messageFromClient = br.readLine();
                // Для отображения информации, что пользователь покинул чат.
//                if (messageFromClient == null){
//                    closeAll(socket, br, bw);
//                    break;
//                }
                if (isPrivateMessage(messageFromClient)){
                    sendPrivateMsg(messageFromClient);
                } else {broadcastMessage(messageFromClient);}
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
            System.out.printf("%s has just joined to chat\n", name);
            broadcastMessage("Server: " + name + " has joined to chat");

        }catch (IOException e){
            closeAll(socket, br, bw);
        }
    }
    private void broadcastMessage(String message){
        for (ClientManager client: clientsList) {
            try{
                if (!client.name.equals(this.name) && message != null) {
                        client.bw.write(message);
                        client.bw.newLine();
                        client.bw.flush();

                }
            }catch(IOException e){
                closeAll(socket, br, bw);
            }
        }

    }
//    Определение необходимости отправки сообщения. В случае если введено два имени из чата то будет оправлено тому чье имя введено вторым.
//    Поэтому если речь идет о ком-то из чата, то его имя необходимо чем-то обособить например @ чтобы ему не дошло сообщение
    private boolean isPrivateMessage(String message){
        boolean isPrivate = false;
        String [] arr = message.split(" ");
        for (String personsName : arr) {
            for (ClientManager client : clientsList) {
                if (!personsName.equalsIgnoreCase(this.name) && personsName.equalsIgnoreCase(client.name)) {
                    this.personsName = personsName;
                    return true;
                }

            }
        }
        return isPrivate;
    }
//  Метод для отправки персональных сообщений
    private void sendPrivateMsg(String message){
        try {
            for (ClientManager client:clientsList) {
                if(client.name.equals(this.personsName) && message != null){
                    client.bw.write(message);
                    client.bw.newLine();
                    client.bw.flush();
                }
            }
        }catch (IOException e) {
            closeAll(socket,br, bw);
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
