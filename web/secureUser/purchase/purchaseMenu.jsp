<%-- 
    Document   : purchaseMenu
    Created on : Dec 15, 2014, 3:05:05 PM
    Author     : Kevin
--%>

<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <jsp:useBean id="loginMember" scope="session" class="model.Member" />
            <%
                List<Product> products = (List) session.getAttribute("allProduct");
            %>

            <h2>Welcome, ${loginMember.memberName}</h2>
            <form action="../../getProductList">
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


            <form action="../../processPurchase">
                <table border="1" style="table-layout: fixed;width: 100%">
                    <thead>
                        <tr>
                            <th>Product Code</th>
                            <th>Product Name</th>
                            <th>Product Desciption</th>
                            <th>Product Price</th>
                            <th>Product Type</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody>


                        <%
                            int[] productQtyList = new int[products.size()];
                            for (int i = 0; i < products.size(); i++) {%>
                        <tr>
                            <td><%=products.get(i).getProductCode()%></td>
                            <td><%=products.get(i).getProductName()%></td>
                            <td style="word-wrap: break-word"><%=products.get(i).getProductDesciption()%></td>
                            <td><%=products.get(i).getProductPrice()%></td>
                            <td><%=products.get(i).getProductType()%></td>
                            <td><input type="text" class="quantity" name="<%= "productQtyList[" + i + "]"%>" value="0"  /></td>

                        </tr>
                        <% }%>
                        <tr><td colspan="6">
                                <input type="submit" value="Submit" /></td></tr>
                    </tbody>
                </table>

            </form>
        </div></body></html>
