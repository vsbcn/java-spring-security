package com.ironhack.security.controllers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.security.enums.House;
import com.ironhack.security.enums.Wing;
import com.ironhack.security.models.HouseAssignment;
import com.ironhack.security.repositories.HouseAssignmentRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class HouseAssignmentControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private HouseAssignmentRepository houseAssignmentRepository;

    private HouseAssignment houseAssignment1;
    private HouseAssignment houseAssignment2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        houseAssignment1 = houseAssignmentRepository.save(new HouseAssignment(House.GRYFFINDOR, Wing.NORTH, 52));
        houseAssignment2 = houseAssignmentRepository.save(new HouseAssignment(House.GRYFFINDOR, Wing.SOUTH, 24));
    }

    @AfterEach
    void tearDown() {
        houseAssignmentRepository.deleteAll();
    }

    @Test
    void getAllHouseAssignmentByHouse_ExistingHouse() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/house-assignment/house/GRYFFINDOR"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("NORTH"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("SOUTH"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("52"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("24"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"assignedBed\":52"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"assignedBed\":24"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains(",\"house\":\"GRYFFINDOR\",\"wing\":\"NORTH\",\"assignedBed\":52}"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains(",\"house\":\"GRYFFINDOR\",\"wing\":\"SOUTH\",\"assignedBed\":24}"));
    }

    @Test
    void getAllHouseAssignmentByHouse_NotExistingHouse_400() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/house-assignment/house/XXX"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
    @Test
    void getAllHouseAssignmentByWing_ExistingWing() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/house-assignment/wing?wing=NORTH"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("NORTH"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("52"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"assignedBed\":52"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains(",\"house\":\"GRYFFINDOR\",\"wing\":\"NORTH\",\"assignedBed\":52}"));
    }

    @Test
    void getAllHouseAssignmentByWing_NotExistingWing_400() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/house-assignment/wing?wing=XXX"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void createHouseAssignment_ValidHouseAssignment() throws Exception {
        HouseAssignment newHouseAssignment = new HouseAssignment(House.SLYTHERIN, Wing.EAST, 17);

        String body = objectMapper.writeValueAsString(newHouseAssignment);

        System.out.println(body);

        MvcResult mvcResult = mockMvc.perform(post("/house-assignment")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains(",\"house\":\"SLYTHERIN\",\"wing\":\"EAST\",\"assignedBed\":17}"));
    }
}