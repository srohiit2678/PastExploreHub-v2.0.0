package com.pastexplorehub.repository;

import com.pastexplorehub.entity.ProjectFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectFileRepository extends JpaRepository<ProjectFile, Long> {
    
    // Get all files (images and PDF) linked to a specific project
    List<ProjectFile> findByProject_ProjectId(Long projectId);
}