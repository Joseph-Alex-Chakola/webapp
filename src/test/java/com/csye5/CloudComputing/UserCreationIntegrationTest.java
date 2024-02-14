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
public class UserCreationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void userCreationTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/user")
                                .accept("application/json")
                                .contentType("application/json")
                                .content("{\n" +
                                        "  \"first_name\": \"Jane\",\n" +
                                        "  \"last_name\": \"Doe\",\n" +
                                        "  \"password\": \"skdjfhskdfjhg\",\n" +
                                        "  \"username\": \"jane.doe@example.com\"\n" +
                                        "}")
                        )
                .andReturn();
        Assertions.assertEquals(201, result.getResponse().getStatus());

        MvcResult responseResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/user/self")
                .header("Authorization", "Basic amFuZS5kb2VAZXhhbXBsZS5jb206c2tkamZoc2tkZmpoZw==")
        ).andReturn();
        Assertions.assertEquals(200, responseResult.getResponse().getStatus());
        Assertions.assertEquals(result.getResponse().getContentLength(), responseResult.getResponse().getContentLength());
        Assertions.assertEquals(result.getResponse().getContentAsString(), responseResult.getResponse().getContentAsString());
    }
}
