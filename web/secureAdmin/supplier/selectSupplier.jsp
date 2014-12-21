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
            <h1>Select Supplier</h1>
            <form action="manageSupplier.jsp" method="POST">
                <select name="drp_supplier">
                    <% for (Supplier sup : suppliers) {%>
                    <option value="<%=sup.getSupplierId()%>"><%=sup.getSupplierName()%></option>
                    <% }%> 
                </select>
                <input type="submit" value="Select" />
            </form>


        </div>
    </body>
</html>
