package com.csye5.CloudComputing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void test() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:"+port+"/healthz", String.class);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void creationTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        String request = "{\n" +
                "  \"first_name\": \"Jane\",\n" +
                "  \"last_name\": \"Doe\",\n" +
                "  \"password\": \"skdjfhskdfjhg\",\n" +
                "  \"username\": \"jane.doe@example.com\"\n" +
                "}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(request, headers);

        ResponseEntity<String> response = restTemplate
                .postForEntity("http://localhost:"+port+"/v2/user", requestEntity,String.class);
        Assertions.assertEquals(201, response.getStatusCode().value());

        HttpHeaders headers1 = new HttpHeaders();
        headers1.set("Authorization","Basic amFuZS5kb2VAZXhhbXBsZS5jb206c2tkamZoc2tkZmpoZw==");

        ResponseEntity<String> response2 = restTemplate
                .exchange("http://localhost:"+port+"/v2/user/self",
                        HttpMethod.GET,
                        new HttpEntity<Object>(headers1),
                        String.class);
        Assertions.assertEquals(403, response2.getStatusCode().value());
//        Assertions.assertEquals(response.getBody(), response2.getBody());
    }

    @Test
    public void updateTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        String request = "{\n" +
                "  \"first_name\": \"Jane\",\n" +
                "  \"last_name\": \"Doe\",\n" +
                "  \"password\": \"skdjfhskdfjhg\",\n" +
                "  \"username\": \"jane.dow@example.com\"\n" +
                "}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(request, headers);

        ResponseEntity<String> response = restTemplate
                .postForEntity("http://localhost:"+port+"/v2/user", requestEntity,String.class);
        Assertions.assertEquals(201, response.getStatusCode().value());

        HttpHeaders headers1 = new HttpHeaders();
        headers1.set("Content-Type", "application/json");
        headers1.set("Authorization","Basic amFuZS5kb3dAZXhhbXBsZS5jb206c2tkamZoc2tkZmpoZw==");
        String request1 = "{\n" +
                "  \"password\": \"password\"\n" +
                "}";
        HttpEntity<String> requestEntity1 = new HttpEntity<String>(request1, headers1);
        ResponseEntity<String> response2 = restTemplate
                .exchange("http://localhost:"+port+"/v2/user/self",
                        HttpMethod.PUT,
                        requestEntity1,
                        String.class);
        Assertions.assertEquals(403, response2.getStatusCode().value());

        HttpHeaders headers2 = new HttpHeaders();
        headers2.set("Authorization","Basic amFuZS5kb3dAZXhhbXBsZS5jb206cGFzc3dvcmQ=");

        ResponseEntity<String> response3 = restTemplate
                .exchange("http://localhost:"+port+"/v2/user/self",
                        HttpMethod.GET,
                        new HttpEntity<Object>(headers2),
                        String.class);
        Assertions.assertEquals(403, response3.getStatusCode().value());
//        Assertions.assertNotEquals(response.getBody(), response3.getBody());



    }

}
