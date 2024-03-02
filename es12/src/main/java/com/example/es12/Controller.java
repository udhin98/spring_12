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

    @GetMapping("/create")
    public User createUser() {
        return new User(1L,"Pritvi", "udhin1998@gmail.com");
    }

    @PutMapping("update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser) {
       User user = repository.findById(id).orElse(null);
       if (user == null) return null;
       user.setId(newUser.getId());
       user.setUsername(newUser.getUsername());
       user.setEmail(newUser.getEmail());
       return repository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
