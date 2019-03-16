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
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
		<jsp:include page="navbar.jsp"/>
		<jsp:include page="searchtaxi.jsp"/>
		<p>${requestScope.errorMessage}</p>
<form method='POST'>
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>${msg:getMessage("book-taken-name")}</th>
						<th>${msg:getMessage("book-taken-authors")}</th>
						<th>${msg:getMessage("book-taken-section")}</th>
					</tr>
				</thead>
				<tbody>

                    <c:forEach var="taxi" items="${requestScope.taxis}" varStatus="status">
                        <tr class="table-active">
                            <td>${status.count+recordsOnPage*(currentPage-1)}
                            <td><c:out value="${taxi.id}"/></td>
                            <td><c:out value="${taxi.descryption}"/></td>
                            <td><c:out value="${taxi.taxiStatus.name}"/></td>
                            <td><c:out value="${taxi.carType.name}"/></td>
                        </tr>
                    </c:forEach>
				</tbody>
			</table>
			</form>
			<jsp:include page="pagination.jsp"/>
		</div>
	</div>
</div>

