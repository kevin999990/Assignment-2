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
                List<Supplier> suppliers = (List) session.getAttribute("allSupplier");
            %>
            <h1>Supplier List</h1>


            <form action="../../SupplierServlet">
                <select name="searchCriteria">
                   <option value="supplierName"> Supplier Name </option>
                    <option value="supplierAddress"> Supplier Address  </option>
                    <option value="supplierTelNo"> Supplier Tel Number</option>
                    <option value="supplierEmail" > Supplier Email </option>
                    <option value="full" selected="selected">All</option>
                </select>
                <input type="text" name="searchValue" value="" /><input type="submit" value="Search" name="action"/>
            </form>


            <table border="1" style="table-layout: fixed;width: 100%">
                <thead>
                    <tr>
                        <th>Supplier ID</th>
                        <th>Supplier Name</th>
                        <th>Supplier Address</th>
                        <th>Supplier Tel Number</th>
                        <th>Supplier Email</th>
                    </tr>
                </thead>
                <tbody>


                    <% for (Supplier sup : suppliers) {%>
                    <tr>
                        <td><%=sup.getSupplierId()%></td>
                        <td><%=sup.getSupplierName()%></td>
                        <td style="word-wrap: break-word"><%=sup.getSupplierAddress()%></td>
                        <td><%=sup.getSupplierTelNo()%></td>
                        <td><%=sup.getSupplierEmail()%></td>

                    </tr>
                    <% }%>
                </tbody>
            </table>

            <br/>
            <a href="addSupplier.jsp"><img src="../../img/addlogo.jpg" alt="add"/></a>
            <a href="selectSupplier.jsp"><img src="../../img/managelogo.jpg" alt="manage"/></a>


        </div>
    </body>
</html>
