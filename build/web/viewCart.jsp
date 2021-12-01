<%-- 
    Document   : viewCart.jsp
    Created on : Oct 13, 2021, 1:59:12 PM
    Author     : Thao
--%>

<%--<%@page import="java.util.Map"%>
<%@page import="thaontt.cart.CartObject"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <h2>Your cart includes: </h2>
        
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">            
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Title</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <form action="DispatchServlet">
                            <c:forEach var="item" items="${cart.item}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${item.key}
                                </td>
                                <td>
                                    ${item.value}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkItem" value="${item.key}" />
                                </td>
                            </tr>
                            </c:forEach>
                            <tr>
                                    <td colspan="3">
                                        <a href="shopping.html">Add More Items to your Cart</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove seclected Items" name="btAction" />
                                    </td>
                                </tr>
                                </form>
                        </tbody>   
                    </table>
        </c:if>
        <c:if test="${empty cart}">
            <h2>
            No cart is existed!
        </h2>
        </c:if>
        <%--
        <%  
            //1. cust goes to his/her cart
            if(session != null){
                //2. take cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if(cart != null){
                    //3. take item
                    Map<String, Integer> items = cart.getItem();
                    if(items != null){
                        %>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>NO.</th>
                                    <th>Title</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <form action="DispatchServlet">
                            <tbody>
                                <% 
                                    int count = 0;
                             for (String title : items.keySet()){
                                 %>
                                <tr>
                                    <td>
                                        <%= ++count %>
                                    </td>
                                    <td>
                                        
                                        <%= title %>
                                    </td>
                                    <td>
                                        <%= items.get(title) %>
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkItem" 
                                               value="<%= title %>" />
                                    </td>
                                </tr>
                                <%
                             }//end 
                                %>
                                <tr>
                                    <td colspan="3">
                                        <a href="shopping.html">Add Move Items to your Cart</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove Selected" name="btAction" />
                                    </td>
                                </tr>
                            </tbody>
                            </form>
                        </table>

        <%
                        return;
                    }//end if items has already existed
                }//cart is exist
            }//end if cust has already added books
        %>
        
        <h2>
            No cart is existed!
        </h2>--%>
    </body>
</html>
