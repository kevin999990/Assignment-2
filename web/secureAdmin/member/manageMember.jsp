<%@page import="model.Membership"%>
<%@page import="java.util.List"%>
<%@page import="model.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Manage Member</title>
        <link href="${pageContext.request.contextPath}/css/navbar.css"  rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%@include file="../../index.html" %> 

        <%
            Member member = new Member(Integer.parseInt(request.getParameter("drp_member")));
            List<Member> members = (List) session.getAttribute("allMember");
            List<Membership> membershipList = (List) session.getAttribute("membershipList");

            for (int i = 0; i < members.size(); i++) {
                if (members.get(i).getMemberId().equals(member.getMemberId())) {
                    member = members.get(i);
                }
            }
        %>

        <div id="container">
            <h1>Manage Member</h1>
            <form action="memberConfirmation.jsp">
                <table border="1">

                    <tbody>
                        <tr>
                            <td>ID:</td>
                            <td><input type="text" name="memberId" value="<%= member.getMemberId()%>" readonly="readonly" /></td>
                        </tr>
                        <tr>
                            <td>Name:</td>
                            <td><input type="text" name="memberName" value="<%=member.getMemberName()%>" maxlength="50" /></td>
                        </tr>
                        <tr>
                            <td>IC:</td>
                            <td><input type="text" name="memberIc" value="<%=member.getMemberIc()%>" maxlength="14"/></td>
                        </tr>
                        <tr>
                            <td>Membership Type:</td>
                            <td>
                                <select name="type">
                                    <% for (int i = 0; i < membershipList.size(); i++) {
                                            if (membershipList.get(i).getMembershipType().equalsIgnoreCase(member.getMemberType().getMembershipType())) {
                                    %>
                                    <option value="<%=membershipList.get(i).getMembershipType()%>" selected="selected" > <%= membershipList.get(i).getMembershipDescribe()%></option>
                                    <%} else {%> <option value="<%=membershipList.get(i).getMembershipType()%>" > <%= membershipList.get(i).getMembershipDescribe()%></option>
                                    <%}
                                        }%>
                                </select> 

                            </td>
                        </tr>
                        <tr>
                            <td>Date:</td>
                            <td>
                                <input type="text" name="memberRegDate" value="<%=member.getMemberRegDate()%>" readonly="readonly" />
                            </td>
                        </tr>
                        <tr>
                            <td>Tel:</td>
                            <td><input type="text" name="memberTelNo" value="<%=member.getMemberTelNo()%>" maxlength="11" /></td>
                        </tr>
                        <tr><td colspan="2">  
                                <input type="submit" value="Update" name="action" />
                                <input type="submit" value="Delete" name="action" />
                            </td>
                        </tr>
                    </tbody>
                </table>

            </form>
        </div>
    </body>
</html>