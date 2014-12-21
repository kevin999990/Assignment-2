<%-- 
    Document   : addMembership
    Created on : Dec 15, 2014, 8:55:55 PM
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Membership</title>
        <link href="${pageContext.request.contextPath}/css/navbar.css"  rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%@include file="../../index.html" %>

        <div id="container">
            <h1>Add New Membership</h1>


            <form action="membershipConfirmation.jsp">
                <table border="1">

                    <tr>
                        <td>Membership Type:</td>
                        <td><input type="text" name="membershipType" value="" maxlength="1" placeholder="N" required="required"/></td>
                    </tr>
                    <tr>
                        <td>Membership Fee:</td>
                        <td><input type="text" name="membershipFee" value="0.0" /></td>
                    </tr>
                    <tr>
                        <td>Membership Discount Rate:</td>
                        <td><input type="text" name="membershipDiscountRate" value="0.0" /></td>
                    </tr>
                    <tr>
                        <td>Membership Describe:</td>
                        <td><input type="text" name="membershipDescribe" value="" placeholder="Normal"/></td>
                    </tr>
                    <tr><td colspan="2">
                            <input type="submit" value="Add" name="action"/>   
                        </td></tr>
                    </tbody>
                </table>
            </form>
        </div>

    </body>
</html>
