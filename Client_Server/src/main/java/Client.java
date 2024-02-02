import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private String name;
    public Client(Socket socket, String name){
        this.socket = socket;
        this.name = name;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch (IOException e){
            closeAll(socket, br,bw);
        }
    }
    public void sendMessage(){
        try {
            bw.write(this.name);
            bw.newLine();
            bw.flush();
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()){
                String message = scanner.nextLine();
                bw.write(this.name + ": " + message);
                bw.newLine();
                bw.flush();
            }
        }catch (IOException e){
            closeAll(socket, br,bw);
        }
    }
    public void listenMessage(){
        new Thread(new Runnable(){

            @Override
            public void run() {
                String message;
                while (socket.isConnected()) {
                    try {
                        message = br.readLine();
                        System.out.println(message);
                    } catch (IOException e) {
                        closeAll(socket, br,bw);
                    }
                }
            }
        }).start();
    }
    private void closeAll(Socket socket, BufferedReader br, BufferedWriter bw){
        try {
            // Завершаем работу буфера на чтение данных
            if (br != null) {
                br.close();
            }
            // Завершаем работу буфера для записи данных
            if (bw != null) {
                bw.close();
            }
            // Завершаем работу клиентского сокета
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
