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
	<div class="row">
		<div class="col-md-12">
		<jsp:include page="parts/navbar.jsp"/>

		<p>${requestScope.errorMessage}</p>
        <form method='POST'>
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>${msg:getMessage("order-history-id")}</th>
						<th>${msg:getMessage("order-history-startPoint")}</th>
						<th>${msg:getMessage("order-history-endPoint")}</th>
						<th>${msg:getMessage("order-history-order_time")}</th>
						<th>${msg:getMessage("order-history-carType")}</th>
						<th>${msg:getMessage("order-history-comment")}</th>
					</tr>
				</thead>
				<tbody>
                    <c:forEach var="order" items="${requestScope.entity}" varStatus="status">
                        <tr class="table-active">
                            <td>${status.count+recordsPerPage*(currentPage-1)}
                            <td><c:out value="${order.id}"/></td>
                            <td><c:out value="${order.startPoint}"/></td>
                            <td><c:out value="${order.endPoint}"/></td>
                            <td><c:out value="${order.order_time}"/></td>
                            <td><c:out value="${order.carType}"/></td>
                            <td><c:out value="${order.comment}"/></td>
                        </tr>
                    </c:forEach>
				</tbody>
			</table>
			</form>

		</div>
	</div>
</div>
<jsp:include page="parts/pagination.jsp"/>
</body>
</html>
