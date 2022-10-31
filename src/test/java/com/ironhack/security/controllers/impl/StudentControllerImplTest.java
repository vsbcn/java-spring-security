package com.ironhack.security.controllers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.security.controllers.dtos.StudentNameDTO;
import com.ironhack.security.enums.House;
import com.ironhack.security.enums.Wing;
import com.ironhack.security.models.HouseAssignment;
import com.ironhack.security.models.Student;
import com.ironhack.security.repositories.HouseAssignmentRepository;
import com.ironhack.security.repositories.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

@SpringBootTest
class StudentControllerImplTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private HouseAssignmentRepository houseAssignmentRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private HouseAssignment houseAssignment1;
    private HouseAssignment houseAssignment2;
    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        houseAssignment1 = houseAssignmentRepository.save(new HouseAssignment(House.GRYFFINDOR, Wing.NORTH, 52));
        houseAssignment2 = houseAssignmentRepository.save(new HouseAssignment(House.GRYFFINDOR, Wing.SOUTH, 24));

        student1 = studentRepository.save(new Student("Seamus", "Finnigan", houseAssignment1));
        student2 = studentRepository.save(new Student("Lavender", "Brown", houseAssignment2));
    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteById(student1.getId());
        studentRepository.deleteById(student2.getId());
        houseAssignmentRepository.deleteById(houseAssignment1.getId());
        houseAssignmentRepository.deleteById(houseAssignment2.getId());
    }

    @Test
    void updateStudent_ExistingStudentIdAndValidName_NameUpdated() throws Exception {
        StudentNameDTO studentNameDTO = new StudentNameDTO("Seamus", "F");
        String body = objectMapper.writeValueAsString(studentNameDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/student/name/" + student1.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(204, mvcResult.getResponse().getStatus());

        Optional<Student> optionalStudent = studentRepository.findById(student1.getId());
        assertEquals("Seamus", optionalStudent.get().getFirstName());
    }


    @Test
    void updateStudent_NotExistingStudentIdAndValidName_NameUpdated() throws Exception {
        StudentNameDTO studentNameDTO = new StudentNameDTO("Seamus", "F");
        String body = objectMapper.writeValueAsString(studentNameDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/student/name/999999")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    void updateStudent_ExistingStudentIdAndInValidName_406() throws Exception {
        StudentNameDTO studentNameDTO = new StudentNameDTO("Seamus", "");
        String body = objectMapper.writeValueAsString(studentNameDTO);

        MvcResult mvcResult = mockMvc.perform(patch("/student/name/" + student1.getId())
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(400, mvcResult.getResponse().getStatus());
    }
}