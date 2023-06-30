package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.QR.QRCodeGenerator;
import org.example.dto.UserRequestDto;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.util.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final QRCodeGenerator qrCodeGenerator;

    @Override
    public void saveUser(UserRequestDto dto) {
        User user = mapper.toEntity(dto);
        user.setQr(qrCodeGenerator.generateQR(user.getInfo(), 200, 200));
        repository.save(user);
    }

    @Override
    public byte[] getUserById(Long id) {
        User user = null;
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }
        return qrCodeGenerator.getQRImageFromString(user.getQr());
    }
}
