package com.csye5.CloudComputing;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHealthCheck200() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/healthz")).andReturn();
        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testHealthCheck405() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/healthz")).andReturn();

        Assertions.assertEquals(405, result.getResponse().getStatus());
    }

    @Test
    public void testHealthCheck404() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/health").contentType("application/json").content("{\"name\":\"test\"}")).andReturn();
        Assertions.assertEquals(404, result.getResponse().getStatus());
    }
}
