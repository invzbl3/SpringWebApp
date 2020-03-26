package com.web.app.mapper.impl;

import com.web.app.dto.UserDTO;
import com.web.app.mapper.UserMapper;
import com.web.app.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDTO toDto(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getRole());
    }
}
