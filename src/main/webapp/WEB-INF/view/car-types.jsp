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
						<th>${msg:getMessage("carType-carTypeName")}</th>
						<th>${msg:getMessage("carType-priceCityKm")}</th>
						<th>${msg:getMessage("carType-priceOverTheCityKm")}</th>
						<th>${msg:getMessage("carType-priceWaitingTimeMinute")}</th>
						<th>${msg:getMessage("carType-priceWaitingTimeFree")}</th>
						<c:if test="${user.role.name == 'ADMIN'}">
                            <th>${msg:getMessage("carType-action")}</th>
                        </c:if>
					</tr>
				</thead>

				<tbody>
				<c:if test="${user.role.name == 'ADMIN'}">
				    <tr class="table-active">
                        <td></td>
                        <td><input class="form-control" type="text" placeholder="carTypeName" name="taxi-add-carTypeName"></td>
                        <td><input class="form-control" type="text" placeholder="priceCityKm" name="taxi-add-priceCityKm"></td>
                        <td><input class="form-control" type="text" placeholder="priceOverTheCityKm" name="taxi-add-priceOverTheCityKm"></td>
                        <td><input class="form-control" type="text" placeholder="priceWaitingTimeMinute" name="taxi-add-priceWaitingTimeMinute"></td>
                        <td><input class="form-control" type="text" placeholder="priceWaitingTimeFree" name="taxi-add-priceWaitingTimeFree"></td>

                        <td><button class="btn btn-success" type="submit" name="add" value="123">add</button>
                    </tr>
                </c:if>

                <c:forEach var="carType" items="${requestScope.entity}" varStatus="status">
                    <tr class="table-active">
                        <td>${status.count+recordsPerPage*(currentPage-1)}
                        <td><c:out value="${carType.carTypeName}"/></td>
                        <td><c:out value="${carType.priceCityKm}"/></td>
                        <td><c:out value="${carType.priceOverTheCityKm}"/></td>
                        <td><c:out value="${carType.priceWaitingTimeMinute}"/></td>
                        <td><c:out value="${carType.priceWaitingTimeFree}"/></td>
                        <c:if test="${user.role.name == 'ADMIN'}">
                            <td><button class="btn btn-danger" type="submit" name="remove" value="${carType.id}">${msg:getMessage("carType-remove")}</button></td>
                        </c:if>
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
