/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thaontt.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.jasper.tagplugins.jstl.core.Catch;
import thaontt.DAO.RegistrationCreateError;
import thaontt.DAO.RegistrationDAO;

/**
 *
 * @author Thao
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    private final String ERROR_PAGE = "signUp.jsp";
    private final String LOGIN_PAGE = "login.html";

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");
        boolean error = false;
        RegistrationCreateError errors = new RegistrationCreateError();
        String url = ERROR_PAGE;
        
        try  {
            //1. checking all user's errors
            
            if(username.trim().length() <6 || username.trim().length() > 30){
                error = true;
                errors.setUsernameLengthErr("Username must be from 6 to 30 characters!");
            }if(password.trim().length() <6 || password.trim().length() > 20){
                error = true;
                errors.setPasswpordLengthErr("Password must be from 6 to 20 characters!");
            } 
            else if(!confirm.trim().equals(password.trim())){
                error = true;
                errors.setConfirmNotMatchPassword("Confirm must match password!");
            }//chi duoc bat khi nao password dung
            //le thuoc vao password
            if(fullName.trim().length() <2 || fullName.trim().length() > 50){
                error = true;
                errors.setFullNameLengthErr("fullname is required from 2 to 50 characters!");
            }
            if(error){
                request.setAttribute("CREATE_ERRORS", errors);
                //luu tru loi len request scope
            }else{
                // khong loi thi insert xuong data base
                //2. Assume that no errors. Insert data to DB
                //call DAO
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.insertAccount(username, fullName, password, false);
             //3. Assume that no errors occur. redirect to login page
            if(result){
                url = LOGIN_PAGE;
                }
            }
            
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }catch(SQLException ex){
            String msg = ex.getMessage();
                    log("CreateAccountServlet _ SQL " + msg);
                     //3.1 if error occured, showing errors to user page
                    if(msg.contains("duplicate")){
                        errors.setUsernameIsExisted(username+ " is existed");
                        //sau khi bat loi phai cap nhat attribute
                        request.setAttribute("CREATE_ERRORS", errors);
                        
                    }
            }catch(NamingException ex){
                    log("CreateAccountServlet _ Naming " + ex.getMessage());
                    errors.setUsernameIsExisted(username + "username valed!");
                    
                    request.setAttribute("CREATE_ERRORS", errors);
        } finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            // vi bao dua loi len, neu dung send di... thi no se huy scope
            rd.forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
