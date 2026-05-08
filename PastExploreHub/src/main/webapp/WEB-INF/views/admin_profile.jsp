
<%@page import="com.pastexplorehub.dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% UserDTO user = (UserDTO)session.getAttribute("loggedInUser"); %>
<% String name = user.getName(); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Profile - Explore Hub</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/profile.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <div class="dashboard-container">
        <nav class="sidebar">
            
            <div class="sidebar-header">
                        <img src="${pageContext.request.contextPath}/assets/images/admin.jpeg" alt="Admin Image" class="profile-img">
                
                <% if(name != null) { %>
                <h3 id="sname" style="color: rgb; font-weight: bold;">
                <%= "Hello, "%><%=  name.toUpperCase().substring(0, name.toUpperCase().indexOf(' ')) %></h3>
                <% } %> 

            </div>
            
            
            <ul class="menu">
                <li><a href="/pastexplorehub/user/home"><i class="fas fa-home"></i> Porjects</a></li>
                <li class="active"><a href="/pastexplorehub/user/admin-profile"><i class="fas fa-user"></i> Profile</a></li>
                <li><a href="/pastexplorehub/user/admin-activity"><i class="fas fa-chart-line"></i> View Activity</a></li>
              <li ><a href="/pastexplorehub/user/actions/addDepartment"><i class="fas fa-add"></i> Actions</a></li>
                <li class="logout-item"><a href="/pastexplorehub/user-api/login"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
            </ul>
        </nav>
        
        <div class="main-content">
            <header>
                <div class="header-left">
                    <button id="toggle-menu" class="toggle-btn"><i class="fas fa-bars"></i></button>
                    <h1>Profile</h1>
                </div>
            </header>
            
            <!-- Profile Page Section -->
            <section class="profile-container">
                <div class="profile-header">
                
                <img src="${pageContext.request.contextPath}/assets/images/admin.jpeg" alt="Admin Image" class="profile-img">
                
                <% if(name != null) { %>
                <h2 id="sname" style="color: rgb; font-weight: bold;">
                <%=  name%></h2>
                <% } %> 

                </div>
                
                <form id="profile-form" action="EditDetailsServlet" method="post">
                    	<%
					if (user != null) {
						String department = null;
						Long val = user.getDepartment();
						switch (val.intValue()) {
						case 1:
							department = "Computer Science";
							break;
						case 2:
							department = "Information Technolodgy";
							break;

						case 3:
							department = "Artificial Intaligance";
							break;
						case 4:
							department = "Genral Department";
							break;
						}
					%>
                    <div class="profile-info">
                        <div class="info-item">
                            <div class="info-label">Name</div>
                            <input type="text" class="info-value" id="name-input" value="<%=user.getName() %>" readonly>
                        </div>
                        
                        <div class="info-item">
                            <div class="info-label">Department</div>
                            <input type="text" class="info-value" id="department-input" value="<%=department %>" readonly>
                        </div>
                        
                        <div class="info-item">
                            <div class="info-label">Role</div>
                            <input type="text" class="info-value" id="project-input" value="<%=user.getRole() %>" readonly>
                        </div>
                        
                        <div class="info-item">
                            <div class="info-label">Email ID</div>
                            <input type="email" class="info-value" id="email-input" value="<%=user.getEmail() %>" readonly>
                        </div>
                        
                        <div class="info-item">
                            <div class="info-label">Student ID</div>
                            <input type="text" class="info-value" id="student-id-input" value="<%=user.getEnrollId() %>" readonly>
                        </div>
                        
                        <div class="info-item">
                            <div class="info-label">Profession</div>
                            <input type="text" class="info-value" id="profession-input" value="<%= department + " " + user.getRole() %>" readonly>
                        </div>
                    </div>
                    
                    <div class="profile-actions">
                        <button type="button" id="edit-profile" class="edit-button">Edit Profile</button>
                        <button type="button" id="save-profile" class="save-button" style="display: none;">Save Profile</button>
                        <button type="button" id="cancel-edit" class="cancel-button" style="display: none;">Cancel</button>
                    </div>
                        <%}
                    else{   response.sendRedirect("login.jsp"); }
                    %>
                </form>
            </section>
        </div>
    </div>
        <script src="${pageContext.request.contextPath}/assets/js/profile.js"></script>
</body>
</html>