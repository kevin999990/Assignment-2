<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%session.removeAttribute("product");%>
<jsp:useBean id="product" scope="session" class="model.Product" />
<jsp:setProperty name="product" property="*"/>
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
            <h1><%=action%> Product Confirmation</h1>
            <%    if (action.equalsIgnoreCase("Delete")) { %>
            <h2 style="color: red">Are you really sure want to delete?</h2>
            <% } else {%>
            <h2>Is the data correct?</h2>
            <%}%>
            <form method="POST" action="../../ProductServlet">
                <table >

                    <tr>
                        <td>Product Code</td>
                        <td>${product.productCode}</td>
                    </tr>
                    <tr>
                        <td>Product Name</td>
                        <td>${product.productName}</td>
                    </tr>
                    <tr>
                        <td>Product Desciption</td>
                        <td>${product.productDesciption}</tr>
                    <tr>
                        <td>Product Price</td>
                        <td>${product.productPrice}</td>
                    </tr>
                    <tr>
                        <td>Product Type</td>
                        <td>${product.productType}</tr>


                    <tr><td colspan="2">
                            <input type="submit" value="<%= action%>" name="action" />
                            <input type="submit" value="Cancel" name="action" /></td></tr></table>
            </form>

        </div>
    </body>
</html>
