package org.example.util;

import org.example.dto.UserRequestDto;
import org.example.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toEntity(UserRequestDto dto) {
        return modelMapper.map(dto, User.class);
    }

    public UserRequestDto toDTO(User user) {
        return modelMapper.map(user, UserRequestDto.class);
    }
}
