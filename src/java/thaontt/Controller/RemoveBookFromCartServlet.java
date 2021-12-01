package thaontt.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thaontt.cart.CartObject;

/**
 *
 * @author Thao
 */
@WebServlet(urlPatterns = {"/RemoveBookFromCartServlet"})
public class RemoveBookFromCartServlet extends HttpServlet {

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
        try {
           //1. go to the his/her cart
            HttpSession session = request.getSession(false);
            if(session != null){
                //take cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if(cart != null){
                    //take item
                    Map<String, Integer> items = cart.getItem();
                    if(items != null){
                        //take selected items
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if(selectedItems != null){
                            //5. remove item
                            for (String item  : selectedItems){
                                cart.removeItemFromCart(item);
                            }
                            //6.update cart into session
                            session.setAttribute("CART", cart);
                        }//end if end user had chosen atleast item
                    }// end if items..
                }//end if cart had existed
            }//end if user exited
        }finally{
            String urlRewriting = "DispatchServlet?btAction=View your cart";
            response.sendRedirect(urlRewriting); // only vi cai kia se tra them 1 respone
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
