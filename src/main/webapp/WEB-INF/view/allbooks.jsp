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
        <script src="<c:url value="/resources/bootstrap/js/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
    </head>

    <body>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
		<jsp:include page="parts/navbar.jsp"/>
		<jsp:include page="parts/searchpanel.jsp"/>
		<p>${requestScope.errorMessage}</p>
<form method='POST'>
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>${msg:getMessage("book-taken-name")}</th>
						<th>${msg:getMessage("book-taken-authors")}</th>
						<th>${msg:getMessage("book-taken-section")}</th>
						<c:if test="${user.role == 'ADMIN'}">
						<th>
						    <button
                               class="btn btn-success"
                               type="button"
                               data-toggle="modal"
                               data-target="#myModal"
                               name="create"
                               class="btn btn-success"
                               data-title="new Record"
                           >


                               <span class="glyphicon glyphicon-plus"></span>
                               ${msg:getMessage("edit-book-button-create")}
                           </button>
                           <div id="myModal" class="modal fade">
                               <div class="modal-dialog">
                                   <div class="modal-content">
                                       <div class="modal-header">
                                           <button class="close" type="button" data-dismiss="modal">
                                               ×
                                           </button>
                                           <h4 class="modal-title">${msg:getMessage("edit-book-header")}</h4>
                                       </div>
                                       <div class="modal-body">
                                           <form class="form-horizontal" >
                                            <div class="form-group">
                                             <label for="pass" class="col-sm-2 control-label">
                                               ${msg:getMessage("book-taken-name")}-en
                                             </label>
                                             <div class="col-sm-10">
                                              <input type="text" name="book-name-en" class="form-control">
                                             </div>
                                            </div>
                                            <div class="form-group">
                                             <label for="pass" class="col-sm-2 control-label">
                                               ${msg:getMessage("book-taken-name")}-uk
                                             </label>
                                             <div class="col-sm-10">
                                              <input type="text" name="book-name-uk" class="form-control">
                                             </div>
                                            </div>

                                            <div class="form-group">
                                             <label for="pass" class="col-sm-2 control-label">
                                              ${msg:getMessage("book-taken-section")}
                                             </label>
                                             <div class="col-sm-10">
                                              <select class="form-control" name="book-section">
                                                <c:forEach var="item" items="${requestScope.Sections}" varStatus="status">
                                                   <option value="${item}">
                                                        ${item}
                                                   </option>
                                                </c:forEach>
                                              </select>
                                             </div>
                                            </div>

                                           </form>
                                       </div>
                                       <div class="modal-footer">
                                           <button class="btn btn-default" type="submit" name="create" >
                                               ${msg:getMessage("edit-book-button-create")}
                                           </button>
                                           <button class="btn btn-default" type="button" data-dismiss="modal">
                                               ${msg:getMessage("edit-book-button-close")}
                                           </button>
                                       </div>
                                   </div>
                               </div>
                           </div>
						</th>
						<th></th>
                        </c:if>
                        <c:if test="${user.role == 'USER'}">
                        <th></th>
                        </c:if>
					</tr>
				</thead>
				<tbody>

                    <c:forEach var="Book" items="${requestScope.Books}" varStatus="status">
                        <tr class="table-active">
                            <td>${status.count+recordsOnPage*(currentPage-1)}
                            <td>
                                <c:if test="${locale =='en'}"> <c:out value="${Book.nameEn}"/></c:if>
                                <c:if test="${locale =='uk'}"> <c:out value="${Book.nameUk}"/></c:if>
                            </td>
                            <td><c:out value="${Book.authors}"/></td>
                            <td><c:out value="${Book.section}"/></td>



                            <c:if test="${user.role == 'ADMIN'}">
                                <td>


                                   <button
                                       class="btn btn-info"
                                       type="button"
                                       data-toggle="modal"
                                       data-target="#myModal${Book.id}"
                                       type="submit"
                                       name="edit"
                                       value="${Book.id}"
                                       class="btn btn-success btn-xs"
                                       data-title="Edit"
                                   >
                                       <span class="glyphicon glyphicon-pencil"></span>
                                       ${msg:getMessage("button-edit")}
                                   </button>

                                   <div id="myModal${Book.id}" class="modal fade">
                                       <div class="modal-dialog">
                                           <div class="modal-content">
                                               <div class="modal-header">
                                                   <button class="close" type="button" data-dismiss="modal">
                                                       ×
                                                   </button>
                                                   <h4 class="modal-title">${msg:getMessage("edit-book-header")}</h4>
                                               </div>
                                               <div class="modal-body">
                                                   <form class="form-horizontal">

                                                    <div class="form-group">
                                                     <label for="pass" class="col-sm-2 control-label">
                                                       ${msg:getMessage("book-taken-name")}-en
                                                     </label>
                                                     <div class="col-sm-10">
                                                      <input type="text" name="book-name-en-edit${Book.id}" class="form-control" value="${Book.nameEn}">
                                                     </div>
                                                    </div>
                                                    <div class="form-group">
                                                     <label for="pass" class="col-sm-2 control-label">
                                                       ${msg:getMessage("book-taken-name")}-uk
                                                     </label>
                                                     <div class="col-sm-10">
                                                      <input type="text" name="book-name-uk-edit${Book.id}" class="form-control" value="${Book.nameUk}">
                                                     </div>
                                                    </div>

                                                    <div class="form-group">
                                                     <label for="pass" class="col-sm-2 control-label">
                                                      ${msg:getMessage("book-taken-section")}
                                                     </label>
                                                     <div class="col-sm-10">
                                                      <select class="form-control" name="book-section-edit${Book.id}">
                                                        <c:forEach var="item" items="${requestScope.Sections}" varStatus="status">
                                                           <option value="${item}" <c:if test="${Book.section == item}">selected</c:if>>${item}</option>
                                                        </c:forEach>
                                                      </select>
                                                     </div>
                                                    </div>

                                                   </form>
                                               </div>
                                               <div class="modal-footer">
                                                   <button class="btn btn-default" type="submit" name="edit" value="${Book.id}">
                                                       ${msg:getMessage("edit-book-button-save")}
                                                   </button>
                                                   <button class="btn btn-success" type="button" data-dismiss="modal">
                                                       ${msg:getMessage("edit-book-button-close")}
                                                   </button>
                                               </div>
                                           </div>
                                       </div>
                                   </div>



                                </td>
                                    <td>
                                        <c:if test="${Book.status != 2}">
                                            <button
                                                type="submit"
                                                name="remove"
                                                value="${Book.id}"
                                                class="btn btn-danger"
                                                data-title="Edit" data-toggle="edit2"
                                                data-target="#edit2" >
                                                <span class="glyphicon glyphicon-trash"></span>
                                                ${msg:getMessage("button-remove")}
                                            </button>
                                        </c:if>
                                    </td>
                            </c:if>



                            <c:if test="${user.role == 'USER'}">
                                <td>
                                <c:if test="${Book.status == 0}">
                                        <button
                                            type="submit"
                                            name="take"
                                            value="${Book.id}"
                                            class="btn btn-warning btn"
                                            data-title="Edit" data-toggle="edit2"
                                            data-target="#edit2" >
                                            <span class="glyphicon glyphicon-pencil"></span>
                                            ${msg:getMessage("button-take")}
                                        </button>

                                </c:if>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
				</tbody>
			</table>
			</form>
			<jsp:include page="parts/pagination.jsp"/>
		</div>
	</div>
</div>

