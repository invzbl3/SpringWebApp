package com.web.app.rest;

import com.web.app.dto.UsersDTO;
import com.web.app.exception.CustomErrorType;
import com.web.app.repository.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    // method to get list of users

    @GetMapping("/")
    public ResponseEntity<List<UsersDTO>> listAllUsers() {
        logger.info("Fetching all users");
        List<UsersDTO> users = userJpaRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // method to create an user

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersDTO> createUser(@Valid @RequestBody final UsersDTO user) {
        logger.info("Creating User : {}", user);
        if (userJpaRepository.findByName(user.getName()) != null) {
            logger.error("Unable to create. A User with name {} already exist", user.getName());
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to create new user. A User with name "
                            + user.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        userJpaRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // method to get user by id

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") final Long id) {
        logger.info("Fetching User with id {}", id);
        Optional<UsersDTO> user = userJpaRepository.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(
                    new CustomErrorType("User with id "
                            + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // method to update an existing user

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(
            @PathVariable("id") final Long id, @RequestBody UsersDTO user) {
        logger.info("Updating User with id {}", id);
        // fetch user based on id and set it to currentUser object of type UserDTO
        Optional<UsersDTO> currentUser = userJpaRepository.findById(id);
        if (!currentUser.isPresent()) {
            return new ResponseEntity<>(
                    new CustomErrorType("Unable to update. User with id "
                            + id + " not found."), HttpStatus.NOT_FOUND);
        }
        // update currentUser object data with user object data
        currentUser.get().setName(user.getName());
        currentUser.get().setAddress(user.getAddress());
        currentUser.get().setEmail(user.getEmail());
        // save currentUser object
        userJpaRepository.saveAndFlush(currentUser.get());
        //return ResponseEntity object
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    // delete an existing user

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UsersDTO> deleteUser(@PathVariable("id") final Long id) {
        Optional<UsersDTO> user = userJpaRepository.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(
                    new CustomErrorType("Unable to delete. User with id "
                            + id + " not found."), HttpStatus.NOT_FOUND);
        }
        userJpaRepository.deleteById(id);
        return new ResponseEntity<>(
                new CustomErrorType("Deleted User with id "
                        + id + "."), HttpStatus.NO_CONTENT);
    }
}
