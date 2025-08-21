<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>


<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><c:out value="${'page'}"></c:out></title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
	crossorigin="anonymous">
</head>
<body>
	<div class="container mt-4">
		<!-- Title -->
		<div class="text-center text-danger fs-1 fw-bold mb-4">
			<div class="d-flex flex-row justify-content-between">
				<h1>Todo Manager</h1>

				<p
					class="border border-primary rounded-circle d-flex justify-content-center align-items-center"
					style="width: 50px; height: 50px; font-size: 24px;">T</p>

			</div>

		</div>

		<c:if test="${not empty msg}">
			<div class="alert alert-success">
				<b><c:out value="${msg}"></c:out></b>
			</div>
		</c:if>

		<!-- Grid for options and content -->
		<div class="row">
			<!-- Options column -->
			<div class="col-md-2">

				<!-- Example buttons -->
				<div class="list-group shadow-sm">
					<button type="button"
						class="list-group-item list-group-item-action active">
						Menu</button>

					<a href='<c:url value='/add'></c:url>'
						class="list-group-item list-group-item-action">Add Todo</a> <a
						href='<c:url value='/home'></c:url>'
						class="list-group-item list-group-item-action">View Todos</a>

					<button type="button"
						class="list-group-item list-group-item-action">Completed</button>
				</div>
			</div>

			<!-- Content column -->
			<div class="col-md-10 py-3">

				<c:if test="${page=='home'}">
					<h3 class="text-center mb-4">All TODOS</h3>

					<div class="row row-cols-1 row-cols-md-2 g-3">
						<c:forEach items="${todos}" var="t">
							<div class="col">
								<div class="card h-100 shadow-sm border-primary rounded">
									<div class="card-body">
										<h5 class="card-title text-primary fw-bold">
											<c:out value="${t.todoTitle}" />
										</h5>
										<p class="card-text text-secondary">
											<c:out value="${t.todoContent}" />
										</p>
									</div>
									<div class="card-footer text-end text-muted small">
										<c:out value="${t.todoDate}" />
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:if>

				<c:if test="${page=='add'}">
					<h3 class="text-center mb-4 text-primary">Add TODO</h3>
					<div class="card shadow-sm p-4 mx-auto"
						style="max-width: 600px; border-radius: 12px;">
						<form:form action="saveTodo" method="post" modelAttribute="todo">

							<div class="mb-3">
								<label for="todoTitle" class="form-label fw-bold">Title</label>
								<form:input path="todoTitle"
									cssClass="form-control form-control-lg"
									placeholder="Enter your Todo Title" id="todoTitle" />
							</div>

							<div class="mb-3">
								<label for="todoContent" class="form-label fw-bold">Content</label>
								<form:input path="todoContent"
									cssClass="form-control form-control-lg"
									placeholder="Enter your Todo Content" id="todoContent" />
							</div>

							<button type="submit" class="btn btn-primary btn-lg w-100">Add
								Todo</button>
						</form:form>
					</div>
				</c:if>

			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
		crossorigin="anonymous"></script>
</body>
</html>
