package com.example.appointmentprogram;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DBAccess {

    private static final String url = "jdbc:mysql://localhost:3306/AppointmentDB";
    private static final String user = "root";
    private static final String password = "Hatouma1!";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static Connection connection = null;
    private static PreparedStatement preparedStatement;

    public static Connection startConnection(){
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");
        }
        catch(ClassNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        }
        catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return connection;
    }

    public static Connection getConnection(){
        return connection;
    }

    public static void closeConnection(){
        try{
            connection.close();
            System.out.println("Connection closed!");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        if(conn != null)
            preparedStatement = conn.prepareStatement(sqlStatement);
        else
            System.out.println("Prepared Statement Creation Failed");
    }

    public static PreparedStatement getPreparedStatement(){
        if(preparedStatement != null)
            return preparedStatement;
        else
            System.out.println("Null reference to Prepared Statement");
        return null;
    }
}
