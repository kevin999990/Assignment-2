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
                Supplier supplier = new Supplier(Integer.parseInt(request.getParameter("drp_supplier")));
                List<Supplier> suppliers = (List) session.getAttribute("allSupplier");

                for (int i = 0; i < suppliers.size(); i++) {
                    if (suppliers.get(i).getSupplierId().equals(supplier.getSupplierId())) {
                        supplier = suppliers.get(i);
                    }
                }
            %>

            <h1>Manage Supplier</h1>
            <form action="supplierConfirmation.jsp">
                <table >

                    <tbody>
                        <tr><td>Supplier ID:</td>
                            <td><input name="supplierId"  value="<%=supplier.getSupplierId()%>" readonly="readonly"/></td>
                        </tr>
                        <tr>
                            <td>Supplier Name:</td>
                            <td><input  name="supplierName" value="<%=supplier.getSupplierName()%>" maxlength="50"/></td>
                        </tr>
                        <tr>
                            <td>Supplier Address:</td>
                            <td ><textarea name="supplierAddress" rows="4" cols="50" ><%=supplier.getSupplierAddress()%></textarea></td>
                        </tr>
                        <tr>
                            <td>Supplier Tel Number:</td>
                            <td><input  name="supplierTelNo" value="<%=supplier.getSupplierTelNo()%>" maxlength="15"/></td>
                        </tr>
                        <tr>
                            <td>Supplier Email:</td>
                            <td><input  name="supplierEmail" value="<%=supplier.getSupplierEmail()%>" maxlength="50" /></td>
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
