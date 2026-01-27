package com.pastexplorehub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pastexplorehub.entity.ProjectComment;

public interface ProjectCommentRepository extends JpaRepository<ProjectComment, Long> {
    // Navigates: Entity -> projectId (the Project object) -> projectId (the Long ID)
	@Query("SELECT c FROM ProjectComment c WHERE c.projectId.projectId = :pid ORDER BY c.date DESC")
    List<ProjectComment> findByProjectId(@Param("pid") Long projectId);
}