package com.pastexplorehub.serviceimpl;

import com.pastexplorehub.entity.Project;
import com.pastexplorehub.entity.ProjectComment;
import com.pastexplorehub.entity.User;
import com.pastexplorehub.repository.ProjectCommentRepository;
import com.pastexplorehub.repository.ProjectRepository;
import com.pastexplorehub.repository.UserRepository;
import com.pastexplorehub.service.ProjectCommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProjectCommentServiceImpl implements ProjectCommentService{

    @Autowired
    private ProjectCommentRepository commentRepo;

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private UserRepository userRepo;

    @Transactional
    public void addComment(Long projectId, Long userId, String message) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProjectComment comment = new ProjectComment();
        comment.setProjectId(project); // Matches your Entity field name
        comment.setUser(user);        // Matches your Entity setter
        comment.setMessage(message);
        comment.setDate(new Date());

        commentRepo.save(comment);
    }

    public List<ProjectComment> getCommentsByProject(Long projectId) {
        return commentRepo.findByProjectId(projectId);
    }
}