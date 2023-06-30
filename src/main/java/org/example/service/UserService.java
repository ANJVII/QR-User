package org.example.service;

import org.example.dto.UserRequestDto;

public interface UserService {
    void saveUser(UserRequestDto dto);
    byte[] getUserById(Long id);
}
