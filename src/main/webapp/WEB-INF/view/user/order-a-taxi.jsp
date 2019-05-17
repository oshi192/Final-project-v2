<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Some Books example</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    </head>

<body>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" ></script>


<div class="container-fluid">
<jsp:include page="../parts/navbar.jsp"/>
	<div class="row">
		<div class="col-md-12">
            <div class="col-md-3"></div>
            <div class="col-md-6">
            <c:choose>
                <c:when test="${order eq null}">
                    <jsp:include page="order-form.jsp"/>
                    <p>${requestScope.errorMessage}</p>
                </c:when>
                <c:when test="${order ne null}">
                    <jsp:include page="order-confirm.jsp"/>
                </c:when>
                <c:otherwise>

                </c:otherwise>
            </c:choose>
            </div>
            <div class="col-md-3"></div>
		</div>
	</div>
</div>
</body>
</html>
