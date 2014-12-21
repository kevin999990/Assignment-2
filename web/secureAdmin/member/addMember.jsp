
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Member" %>
<%@page import="model.Membership" %>
<%@page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Member</title>
        <link href="${pageContext.request.contextPath}/css/navbar.css"  rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%@include file="../../index.html" %> 
        <%
            List<Membership> membershipList = (List) session.getAttribute("membershipList");
            //AutoID
            int autoId = (Integer) session.getAttribute("autoId");

            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/YYYY");
            String tDate = sd.format(new Date());


        %>

        <div id="container">
            <h1>Add Member</h1>
            <form action="memberConfirmation.jsp" method="post">
                <table>
                    <tbody>
                        <tr>
                            <td>ID:</td>
                            <td><input type="text" name="memberId" value="<%=autoId%>" readonly="readonly" /></td>
                        </tr>
                        <tr>
                            <td>Name:</td>
                            <td><input type="text" name="memberName" value="" placeholder="Tan Ming Xuan" maxlength="50"/></td>
                        </tr>
                        <tr>
                            <td>IC:</td>
                            <td><input type="text" name="memberIc" value="" placeholder="999999-99-9999" maxlength="14"/></td>
                        </tr>
                        <tr>
                            <td>Membership Type:</td>
                            <td><select name="type">
                                    <% for (int i = 0; i < membershipList.size(); i++) {%>
                                    <option value="<%=membershipList.get(i).getMembershipType()%>" > <%= membershipList.get(i).getMembershipDescribe()%></option>
                                    <%}%>
                                </select>                  
                            </td>
                        </tr>
                        <tr>
                            <td>Date:</td>
                            <td>
                                <input type="text" name="memberRegDate" value="<%=tDate%>" readonly="readonly" />
                            </td>
                        </tr>
                        <tr>
                            <td>Tel:</td>
                            <td><input type="text" name="memberTelNo" value="" placeholder="0109876543" maxlength="11" /></td>
                        </tr> 
                        <tr><td colspan="2"><input type="submit" value="Add" name="action" /></td></tr>
                    </tbody>
                </table>
            </form>

        </div>
    </body>
</html>