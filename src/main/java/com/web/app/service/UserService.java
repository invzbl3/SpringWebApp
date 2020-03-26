package com.web.app.service;

import com.web.app.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
}
