<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="msg" uri="msg" %>

<nav class="navbar navbar-inverse ">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" id="nav-logo" href="${pageContext.request.contextPath}/taxi">${msg:getMessage("nav-bar-service")}</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active">
            <a href="${pageContext.request.contextPath}/all-books">
                text1
            </a>
        </li>
        <c:if test="${user.role.name == 'ADMIN'}">
            <jsp:include page="admin-menu.jsp"/>
        </c:if>
        <c:if test="${user.role.name == 'USER'}">
            <jsp:include page="usermenu.jsp"/>
        </c:if>
      </ul>
      <ul class="nav navbar-nav navbar-right">

            <c:choose>
                  <c:when test="${user eq Nan}">
                      <li>
                          <a href="${pageContext.request.contextPath}/taxi/registration">
                              <span class="glyphicon glyphicon-user"></span>
                              ${msg:getMessage("nav-bar-register")}<span class="sr-only">
                          </a>
                      </li>
                      <li>
                          <a href="${pageContext.request.contextPath}/taxi/login">
                              <span class="glyphicon glyphicon-log-in"></span>
                              ${msg:getMessage("nav-bar-login")}
                          </a>
                      </li>
                  </c:when>
                  <c:otherwise>
                      <li class="nav-item active">
                          <a href="#">
                              <span class="glyphicon glyphicon-user"></span>
                              ${user.email}
                          </a>
                      </li>
                      <li class="nav-item active">
                      <a class="nav-link" href="${pageContext.request.contextPath}/taxi/logout">
                      <span class="glyphicon glyphicon-log-out"></span>
                      ${msg:getMessage("nav-bar-logout")}</a>
                      </li>
                  </c:otherwise>
              </c:choose>
              <li>
            <ul class="lang">
                <li><a href="?lang=En">En   .</a></li>
                <li><a href="?lang=Uk">Ua   .</a></li>
            </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
