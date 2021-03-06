/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flirtMachine;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

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
            String USER = "";
            String PASS = "";
            String DB_URL = "";
            String openShiftCheck = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            
            if (openShiftCheck == null || openShiftCheck == "") {
                DB_URL = "jdbc:mysql://localhost/flirt_machine";
                USER = "flirt";
                PASS = "flirt-pass"; 
            }
            else {
                String HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
                String PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
                DB_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/flirt_machine";
                USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                PASS = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            }

            Connection conn = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println(pickUpLine);
            String sql;
            sql = "INSERT INTO pickup (content) VALUES (?);";//(\'" + pickUpLine + "\');";// INSERT INTO user_pickup (user_id, pickup_id) VALUES (" + userId + ", (SELECT pickup_id FROM pickup WHERE pickup_id = (SELECT max(pickup_id) FROM pickup))); ";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, pickUpLine);
            
            System.out.println(sql);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("completed");
            
            conn.close();
            System.out.println("completed2");
            // 
            conn = null;
            Statement stmt = null;
            System.out.println("completed3");
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("completed4");
            System.out.println(pickUpLine);
            stmt = conn.createStatement();
            sql = "SELECT max(pickup_id) AS pickup_id FROM pickup;";
            System.out.println("completed5");
            System.out.println(sql);

            ResultSet rs = stmt.executeQuery(sql);
            int insertIndex = -1;
            System.out.println(insertIndex);
            while (rs.next()) {
                insertIndex = rs.getInt("pickup_id");
            }
            System.out.println("index: " + insertIndex);
            
            conn = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            System.out.println("completed6");
            System.out.println(pickUpLine);
            sql = "INSERT INTO user_pickup (user_id, pickup_id) VALUES (?, ?);";//" + userId + ", " + insertIndex + ");";
            
            System.out.println("completed7");
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql);
            preparedStatement2.setInt(1, userId);
            preparedStatement2.setInt(2, insertIndex);
            
            System.out.println(sql);

            rowsAffected = preparedStatement2.executeUpdate();

            //rs.close();
            conn.close();
        } catch (Exception e) {

        }
    }
    
    public List<PickupItem> getPickupLineNotMine(int userId) {
        List<PickupItem> lines = new ArrayList<>();
        System.out.println("getPickupLine");
        try {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            String USER = "";
            String PASS = "";
            String DB_URL = "";
            String openShiftCheck = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            
            if (openShiftCheck == null || openShiftCheck == "") {
                DB_URL = "jdbc:mysql://localhost/flirt_machine";
                USER = "flirt";
                PASS = "flirt-pass"; 
            }
            else {
                String HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
                String PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
                DB_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/flirt_machine";
                USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                PASS = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            }

            Connection conn = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql;
            sql = "SELECT p.content, p.pickup_id, up.rate FROM user_pickup up INNER JOIN pickup p ON up.pickup_id = p.pickup_id WHERE up.user_id <> ? ORDER BY up.rate DESC;";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            
            System.out.println(sql);
           
            ResultSet rs2 = preparedStatement.executeQuery();

            int lineCount = 0;
            
            
            while (rs2.next()) {
                System.out.println("inside the while");
                int id = rs2.getInt("pickup_id");
                int rating;
                boolean isRated;
                if (rs2.getInt("rate") > 0) {
                    rating = rs2.getInt("rate");
                    isRated = true;
                }
                else {
                    rating = 0;
                    isRated = false;
                }
                System.out.println(id);
                String line = rs2.getString("content");
                System.out.println(line);
                PickupItem pickup = new PickupItem(id, line, rating, isRated);
                lines.add(pickup);
                lineCount++;
            }
//            System.out.println(lines.get(0).id);
            rs2.close();
            conn.close();
        } catch (Exception e) {

        }
        return lines;     
    }
    
    public List<PickupItem> getPickupLine(int userId) {
        List<PickupItem> lines = new ArrayList<>();
        System.out.println("getPickupLine");
        try {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            String USER = "";
            String PASS = "";
            String DB_URL = "";
            String openShiftCheck = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            
            if (openShiftCheck == null || openShiftCheck == "") {
                DB_URL = "jdbc:mysql://localhost/flirt_machine";
                USER = "flirt";
                PASS = "flirt-pass"; 
            }
            else {
                String HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
                String PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
                DB_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/flirt_machine";
                USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                PASS = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            }

            Connection conn = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql;
            sql = "SELECT p.content, p.pickup_id, up.rate FROM user_pickup up INNER JOIN pickup p ON up.pickup_id = p.pickup_id WHERE up.user_id = ? ORDER BY up.rate DESC;";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            
            System.out.println(sql);
           
            ResultSet rs2 = preparedStatement.executeQuery();

            int lineCount = 0;
            
            
            while (rs2.next()) {
                System.out.println("inside the while");
                int id = rs2.getInt("pickup_id");
                int rating;
                boolean isRated;
                if (rs2.getInt("rate") > 0) {
                    rating = rs2.getInt("rate");
                    isRated = true;
                }
                else {
                    rating = 0;
                    isRated = false;
                }
                System.out.println(id);
                String line = rs2.getString("content");
                System.out.println(line);
                PickupItem pickup = new PickupItem(id, line, rating, isRated);
                lines.add(pickup);
                lineCount++;
            }
//            System.out.println(lines.get(0).id);
            rs2.close();
            conn.close();
        } catch (Exception e) {

        }
        return lines;
    }
    public void addRating(int user_id, int pickup_id, int star_count) throws ClassNotFoundException, SQLException{
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            String USER = "";
            String PASS = "";
            String DB_URL = "";
            String openShiftCheck = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            
            if (openShiftCheck == null || openShiftCheck == "") {
                DB_URL = "jdbc:mysql://localhost/flirt_machine";
                USER = "flirt";
                PASS = "flirt-pass"; 
            }
            else {
                String HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
                String PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
                DB_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/flirt_machine";
                USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                PASS = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            }

            Connection conn = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql;
            sql = "UPDATE user_pickup SET rate = " + star_count + " WHERE user_id = " + user_id + " AND pickup_id = " + pickup_id + ";";
            
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            
            System.out.println(sql);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("completed");
            
            conn.close();
    }

    public boolean existUsername(String username, String password, String displayName) throws ClassNotFoundException, SQLException {
        int userCount = 0;
        try {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            String USER = "";
            String PASS = "";
            String DB_URL = "";
            String openShiftCheck = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            
            if (openShiftCheck == null || openShiftCheck == "") {
                DB_URL = "jdbc:mysql://localhost/flirt_machine";
                USER = "flirt";
                PASS = "flirt-pass"; 
            }
            else {
                String HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
                String PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
                DB_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/flirt_machine";
                USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                PASS = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            }
            
            password = BCrypt.hashpw(password, BCrypt.gensalt());
            System.out.println(password);
            
            Connection conn = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            String sql;
            sql = "SELECT * FROM user WHERE username=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                userCount++;
            }
            rs.close();
            conn.close();
        } catch (Exception e) {

        }
        if (userCount > 0) {
            return true;
        } else {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            String USER = "";
            String PASS = "";
            String DB_URL = "";
            String openShiftCheck = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            
            if (openShiftCheck == null || openShiftCheck == "") {
                DB_URL = "jdbc:mysql://localhost/flirt_machine";
                USER = "flirt";
                PASS = "flirt-pass"; 
            }
            else {
                String HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
                String PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
                DB_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/flirt_machine";
                USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                PASS = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            }
            Connection conn = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            String sql;
            sql = "INSERT INTO user (username, password, name) VALUES(?, ?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, displayName);
            preparedStatement.executeUpdate();

            conn.close();

            return false;
        }
    }
    
    public String[] getUserByUsernamePassword(String username) {
        String name = "";
        String myUsername = "";
        String id = "";
        String password = "";
        try {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            String USER = "";
            String PASS = "";
            String DB_URL = "";
            String openShiftCheck = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            
            if (openShiftCheck == null || openShiftCheck == "") {
                DB_URL = "jdbc:mysql://localhost/flirt_machine";
                USER = "flirt";
                PASS = "flirt-pass"; 
            }
            else {
                String HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
                String PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
                DB_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/flirt_machine";
                USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                PASS = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            }

            Connection conn = null;

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            String sql;
            sql = "SELECT * FROM user WHERE username=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            int userCount = 0;

            while (rs.next()) {
                name = rs.getString("name");
                id = Integer.toString(rs.getInt("user_id"));
                password = rs.getString("password");
                userCount++;
            }
            rs.close();
            conn.close();
        } catch (Exception e) {

        }
        if (name != "") {
            String[] myUser = new String[4];
            myUser[0] = name;
            myUser[1] = username;
            myUser[2] = id;
            myUser[3] = password;

            return myUser;
        } else {
            String[] myUser = new String[1];

            myUser[0] = "notauser";
            return myUser;
        }
    }
}
