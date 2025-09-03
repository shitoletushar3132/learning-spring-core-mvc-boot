<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@ page import="java.util.*"%>

<%
String user = (String) session.getAttribute("loggedInUser");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Spring MVC Blog</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css"
	rel="stylesheet">
<style>
.blog-card {
	transition: transform 0.2s;
	border: 1px solid #dee2e6;
	border-radius: 10px;
}

.blog-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

.content-preview {
	display: -webkit-box;
	-webkit-line-clamp: 3;
	-webkit-box-orient: vertical;
	overflow: hidden;
	text-overflow: ellipsis;
	color: #6c757d;
}

.header-section {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	color: white;
	padding: 2rem 0;
	margin-bottom: 2rem;
}

.btn-custom {
	border-radius: 20px;
	padding: 8px 20px;
}
</style>
</head>
<body class="bg-light">

	<!-- Header Section -->
	<div class="header-section">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-md-8">
					<h1 class="display-4 mb-0">
						<i class="bi bi-journal-bookmark-fill me-3"></i>My Blog
					</h1>
					<p class="lead mb-0">Share your thoughts with the world</p>
				</div>
				<div class="col-md-4 text-end">
					<a href="add" class="btn btn-light btn-lg btn-custom"> <i
						class="bi bi-plus-circle me-2"></i>Add New Post
					</a>
					<%
					if (user == null) {
					%>
					<a href="login" class="btn btn-light btn-lg btn-custom"> <i
						class="bi bi-box-arrow-in-right me-2"></i> Login
					</a>
					<%
					} else {
					%>
					<a href="logout" class="btn btn-light btn-lg btn-custom"> <i
						class="bi bi-box-arrow-right me-2"></i> Logout
					</a>
					<%
					}
					%>

				</div>
			</div>
		</div>
	</div>

	<!-- Main Content -->
	<div class="container">
		<!-- Stats Section -->
		<div class="row mb-4">
			<div class="col-12">
				<div class="alert alert-info d-flex align-items-center" role="alert">
					<i class="bi bi-info-circle-fill me-2"></i>
					<div>
						<strong>Total Posts:</strong> ${posts.size()}
					</div>
				</div>
			</div>
		</div>

		<!-- Blog Posts -->
		<div class="row">
			<%
			List posts = (List) request.getAttribute("posts");
			if (posts != null && posts.size() > 0) {
				for (int i = 0; i < posts.size(); i++) {
					pageContext.setAttribute("currentIndex", i);
			%>
			<div class="col-lg-4 col-md-6 mb-4">
				<div class="card blog-card h-100">
					<div class="card-body d-flex flex-column">
						<!-- Post Title -->
						<h5 class="card-title text-primary mb-3">
							<i class="bi bi-file-earmark-text me-2"></i>
							${posts[currentIndex].title}
						</h5>

						<!-- Post Content Preview -->
						<p class="card-text content-preview flex-grow-1">
							${posts[currentIndex].content}</p>

						<!-- Post Meta Info -->
						<div class="text-muted small mb-3">
							<i class="bi bi-calendar3 me-1"></i> <span>Published
								recently</span>
						</div>

						<!-- Action Buttons -->
						<div class="mt-auto">
							<div class="d-flex justify-content-between align-items-center">
								<a href="view/${posts[currentIndex].id}"
									class="btn btn-outline-primary btn-sm btn-custom"> <i
									class="bi bi-eye me-1"></i>Read More
								</a>
								<div class="btn-group" role="group">
									<a href="edit/${posts[currentIndex].id}"
										class="btn btn-outline-success btn-sm" title="Edit Post">
										<i class="bi bi-pencil-square"></i>
									</a>
									<button type="button" class="btn btn-outline-danger btn-sm"
										onclick="confirmDelete(${posts[currentIndex].id})"
										title="Delete Post">
										<i class="bi bi-trash"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			%>
			<!-- Empty State -->
			<div class="col-12">
				<div class="text-center py-5">
					<i class="bi bi-journal-x display-1 text-muted"></i>
					<h3 class="text-muted mt-3">No Posts Yet</h3>
					<p class="text-muted">Start by creating your first blog post!</p>
					<a href="add" class="btn btn-primary btn-lg btn-custom"> <i
						class="bi bi-plus-circle me-2"></i>Create First Post
					</a>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>

	<!-- Footer -->
	<footer class="bg-dark text-light py-4 mt-5">
		<div class="container text-center">
			<p class="mb-0">&copy; 2024 My Blog. Built with Spring MVC &
				Bootstrap.</p>
		</div>
	</footer>

	<!-- Bootstrap JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>

	<script>
        function confirmDelete(postId) {
            if (confirm('Are you sure you want to delete this post? This action cannot be undone.')) {
                // You can either use a form submission or AJAX call
                // Option 1: Form submission
                var form = document.createElement('form');
                form.method = 'POST';
                form.action = 'delete/' + postId;
                
                // Add CSRF token if needed
                var csrfInput = document.createElement('input');
                csrfInput.type = 'hidden';
                csrfInput.name = '_method';
                csrfInput.value = 'DELETE';
                form.appendChild(csrfInput);
                
                document.body.appendChild(form);
                form.submit();
                
                // Option 2: Simple redirect (if using GET for delete - not recommended)
                // window.location.href = 'delete/' + postId;
            }
        }
        
      
    </script>

</body>
</html>