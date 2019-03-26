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
<script type="text/javascript">
  $(function() {
    $('#datetimepicker1').datetimepicker({
      language: 'pt-BR'
    });
  });
</script>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
		<jsp:include page="parts/navbar.jsp"/>
        <form method='POST'>
			<table class="table">
                <thead>
					<tr>
						<th>#</th>
						<th>${msg:getMessage("discount-text")}</th>
						<th>${msg:getMessage("discount-startdate")}</th>
						<th>${msg:getMessage("discount-enddate")}</th>
						<th>${msg:getMessage("discount-SumWithUserDisc")}</th>
						<c:if test="${user.role.name == 'ADMIN'}">
                            <th>${msg:getMessage("discount-action")}</th>
                        </c:if>
					</tr>
				</thead>
				<tbody>
				<c:if test="${requestScope.messages != Nan}">
				<tr>
                    <th>#</th>
                    <th></p>${requestScope.messages.MSGtext}<p></p>${requestScope.messages.MSGtext_uk}<p></th>
                    <th>${requestScope.messages.MSGstartTime}</th>
                    <th>${requestScope.messages.MSGendTime}</th>
                    <th></th>
                    <th></th>
                </tr>
                </c:if>
				<c:if test="${user.role.name == 'ADMIN'}">
				    <tr class="table-active">
                        <td></td>
                        <td><p><input class="form-control" type="text" placeholder="text" name="discount-add-text"></p>
                            <p><input class="form-control" type="text" placeholder="text_uk" name="discount-add-text_uk"></p>
                        </td>

                        <td><div class="well">
                          <div id="datetimepicker1" class="input-append date">
                            <input data-format="dd/MM/yyyy hh:mm:ss" type="text" name="discount-add-startTime"></input>
                            <span class="add-on">
                              <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                              </i>
                            </span>
                          </div>
                        </div></td>
                        <td><div class="well">
                          <div id="datetimepicker2" class="input-append date">
                            <input data-format="dd/MM/yyyy hh:mm:ss" type="text" name="discount-add-endTime"></input>
                            <span class="add-on">
                              <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                              </i>
                            </span>
                          </div>
                        </div></td>
                        <td>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1"name="discount-add-sumWithUserDisc">
                            <label class="form-check-label" for="exampleCheck1"></label>
                          </div>
                          </td>
                        <td><button class="btn btn-success" type="submit" name="add" value="123">${msg:getMessage("discount-add")}</button></td>
                    </tr>

                </c:if>

                <c:forEach var="discount" items="${requestScope.entity}" varStatus="status">
                    <tr class="table-active">
                        <td>${status.count+recordsPerPage*(currentPage-1)}</td>
                        <td><c:out value="${discount.text}"/></td>
                        <td><c:out value="${discount.startTime}"/></td>
                        <td><c:out value="${discount.endTime}"/></td>
                        <td><c:out value="${discount.isSumWithUserDisc}"/></td>
                        <c:if test="${user.role.name == 'ADMIN'}">
                            <td><button class="btn btn-danger" type="submit" name="remove" value="${discount.id}">${msg:getMessage("discount-remove")}</button></td>
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
