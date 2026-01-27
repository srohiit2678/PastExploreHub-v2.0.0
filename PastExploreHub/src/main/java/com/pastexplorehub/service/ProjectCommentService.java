package com.pastexplorehub.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.pastexplorehub.entity.ProjectComment;

@Service
public interface ProjectCommentService {
	public void addComment(Long projectId, Long userId, String message);
	public List<ProjectComment> getCommentsByProject(Long projectId);
}
