package com.web.app.rest;

import com.web.app.dto.UsersDTO;
import com.web.app.repository.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

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
        logger.info("Fetching all users");
        List<UsersDTO> users = userJpaRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDTO> createUser(@RequestBody final UsersDTO user) {
        logger.info("Creating User : {}", user);
        userJpaRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsersDTO>> getUserById(@PathVariable("id") final Long id) {
        logger.info("Fetching User with id {}", id);
        Optional<UsersDTO> user = userJpaRepository.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
