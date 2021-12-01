<%-- 
    Document   : signUp.jsp
    Created on : Oct 15, 2021, 1:52:15 PM
    Author     : Thao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="DispatchServlet" method="POST">
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(e.g, 6-30 chars)<br/>
             <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameLengthErr}
                </font><br/>
            </c:if>
                Password* <input type="password" name="txtPassword" value="${param.txtPassword}" /> (e.g, 6-20 chars)<br/> 
            <c:if test="${not empty errors.passwpordLengthErr}">
                <font color="red">
                ${errors.passwpordLengthErr}
                </font><br/>
            </c:if>
            Confirm <input type="password" name="txtConfirm" value="${param.txtPassword}" /><br/> 
            <c:if test="${not empty errors.confirmNotMatchPassword}">
                <font color="red">
                ${errors.confirmNotMatchPassword}
                </font><br/>
            </c:if>
            Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" /> (e.g, 2-50 chars)<br/>
            <c:if test="${not empty errors.fullNameLengthErr}">
                <font color="red">
                ${errors.fullNameLengthErr}
                </font><br/>
            </c:if>
            <input type="submit" value="Create new account" name="btAction" />
            <input type="reset" value="Reset" />
        </form> <br/>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                    ${errors.usernameIsExisted}
                    </font></br>
            </c:if>
    </body>
</html>
