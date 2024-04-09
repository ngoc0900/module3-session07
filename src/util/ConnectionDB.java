package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DRIVE = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/session7_jdbc";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hongngoc09";
    public static Connection openConnection(){
        Connection connection;
        try {
            // đăng ký driver
            Class.forName(DRIVE);
            // khởi tạo đối tượng connection thông qua Driver Manager
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void main(String[] args) {
        System.out.println(ConnectionDB.openConnection());
    }

    public static void closeConnection(Connection connection){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
