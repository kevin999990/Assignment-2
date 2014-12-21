

<%@page import="java.util.List"%>
<%@page import="model.Membership"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% session.removeAttribute("membership");%>
<jsp:useBean id="membership" scope="session" class="model.Membership" />
<jsp:setProperty name="membership" property="*"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Membership</title>
        <link href="${pageContext.request.contextPath}/css/navbar.css"  rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%@include file="../../index.html" %>  
        <div id="container">
            <%
                if (membership.getMembershipType().isEmpty() || Character.isDigit(membership.getMembershipType().charAt(0))) {
                    response.sendRedirect("/EuYanSang/error/invalidInput.html");
             return;
         }%>

            <% String action = request.getParameter("action");%>

            <h1><%=action%> Membership Confirmation</h1>
            <%    if (action.equalsIgnoreCase("Delete")) { %>
            <h2 style="color: red">Are you really sure want to delete?</h2>
            <% } else {%>
            <h2>Is the data correct?</h2>
            <%}%>
            <form method="POST" action="../../MembershipServlet">
                <table>

                    <tr>
                        <td>Membership Type:</td>
                        <td>${membership.membershipType}</td>
                    </tr>
                    <tr>
                        <td>Membership Fee:</td>
                        <td>${membership.membershipFee}</td>
                    </tr>

                    <tr>
                        <td>Membership Discount Rate:</td>
                        <td>${membership.membershipDiscountRate}</td>
                    </tr>
                    <tr>
                        <td>Membership Describe:</td>
                        <td>${membership.membershipDescribe}</td>
                    </tr>


                    <tr><td colspan="2">

                            <input type="submit" value="<%= action%>" name="action" />
                            <input type="submit" value="Cancel" name="action" />
                        </td></tr>
                </table>
            </form>

        </div>
    </body>
</html>
