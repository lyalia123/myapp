package me.leila.myapp.controller;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import me.leila.myapp.controller.dto.UserRequestDto;
import me.leila.myapp.model.Usr;

import java.util.List;

import me.leila.myapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PermitAll
    @PostMapping("/create")
    public Usr create(@RequestBody UserRequestDto user) {
        return userService.create(user);
    }

    @GetMapping("/all")
    public List<Usr> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/by_id")
    public ResponseEntity<Usr> getById(@RequestParam(name = "id") Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam(name = "id") Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
