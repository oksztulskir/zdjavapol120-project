package com.sdacademy.springdatajpaexample;

import com.sdacademy.springdatajpaexample.model.User;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class UserRestControllerTest extends BaseTest {
    @Test
    void shouldSendPostForUserCreation() throws Exception {
        // given
        // when
        // then
        mvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic dXNlcjJAZ21haWwuY29tOnVzZXIx")
                .content(mapper.writeValueAsString(new User("Anna", "Nowak", "nowaka@gmail.com", "password123456", User.Roles.ROLE_ADMIN)))
        ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.equalTo("Anna")));

    }
}
