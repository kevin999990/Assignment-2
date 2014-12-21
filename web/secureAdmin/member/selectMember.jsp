

<%@page import="java.util.List"%>
<%@page import="model.Member"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Manage Member</title>
        <link href="${pageContext.request.contextPath}/css/navbar.css"  rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%@include file="../../index.html" %> 


        <%
            List<Member> members = (List) session.getAttribute("allMember");
        %>

        <div id="container">
            <h1>Select Member</h1>
            <form action="manageMember.jsp" method="POST">
                <select name="drp_member">
                    <% for (Member mem : members) {%>
                    <option value="<%=mem.getMemberId()%>"><%=mem.getMemberName()%></option>
                    <% }%> 
                </select>
                <input type="submit" value="Select" />
            </form>
        </div>
    </body></html>
