package com.web.app.mapper;

import com.web.app.dto.UserDTO;
import com.web.app.model.User;

public interface UserMapper {
    UserDTO toDto(User user);
}
