package com.pastexplorehub.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTeamMemberTest {

    private ProjectTeamMember projectTeamMember;


    @BeforeEach
    void setUp(){
        projectTeamMember = new ProjectTeamMember();
    }

    @Test
    void testSetAndGetId(){
        projectTeamMember.setId(1L);
        assertEquals(1L,projectTeamMember.getId());
    }

    @Test
    void testSetAndGetName(){
        projectTeamMember.setName("Rohan");
        assertEquals("Rohan",projectTeamMember.getName());
    }

    @Test
    void testSetAndGetenrollId(){
        projectTeamMember.setEnrollId("0832IT111011");
        assertEquals("0832IT111011",projectTeamMember.getEnrollId());
    }

    @Test
    void testTOString(){
        projectTeamMember.setId(10L);
        projectTeamMember.setName("Rohan");
        projectTeamMember.setEnrollId("0832IT111011");

        String result  =projectTeamMember.toString();

        assertTrue(result.contains("10"));
        assertTrue(result.contains("Rohan"));
        assertTrue(result.contains("0832IT111011"));
    }

    @Test
    void testProjectTeamMemberObjectCreation() {

        ProjectTeamMember projectTeamMemberObject = new ProjectTeamMember();

        assertNotNull(projectTeamMemberObject);
    }
}