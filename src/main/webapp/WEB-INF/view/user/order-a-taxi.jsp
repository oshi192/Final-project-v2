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
            <c:if test="${order eq Nan}}">
                <jsp:include page="order-form.jsp"/>
            </c:if>
                <p>${requestScope.errorMessage}</p>
                <form method='POST'>
                    <div class="form-group">
                        <label for="exampleFormControlSelect1">From</label>
                        <select class="form-control" id="exampleFormControlSelect1" name="fromCity">
                            <c:forEach var="city" items="${requestScope.cities}" >
                                <option value="${city.id}">${city.cityName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlSelect1">to</label>
                        <select class="form-control" id="exampleFormControlSelect1" name="toCity">
                            <c:forEach var="city" items="${requestScope.cities}" >
                                <option value="${city.id}">${city.cityName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlSelect1">Car type</label>
                        <select class="form-control" id="exampleFormControlSelect1" name="carType">
                            <c:forEach var="carType" items="${requestScope.carTypes}" >
                                <option value="${carType.id}">${carType.carTypeName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlTextarea1" name="comment">Comment</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                    </div>
                     <button type="submit" class="btn btn-primary col-md-6">Order</button>
                </form>
            </div>
            <div class="col-md-3"></div>
		</div>
	</div>
</div>
</body>
</html>
