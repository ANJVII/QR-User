package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserRequestDto;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/QRUserApp")
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserRequestDto> save(@RequestBody UserRequestDto dto) {
        userService.saveUser(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("user/{id}")
    public @ResponseBody byte[] get(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
