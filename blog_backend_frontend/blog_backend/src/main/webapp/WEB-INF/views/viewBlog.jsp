<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${post.title}-Spring MVC Blog</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.5/font/bootstrap-icons.min.css"
	rel="stylesheet">
<style>
.hero-section {
	background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
	color: white;
	padding: 3rem 0;
}

.article-container {
	background: white;
	border-radius: 15px;
	box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
	padding: 3rem;
	margin-top: -2rem;
	position: relative;
	z-index: 1;
}

.article-title {
	font-size: 2.5rem;
	font-weight: 700;
	color: #2c3e50;
	line-height: 1.2;
	margin-bottom: 1.5rem;
}

.article-meta {
	border-bottom: 2px solid #f8f9fa;
	padding-bottom: 1.5rem;
	margin-bottom: 2rem;
}

.article-content {
	font-size: 1.1rem;
	line-height: 1.8;
	color: #444;
	margin-bottom: 3rem;
}

.article-content p {
	margin-bottom: 1.5rem;
}

.btn-custom {
	border-radius: 25px;
	padding: 12px 25px;
	font-weight: 500;
	transition: all 0.3s ease;
	text-decoration: none;
}

.btn-custom:hover {
	transform: translateY(-2px);
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
}

.action-buttons {
	background: #f8f9fa;
	border-radius: 10px;
	padding: 1.5rem;
	margin-bottom: 2rem;
}

.share-section {
	background: linear-gradient(45deg, #f8f9fa, #e9ecef);
	border-radius: 10px;
	padding: 1.5rem;
	text-align: center;
}

.reading-time {
	color: #667eea;
	font-weight: 500;
}

.social-share .btn {
	margin: 0 0.25rem;
	border-radius: 50%;
	width: 40px;
	height: 40px;
	display: inline-flex;
	align-items: center;
	justify-content: center;
}

@media ( max-width : 768px) {
	.article-container {
		margin: 1rem;
		padding: 2rem 1.5rem;
	}
	.article-title {
		font-size: 2rem;
	}
}
</style>
</head>
<body class="bg-light">

	<!-- Hero Section -->
	<div class="hero-section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb text-white-50 mb-3">
							<li class="breadcrumb-item"><a
								href="${pageContext.request.contextPath}/" class="text-white">
									<i class="bi bi-house-door me-1"></i>Home
							</a></li>
							<li class="breadcrumb-item"><a
								href="${pageContext.request.contextPath}/posts"
								class="text-white">Blog</a></li>
							<li class="breadcrumb-item active text-white" aria-current="page">Article</li>
						</ol>
					</nav>

					<div class="row align-items-center">
						<div class="col-md-8">
							<span class="badge bg-light text-primary mb-2 px-3 py-2">
								<i class="bi bi-journal-bookmark me-1"></i>Blog Post
							</span>
							<h1 class="display-6 mb-0">Reading Article</h1>
						</div>
						<div class="col-md-4 text-end">
							<a href="${pageContext.request.contextPath}"
								class="btn btn-light btn-lg btn-custom"> <i
								class="bi bi-arrow-left me-2"></i>All Posts
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Main Content -->
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10">
				<article class="article-container">
					<header class="article-meta">
						<h1 class="article-title">${post.title}</h1>

						<div class="row align-items-center text-muted">
							<div class="col-md-6">
								<i class="bi bi-calendar3 me-2"></i> <span>Published on </span>
							</div>
							<div class="col-md-6 text-md-end mt-2 mt-md-0">
								<i class="bi bi-clock me-2"></i> <span class="reading-time"
									id="readingTime">5 min read</span>
							</div>
						</div>

						<div class="row mt-3">
							<div class="col-md-6">
								<i class="bi bi-person me-2 text-muted"></i> <span
									class="text-muted">By </span> <strong class="text-primary">Admin</strong>
							</div>
							<div class="col-md-6 text-md-end mt-2 mt-md-0">
								<i class="bi bi-eye me-2 text-muted"></i> <span
									class="text-muted">1,234 views</span>
							</div>
						</div>
					</header>

					<!-- Article Content -->
					<div class="article-content" id="articleContent">
						${post.content}</div>

					<!-- Article Footer -->
					<footer class="border-top pt-3">
						<div class="row align-items-center">
							<div class="col-md-6">
								<small class="text-muted"> <i class="bi bi-tags me-1"></i>
									Tags: <span class="badge bg-light text-primary me-1">Spring
										MVC</span> <span class="badge bg-light text-primary me-1">Blog</span>
									<span class="badge bg-light text-primary">Web
										Development</span>
								</small>
							</div>
							<div class="col-md-6 text-md-end mt-2 mt-md-0">
								<small class="text-muted"> Last updated: Today </small>
							</div>
						</div>
					</footer>
				</article>

				<!-- Action Buttons -->
				<div class="action-buttons">
					<div class="row align-items-center">
						<div class="col-md-6">
							<div class="d-flex flex-wrap">
								<a href="${pageContext.request.contextPath}/"
									class="btn btn-outline-primary btn-custom me-2 mb-2"> <i
									class="bi bi-house me-2"></i>Back to Home
								</a> <a href="${pageContext.request.contextPath}/posts"
									class="btn btn-outline-secondary btn-custom me-2 mb-2"> <i
									class="bi bi-list me-2"></i>All Posts
								</a>
							</div>
						</div>
						<div class="col-md-6 text-md-end mt-3 mt-md-0">
							<div class="btn-group" role="group">
								<a href="${pageContext.request.contextPath}/edit/${post.id}"
									class="btn btn-success btn-custom"> <i
									class="bi bi-pencil-square me-2"></i>Edit Post
								</a>
								<button type="button" class="btn btn-danger btn-custom"
									onclick="confirmDelete(${post.id})">
									<i class="bi bi-trash me-2"></i>Delete
								</button>
							</div>
						</div>
					</div>
				</div>

				<!-- Share Section -->
				<div class="share-section">
					<h5 class="mb-3">
						<i class="bi bi-share me-2"></i>Share this article
					</h5>
					<div class="social-share">
						<button class="btn btn-primary" onclick="shareOnFacebook()"
							title="Share on Facebook">
							<i class="bi bi-facebook"></i>
						</button>
						<button class="btn btn-info" onclick="shareOnTwitter()"
							title="Share on Twitter">
							<i class="bi bi-twitter"></i>
						</button>
						<button class="btn btn-success" onclick="shareOnWhatsApp()"
							title="Share on WhatsApp">
							<i class="bi bi-whatsapp"></i>
						</button>
						<button class="btn btn-dark" onclick="copyLink()"
							title="Copy Link">
							<i class="bi bi-link-45deg"></i>
						</button>
						<button class="btn btn-warning" onclick="printArticle()"
							title="Print Article">
							<i class="bi bi-printer"></i>
						</button>
					</div>
					<div class="mt-3">
						<small class="text-muted">Help others discover this
							content by sharing!</small>
					</div>
				</div>

				<!-- Related Posts Section -->
				<div class="card border-0 shadow-sm mt-4">
					<div class="card-body">
						<h5 class="card-title text-primary">
							<i class="bi bi-collection me-2"></i>Related Posts
						</h5>
						<div class="row">
							<div class="col-md-6 mb-3">
								<div class="d-flex">
									<div class="flex-shrink-0">
										<div class="bg-primary bg-gradient rounded"
											style="width: 50px; height: 50px; display: flex; align-items: center; justify-content: center;">
											<i class="bi bi-file-earmark-text text-white"></i>
										</div>
									</div>
									<div class="flex-grow-1 ms-3">
										<h6 class="mb-1">
											<a href="#" class="text-decoration-none">Getting Started
												with Spring MVC</a>
										</h6>
										<small class="text-muted">5 min read</small>
									</div>
								</div>
							</div>
							<div class="col-md-6 mb-3">
								<div class="d-flex">
									<div class="flex-shrink-0">
										<div class="bg-success bg-gradient rounded"
											style="width: 50px; height: 50px; display: flex; align-items: center; justify-content: center;">
											<i class="bi bi-code-slash text-white"></i>
										</div>
									</div>
									<div class="flex-grow-1 ms-3">
										<h6 class="mb-1">
											<a href="#" class="text-decoration-none">Advanced Spring
												Boot Features</a>
										</h6>
										<small class="text-muted">8 min read</small>
									</div>
								</div>
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
			<div class="row">
				<div class="col-md-6">
					<p class="mb-0">&copy; 2024 My Blog. Built with Spring MVC &
						Bootstrap.</p>
				</div>
				<div class="col-md-6 text-md-end">
					<small class="text-light"> <i
						class="bi bi-heart-fill text-danger me-1"></i> Made with love for
						sharing knowledge
					</small>
				</div>
			</div>
		</div>
	</footer>

	<!-- Bootstrap JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>

	<script>
        // Calculate reading time based on content length
        function calculateReadingTime() {
            const content = document.getElementById('articleContent').textContent;
            const wordsPerMinute = 200;
            const words = content.trim().split(/\s+/).length;
            const minutes = Math.ceil(words / wordsPerMinute);
            document.getElementById('readingTime').textContent = minutes + ' min read';
        }
        
        // Social sharing functions
        function shareOnFacebook() {
            const url = encodeURIComponent(window.location.href);
            const title = encodeURIComponent(document.title);
            window.open(`https://www.facebook.com/sharer/sharer.php?u=${url}&t=${title}`, 
                       'facebook-share', 'width=580,height=296');
        }
        
        function shareOnTwitter() {
            const url = encodeURIComponent(window.location.href);
            const title = encodeURIComponent(document.title);
            window.open(`https://twitter.com/intent/tweet?url=${url}&text=${title}`, 
                       'twitter-share', 'width=550,height=235');
        }
        
        function shareOnWhatsApp() {
            const url = encodeURIComponent(window.location.href);
            const title = encodeURIComponent(document.title);
            window.open(`https://wa.me/?text=${title} ${url}`, 'whatsapp-share');
        }
        
        function copyLink() {
            navigator.clipboard.writeText(window.location.href).then(function() {
                // Create a temporary toast notification
                const toast = document.createElement('div');
                toast.className = 'alert alert-success position-fixed';
                toast.style.cssText = 'top: 20px; right: 20px; z-index: 9999;';
                toast.innerHTML = '<i class="bi bi-check-circle me-2"></i>Link copied to clipboard!';
                document.body.appendChild(toast);
                
                setTimeout(() => {
                    document.body.removeChild(toast);
                }, 3000);
            });
        }
        
        function printArticle() {
            window.print();
        }
        
        // Delete confirmation
        function confirmDelete(postId) {
            if (confirm('Are you sure you want to delete this post? This action cannot be undone.')) {
                // Redirect to delete endpoint
                window.location.href = '${pageContext.request.contextPath}/delete/' + postId;
            }
        }
        
        // Smooth scrolling for internal links
        document.addEventListener('DOMContentLoaded', function() {
            calculateReadingTime();
            
            // Add smooth scroll behavior
            document.documentElement.style.scrollBehavior = 'smooth';
            
            // Add animation to article content
            const article = document.querySelector('.article-container');
            article.style.animation = 'fadeInUp 0.6s ease-out';
        });
        
        // Add CSS animation keyframes
        const style = document.createElement('style');
        style.textContent = `
            @keyframes fadeInUp {
                from {
                    opacity: 0;
                    transform: translateY(30px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }
            
            .article-content img {
                max-width: 100%;
                height: auto;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
                margin: 1.5rem 0;
            }
            
            .article-content blockquote {
                border-left: 4px solid #667eea;
                padding-left: 1rem;
                margin: 1.5rem 0;
                font-style: italic;
                color: #6c757d;
            }
            
            .article-content code {
                background: #f8f9fa;
                padding: 0.2rem 0.4rem;
                border-radius: 4px;
                font-size: 0.9em;
            }
        `;
        document.head.appendChild(style);
    </script>

</body>
</html>