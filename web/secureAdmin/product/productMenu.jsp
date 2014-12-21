
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
            <h1>Product List</h1>

            <form action="../../ProductServlet">
                <select name="searchCriteria">
                    <option value="productCode"> Product Code</option>
                    <option value="productName" > Product Name</option>
                    <option value="productDesciption"> Product Desciption</option>
                    <option value="productPrice"> Product Price</option>
                    <option value="productType"> Product Type</option>
                    <option value="full" selected="selected">All</option>
                </select>
                <input type="text" name="searchValue" value="" /><input type="submit" value="Search" name="action"/>
            </form>


            <table border="1" style="table-layout: fixed;width: 100%">
                <thead>
                    <tr>


                        <th>Product Code</th>
                        <th>Product Name</th>
                        <th>Product Desciption</th>
                        <th>Product Price</th>
                        <th>Product Type</th>
                    </tr>
                </thead>
                <tbody>


                    <% for (Product prod : products) {%>
                    <tr>
                        <td><%=prod.getProductCode()%></td>
                        <td><%=prod.getProductName()%></td>
                        <td style="word-wrap: break-word"><%=prod.getProductDesciption()%></td>
                        <td><%=prod.getProductPrice()%></td>
                        <td><%=prod.getProductType()%></td>

                    </tr>
                    <% }%>
                </tbody>
            </table>

            <br/>
            <a href="addProduct.jsp"><img src="../../img/addlogo.jpg" alt="add"/></a>
            <a href="selectProduct.jsp"><img src="../../img/managelogo.jpg" alt="manage"/></a>

        </div>
    </body>
</html>
