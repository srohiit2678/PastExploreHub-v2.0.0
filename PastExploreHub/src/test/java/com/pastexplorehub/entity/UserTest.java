package com.pastexplorehub.entity;

import static org.junit.jupiter.api.Assertions.*;
import com.pastexplorehub.dto.UserDTO;
import com.pastexplorehub.model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testSetAndGetUserId() {
        user.setUserId(1L);

        assertEquals(1L, user.getUserId());
    }

    @Test
    void testSetAndGetEnrollId() {
        user.setEnrollId("0832CS221001");

        assertEquals("0832CS221001", user.getEnrollId());
    }

    @Test
    void testSetAndGetName() {
        user.setName("Rohit");

        assertEquals("Rohit", user.getName());
    }

    @Test
    void testSetAndGetEmail() {
        user.setEmail("rohit@gmail.com");

        assertEquals("rohit@gmail.com", user.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        user.setPassword("12345");

        assertEquals("12345", user.getPassword());
    }

    @Test
    void testSetRoleAdmin() {
        user.setRole("admin");

        assertEquals("ADMIN", user.getRole());
    }

    @Test
    void testSetRoleStudent() {
        user.setRole("student");

        assertEquals("STUDENT", user.getRole());
    }

    @Test
    void testSetRoleTeacher() {
        user.setRole("teacher");

        assertEquals("TEACHER", user.getRole());
    }

    @Test
    void testSetAndGetDepartment() {
        Department department = new Department();
        department.setId(101L);

        user.setDepartment(department);

        assertEquals(101L, user.getDepartment().getId());
    }

    @Test
    void testToString() {
        user.setUserId(1L);
        user.setEnrollId("0832CS221001");
        user.setName("Rohit");
        user.setEmail("rohit@gmail.com");
        user.setPassword("12345");
        user.setRole("student");

        String result = user.toString();

        assertTrue(result.contains("Rohit"));
        assertTrue(result.contains("0832CS221001"));
        assertTrue(result.contains("STUDENT"));
    }

    @Test
    void testToUserMethod() {

        UserDTO dto = new UserDTO();

        dto.setUserId(10L);
        dto.setName("Aman");
        dto.setEnrollId("0832CS221002");
        dto.setEmail("aman@gmail.com");
        dto.setRole("teacher");
        dto.setDepartment(5L);

        User convertedUser = user.toUser(dto);

        assertEquals(10L, convertedUser.getUserId());
        assertEquals("Aman", convertedUser.getName());
        assertEquals("0832CS221002", convertedUser.getEnrollId());
        assertEquals("aman@gmail.com", convertedUser.getEmail());
        assertEquals("TEACHER", convertedUser.getRole());

        assertNotNull(convertedUser.getDepartment());
        assertEquals(5L, convertedUser.getDepartment().getId());
    }

    @Test
    void testToUserWithoutDepartment() {

        UserDTO dto = new UserDTO();

        dto.setUserId(11L);
        dto.setName("Karan");
        dto.setEnrollId("0832CS221003");
        dto.setEmail("karan@gmail.com");
        dto.setRole("student");

        User convertedUser = user.toUser(dto);

        assertEquals(11L, convertedUser.getUserId());
        assertEquals("Karan", convertedUser.getName());
        assertEquals("0832CS221003", convertedUser.getEnrollId());
        assertEquals("STUDENT", convertedUser.getRole());

        assertNull(convertedUser.getDepartment());
    }
}