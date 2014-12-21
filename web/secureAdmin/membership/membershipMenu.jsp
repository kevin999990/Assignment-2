

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
            <h1>Membership List</h1>
            <form action="../../MembershipServlet">
                <select name="searchCriteria">
                    <option value="membershipDescribe"> Membership Describe</option>
                    <option value="membershipType">Membership Type</option>
                    <option value="full" selected="selected">All</option>
                </select>
                <input type="text" name="searchValue" value="" /><input type="submit" value="Search" name="action"/>
            </form>

            <table border="1">
                <thead>
                    <tr>
                        <th>Membership Type</th>
                        <th>Membership Fee</th>
                        <th>Membership Discount Rate</th>
                        <th>Membership Describe</th>
                    </tr>
                </thead>
                <tbody>


                    <%if (!session.isNew()) {
                            for (Membership mem : memberships) {%>
                    <tr>
                        <td><%=mem.getMembershipType()%></td>
                        <td><%=mem.getMembershipFee()%></td>
                        <td><%=mem.getMembershipDiscountRate()%></td>
                        <td><%=mem.getMembershipDescribe()%></td>
                    </tr>
                    <%}
                        }%>
                </tbody>
            </table>

            <br/>
            <a href="addMembership.jsp"><img src="../../img/addlogo.jpg" alt="add"/></a>
            <a href="selectMembership.jsp"><img src="../../img/managelogo.jpg" alt="manage"/></a>

        </div>
    </body>
</html>
