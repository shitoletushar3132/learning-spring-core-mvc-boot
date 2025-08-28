<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>

<%
String user = (String) session.getAttribute("loggedInUser");
if (user == null) {
	response.sendRedirect("login"); // not logged in
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add New Blog Post - Spring MVC Blog</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css"
	rel="stylesheet">
<style>
.header-section {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	color: white;
	padding: 2rem 0;
	margin-bottom: 2rem;
}

.form-container {
	background: white;
	border-radius: 15px;
	box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
	padding: 2rem;
	margin-bottom: 2rem;
}

.btn-custom {
	border-radius: 25px;
	padding: 12px 30px;
	font-weight: 500;
	transition: all 0.3s ease;
}

.btn-custom:hover {
	transform: translateY(-2px);
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
}

.form-control:focus {
	border-color: #667eea;
	box-shadow: 0 0 0 0.2rem rgba(102, 126, 234, 0.25);
}

.character-counter {
	font-size: 0.875rem;
	color: #6c757d;
}

.preview-section {
	background: #f8f9fa;
	border-left: 4px solid #667eea;
	padding: 1rem;
	border-radius: 0 8px 8px 0;
	margin-top: 1rem;
}
</style>
</head>
<body class="bg-light">



	<!-- Header Section -->
	<div class="header-section">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-md-8">
					<h1 class="display-5 mb-0">
						<i class="bi bi-plus-circle-fill me-3"></i>Create New Post
					</h1>
					<p class="lead mb-0">Share your thoughts and ideas</p>
				</div>
				<div class="col-md-4 text-end">
					<a href="${pageContext.request.contextPath}"
						class="btn btn-light btn-lg"> <i class="bi bi-arrow-left me-2"></i>Back
						to Posts
					</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Main Content -->
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10">
				<div class="form-container">
					<!-- Form Header -->
					<div class="text-center mb-4">
						<h3 class="text-primary">
							<i class="bi bi-journal-plus me-2"></i>New Blog Post
						</h3>
						<p class="text-muted">Fill in the details below to create your
							blog post</p>
					</div>

					<!-- Blog Post Form -->
					<form action="save" method="post" id="blogForm" novalidate>
						<!-- Title Input -->
						<div class="mb-4">
							<label for="title" class="form-label"> <i
								class="bi bi-type me-2 text-primary"></i> <strong>Post
									Title</strong>
							</label> <input type="text" class="form-control form-control-lg"
								id="title" name="title"
								placeholder="Enter an engaging title for your post..." required
								maxlength="100">
							<div class="d-flex justify-content-between mt-2">
								<div class="invalid-feedback">Please provide a valid title
									for your post.</div>
								<small class="character-counter"> <span id="titleCount">0</span>/100
									characters
								</small>
							</div>
						</div>

						<!-- Content Textarea -->
						<div class="mb-4">
							<label for="content" class="form-label"> <i
								class="bi bi-file-earmark-text me-2 text-primary"></i> <strong>Post
									Content</strong>
							</label>
							<textarea class="form-control" id="content" name="content"
								rows="10"
								placeholder="Write your blog post content here... Share your thoughts, experiences, or knowledge with your readers."
								required maxlength="5000"></textarea>
							<div class="d-flex justify-content-between mt-2">
								<div class="invalid-feedback">Please provide content for
									your post.</div>
								<small class="character-counter"> <span
									id="contentCount">0</span>/5000 characters
								</small>
							</div>
						</div>

						<!-- Live Preview Toggle -->
						<div class="mb-3">
							<button type="button" class="btn btn-outline-info btn-sm"
								onclick="togglePreview()">
								<i class="bi bi-eye me-1"></i> <span id="previewToggleText">Show
									Preview</span>
							</button>
						</div>

						<!-- Preview Section (Hidden by default) -->
						<div id="previewSection" class="preview-section"
							style="display: none;">
							<h5 class="text-primary mb-3">
								<i class="bi bi-eye me-2"></i>Preview
							</h5>
							<div id="previewContent">
								<h4 id="previewTitle" class="mb-3">Your title will appear
									here</h4>
								<p id="previewText" class="text-muted">Your content will
									appear here</p>
							</div>
						</div>

						<!-- Action Buttons -->
						<div
							class="d-flex justify-content-between align-items-center mt-4 pt-3 border-top">
							<a href="${pageContext.request.contextPath}/posts"
								class="btn btn-outline-secondary btn-lg btn-custom"> <i
								class="bi bi-x-circle me-2"></i>Cancel
							</a>

							<div>
								<button type="button"
									class="btn btn-outline-primary btn-lg btn-custom me-2"
									onclick="saveDraft()">
									<i class="bi bi-file-earmark me-2"></i>Save as Draft
								</button>
								<button type="submit" class="btn btn-primary btn-lg btn-custom">
									<i class="bi bi-check-circle me-2"></i>Publish Post
								</button>
							</div>
						</div>
					</form>
				</div>

				<!-- Writing Tips Card -->
				<div class="card border-0 shadow-sm">
					<div class="card-body">
						<h5 class="card-title text-primary">
							<i class="bi bi-lightbulb me-2"></i>Writing Tips
						</h5>
						<div class="row">
							<div class="col-md-6">
								<ul class="list-unstyled">
									<li><i class="bi bi-check-circle-fill text-success me-2"></i>Use
										engaging titles</li>
									<li><i class="bi bi-check-circle-fill text-success me-2"></i>Break
										content into paragraphs</li>
									<li><i class="bi bi-check-circle-fill text-success me-2"></i>Share
										personal experiences</li>
								</ul>
							</div>
							<div class="col-md-6">
								<ul class="list-unstyled">
									<li><i class="bi bi-check-circle-fill text-success me-2"></i>Ask
										questions to engage readers</li>
									<li><i class="bi bi-check-circle-fill text-success me-2"></i>Use
										clear, simple language</li>
									<li><i class="bi bi-check-circle-fill text-success me-2"></i>Review
										before publishing</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
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
		// Character counters
		document
				.getElementById('title')
				.addEventListener(
						'input',
						function() {
							document.getElementById('titleCount').textContent = this.value.length;
							updatePreview();
						});

		document
				.getElementById('content')
				.addEventListener(
						'input',
						function() {
							document.getElementById('contentCount').textContent = this.value.length;
							updatePreview();
						});

		// Form validation
		(function() {
			'use strict';
			const form = document.getElementById('blogForm');

			form.addEventListener('submit', function(event) {
				if (!form.checkValidity()) {
					event.preventDefault();
					event.stopPropagation();
				}
				form.classList.add('was-validated');
			}, false);
		})();

		// Preview functionality
		function togglePreview() {
			const previewSection = document.getElementById('previewSection');
			const toggleText = document.getElementById('previewToggleText');

			if (previewSection.style.display === 'none') {
				previewSection.style.display = 'block';
				toggleText.textContent = 'Hide Preview';
				updatePreview();
			} else {
				previewSection.style.display = 'none';
				toggleText.textContent = 'Show Preview';
			}
		}

		function updatePreview() {
			const title = document.getElementById('title').value
					|| 'Your title will appear here';
			const content = document.getElementById('content').value
					|| 'Your content will appear here';

			document.getElementById('previewTitle').textContent = title;
			document.getElementById('previewText').textContent = content;
		}

		// Save as draft functionality
		function saveDraft() {
			const formData = new FormData(document.getElementById('blogForm'));
			formData.append('status', 'draft');

			// You can implement AJAX call here to save as draft
			alert('Draft saved successfully! (This would save to your drafts folder)');
		}

		// Auto-save functionality (optional)
		let autoSaveTimer;
		function startAutoSave() {
			clearTimeout(autoSaveTimer);
			autoSaveTimer = setTimeout(function() {
				const title = document.getElementById('title').value;
				const content = document.getElementById('content').value;

				if (title.trim() || content.trim()) {
					// Save to localStorage as backup
					localStorage.setItem('blog_draft_title', title);
					localStorage.setItem('blog_draft_content', content);
					console.log('Auto-saved to local storage');
				}
			}, 5000); // Auto-save every 5 seconds
		}

		document.getElementById('title').addEventListener('input',
				startAutoSave);
		document.getElementById('content').addEventListener('input',
				startAutoSave);

		// Load draft from localStorage on page load
		window
				.addEventListener(
						'load',
						function() {
							const draftTitle = localStorage
									.getItem('blog_draft_title');
							const draftContent = localStorage
									.getItem('blog_draft_content');

							if (draftTitle) {
								document.getElementById('title').value = draftTitle;
								document.getElementById('titleCount').textContent = draftTitle.length;
							}

							if (draftContent) {
								document.getElementById('content').value = draftContent;
								document.getElementById('contentCount').textContent = draftContent.length;
							}
						});

		// Clear localStorage when form is successfully submitted
		document.getElementById('blogForm').addEventListener('submit',
				function() {
					if (this.checkValidity()) {
						localStorage.removeItem('blog_draft_title');
						localStorage.removeItem('blog_draft_content');
					}
				});

		// Add loading state to submit button
		document
				.getElementById('blogForm')
				.addEventListener(
						'submit',
						function(e) {
							if (this.checkValidity()) {
								const submitBtn = this
										.querySelector('button[type="submit"]');
								submitBtn.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Publishing...';
								submitBtn.disabled = true;
							}
						});
	</script>

</body>
</html>