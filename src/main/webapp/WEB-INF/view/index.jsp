<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${msg:getMessage("index-theme")}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.min.js" ></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body>
    <div class="container-fluid">
        	<div class="row">
        		<div class="col-md-12">
                    <jsp:include page="parts/navbar.jsp"/>

                    <div class="jumbotron">
                        <c:choose>
                            <c:when test="${requestScope.confirmMSG != Nan}">
                                          <p>${requestScope.confirmMSG}</p>
                              </c:when>
                              <c:otherwise>
                              <h2>
                                  ${msg:getMessage("index-theme")}
                              </h2>
                              <p>
                                  ${msg:getMessage("index-text")}
                              </p>
                              </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>