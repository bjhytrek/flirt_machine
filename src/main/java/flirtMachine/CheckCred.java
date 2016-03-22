/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flirtMachine;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
@WebServlet(name = "CheckCred", urlPatterns = {"/CheckCred"})
public class CheckCred extends HttpServlet {

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
                System.out.println("here1");
                checkCredentials(request, response);
            }
        }
        else {
            System.out.println("here2");
            checkCredentials(request, response);
        }
    }
    
    public void checkCredentials(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        boolean signInCorrect = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = "";//get the full name from the database
        int id = 0;

        GetUsers userGetter = new GetUsers();
        String[] user = userGetter.getUserByUsernamePassword(username, password);
        
        if (user[0].equals("notauser")) {
            signInCorrect = false;            
            request.getSession().setAttribute("correctLogin", "false");
            request.getSession().setAttribute("loggedIn", "false");
            response.setHeader("Cache-Control", "no-cache");        
            response.getWriter().write("incorrect");
        }
        else {            
            request.setAttribute("currentUserName", username);
            request.setAttribute("currentName", name);
            request.setAttribute("currentId", id);
            request.getSession().setAttribute("correctLogin", "true");
            request.getSession().setAttribute("loggedIn", "true");
            signInCorrect = true;
            name = user[0];
            id = Integer.parseInt(user[2]);
            response.setHeader("Cache-Control", "no-cache");        
            response.getWriter().write("correct");
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
            Logger.getLogger(CheckCred.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CheckCred.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CheckCred.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CheckCred.class.getName()).log(Level.SEVERE, null, ex);
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
