package forthtask;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        ConnectionDataBase connectionDataBase = new ConnectionDataBase();
        // Вывод баз данных
//        connectionDataBase.showTables();
//        System.out.println();

        // Удаление Базы Данных
//        connectionDataBase.dropDataBase("schoolDB");
//        System.out.println();

        // Вывод баз данных
        connectionDataBase.showDataBases();
        System.out.println();

        // Создание Базы Данных
//       connectionDataBase.createDataBase("schoolDB");
//        System.out.println();

        // Вывод баз данных
//        connectionDataBase.showDataBases();

        // Создание таблице в Базе данных
        connectionDataBase.createTable("schoolDB","Courses");

        // Вывод таблиц в Базе данных
        connectionDataBase.showTables("schoolDB");

        connectionDataBase.close();
    }
}
