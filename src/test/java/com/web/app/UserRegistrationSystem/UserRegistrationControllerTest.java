package com.web.app.UserRegistrationSystem;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.web.app.UserRegistrationSystemApplication;
import com.web.app.dto.UsersDTO;
import com.web.app.repository.UserJpaRepository;
import com.web.app.rest.UserRegistrationRestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

// Testing can be realized only after disconnection Spring Security
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserRegistrationRestController.class)
@ContextConfiguration(classes = UserRegistrationSystemApplication.class)
public class UserRegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserJpaRepository userJpaRepositoryMock;
    private MediaType contentType;
    private UsersDTO user;
    @Before
    public void setup() {
        contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                StandardCharsets.UTF_8);
        user = new UsersDTO();
        user.setName("Steve");
        user.setAddress("42nd Street");
        user.setEmail("steve.author@gmail.com");
    }
    @Test
    public void shouldReturnSuccessMessage() throws Exception {
        when(this.userJpaRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(user));
        this.mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.name", is("Steve")))
                .andExpect(jsonPath("$.address",
                        is("42nd Street")))
                .andExpect(jsonPath("$.email",
                        is("steve.author@gmail.com")))
                .andDo(print());
    }
}