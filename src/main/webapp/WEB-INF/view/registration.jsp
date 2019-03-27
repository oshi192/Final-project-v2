<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>
<html>
    <head>
        <title>Login page</title>
        <link href="<c:url value="/resources/css/login5.css" />" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="<c:url value="/resources/bootstrap/js/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
    </head>
    <body>
    <div class="container-fluid bg">
    <jsp:include page="parts/navbar.jsp"/>
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
            <div class="col-md-4 col-sm-4 col-xs-12">

                <div id="log">
                    <form  method="POST" action="${pageContext.request.contextPath}/taxi/registration">
                        <h1>${msg:getMessage("registration-please-register")}</h1>
                        <div id="errormessagesbox">
                            <c:if test='${errorMessage!=""}'>
                                <c:out value="${errorMessage}"/>
                            </c:if>
                        </div>
                        <div id="messagesbox">
                            <c:if test='${message!=""}'>
                                <c:out value="${message}"/>
                            </c:if>
                        </div>
                        <img class="rounded mx-auto d-block img img-responsive img-circle" src="<c:url value="/resources/image/user1r.png" />"/>
                            <div class="form-group">
                                ${requestScope.messages.email}
                            <div>
                            <div class="form-group">
                                <input
                                    name="email"
                                    type="email"
                                    class="form-control"
                                    placeholder=${msg:getMessage("registration-email-label")}
                                    pattern="${msg:getMessage("registration-email-regex")}"
                                    title="${msg:getMessage("registration-email-hint")}"
                                    value="${requestScope.newUser.getEmail()}"
                                    required>
                            </div>
                            <div class="form-group">
                                ${requestScope.messages.phoneNumber}
                            <div>
                            <div class="form-group">
                                <input
                                    name="phone-number"
                                    type="text"
                                    class="form-control"
                                    placeholder=${msg:getMessage("registration-phone-label")}
                                    pattern="${msg:getMessage("registration-phone-regex")}"
                                    title="${msg:getMessage("registration-phone-hint")}"
                                    value="${requestScope.newUser.getPhoneNumber()}"
                                    required>
                            </div>
                            <div class="form-group">
                                ${requestScope.messages.password}
                            <div>
                            <div class="form-group">
                                <input
                                    name="password"
                                    type="password"
                                    class="form-control"
                                    placeholder=${msg:getMessage("registration-password-label")}
                                    pattern="${msg:getMessage("registration-password-regex")}"
                                    title="${msg:getMessage("registration-password-hint")}"
                                    value="${requestScope.newUser.getPassword()}"
                                    required>
                            </div>
                            <div class="form-group">
                                <input
                                    name="confirm-password"
                                    type="password"
                                    class="form-control"
                                    placeholder=${msg:getMessage("registration-password-label-confirm")}
                                    pattern="${msg:getMessage("registration-password-regex")}"
                                    title="${msg:getMessage("registration-password-hint")}"
                                    required>
                            </div>
                            <div class="form-group">
                                ${requestScope.messages.name}
                            <div>
                            <div class="form-group">
                                <input
                                    name="first-name"
                                    type="text"
                                    placeholder=${msg:getMessage("registration-name-label")}
                                    class="form-control"
                                    pattern="${msg:getMessage("registration-name-regex")}"
                                    title="${msg:getMessage("registration-name-hint")}"
                                    value="${requestScope.newUser.getName()}"
                                    required>
                            </div>
                            <div class="form-group">
                                ${requestScope.messages.surname}
                            <div>
                            <div class="form-group">
                                <input
                                    name="surname"
                                    type="text"
                                    placeholder=${msg:getMessage("registration-surname-label")}
                                    class="form-control"
                                    pattern="${msg:getMessage("registration-surname-regex")}"
                                    title="${msg:getMessage("registration-surname-hint")}"
                                    value="${requestScope.newUser.getSurname()}"
                                    required>
                            </div>
                            <button type="submit" class="btn btn-success btn-block ">${msg:getMessage("registration-button")}</button>
                    </form>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12"></div>
        </div>
    </div>
    </body>
</html>
