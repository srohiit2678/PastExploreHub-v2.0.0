
<%@page import="com.pastexplorehub.dto.UserDTO"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Dashboard - Explore Hub</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/validation.js"></script>
    <style>
         
     .popup {
    display: none; /* Initially hidden */
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100vh; /* Ensure the popup takes full screen */
    background: rgba(0, 0, 0, 0.5);
    justify-content: center;
    align-items: center;
}

.popup-content {
    background: white;
    width: 70%;
    height: 80vh; /* Explicit height */
    position: relative;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
    display: flex;
    flex-direction: column; /* Ensures iframe fills the content */
}

.popup-content iframe {
    flex: 1; /* Makes iframe take full available height */
    width: 100%;
    height: 100%;
    border: none;
}

    </style>

</head>
<body>
    
    <div class="dashboard-container">
        
        <nav class="sidebar">
            <div class="sidebar-header">
                <img src="${pageContext.request.contextPath}/assets/images/student.webp" alt="Student Image" class="profile-img">
                
                <% UserDTO user = (UserDTO) session.getAttribute("loggedInUser"); %>
                <% if(user.getName() != null) { %>
                <h3 id="sname" style="color: rgb; font-weight: bold;">
                <%= "Hello, "%><%=  user.getName().toUpperCase().substring(0, user.getName().toUpperCase().indexOf(' ')) %></h3>
                <% } %> 

            </div>
            <ul class="menu">
                <li class="active"><a href="/pastexplorehub/user/home"><i class="fas fa-home"></i> Porjects</a></li>
                <li><a href="/pastexplorehub/user/student-profile"><i class="fas fa-user"></i> Profile</a></li>
                <li><a href="/pastexplorehub/user/upload-project"><i class="fas fa-upload"></i>  Upload Projects</a></li>
                <li class="logout-item"><a href="/pastexplorehub/user-api/login"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
            </ul>
        </nav>
        
        <div class="main-content">
            <header>
                <div class="header-left">
                    <button id="toggle-menu" class="toggle-btn"><i class="fas fa-bars"></i></button>
                    <h1>Projects-Dashboard</h1>
                </div>
                <input type="text" id="search" placeholder="Search projects...">
            </header>
            <section class="project-list content-section" id="dashboard-content">
              				<jsp:include page="dashboard.jsp" />
              
                <!-- Pagination Controls -->
                <div class="pagination">
                    <a href="#" class="prev">Previous</a>
                    <span>Page 1 of 5</span>
                    <a href="#" class="next">Next</a>
                </div>
      
      </div>
  </div>
</body>
    <script>
        searching();
    </script>	
</html>