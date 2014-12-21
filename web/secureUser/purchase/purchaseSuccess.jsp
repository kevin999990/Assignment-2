<%-- 
    Document   : purchaseSuccess
    Created on : Dec 15, 2014, 5:48:19 PM
    Author     : Kevin
--%>

<%@page import="model.Invoice"%>
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
            <%
                Invoice invoice = (Invoice) session.getAttribute("invoice");
            %>

            <h2> Name: <%=invoice.getMemberId().getMemberName()%>
                <br/>
                Discount Rate: <%=invoice.getMemberId().getMemberType().getMembershipDiscountRate()%></h2>
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

                        for (int i = 0; i < invoice.getPurchaseList().size(); i++) {%>
                    <tr>
                        <td><%=invoice.getPurchaseList().get(i).getProductCode().getProductCode()%></td>
                        <td><%=invoice.getPurchaseList().get(i).getProductCode().getProductName()%></td>
                        <td style="word-wrap: break-word"><%=invoice.getPurchaseList().get(i).getProductCode().getProductDesciption()%></td>
                        <td><%=invoice.getPurchaseList().get(i).getProductCode().getProductPrice()%></td>
                        <td><%=invoice.getPurchaseList().get(i).getProductCode().getProductType()%></td>
                        <td><%=invoice.getPurchaseList().get(i).getPurchaseQuantity()%></td>
                    </tr>

                    <% }%>
                    <tr>
                        <td colspan="4" style="text-align:right ">Total Price:</td><td colspan="2"><%=String.format("%.2f", invoice.totalPrice())%></td></tr>
                    <tr><td colspan="4" style="text-align:right ">After Discount:</td><td colspan="2"><%=String.format("%.2f", invoice.discountPrice())%></td>
                    </tr>
                </tbody>
            </table>
        </div></body></html>