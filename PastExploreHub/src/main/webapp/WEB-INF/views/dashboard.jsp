<%@page import="java.util.List"%>
<%@page import="com.pastexplorehub.dto.ProjectDTO"%>

<div class="table-responsive">
<table>
    <thead>
        <tr>
            <th>S.NO.</th>
            <th>Project Title</th>
            <th>Guide</th>
            <th>Submission Date</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
    </thead>       
    <tbody>
        <%
        List<ProjectDTO> ProjectList = (List<ProjectDTO>) session.getAttribute("projects");
        if(ProjectList!=null){ int size = 1;
        %>    
        <%
        for(ProjectDTO project:ProjectList){
        %>
            <tr>
                <td><%= size++ %></td>
                <td><%= project.getTitle()%></td>
                <td><%= project.getGuideName() %></td>
                <td><%= project.getCreatedAt().getDate() %> - <%=project.getCreatedAt().getMonth() %> - <%= project.getCreatedAt().getYear()+1900 %></td>
                <td><span class="status approved" title="Approved"></span></td>
                <td><button class="view" type="submit" name="id" onclick="openPopup(<%=project.getProjectId()%>)">
						<i class="fas fa-eye"></i></button></td>
            </tr>
        <%}}else{%>
        <tr>Error:your Session might be End</tr>
        <%response.sendRedirect("login.jsp");}%>
    </tbody>
</table>
</div>
    <div id="popupContainer" class="popup">
    <div class="popup-content">
        <span class="close-btn"  style="font-size:28px; padding-right:15px;color:blue; text-align:right"><span onclick="closePopup()" style="cursor:pointer;">&times;</span></span>
        <iframe src="about:blank" id="projectIframe" frameborder="0"></iframe>
    </div>
</div>
    <script>
      function openPopup(projectId) {
                 // Set iframe source with project ID
        const iframe = document.querySelector("#popupContainer iframe");
        iframe.src = "/pastexplorehub/project/project-view?id=" + projectId;
       
          document.getElementById("popupContainer").style.display = "flex";
       }

      function closePopup() {
          document.getElementById("popupContainer").style.display = "none";
      }
  </script>