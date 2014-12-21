
<%@page import="java.util.List"%>
<%@page import="model.Membership"%>

<html>
    <head>
        <title>Manage Member</title>
        <link href="${pageContext.request.contextPath}/css/navbar.css"  rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%@include file="../../index.html" %> 

        <%session.removeAttribute("member");%>
        <jsp:useBean id="member" scope="session" class="model.Member" />

        <jsp:setProperty name="member" property="*"/>

        <%
            String action = request.getParameter("action");

            List<Membership> membershipList = (List) session.getAttribute("membershipList");

            for (int i = 0; i < membershipList.size(); i++) {
                if (membershipList.get(i).getMembershipType().equalsIgnoreCase(request.getParameter("type"))) {
                    member.setMemberType(membershipList.get(i));
                }
            }

          //  member.setMemberType(membership);

        %>

        <div id="container">
            <h1><%=action%> Member Confirmation</h1>
            <%    if (action.equalsIgnoreCase("Delete")) { %>
            <h2 style="color: red">Are you really sure want to delete?</h2>
            <% } else {%>
            <h2>Is the data correct?</h2>
            <%}%>
            <form method="POST" action="../../MemberServlet">
                <table border="1">

                    <tr>
                        <td>Member ID</td>
                        <td>${member.memberId}</td>
                    </tr>
                    <tr>
                        <td>Member Name</td>
                        <td>${member.memberName}</td>
                    </tr>
                    <tr>
                        <td>Member IC</td>
                        <td>${member.memberIc}</td>
                    </tr>
                    <tr>
                        <td>Member Type</td>
                        <td>${member.memberType.membershipDescribe}</td>
                    </tr>
                    <tr>
                        <td>Member Register Date</td>
                        <td>${member.memberRegDate}</td>
                    </tr>
                    <tr>
                        <td>Member Tel Number</td>
                        <td>${member.memberTelNo}</td>
                    </tr>
                    <tr><td colspan="2">
                            <input type="submit" value="<%= action%>" name="action" />
                            <input type="submit" value="Cancel" name="action" />
                        </td></tr></table>
            </form>
        </div>
    </body></html>