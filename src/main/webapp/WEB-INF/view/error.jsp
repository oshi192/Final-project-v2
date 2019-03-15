<%@ page language="java" isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<html lang="en">

<head>
    <title>Error page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/resources/css/text.css" rel="stylesheet">
</head>
<body>
    <div>
    <center>
        <h1 class="glitched">Error</h1>
        <h2><%= exception %></h1>
    </div>
    <a href ="${pageContext.request.contextPath}/taxi">mainPage</a>
    </center>
</body>

</html>
