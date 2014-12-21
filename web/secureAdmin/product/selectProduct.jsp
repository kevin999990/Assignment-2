<%@page import="java.util.List"%>
<%@page import="model.Product"%>

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
                List<Product> products = (List) session.getAttribute("allProduct");
            %>
            <form action="manageProduct.jsp" method="POST">
                <select name="drp_product">
                    <% for (Product sup : products) {%>
                    <option value="<%=sup.getProductCode()%>"><%=sup.getProductName()%></option>
                    <% }%> 
                </select>
                <input type="submit" value="Select" />
            </form>

        </div>
    </body>
</html>
