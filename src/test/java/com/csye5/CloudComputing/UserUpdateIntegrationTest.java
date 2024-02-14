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
public class UserUpdateIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void userUpdateTest() throws Exception {
        MvcResult resultBefore = mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/user/self")
                .header("Authorization","Basic amFuZS5kb2VAZXhhbXBsZS5jb206c2tkamZoc2tkZmpoZw==")
        ).andReturn();

        Assertions.assertEquals(200, resultBefore.getResponse().getStatus());

        MvcResult responseResult = mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/user/self")
                .header("Authorization","Basic amFuZS5kb2VAZXhhbXBsZS5jb206c2tkamZoc2tkZmpoZw==")
                .accept("application/json")
                .contentType("application/json")
                .content("{\n" +
                        "    \"password\":\"password\"\n" +
                        "}")
        ).andReturn();
        Assertions.assertEquals(204, responseResult.getResponse().getStatus());

        MvcResult resultAfter = mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/user/self")
                .header("Authorization","Basic amFuZS5kb2VAZXhhbXBsZS5jb206cGFzc3dvcmQ=")
        ).andReturn();

        Assertions.assertEquals(200, resultAfter.getResponse().getStatus());
        Assertions.assertNotEquals(resultBefore.getResponse().getContentAsString(), resultAfter.getResponse().getContentAsString());

    }
}
