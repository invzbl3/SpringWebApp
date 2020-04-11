package com.web.app.UserRegistrationSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static org.mockito.Mockito.when;
import com.web.app.UserRegistrationSystemApplication;
import com.web.app.dto.UsersDTO;
import com.web.app.repository.UserJpaRepository;
import com.web.app.rest.UserRegistrationRestController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserRegistrationSystemApplication.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserRegistrationRestControllerTest {
    @Spy
    private UserRegistrationRestController userRegistrationRestController;
    @Mock
    private UserJpaRepository userJpaRepository;
    @Before
    public void setup() {
        userRegistrationRestController = new UserRegistrationRestController();
        ReflectionTestUtils.setField(userRegistrationRestController,
                "userJpaRepository", userJpaRepository);
    }
    @Test
    public void testListAllUsers() {
        List<UsersDTO> userList = new ArrayList<>();
        userList.add(new UsersDTO());
        when(this.userJpaRepository.findAll()).thenReturn(userList);
        ResponseEntity<List<UsersDTO>> responseEntity =
                this.userRegistrationRestController.listAllUsers();
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).size());
    }
    @After
    public void teardown() {
        userRegistrationRestController = null;
    }
}
