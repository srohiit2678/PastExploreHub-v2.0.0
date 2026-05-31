package com.pastexplorehub.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    private Department department;

    @BeforeEach
    void setUp() {
        department = new Department();
    }

    @Test
    void testSetAndGetId() {
        department.setId(1L);

        assertEquals(1L, department.getId());
    }

    @Test
    void testSetAndGetDeptName() {
        department.setDeptName("Computer Science");

        assertEquals("Computer Science", department.getDeptName());
    }

    @Test
    void testSetAndGetDeptCode() {
        department.setDeptCode("CSE");

        assertEquals("CSE", department.getDeptCode());
    }

    @Test
    void testToString() {

        department.setId(10L);
        department.setDeptName("Information Technology");
        department.setDeptCode("IT");

        String result = department.toString();

        assertTrue(result.contains("10"));
        assertTrue(result.contains("Information Technology"));
        assertTrue(result.contains("IT"));
    }

    @Test
    void testDepartmentObjectCreation() {

        Department dept = new Department();

        assertNotNull(dept);
    }
}