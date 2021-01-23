package com.galvanize.guestbook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.guestbook.model.GuestEntry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GuestControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Autowired
    ObjectMapper objectMapper;


    @Test
    @DisplayName("Add comment by user test")
    void addCommentByUserTest() throws Exception {
        GuestEntry guestEntry = new GuestEntry("Rick Sanchez", "Rick was here.");

        mockMvc.perform(
                post("/guestbook")
                .content(objectMapper.writeValueAsString(guestEntry))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Adding a guest entry will be returned in get all entries")
    void addEntryShouldBeReturnedInGetAllEntries() throws Exception {
        GuestEntry guestEntry = new GuestEntry("Rick Sanchez", "Rick was here.");

        mockMvc.perform(
                post("/guestbook")
                        .content(objectMapper.writeValueAsString(guestEntry))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


        mockMvc.perform(get("/guestbook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Rick Sanchez"))
                .andExpect(jsonPath("[0].comment").value("Rick was here."));
    }

    @Test
    @DisplayName("Adding two guests entries will be returned in get all entries")
    void addTwoEntryShouldBeReturnedInGetAllEntries() throws Exception {
        GuestEntry guestEntry = new GuestEntry("Rick Sanchez", "Rick was here.");

        mockMvc.perform(
                post("/guØestbook")
                        .content(objectMapper.writeValueAsString(guestEntry))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        guestEntry = new GuestEntry("Morty", "Morty was here.");

        mockMvc.perform(
                post("/guestbook")
                        .content(objectMapper.writeValueAsString(guestEntry))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


        mockMvc.perform(get("/guestbook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("[0].name").value("Rick Sanchez"))
                .andExpect(jsonPath("[0].comment").value("Rick was here."))
                .andExpect(jsonPath("[1].name").value("Morty"))
                .andExpect(jsonPath("[1].comment").value("Morty was here."));
    }
}