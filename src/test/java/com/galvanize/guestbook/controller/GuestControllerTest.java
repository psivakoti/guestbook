package com.galvanize.guestbook.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureWebMvc
class GuestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void addCommentByUserTest() throws Exception {
        mockMvc.perform(post("/guestbook"))
                .andExpect(status().isCreated());
    }

}