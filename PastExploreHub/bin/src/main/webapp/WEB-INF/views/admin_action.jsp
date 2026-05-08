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
    <style>
        :root {
            --primary: #4361ee;
            --secondary: #3f37c9;
            --text-main: #2b2d42;
            --text-muted: #6c757d;
            --bg-card: #ffffff;
            --border-color: #e2e8f0;
        }

        /* Form Container Styles */
        .action-card {
            background: var(--bg-card);
            max-width: 600px;
            margin: 40px auto;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.08);
            border: 1px solid var(--border-color);
        }

        .card-header {
            text-align: center;
            margin-bottom: 35px;
        }

        .card-header i {
            font-size: 2.5rem;
            color: var(--primary);
            margin-bottom: 15px;
        }

        .card-header h2 {
            color: var(--text-main);
            font-size: 1.5rem;
            font-weight: 700;
        }

        .card-header p {
            color: var(--text-muted);
            font-size: 0.9rem;
        }

        /* Form Controls */
        .form-group {
            margin-bottom: 25px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: var(--text-main);
            font-size: 0.95rem;
        }

        .input-group {
            position: relative;
            display: flex;
            align-items: center;
        }

        .input-group i {
            position: absolute;
            left: 15px;
            color: var(--text-muted);
            transition: color 0.3s;
        }

        .input-group input {
            width: 100%;
            padding: 14px 15px 14px 45px;
            border: 2px solid var(--border-color);
            border-radius: 12px;
            font-size: 1rem;
            outline: none;
            transition: all 0.3s;
        }

        .input-group input:focus {
            border-color: var(--primary);
            box-shadow: 0 0 0 4px rgba(67, 97, 238, 0.1);
        }

        .input-group input:focus + i {
            color: var(--primary);
        }

        /* Button Style */
        .submit-btn {
            width: 100%;
            padding: 16px;
            background: var(--primary);
            color: white;
            border: none;
            border-radius: 12px;
            font-size: 1rem;
            font-weight: 700;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 10px;
            transition: all 0.3s;
        }

        .submit-btn:hover {
            background: var(--secondary);
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(63, 55, 201, 0.2);
        }

        .submit-btn:active {
            transform: translateY(0);
        }
    </style>
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