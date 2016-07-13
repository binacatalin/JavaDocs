<%@ page import="ro.teamnet.zth.appl.dao.LocationDao" %>
<%@ page import="ro.teamnet.zth.appl.domain.Location" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Employee List</title>
</head>
<body>
<% List<Location> locationList = new LocationDao().getAllLocations(); %>
<table border="1">
    <thead>
    <tr>
        <td>Id</td>
        <td>Street address</td>
        <td>Postal code</td>
        <td>City</td>
        <td>State province</td>
    </tr>
    </thead>
    <tbody>
    <%!
        int id;
        String address, postalCode, city, province;
     %>
    <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for (Location location : locationList) {
    %>
    <tr>
        <td>
            <%id = location.getId();%>
            <%=id%>
        </td>
        <td>
            <%address = location.getStreetAddress();%>
            <%=address%>
        </td>
        <td>
            <%postalCode = location.getPostalCode();%>
            <%=postalCode%>
        </td>
        <td>
            <%city = location.getCity();%>
            <%=city%>
        </td>
        <td>
            <%province = location.getStateProvince();%>
            <%=province%>
        </td>
        <td>
            <a href="locationView.jsp?id=<%=location.getId()%>">View</a>
        </td>
    </tr>
    <%
        }%>
    </tbody>
</table>

</body>
</html>
