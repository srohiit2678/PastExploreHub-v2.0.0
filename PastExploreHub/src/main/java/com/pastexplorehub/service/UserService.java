package com.pastexplorehub.service;

import java.util.List;
import com.pastexplorehub.dto.TeacherDTO;
import com.pastexplorehub.dto.UserDTO;
import com.pastexplorehub.entity.User;

public interface UserService {
    User registerUser(UserDTO user) throws Exception;
    UserDTO login(String enrollId, String password) throws Exception;
    List<TeacherDTO> getAllTeachers();
	void hello();
}