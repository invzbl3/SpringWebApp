package com.web.app.rest;

import com.web.app.dto.UsersDTO;
import com.web.app.repository.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {
    public static final Logger logger =
            LoggerFactory.getLogger(UserRegistrationRestController.class);
    private UserJpaRepository userJpaRepository;
    @Autowired
    public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<UsersDTO>> listAllUsers() {
        List<UsersDTO> users = userJpaRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
