package me.leila.myapp.service;

import lombok.RequiredArgsConstructor;
import me.leila.myapp.controller.dto.UserRequestDto;
import me.leila.myapp.model.Usr;
import me.leila.myapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


import java.util.List;

@Service

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public List<Usr> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Usr create(UserRequestDto userRequestDto) {
        Usr usr = new Usr();
        usr.setEmail(userRequestDto.getEmail());
        usr.setKeycloakId(userRequestDto.getKeycloakId());
        usr.setFullName(userRequestDto.getFullName());
        usr.setCreatedAt(LocalDateTime.now());
        return userRepository.save(usr);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<Usr> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
