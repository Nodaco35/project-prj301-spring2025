package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import dao.*;

/**
 *
 * @author admin
 */
public class LoginServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
   try (PrintWriter out = response.getWriter()) {
       String username = request.getParameter("userName");
       String password = request. getParameter("userPwrd");
       Account account = AccountDAO.getAccountByUserAndPassword(username, password);
       
       if (username.isBlank() || password.isBlank()) {
           out.println("<h2>Error</h2>");
           
       } else if(account !=null && account.getRoleId() == 1) {
           out.println("<h1>Hello Admin</h1>");
          out.println("<h2>UserName: " + username + "</h2>");
          out.println("<h2>Password: " + password + "</h2>");
          
       }  else if(account !=null && account.getRoleId() == 2) {
           out.println("<h1>Hello Student</h1>");
          out.println("<h2>UserName: " + username + "</h2>");
          out.println("<h2>Password: " + password + "</h2>");
                 

       }else{
           out.println("<h2>ERROR: Not match</h2>");

       }
       
   }
    
    
    
    
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
