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
            <h1>Manage Product</h1>

            <%
                Product product = new Product(request.getParameter("drp_product"));
                List<Product> products = (List) session.getAttribute("allProduct");

                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).getProductCode().equals(product.getProductCode())) {
                        product = products.get(i);
                    }
                }
            %>


            <form action="productConfirmation.jsp">
                <table >

                    <tbody>
                        <tr><td>Product Code:</td>
                            <td><input name="productCode" value="<%=product.getProductCode()%>" readonly="readonly"/></td>
                        </tr>
                        <tr>
                            <td>Product Name:</td>
                            <td><input  name="productName" value="<%=product.getProductName()%>" maxlength="50"/></td>
                        </tr>
                        <tr>
                            <td>Product Desciption:</td>
                            <td><textarea name="productDesciption" rows="4" cols="23"><%=product.getProductDesciption()%></textarea></td>
                        </tr>
                        <tr>
                            <td>Product Price:</td>
                            <td><input  name="productPrice" value="<%=product.getProductPrice()%>" /></td>
                        </tr>
                        <tr>
                            <td>Product Type:</td>
                            <td><input  name="productType" value="<%=product.getProductType()%>" maxlength="50" /></td>
                        </tr>

                        <tr><td colspan="2">
                                <input type="submit" value="Update" name="action" />
                                <input type="submit" value="Delete" name="action" /> 
                            </td></tr>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
