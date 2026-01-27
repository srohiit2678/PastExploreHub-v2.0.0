package com.pastexplorehub.repository;

import com.pastexplorehub.entity.ProjectTeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectTeamMemberRepository extends JpaRepository<ProjectTeamMember, Long> {
    
    // Get all team members for a specific project
    List<ProjectTeamMember> findByProject_ProjectId(Long projectId);
}