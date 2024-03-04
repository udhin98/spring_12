package com.example.es12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Controller {
    @Autowired
    private Repository repository;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping("update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser) {
       User user = repository.findById(id).orElse(null);
       if (user == null) return null;
       user.setUsername(newUser.getUsername());
       user.setEmail(newUser.getEmail());
       return repository.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
