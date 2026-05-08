<%@page import="com.pastexplorehub.dto.ProjectDTO"%>
<%@page import="com.pastexplorehub.entity.ProjectComment"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Project View - Explore Hub</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap">
    <style>
        /* CSS Variables & Global Styles */
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Poppins', sans-serif; }
        :root {
            --primary-color: #4361ee;
            --secondary-color: #3f37c9;
            --accent-color: #4cc9f0;
            --text-color: #2b2d42;
            --light-text: #8d99ae;
            --background: #f8f9fa;
            --card-bg: #ffffff;
            --border-radius: 12px;
            --box-shadow: 0 10px 20px rgba(0, 0, 0, 0.05);
            --hover-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
            --border-color: #e9ecef;
        }
        body { background-color: var(--background); color: var(--text-color); padding: 20px; }
        .container { max-width: 1200px; margin: 0 auto; }
        .main-content { display: flex; flex-wrap: wrap; gap: 30px; }
        .project-details { flex: 2; min-width: 300px; }
        .college-info { flex: 1; min-width: 280px; background: var(--card-bg); padding: 25px; border-radius: var(--border-radius); box-shadow: var(--box-shadow); border: 1px solid var(--border-color); height: fit-content; }
        
        .section { background: var(--card-bg); padding: 25px; border-radius: var(--border-radius); margin-bottom: 25px; box-shadow: var(--box-shadow); border: 1px solid var(--border-color); }
        .project-title { font-size: 28px; font-weight: 600; margin-bottom: 15px; color: var(--primary-color); border-bottom: 2px solid var(--accent-color); padding-bottom: 10px; }

        /* Slideshow Styling */
        .slideshow-container { position: relative; width: 100%; height: 400px; margin-bottom: 25px; border-radius: var(--border-radius); overflow: hidden; background: #000; border: 1px solid var(--border-color); }
        .slide { display: none; width: 100%; height: 100%; }
        .slide.active { display: block; animation: fadeIn 0.8s; }
        @keyframes fadeIn { from { opacity: 0.4; } to { opacity: 1; } }
        .slide img { width: 100%; height: 100%; object-fit: contain; }

        .prev, .next { position: absolute; top: 50%; transform: translateY(-50%); padding: 12px; color: white; background: rgba(67, 97, 238, 0.7); border: none; cursor: pointer; border-radius: 50%; z-index: 10; }
        .prev { left: 15px; } .next { right: 15px; }
        
        .indicators { text-align: center; position: absolute; bottom: 15px; width: 100%; z-index: 10; }
        .indicator { display: inline-block; width: 10px; height: 10px; background: rgba(255,255,255,0.5); border-radius: 50%; margin: 0 5px; cursor: pointer; }
        .indicator.active { background: white; transform: scale(1.3); }

        /* Info Styles */
        .tech-tag { display: inline-block; background: rgba(67, 97, 238, 0.1); color: var(--primary-color); padding: 5px 12px; border-radius: 20px; font-size: 14px; margin-top: 10px; font-weight: 500; }
        .project-link { display: inline-flex; align-items: center; gap: 10px; padding: 12px 20px; background: var(--primary-color); color: white; text-decoration: none; border-radius: 25px; margin-right: 10px; margin-top: 10px; transition: 0.3s; }
        .project-link:hover { background: var(--secondary-color); transform: translateY(-2px); }
        
        .team-member { padding: 12px; border-radius: 8px; background: #f8f9fa; border: 1px solid var(--border-color); margin-bottom: 10px; }
        .member-name { font-weight: 500; font-size: 15px; }
        .college-logo { width: 100px; display: block; margin: 0 auto 15px; }
        
        @media (max-width: 768px) { .main-content { flex-direction: column; } .slideshow-container { height: 250px; } }
    </style>
    
    <style>
    .comment-section { margin-top: 30px; border-top: 2px solid var(--border-color); padding-top: 20px; }
    .comment-box { background: #fff; padding: 15px; border-radius: 8px; margin-bottom: 15px; border: 1px solid var(--border-color); }
    .comment-user { font-weight: 600; color: var(--primary-color); font-size: 14px; }
    .comment-date { font-size: 12px; color: var(--light-text); margin-left: 10px; }
    .comment-user { font-weight: 600; color: var(--primary-color); font-size: 14px;  }
    .comment-text { margin-top: 8px; font-size: 14px; line-height: 1.5; }
    .comment-form textarea { width: 100%; padding: 12px; border-radius: 8px; border: 1px solid var(--border-color); resize: vertical; margin-bottom: 10px; }
    .btn-comment { background: var(--primary-color); color: white; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer; }
	</style>
    
</head>
<body>
    <% try { 
        ProjectDTO project = (ProjectDTO) session.getAttribute("projectdetails");
      
        if (project != null) { 
    %>
    <div class="container">
        <div class="main-content">
            <div class="project-details">
                <div class="section">
                    <h1 class="project-title"><%= project.getTitle() %></h1>
                </div>

                <% if (project.getBase64Images() != null && !project.getBase64Images().isEmpty()) { %>
                <div class="slideshow-container">
                    <% for (int i = 0; i < project.getBase64Images().size(); i++) { %>
                        <div class="slide <%= (i == 0) ? "active" : "" %>">
                            <img src="<%= project.getBase64Images().get(i) %>" alt="Project Screenshot">
                        </div>
                    <% } %>

                    <% if (project.getBase64Images().size() > 1) { %>
                        <button class="prev" onclick="changeSlide(-1)"><i class="fas fa-chevron-left"></i></button>
                        <button class="next" onclick="changeSlide(1)"><i class="fas fa-chevron-right"></i></button>
                        <div class="indicators">
                            <% for (int i = 0; i < project.getBase64Images().size(); i++) { %>
                                <span class="indicator <%= (i == 0) ? "active" : "" %>" onclick="goToSlide(<%= i %>)"></span>
                            <% } %>
                        </div>
                    <% } %>
                </div>
                <% } %>

                <div class="section">
                    <h2 style="font-size: 20px; margin-bottom: 10px; color: var(--primary-color);">Description</h2>
                    <p style="line-height: 1.6;"><%= project.getDescription() %></p>
                    <span class="tech-tag"><i class="fas fa-code"></i> <%= project.getTechStack() %></span>
                </div>

                <div class="section">
                    <h2 style="font-size: 20px; margin-bottom: 15px; color: var(--primary-color);">Project Assets</h2>
                    <div class="links-container">
                        <% if (project.getProjectLink() != null && !project.getProjectLink().isEmpty()) { %>
                            <a href="<%= project.getProjectLink() %>" class="project-link" target="_blank">
                                <i class="fab fa-github"></i> View Repository
                            </a>
                        <% } %>

						<%
						if (project.getBase64PDF() != null) {
						%>
						<a href="<%=project.getBase64PDF()%>" download="<%=project.getPdfFileName()%>" class="project-link"
							style="background: #e63946; color: white; text-decoration: none; padding: 10px 20px; border-radius: 25px;">
							<i class="fas fa-file-pdf"></i> Download Project PDF
						</a>
						<%
						}
						%>
					</div>
                </div>
            </div>

            <div class="college-info">
                <img src="${pageContext.request.contextPath}/assets/images/cdgi.png" alt="College Logo" class="college-logo">
                <h2 style="text-align: center; font-size: 18px; margin-bottom: 20px; color: var(--primary-color);">CDGI, Indore</h2>

                <div style="margin-bottom: 20px;">
                    <h3 style="font-size: 16px; margin-bottom: 10px;"><i class="fas fa-user-tie"></i> Mentor</h3>
                    <p style="color: var(--secondary-color); font-weight: 500;"><%= project.getGuideName() %></p>
                </div>

                <div>
                    <h3 style="font-size: 16px; margin-bottom: 10px;"><i class="fas fa-users"></i> Team Members</h3>
                    <div class="team-member" style="border-left: 4px solid var(--accent-color);">
                        <p class="member-name"><strong>Lead:</strong> <%= project.getLeadName() %></p>
                        <p style="font-size: 12px; color: var(--light-text);"><%= project.getLeadEnrollId() %></p>
                    </div>

                    <%
                        List<String> names = project.getTeamMemberName();
                        List<String> enrolls = project.getTeamMemberEnrollId();
                        if(names != null) {
                            for(int i = 0; i < names.size(); i++) {
                    %>
                        <div class="team-member">
                            <p class="member-name"><%= names.get(i) %></p>
                            <p style="font-size: 12px; color: var(--light-text);"><%= enrolls.get(i) %></p>
                        </div>

                    <%
                            }
                        }
                    %>

<div class="section comment-section">
    <h2 style="font-size: 20px; margin-bottom: 15px; color: var(--primary-color);">Discussion</h2>

   

        <% List<ProjectComment> comments =  (List<ProjectComment>)session.getAttribute("project-comments");
        	if(!comments.isEmpty()){ %>
       <div id="commentsContainer">        		
            	 <% for(ProjectComment comment : comments){ %>
			<div class="comment-box">
                <span class="comment-user" ><%=comment.getUser().getName() %></span>
                <span class="comment-user" >[<%= comment.getUser().getEnrollId()%>]</span>
                <span class="comment-date" ><%=comment.getDate()%></span>
                <p class="comment-text" ><%=comment.getMessage() %></p>
            </div>
              <% } %>
             <% }else{ %>
                <p style="color: var(--light-text); font-size: 14px;">No comments yet. Start the discussion!</p>
             <% } %>        
    </div>
    <div class="comment-form" style="margin-top: 20px;">
        <form action="/pastexplorehub/project/add-comment" method="post">
            <input type="hidden" name="projectId" value="<%= project.getProjectId() %>">
            <textarea name="message" rows="3" placeholder="Write a comment..." required></textarea>
            <button type="submit" class="btn-comment">Post Comment</button>
        </form>
    </div>
</div>
				</div>
            </div>
        </div>
        <%
        } else {
        %>
            <div class="section"><p>Project details not found.</p></div>
        <% } 
    } catch(Exception e) { e.printStackTrace(); } %>
    </div>
    
    <script>
        let currentSlide = 0;

        function showSlide(index) {
            const slides = document.querySelectorAll('.slide');
            const indicators = document.querySelectorAll('.indicator');
            
            if (slides.length === 0) return;

            // Reset
            slides.forEach(s => s.classList.remove('active'));
            indicators.forEach(i => i.classList.remove('active'));

            // Show current
            if (slides[index]) {
                slides[index].classList.add('active');
                if (indicators[index]) indicators[index].classList.add('active');
            }
        }

        function changeSlide(step) {
            const slides = document.querySelectorAll('.slide');
            currentSlide = (currentSlide + step + slides.length) % slides.length;
            showSlide(currentSlide);
        }

        function goToSlide(index) {
            currentSlide = index;
            showSlide(currentSlide);
        }
        // Auto play if more than 1 slide
        setInterval(() => {
            const slides = document.querySelectorAll('.slide');
            if (slides.length > 1) changeSlide(1);
        }, 5000);
    </script>
</body>
</html>