<!DOCTYPE html>
<%@ page session="false" %>
<%
java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy");
request.setAttribute("year", sdf.format(new java.util.Date()));
request.setAttribute("tomcatUrl", "http://tomcat.apache.org/");
request.setAttribute("tomcatDocUrl", "/docs/");
request.setAttribute("tomcatExamplesUrl", "/examples/");
%>
<html lang="en">
    <head>
        <title><%=request.getServletContext().getServerInfo() %></title>
    </head>

    <body>
    	${user }
    </body>

</html>
