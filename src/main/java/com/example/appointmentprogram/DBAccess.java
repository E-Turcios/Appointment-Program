package com.example.appointmentprogram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * @author Abdoulaye Boundy Djikine
 * This class is for establishing connection and  interact with the MySQL database using JDBC.
 */
public class DBAccess {

//    /**
//     * The URL of the MySQL database.
//     */
//    private static final String url = "jdbc:mysql://localhost:3306/AppointmentDB";
//
//    /**
//     * The username to use when connecting to the MySQL database.
//     */
//    private static final String user = "root";
//
//    /**
//     * The password to use when connecting to the MySQL database.
//     */
//    private static final String password = "Hatouma1!";
//
//    /**
//     * The fully qualified name of the JDBC driver class to use.
//     */
//    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "sqlUser";
    private static String password = "Passw0rd!";

    /**
     * The connection to the MySQL database.
     */
    private static Connection connection = null;

    /**
     * The prepared statement used for executing SQL statements on the MySQL database.
     */
    private static PreparedStatement preparedStatement;

    /**
     * Attempts to start a connection to the MySQL database using the configured URL, username, and password.
     * @return The connection to the MySQL database.
     */
    public static Connection startConnection(){
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
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

    /**
     * Returns the connection to the MySQL database.
     * @return The connection to the MySQL database.
     */
    public static Connection getConnection(){
        return connection;
    }

    /**
     * Closes the connection to the MySQL database.
     */
    public static void closeConnection(){
        try{
            connection.close();
            System.out.println("Connection closed!");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a prepared statement for executing SQL statements on the MySQL database.
     * @param conn The connection to the MySQL database.
     * @param sqlStatement The SQL statement to prepare.
     * @throws SQLException If there is an error creating the prepared statement.
     */
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        if(conn != null)
            preparedStatement = conn.prepareStatement(sqlStatement);
        else
            System.out.println("Prepared Statement Creation Failed");
    }

    /**
     * Returns the prepared statement used for executing SQL statements on the MySQL database.
     * @return The prepared statement used for executing SQL statements on the MySQL database.
     */
    public static PreparedStatement getPreparedStatement(){
        if(preparedStatement != null)
            return preparedStatement;
        else
            System.out.println("Null reference to Prepared Statement");
        return null;
    }
}

