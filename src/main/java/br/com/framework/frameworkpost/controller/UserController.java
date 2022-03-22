package br.com.framework.frameworkpost.controller;

import br.com.framework.frameworkpost.domain.User;
import br.com.framework.frameworkpost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> listAll() {
        return userService.listAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody  User user) {
        return userService.update(id, user);
    }

}
