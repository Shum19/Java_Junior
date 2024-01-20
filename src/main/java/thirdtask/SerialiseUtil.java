package thirdtask;

import java.io.*;

public class SerialiseUtil <T>{
    public static <T> void serialise(String fileName, T obj) {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(obj);
            System.out.println("File Saved!");
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static Object deserialize(String fileName){
       try {
           FileInputStream file = new FileInputStream(fileName);
           ObjectInputStream in = new ObjectInputStream(file);
           System.out.println("File read!");
           return in.readObject();
       }catch (IOException e){
           e.printStackTrace();
       }catch (ClassNotFoundException e){
           e.printStackTrace();
       }
       return null;
    }
}
