package forthtask;

import java.sql.*;

public class ConnectionDataBase {
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    Connection connection;
    public ConnectionDataBase() throws SQLException {
        URL = "jdbc:mysql://localhost:3306/";// ip for DB
        USER = "root";
        PASSWORD = "password";
        connect();

    }

    private void connect() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(URL,USER, PASSWORD);
            System.out.println("Connected Successfully");
        }catch (SQLException e){
            e.getMessage();
        }
    }

    //
    public void showDataBases() throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SHOW DATABASES;");
        while (resultSet.next()){
            System.out.print(resultSet.getString(1));
            System.out.println();
        }

    }
    public void dropDataBase(String nameDataBase) throws SQLException {
        String dropDataBaseSQL = "DROP DATABASE IF EXISTS " + nameDataBase + ";";
        PreparedStatement preparedStatement = this.connection.prepareStatement(dropDataBaseSQL);
        preparedStatement.execute();
        System.out.println(nameDataBase + " Dropped Successfully");
    }

    public void createDataBase(String nameDataBase) throws SQLException {
        String createDataBaseSQL = "CREATE DATABASE IF NOT EXISTS " + nameDataBase + ";";
        PreparedStatement preparedStatement = this.connection.prepareStatement(createDataBaseSQL);
        preparedStatement.execute();
        System.out.println("Data Base ".toUpperCase() + nameDataBase + " Created Successfully");
    }
    public void createTable(String nameDataBase, String nameTable) throws SQLException {
        useDataBase(nameDataBase);
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + nameTable +
                                " (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(100), duration INT);";
        PreparedStatement preparedStatement = this.connection.prepareStatement(createTableSQL);
        preparedStatement.execute();
        System.out.println("Table ".toUpperCase() + nameTable + " Created Successfully");

    }
    public void useDataBase(String nameDataBase) throws SQLException{
        String useDataBase = "USE " + nameDataBase + ";";
        PreparedStatement preparedStatement = this.connection.prepareStatement(useDataBase);
        preparedStatement.execute();

    }
    public void showTables(String nameDataBase)throws SQLException{
        useDataBase(nameDataBase);
        String showTablesSQL = "SHOW TABLES;";
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(showTablesSQL);
        while (resultSet.next()){
            System.out.print(resultSet.getString(1));
            System.out.println();
        }
    }
    public void close() throws SQLException {
        this.connection.close();
    }
}
