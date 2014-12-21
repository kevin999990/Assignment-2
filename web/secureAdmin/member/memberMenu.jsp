
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Member"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Menu</title>
        <link href="${pageContext.request.contextPath}/css/navbar.css"  rel="stylesheet" type="text/css" />
    </head>

    <body>  
        <%@include file="../../index.html" %> 
        <%List<Member> members = (List) session.getAttribute("allMember");%> 

        <div id="container">


            <h1>Member List</h1>
            <form action="../../MemberServlet">
                <select name="searchCriteria">
                    <option value="memberIc"/>Member IC</option>
                    <option value="memberName"/>Member Name</option>
                    <option value="memberRegDate"/>Member Register Date</option>
                    <option value="memberTelNo"/>Member Tel Number</option>
                    <option value="full" selected="selected">All</option>
                </select>
                <input type="text" name="searchValue" value="" /><input type="submit" value="Search" name="action"/>
            </form>


            <table>
                <thead>
                    <tr>
                        <th>Member ID</th>
                        <th>Member Name</th>
                        <th>Member IC</th>
                        <th>Member Type</th>
                        <th>Member Register Date</th>
                        <th>Member Tel Number</th>
                    </tr>
                </thead>
                <tbody>

                    <% for (Member mem : members) {%>
                    <tr>
                        <td><%=mem.getMemberId()%></td>
                        <td><%=mem.getMemberName()%></td>
                        <td><%=mem.getMemberIc()%></td>
                        <td><%=mem.getMemberType().getMembershipDescribe()%></td>
                        <td><%=mem.getMemberRegDate()%></td>
                        <td><%=mem.getMemberTelNo()%></td>
                    </tr>
                    <% }%>
                </tbody>
            </table>

            <br/>
            <a href="addMember.jsp"><img src="../../img/addlogo.jpg" alt="add"/></a>
            <a href="selectMember.jsp"><img src="../../img/managelogo.jpg" alt="manage"/></a>
        </div>

    </body>
</html>
