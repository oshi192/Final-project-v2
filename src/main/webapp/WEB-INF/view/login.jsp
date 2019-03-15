<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<html>
    <head>
        <title>Login page</title>
        <link href="${pageContext.request.contextPath}/resources/css/login5.css" />" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/flags.css" />" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="<c:url value="/resources/bootstrap/js/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
    </head>
    <body>
    <div class="container-fluid bg">
     <jsp:include page="navbar.jsp"/>
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
            <div class="col-md-4 col-sm-4 col-xs-12">
                <div id="log">
                    <form  method="POST" action="${pageContext.request.contextPath}/taxi/login">
                    <h1>${msg:getMessage("login-please-login")}</h1>
                    <div id="errormessagesbox">
                        <c:if test='${errorMessage!=""}'>
                            <c:out value="${errorMessage}"/>
                        </c:if>
                    </div>
                    <div id="messagesbox">
                        <c:if test='${message!=""}'>
                            <c:out value="${messages.inputError}"/>
                        </c:if>
                    </div>
                    <img class="rounded mx-auto d-block img img-responsive img-circle" src="<c:url value="/resources/image/user1.png" />"/>
                        <div class="form-group">
                            ${requestScope.messages.emailMessage}
                        <div>
                        <div class="form-group">
                            <input name="email"
                            type="email"
                            class="form-control"
                            placeholder=${msg:getMessage("login-label-email-placeholder")}
                            value="${requestScope.userEmail}">
                        </div>
                        <div class="form-group">
                            ${requestScope.messages.passwordMessage}
                        <div>
                        <div class="form-group">
                            <input name="password"
                            type="password"
                            class="form-control"
                            placeholder=${msg:getMessage("login-label-password-placeholder")}>
                        </div>
                        <button type="submit" class="btn btn-success btn-block ">${msg:getMessage("login-button-login")}</button>
                    </form>
                        <a href="${pageContext.request.contextPath}/taxi/registration">
                            <button class="btn btn-danger btn-block ">${msg:getMessage("login-button-register")}</button>
                        </a>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
        </div>
    </div>
    </body>
</html>
