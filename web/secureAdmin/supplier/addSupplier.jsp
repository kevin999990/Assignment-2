<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Supplier" %>
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


            <%
                //AutoID
                int autoId = (Integer) session.getAttribute("autoId");
            %>

            <h1>Add Supplier</h1>
            <form action="supplierConfirmation.jsp" method="post">
                <table >
                    <tbody>
                        <tr><td>Supplier ID:</td>
                            <td><input name="supplierId" value="<%=autoId%>" readonly="readonly"/></td>
                        </tr>
                        <tr>
                            <td>Supplier Name:</td>
                            <td><input type="text" required="required" name="supplierName" value="" placeholder="Tan Inc." maxlength="50" /></td>
                        </tr>
                        <tr>
                            <td>Supplier Address:</td>
                            <td><textarea required="required" name="supplierAddress" rows="4" cols="22" ></textarea></td>
                        </tr>
                        <tr>
                            <td>Supplier Tel Number:</td>
                            <td><input type="text" name="supplierTelNo" value="" placeholder="088988765" maxlength="15"/></td>
                        </tr>
                        <tr>
                            <td>Supplier Email:</td>
                            <td><input type="email" name="supplierEmail" value="" placeholder="abcde@email.com" maxlength="50"/></td>
                        </tr>
                        <tr><td colspan="2"><input type="submit" value="Add" name="action" /></td></tr>
                    </tbody>
                </table>
            </form>


        </div>
    </body>
</html>
