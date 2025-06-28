package me.leila.myapp.service;
import me.leila.myapp.controller.dto.UserRequestDto;
import me.leila.myapp.model.Usr;
import java.util.List;
import java.util.Optional;
public interface UserService {
    List<Usr> getAllUsers();
    Optional<Usr> getUserById(Long id);

    Usr create(UserRequestDto user);
    void delete(Long id);
}
