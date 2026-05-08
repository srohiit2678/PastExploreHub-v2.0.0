package com.pastexplorehub.serviceimpl;

import com.pastexplorehub.dto.TeacherDTO;
import com.pastexplorehub.dto.UserDTO;
import com.pastexplorehub.entity.Department;
import com.pastexplorehub.entity.User;
import com.pastexplorehub.exception.InvalidCredentialsException;
import com.pastexplorehub.model.UserRole;
import com.pastexplorehub.repository.DepartmentRepository;
import com.pastexplorehub.repository.UserRepository;
import com.pastexplorehub.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public User registerUser(UserDTO dto) throws Exception {
        // 1. Validation
        if (!dto.getEnrollId().contains("0832")) {
            throw new InvalidCredentialsException("Enrollment ID must contain 0832");
        }

        // 2. Map DTO to Entity
        User user = new User();
        user.setName(dto.getName().length()<=5?dto.getName()+" _":dto.getName());
        user.setEnrollId(dto.getEnrollId());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        // 3. Handle Department ID -> Department Object
        Department dept = departmentRepository.findById(dto.getDepartment())
            .orElseThrow(() -> new Exception("Department not found"));
        user.setDepartment(dept);

        return userRepository.save(user);
    }

    @Override
    public UserDTO login(String enrollId, String password) throws Exception {
       
    	User user = userRepository.findByEnrollId(enrollId)
                .orElseThrow(() -> new Exception("User not found"));
        
        if(!user.getPassword().equals(password)) {
            throw new Exception("Invalid password");
        }
        UserDTO userDto = new UserDTO(user);
        return userDto;
    }
    
    public void hello() {
    	System.out.println("hello from services");
    }
    
    @Override
    public List<TeacherDTO> getAllTeachers() {
    	
    	List<User> userDto = userRepository.findByRole(UserRole.TEACHER);
        
    	return userDto.stream().map(teacher->new TeacherDTO(teacher.getUserId(), teacher.getName())).collect(Collectors.toList());
    }
}