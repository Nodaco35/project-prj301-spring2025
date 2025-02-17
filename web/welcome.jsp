<%-- 
    Document   : welcome
    Created on : Feb 17, 2025, 11:33:09 AM
    Author     : NC PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Account" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int roleId = (int)request.getAttribute("roleId");
            String accountId = (String)request.getAttribute("accountId");
            String pass = (String)request.getAttribute("pass");
            Account ac = (Account)request.getAttribute("ac");
            out.print(ac.getAccountId());
            if(roleId == 1) {
        %>
        <h1>Hello ADMIN</h1>
        <h2><%= accountId %></h2>
        <h2><%= pass %></h2>
        <% 
            }else if(roleId == 2) {
        %>
        <h1>Hello Student</h1>
        <h2><%= accountId %></h2>
        <h2><%= pass %></h2>
        <% }%>
    </body>
</html>
