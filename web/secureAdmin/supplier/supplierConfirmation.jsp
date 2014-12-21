<%@page import="model.Supplier" %>
<%@page import="java.util.List" %>
<%session.removeAttribute("supplier");%>
<jsp:useBean id="supplier" scope="session" class="model.Supplier" />
<jsp:setProperty name="supplier" property="*"/>

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



            <%String action = request.getParameter("action");%>

            <h1><%=action%> Supplier Confirmation</h1>
            <%    if (action.equalsIgnoreCase("Delete")) { %>
            <h2 style="color: red">Are you sure you want to delete?</h2>
            <% } else {%>
            <h2>Is the data correct?</h2>
            <%}%>
            <form method="POST" action="../../SupplierServlet">
                <table>

                    <tr>
                        <td>Supplier ID</td>
                        <td>${supplier.supplierId}</td>
                    </tr>
                    <tr>
                        <td>Supplier Name</td>
                        <td>${supplier.supplierName}</td>
                    </tr>
                    <tr>
                        <td>Supplier Address</td>
                        <td>${supplier.supplierAddress}</td>
                    </tr>
                    <tr>
                        <td>Supplier Tel Number</td>
                        <td>${supplier.supplierTelNo}</td>
                    </tr>
                    <tr>
                        <td>Supplier Email</td>
                        <td>${supplier.supplierEmail}</tr>

                    <tr><td colspan="2">

                            <input type="submit" value="<%= action%>" name="action" />
                            <input type="submit" value="Cancel" name="action" /></td></tr>
                </table>
            </form>



        </div>
    </body>
</html>
