<%-- 
    Document   : index
    Created on : Feb 17, 2025, 11:08:11 AM
    Author     : NC PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Login" method="post">
        <body>
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="userName"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="text" name="userPwrd"></td>
                </tr>
            
                <tr>
                    <td></td>
                    <td><input type="submit" value=" Login"></td>
                </tr>

            
            <% String error = (String)request.getAttribute("error") ;
                if(error!=null)  {
                    
            %>
                <tr>
                    <td><% out.print("Error: "); %></td>
                    <td><p style="font-style: italic;display: inline"><% out.print(error); %></p></td>
                </tr>
            <% }%>
            </table>
    </form>
    </body>
</html>
