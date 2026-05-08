<%@page import="com.pastexplorehub.dto.UserDTO"%>
<%@page import="com.pastexplorehub.dto.ProjectDTO"%>
<%@page import="com.pastexplorehub.dto.AdminActivityDTO"%>

<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Insights | PastExploreHub</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"></script>

    <style>
        :root {
            --primary: #4361ee;
            --success: #4cc9f0;
            --warning: #f72585;
            --bg: #f8f9fd;
            --sidebar-bg: #1e1e2d;
            --card-shadow: 0 10px 30px rgba(0, 0, 0, 0.05);
            --transition: all 0.3s ease;
        }

        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Inter', sans-serif; }
        body { background-color: var(--bg); color: #333; overflow-x: hidden; }

        .dashboard-container { display: flex; min-height: 100vh; }

        /* --- Sidebar --- */
        .sidebar {
            width: 260px;
            background: var(--sidebar-bg);
            color: #fff;
            padding: 20px;
            transition: var(--transition);
            z-index: 1000;
        }

        .sidebar-header { text-align: center; padding-bottom: 30px; border-bottom: 1px solid rgba(255,255,255,0.1); }
        .profile-img { width: 80px; height: 80px; border-radius: 50%; border: 3px solid var(--primary); margin-bottom: 15px; object-fit: cover; }
        
        .menu { list-style: none; margin-top: 30px; }
        .menu li { margin-bottom: 15px; }
        .menu a { 
            color: #a2a3b7; text-decoration: none; display: flex; align-items: center; 
            padding: 12px 15px; border-radius: 10px; transition: var(--transition);
        }
        .menu li.active a, .menu a:hover { background: var(--primary); color: #fff; transform: translateX(5px); }
        .menu i { margin-right: 15px; font-size: 1.1rem; }

        /* --- Main Content --- */
        .main-content { flex: 1; padding: 30px; transition: var(--transition); }
        
        header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 40px; }
        .header-actions { display: flex; gap: 10px; }
        
        .action-btn { 
            background: #fff; border: 1px solid #ddd; padding: 8px 15px; 
            border-radius: 8px; cursor: pointer; font-size: 0.85rem; 
            display: flex; align-items: center; gap: 8px; transition: var(--transition);
        }
        .action-btn:hover { background: var(--primary); color: #fff; border-color: var(--primary); }

        /* --- New: Filter Bar --- */
        .filter-bar {
            background: #fff; padding: 15px 25px; border-radius: 16px; 
            box-shadow: var(--card-shadow); margin-bottom: 30px;
            display: flex; justify-content: space-between; align-items: center;
            gap: 20px;
        }
        .filter-group { display: flex; align-items: center; gap: 10px; }
        select { padding: 8px 12px; border-radius: 8px; border: 1px solid #ddd; outline: none; background: #f9f9f9; cursor: pointer; }

        /* --- Stat Cards --- */
        .stats-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 20px; margin-bottom: 40px; }
        .stat-card { 
            background: #fff; padding: 25px; border-radius: 16px; box-shadow: var(--card-shadow);
            display: flex; align-items: center; transition: var(--transition);
        }
        .stat-card:hover { transform: translateY(-5px); }
        .stat-icon { width: 50px; height: 50px; border-radius: 12px; display: flex; align-items: center; justify-content: center; margin-right: 15px; font-size: 1.5rem; }
        .icon-blue { background: #e0e7ff; color: var(--primary); }
        .icon-pink { background: #ffe4f0; color: var(--warning); }
        .icon-cyan { background: #e0f7ff; color: var(--success); }

        /* --- Table Container --- */
        .table-container { background: #fff; border-radius: 16px; padding: 25px; box-shadow: var(--card-shadow); }
        .table-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .search-box { position: relative; }
        .search-box input { padding: 10px 15px 10px 40px; border-radius: 10px; border: 1px solid #ddd; width: 250px; outline: none; }
        .search-box i { position: absolute; left: 15px; top: 12px; color: #aaa; }

        table { width: 100%; border-collapse: collapse; text-align: left; }
        th { padding: 15px; color: #7e8299; font-weight: 600; border-bottom: 1px solid #f1f1f4; font-size: 0.9rem; }
        td { padding: 18px 15px; border-bottom: 1px solid #f1f1f4; vertical-align: middle; font-size: 0.9rem; }
        
        .progress-container { width: 100%; background: #eee; border-radius: 10px; height: 8px; margin-top: 5px; overflow: hidden; }
        .progress-bar { height: 100%; border-radius: 10px; background: var(--primary); transition: width 0.5s ease; }

        .badge { padding: 5px 12px; border-radius: 20px; font-size: 0.75rem; font-weight: bold; }
        .badge-approved { background: #e8fff3; color: #50cd89; }
        .badge-pending { background: #fff8dd; color: #ffc700; }
        .badge-rejected { background: #fff5f8; color: #f1416c; }
        
        /* Performance Tag */
        .perf-tag { font-size: 11px; font-weight: 700; padding: 2px 8px; border-radius: 4px; margin-left: 5px; }
        .high { background: #50cd89; color: #fff; }
        .mid { background: #ffc700; color: #fff; }

        @media (max-width: 992px) {
            .sidebar { position: fixed; left: -260px; height: 100vh; }
            .sidebar.active { left: 0; }
            .main-content { width: 100%; }
            .filter-bar { flex-direction: column; align-items: flex-start; }
        }
    </style>
</head>
<body>
				<% UserDTO user = (UserDTO)session.getAttribute("loggedInUser"); 
				if(user == null){%>
					response.sendRedirect(request.getContextPath() + "/user-api/login");
					return;
					<%}%>
				<% List<String> departments = (List<String>) request.getAttribute("deptList"); %>
				<% List<AdminActivityDTO> activityStats = (List<AdminActivityDTO>) request.getAttribute("activityStats"); %>
				
				<%
				List<ProjectDTO> approvedProjects = (List<ProjectDTO>)request.getAttribute("approvedProjects");
				List<ProjectDTO> pendingProjects = (List<ProjectDTO>)request.getAttribute("pendingProjects");
				List<ProjectDTO> rejectedProjects = (List<ProjectDTO>)request.getAttribute("rejectedProjects");
				
				%>
				<% String name = user.getName(); %>
               
    <div class="dashboard-container">
        <nav class="sidebar">
            <div class="sidebar-header">
                <img src="${pageContext.request.contextPath}/assets/images/admin.jpeg" alt="Admin" class="profile-img">
                
                <h3 style="font-size: 1.1rem;">Hello, <%=  name.toUpperCase().substring(0, name.toUpperCase().indexOf(' ')) %> </h3>
            </div>
            <ul class="menu">
                <li><a href="/pastexplorehub/user/home"><i class="fas fa-home"></i> Projects</a></li>
                <li><a href="/pastexplorehub/user/admin-profile"><i class="fas fa-user"></i> Profile</a></li>
                <li class="active"><a href="/pastexplorehub/user/admin-activity"><i class="fas fa-chart-line"></i> View Activity</a></li>
                <li ><a href="/pastexplorehub/admin/actions/addDepartment"><i class="fas fa-add"></i> Actions</a></li>
                <li style="margin-top: auto;"><a href="/pastexplorehub/user-api/login"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
            </ul>
        </nav>

        <div class="main-content" id="dashboard-capture">
            <header>
                <div class="header-left" style="display: flex; align-items: center; gap: 20px;">
                    <h1>Activity Insights</h1>
                </div>
                <div class="header-actions">
                    <button class="action-btn" onclick="generatePDF()"><i class="fas fa-file-pdf"></i> Export PDF Report</button>
                    <span id="current-date" style="color: #7e8299; font-weight: 500; align-self: center; margin-left: 10px;"></span>
                </div>
            </header>

            <div class="filter-bar">
                <div class="filter-group">
                    <i class="fas fa-filter" style="color: var(--primary);"></i>
                    <label style="font-size: 0.85rem; font-weight: 600;">Department:</label>
                    <select id="deptFilter" onchange="filterTable()">
                        <option value="all">All Departments</option>
                       <% for(String departmentName:departments){ %>
                        <option value="<%= departmentName%>"><%=departmentName %></option>
                       <%} %>
                    </select>
                </div>
                <div class="filter-group">
                    <span style="font-size: 0.85rem; color: #7e8299;">Showing <b id="visible-count">2</b> active records</span>
                </div>
            </div>

            <div class="stats-grid">
                <div class="stat-card">
                    <div class="stat-icon icon-blue"><i class="fas fa-check-circle"></i></div>
                    <div><p style="color: #aaa; font-size: 0.8rem;">Approved</p><h3><%=approvedProjects.size() %></h3></div>
                </div>
                <div class="stat-card">
                    <div class="stat-icon icon-pink"><i class="fas fa-clock"></i></div>
                    <div><p style="color: #aaa; font-size: 0.8rem;">Pending</p><h3><%=pendingProjects.size() %></h3></div>
                </div>
                <div class="stat-card">
                    <div class="stat-icon icon-cyan"><i class="fas fa-folder"></i></div>
                    <div><p style="color: #aaa; font-size: 0.8rem;">Total Projects</p><h3><%=approvedProjects.size()+pendingProjects.size()+rejectedProjects.size() %></h3></div>
                </div>
            </div>

            <div class="table-container">
                <div class="table-header">
                    <h2 style="font-size: 1.2rem;">Guide Productivity Analytics</h2>
                    <div class="search-box">
                        <i class="fas fa-search"></i>
                        <input type="text" id="tableSearch" placeholder="Search Guide Name...">
                    </div>
                </div>
                <table id="projectTable">
                    <thead>
                        <tr>
                            <th>Guide Name</th>
                            <th>Department</th>
                            <th>Approved</th>
                            <th>Pending</th>
                            <th>Rejected</th>
                            <th>Efficiency</th>
                        </tr>
                    </thead>
                    <tbody>
                   <%	
                   		for(AdminActivityDTO activityStat:activityStats){
                
                %> 
                        <tr class="guide-row" data-dept="<%= activityStat.getDepartment() %>">
                            <td>
                                <div style="display: flex; align-items: center; gap: 10px;">
                                    <div style="width: 35px; height: 35px; background: #eee; border-radius: 50%; display: flex; align-items: center; 
                                    justify-content: center; font-weight: bold; color: var(--primary);"><%= activityStat.getGuideName().charAt(0)+""+activityStat.getGuideName().charAt(activityStat.getGuideName().indexOf(' ')+1) %></div>
                                    <div>
                                        <strong><%= activityStat.getGuideName() %></strong>
                                        <span class="perf-tag high">TOP PERFORMER</span>
                                    </div>
                                </div>
                            </td>
                            <td><%= activityStat.getDepartment() %></td>
                            <td><span class="badge badge-approved"><%= activityStat.getApproved() %></span></td>
                            <td><span class="badge badge-pending"><%= activityStat.getPending() %></span></td>
                            <td><span class="badge badge-rejected"><%= activityStat.getRejected() %></span></td>
                            <td>
                                <div style="font-size: 0.8rem; margin-bottom: 5px; font-weight: bold; color: var(--primary);"><%= activityStat.GetEfficiency() %>%</div>
                                <div class="progress-container"><div class="progress-bar" style="width: <%= activityStat.GetEfficiency() %>%;"></div></div>
                            </td>
                        </tr>
                <% }%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script>
        // Set current date
        document.getElementById('current-date').innerText = new Date().toDateString();

        // Combined Search and Department Filter
        function filterTable() {
            let searchFilter = document.getElementById('tableSearch').value.toUpperCase();
            let deptFilter = document.getElementById('deptFilter').value;
            let rows = document.querySelectorAll('.guide-row');
            let visibleCount = 0;

            rows.forEach(row => {
                let name = row.querySelector('strong').innerText.toUpperCase();
                let dept = row.getAttribute('data-dept');
                
                let matchesSearch = name.includes(searchFilter);
                let matchesDept = (deptFilter === 'all' || dept === deptFilter);

                if (matchesSearch && matchesDept) {
                    row.style.display = "";
                    visibleCount++;
                } else {
                    row.style.display = "none";
                }
            });
            document.getElementById('visible-count').innerText = visibleCount;
        }

        document.getElementById('tableSearch').addEventListener('keyup', filterTable);

        // Sidebar Toggle
        const toggleMenu = document.getElementById("toggle-menu");
        const sidebar = document.querySelector(".sidebar");
        toggleMenu.addEventListener("click", () => sidebar.classList.toggle("active"));

        // Feature: Generate PDF Report
        function generatePDF() {
            const element = document.getElementById('dashboard-capture');
            const opt = {
                margin:       [0.5, 0.5],
                filename:     'Admin_Activity_Report.pdf',
                image:        { type: 'jpeg', quality: 0.98 },
                html2canvas:  { scale: 2, useCORS: true },
                jsPDF:        { unit: 'in', format: 'letter', orientation: 'portrait' }
            };
            html2pdf().set(opt).from(element).save();
        }
    </script>
</body>
</html>