

<%@page import="java.util.List"%>
<%@page import="model.Membership"%>
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

        <%
            Membership membership = new Membership(request.getParameter("drp_membership"));
            List<Membership> memberships = (List) session.getAttribute("allMembership");

            for (int i = 0; i < memberships.size(); i++) {
                if (memberships.get(i).getMembershipDescribe().equalsIgnoreCase(membership.getMembershipType())) {
                    membership = memberships.get(i);
                }
            }
            
            
            
        %>
        <div id="container">

            <h1>Manage Membership</h1>

            <form action="membershipConfirmation.jsp">
                <table>

                    <tr>
                        <td>Membership Type:</td>
                        <td><input type="text" name="membershipType" value="<%=membership.getMembershipType()%>" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>Membership Fee:</td>
                        <td><input type="text" name="membershipFee" value="<%=membership.getMembershipFee()%>" /></td>
                    </tr>
                    <tr>
                        <td>Membership Discount Rate:</td>
                        <td><input type="text" name="membershipDiscountRate" value="<%=membership.getMembershipDiscountRate()%>" /></td>
                    </tr>
                    <tr>
                        <td>Membership Describe:</td>
                        <td><input type="text" name="membershipDescribe" value="<%=membership.getMembershipDescribe()%>" /></td>
                    </tr>
                    <tr><td colspan="2">
                            <input type="submit" value="Update" name="action" />
                            <input type="submit" value="Delete" name="action" />
                        </td></tr>   
                    </tbody>
                </table></form>
        </div>
    </body>
</html>
