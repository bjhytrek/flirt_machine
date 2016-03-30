/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flirtMachine;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jbeag_000
 */
public class GetUsers {

    public GetUsers() {
    }

    public void addPickupLine(int userId, String pickUpLine) {
        try {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            String DB_URL = "jdbc:mysql://localhost/flirt_machine";

            //Database credentials
            String USER = "flirt";
            String PASS = "flirt-pass";

            Connection conn = null;
            Statement stmt = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println(pickUpLine);
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO pickup (content) VALUES (\'" + pickUpLine + "\');";// INSERT INTO user_pickup (user_id, pickup_id) VALUES (" + userId + ", (SELECT pickup_id FROM pickup WHERE pickup_id = (SELECT max(pickup_id) FROM pickup))); ";

            System.out.println(sql);

            stmt.executeUpdate(sql);
            
            //rs.close();
            stmt.close();
            conn.close();
            // 
            conn = null;
            stmt = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println(pickUpLine);
            stmt = conn.createStatement();
            sql = "SELECT max(pickup_id) AS pickup_id FROM pickup;";

            System.out.println(sql);

            ResultSet rs = stmt.executeQuery(sql);
            int insertIndex = -1;
            System.out.println(insertIndex);
            while (rs.next()) {
                insertIndex = rs.getInt("pickup_id");
            }
            System.out.println("index: " + insertIndex);
            
            conn = null;
            stmt = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println(pickUpLine);
            stmt = conn.createStatement();
            sql = "INSERT INTO user_pickup (user_id, pickup_id) VALUES (" + userId + ", " + insertIndex + ");";

            System.out.println(sql);

            stmt.executeUpdate(sql);

            //rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {

        }
    }

    public List<String> getPickupLine(int userId) {
        List<String> lines = new ArrayList<>();
        try {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            String DB_URL = "jdbc:mysql://localhost/flirt_machine";

            //Database credentials
            String USER = "flirt";
            String PASS = "flirt-pass";

            Connection conn = null;
            Statement stmt = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT p.content FROM user_pickup up INNER JOIN pickup p ON up.pickup_id = p.pickup_id WHERE up.user_id = " + userId + ";";

            System.out.println(sql);
           
            ResultSet rs2 = stmt.executeQuery(sql);

            int lineCount = 0;
            
            
            while (rs2.next()) {
                String line = rs2.getString("content");
                System.out.println(line);
                lines.add(line);
                lineCount++;
            }
            rs2.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {

        }
        return lines;
    }

    public boolean existUsername(String username, String password, String displayName) throws ClassNotFoundException, SQLException {
        int userCount = 0;
        try {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            String DB_URL = "jdbc:mysql://localhost/flirt_machine";

            //Database credentials
            String USER = "flirt";
            String PASS = "flirt-pass";

            Connection conn = null;
            Statement stmt = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user WHERE username='" + username + "';";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                userCount++;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {

        }
        if (userCount > 0) {
            return true;
        } else {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            String DB_URL = "jdbc:mysql://localhost/flirt_machine";

            //Database credentials
            String USER = "flirt";
            String PASS = "flirt-pass";

            Connection conn = null;
            Statement stmt = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO user (username, password, name) VALUES('" + username + "', '" + password + "', '" + displayName + "');";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

            return false;
        }
    }

    public String[] getUserByUsernamePassword(String username, String password) {
        String name = "";
        String myUsername = "";
        String id = "";
        try {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            String DB_URL = "jdbc:mysql://localhost/flirt_machine";

            //Database credentials
            String USER = "flirt";
            String PASS = "flirt-pass";

            Connection conn = null;
            Statement stmt = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "';";
            ResultSet rs = stmt.executeQuery(sql);

            int userCount = 0;

            while (rs.next()) {
                name = rs.getString("name");
                id = Integer.toString(rs.getInt("user_id"));
                userCount++;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {

        }
        if (name != "") {
            String[] myUser = new String[3];
            myUser[0] = name;
            myUser[1] = username;
            myUser[2] = id;

            return myUser;
        } else {
            String[] myUser = new String[1];

            myUser[0] = "notauser";
            return myUser;
        }
    }
}
