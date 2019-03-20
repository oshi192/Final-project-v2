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
		<jsp:include page="navbar.jsp"/>

		<p>${requestScope.errorMessage}</p>
		<form method="POST">

            <input type="hidden" name="currentPage" value="1">

            <div class="form-group col-md-4">

                <label for="records">Select records per page:</label>

                <select class="form-control" id="records" name="recordsPerPage" >
                    <option value="5">5</option>
                    <option value="10" selected>10</option>
                    <option value="15">15</option>
                </select>

            </div>

            <button type="submit" class="btn btn-primary">Submit</button>

        </form>
        <form method='POST'>
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>${msg:getMessage("taxis-id")}</th>
						<th>${msg:getMessage("taxis-description")}</th>
						<th>${msg:getMessage("taxis-status")}</th>
						<th>${msg:getMessage("taxis-carType")}</th>
						<th>${msg:getMessage("taxis-action")}</th>
					</tr>
				</thead>
				<tbody>
				    <tr class="table-active">
                        <td></td>
                        <td>${requestScope.messages.descriptionMsg}</td>
                        <td><input class="form-control" type="text" placeholder="description" name="taxi-add-description"></td>
                        <td>${requestScope.messages.carTypeMsg}</td>
                        <td>
                            <div class="form-group">
                                <select class="form-control" id="exampleFormControlSelect1" name="taxi-add-cartype">
                                  <c:forEach var="carType" items="${requestScope.carTypes}" >
                                    <option>${carType.carTypeName}</option>
                                  </c:forEach>
                                </select>
                            </div>
                        </td>
                        <td><button class="btn btn-success" type="submit" name="add" value="123">add</button>
                    </tr>
                    <c:forEach var="taxi" items="${requestScope.entity}" varStatus="status">
                        <tr class="table-active">
                            <td>${status.count+recordsOnPage*(currentPage-1)}
                            <td><c:out value="${taxi.id}"/></td>
                            <td><c:out value="${taxi.description}"/></td>
                            <td><c:out value="${taxi.taxiStatus.name}"/></td>
                            <td><c:out value="${taxi.carType.carTypeName}"/></td>
                            <td><button class="btn btn-danger" type="submit" name="remove" value="${taxi.id}">remove</button></td>
                        </tr>
                    </c:forEach>
				</tbody>
			</table>
			</form>

		</div>
	</div>
</div>
<nav aria-label="Navigation for countries">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                href="?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                        href="?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                href="?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>
</body>
</html>
