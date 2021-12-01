<%-- 
    Document   : search
    Created on : Oct 4, 2021, 1:06:17 PM
    Author     : Thao
--%>

<%--<%@page import="thaontt.DAO.RegistrationDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="orange">
        Welcome, ${sessionScope.USERID}
        <a href="DispatchServlet?btAction=logout">Logout</a>
        </font>
        <h1>Search page</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" /><br/> <%--para la viet tat cua request. parameter --%>
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        
        <c:set var="searchValue" value="${param.txtSearchValue}"/> 
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}"><%-- resilt kieu list DTO--%>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full name</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                            <form action="DispatchServlet">
                                <tr>
                                <td>
                                    ${counter.count}    
                                .</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                            <input type="checkbox" name="chkAdmin" value="ON"
                            <c:if test="${dto.role}">
                            checked="checked"
                            </c:if>
                            />
                        </td>
                                <td>
                            <c:url var="urlRewriting" value="DispatchServlet">
                                <c:param name="btAction" value="delete"/>
                                <c:param name="pk" value="${dto.username}"/>
                                <c:param name="lastSearchValue" value="${searchValue}"/>
                                <%-- tham cheu nho dinh tri bang dau dola,
                                co them ssearch vi khi ddelete thi respone da tra ve
                                tuc la server xoa het du lieu
                                vi vay can tao moi 1 param de dua vao trang search--%>
                            </c:url>
                            <a href="${urlRewriting}">Delete</a>
                        </td>
                        <td>
                            <input type="submit" value="Update" name="btAction" />
                            <input type="hidden" name="lastSearchValue" 
                                   value="${searchValue}" />
                        </td>
                            </tr>
                            </form>
                            </c:forEach>
                        </tbody>
                    </table>
            </c:if>
            <c:if test="${empty result}">
                    <h2>
                        No record is matched!!!
                    </h2>
                </c:if>
        </c:if>
        <%--<% //get all cookies from domai
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                //get last cookie
                Cookie lastCookie = cookies[cookies.length - 1];

        %> 
        <font color="orange">
        Welcome <%= lastCookie.getName() %>
                        </font>
                        <%
            }//end if cookies is existed
                    %>
        <h1>Search page</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value="<%= request.getParameter("txtSearchValue")%>" /><br/>
            Search <input type="submit" value="Search" name="btAction" />
        </form><br/>

        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result
                        = (List<RegistrationDTO>) request.getAttribute("SEARCHRESULT");
                if (result != null) {
        %> 
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full Name</th>
                    <th>Role</th>  
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchServlet"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue= " + searchValue;
                %> 
            <form action="DispatchServlet">
                <tr>
                    <td>
                        <%= ++count%>
                        .</td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" 
                               value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" 
                               value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getFullName()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ON" 
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                               }//end admin role is true
%>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btAction" />
                        <input type="hidden" name="lastSearchValue" 
                               value="<%= searchValue%>" />
                    </td>
                </tr>  
            </form>
            <%
                }//end for traverse DTO to show
            %>
        </tbody>
    </table>

    <%
    } else {
    %>
    <h2>
        no record is matched!
    </h2>
    <%
            }
        }//end if search value had inputted
%>--%>
</body>
</html>
