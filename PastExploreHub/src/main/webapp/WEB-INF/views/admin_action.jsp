<%@page import="com.pastexplorehub.dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% UserDTO user = (UserDTO)session.getAttribute("loggedInUser"); %>
<% String name = (user != null) ? user.getName() : "Admin"; %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actions - Explore Hub</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/profile.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin_action.css">
</head>
<body>
    <div class="dashboard-container">
        <nav class="sidebar">
            <div class="sidebar-header">
                <img src="${pageContext.request.contextPath}/assets/images/admin.jpeg" alt="Admin Image" class="profile-img">
                <% if(name != null) { 
                    int spaceIdx = name.indexOf(' ');
                    String firstName = (spaceIdx != -1) ? name.substring(0, spaceIdx) : name;
                %>
                <h3 id="sname" style="font-weight: bold;">
                    Hello, <%= firstName.toUpperCase() %>
                </h3>
                <% } %> 
            </div>
            
            <ul class="menu">
                <li><a href="/pastexplorehub/user/home"><i class="fas fa-home"></i> Projects</a></li>
                <li><a href="/pastexplorehub/user/admin-profile"><i class="fas fa-user"></i> Profile</a></li>
                <li><a href="/pastexplorehub/user/admin-activity"><i class="fas fa-chart-line"></i> View Activity</a></li>
                <li class="active"><a href="/pastexplorehub/user/actions/addDepartment"><i class="fas fa-plus-square"></i> Actions</a></li>
                <li class="logout-item"><a href="/pastexplorehub/user-api/login"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
            </ul>
        </nav>
        
        <div class="main-content">
            <header>
                <div class="header-left">
                    <button id="toggle-menu" class="toggle-btn"><i class="fas fa-bars"></i></button>
                    <h1>Administrative Actions</h1>
                </div>
            </header>
            
            <section class="profile-container">
                <div class="action-card">
                    <div class="card-header">
                        <i class="fas fa-university"></i>
                        <h2>Register Department</h2>
                        <p>Configure a new department for project allocation</p>
                    </div>

                    <form action="/pastexplorehub/user/actions/saveDepartment" method="POST">
                        <div class="form-group">
                            <label for="deptName">Department Name</label>
                            <div class="input-group">
                                <i class="fas fa-signature"></i>
                                <input type="text" id="deptName" name="deptName" placeholder="e.g. Information Technology" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="deptCode">Department Code</label>
                            <div class="input-group">
                                <i class="fas fa-hashtag"></i>
                                <input type="text" id="deptCode" name="deptCode" placeholder="e.g. IT" required>
                            </div>
                        </div>

                        <button type="submit" class="submit-btn"><i class="fas fa-save"></i> Save Department </button>
                    </form>
                </div>
                </section>
        </div>
    </div>
    
    <script>
        // Simple sidebar toggle logic if needed
        document.getElementById('toggle-menu').onclick = function() {
            document.querySelector('.sidebar').classList.toggle('active');
        };
    </script>
</body>
</html>