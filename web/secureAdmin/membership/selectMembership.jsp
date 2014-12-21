
<%@page import="java.util.List"%>
<%@page import="model.Membership"%>
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
                List<Membership> memberships = (List) session.getAttribute("allMembership");
            %>
            <h1>Select Membership</h1>
            <form action="manageMembership.jsp" method="POST">
                <select name="drp_membership">
                    <% for (Membership mem : memberships) {%>
                    <option><%=mem.getMembershipDescribe()%></option>
                    <% }%> 
                </select>
                <input type="submit" value="Select" />
            </form>

        </div>
    </body>
</html>
