/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flirtMachine;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jbeag_000
 */
@WebServlet(name = "SignIn", urlPatterns = {"/SignIn"})
public class SignIn extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        if (request.getSession().getAttribute("loggedIn") != null) {
            if (request.getSession().getAttribute("loggedIn") == "true") {
                request.getRequestDispatcher("myList.jsp").forward(request, response); //check the jsp name
            }
            else {  
                boolean signInCorrect = false;
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String name = "";//get the full name from the database
                int id = 0;

                //verify that the username and password are correct
                //
                //
                //
                String passId = request.getParameter("id");
                System.out.println(passId);

                String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
                String DB_URL = "jdbc:mysql://localhost/flirt_machine";

                //Database credentials
                String USER = "flirt";
                String PASS = "flirt-pass";

                Connection conn = null;
                Statement stmt = null;        

                Class.forName("com.mysql.jdbc.Driver");

                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

                System.out.println("Creating statement...");
                stmt = conn.createStatement();
                String sql;
                sql = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "';";
                ResultSet rs = stmt.executeQuery(sql);

                int userCount = 0;

                while(rs.next()){
                    name = rs.getString("name");
                    id = rs.getInt("user_id");           
                    userCount++;
                }
                rs.close();
                stmt.close();
                conn.close();

                System.out.println("name: " + name + ", username: " + username + ", id: " + id);

                if (userCount > 0) {
                    signInCorrect = true;
                }

                if (signInCorrect) {
                    request.setAttribute("currentUserName", username);
                    request.setAttribute("currentName", name);
                    request.setAttribute("currentId", id);
                    request.getSession().setAttribute("correctLogin", "true");
                    request.getSession().setAttribute("loggedIn", "true");
                    System.out.println("correctLogin");

                    request.getRequestDispatcher("myList.jsp").forward(request, response);             
                }
                else {
                    request.getSession().setAttribute("correctLogin", "false");
                    request.getSession().setAttribute("loggedIn", "false");
                    request.getRequestDispatcher("signIn.jsp").forward(request, response);
                }        
            }
        }
        else {
            boolean signInCorrect = false;
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = "";//get the full name from the database
            int id = 0;

            //verify that the username and password are correct
            //
            //
            //
            String passId = request.getParameter("id");
            System.out.println(passId);

            String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
            String DB_URL = "jdbc:mysql://localhost/flirt_machine";

            //Database credentials
            String USER = "flirt";
            String PASS = "flirt-pass";

            Connection conn = null;
            Statement stmt = null;        

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "';";
            ResultSet rs = stmt.executeQuery(sql);

            int userCount = 0;

            while(rs.next()){
                name = rs.getString("name");
                id = rs.getInt("user_id");           
                userCount++;
            }
            rs.close();
            stmt.close();
            conn.close();

            System.out.println("name: " + name + ", username: " + username + ", id: " + id);

            if (userCount > 0) {
                signInCorrect = true;
            }

            if (signInCorrect) {
                request.setAttribute("currentUserName", username);
                request.setAttribute("currentName", name);
                request.setAttribute("currentId", id);
                request.getSession().setAttribute("correctLogin", "true");
                request.getSession().setAttribute("loggedIn", "true");
                System.out.println("correctLogin");

                request.getRequestDispatcher("myList.jsp").forward(request, response);             
            }
            else {
                request.getSession().setAttribute("correctLogin", "false");
                request.getSession().setAttribute("loggedIn", "false");
                request.getRequestDispatcher("signIn.jsp").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
