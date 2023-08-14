package com.richards.promeescuous.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.richards.promeescuous.dtos.requests.RegisterUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testRegister(){
        try {
            RegisterUserRequest request = new RegisterUserRequest();
            request.setEmail("bibeni9669@touchend.com");
            request.setPassword("password");
            String json=mapper.writeValueAsString(request);
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/api/v1/user")
                                    .content(json)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    ).andExpect(MockMvcResultMatchers.status()
                            .is(HttpStatus.CREATED.value()))
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}