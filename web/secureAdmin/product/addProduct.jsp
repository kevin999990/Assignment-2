
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Product" %>
<%@page import="java.util.List" %>

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
            <h1>Add new Product</h1>

            <form action="productConfirmation.jsp" method="get">
                <table >

                    <tbody>
                        <tr><td>Product Code:</td>
                            <td><input name="productCode" value="" placeholder="BEE000" maxlength="6" required="required"/></td>
                        </tr>
                        <tr>
                            <td>Product Name:</td>
                            <td><input type="text" name="productName" value="" placeholder="Ginseng" maxlength="50"/></td>
                        </tr>
                        <tr>
                            <td>Product Desciption</td>
                            <td><textarea name="productDesciption" rows="4" cols="22" ></textarea></td>
                        </tr>
                        <tr>
                            <td>Product Price:</td>
                            <td><input type="text" name="productPrice" value="0.0" /></td>
                        </tr>
                        <tr>
                            <td>Product Type:</td>
                            <td><input type="text" name="productType" value="" placeholder="Honey" maxlength="10"/></td>
                        </tr>
                        <tr><td colspan="2"><input type="submit" value="Add" name="action" /></td></tr>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
