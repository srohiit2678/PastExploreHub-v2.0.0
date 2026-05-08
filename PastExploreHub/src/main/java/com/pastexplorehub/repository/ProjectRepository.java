package com.pastexplorehub.repository;

import com.pastexplorehub.dto.AdminActivityDTO;
import com.pastexplorehub.entity.Project;
import com.pastexplorehub.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    // Use UserId because User entity has 'userId'
    List<Project> findByStudent_UserId(Long studentId);

    // Use UserId because User entity has 'userId'
    List<Project> findByGuide_UserIdAndStatus(Long guideId, Status status);

    // If Department entity uses 'departmentId', use that here:
    List<Project> findByDepartment_Id(Long departmentId); 
    
    List<Project> findByStatus(Status status);
    
    Project findByProjectId(Long id);
    
     @Modifying
     @Query("UPDATE Project p SET p.status = :status, p.message=:message WHERE p.projectId = :id")
     void updateProjectStatus(@Param("id") Long id, @Param("status") Status status, @Param("message") String message);
   
	public List<Project> findByGuideUserIdAndStatus(Long guideUserId,Status staus);
	
	
	@Query("SELECT new com.pastexplorehub.dto.AdminActivityDTO(" +
		       "COALESCE(g.name, 'No Guide'), " +
		       "COALESCE(d.deptName, 'No Dept'), " +
		       "SUM(CASE WHEN p.status = com.pastexplorehub.model.Status.APPROVED THEN 1L ELSE 0L END), " +
		       "SUM(CASE WHEN p.status = com.pastexplorehub.model.Status.PENDING THEN 1L ELSE 0L END), " +
		       "SUM(CASE WHEN p.status = com.pastexplorehub.model.Status.REJECTED THEN 1L ELSE 0L END)) " +
		       "FROM Project p " +
		       "LEFT JOIN p.guide g " +
		       "LEFT JOIN p.department d " +
		       "GROUP BY g.name, d.deptName")
		List<AdminActivityDTO> getGuideActivityStats();
}