package br.com.framework.frameworkpost.controller;

import br.com.framework.frameworkpost.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listAll() {
        return "framework";
    }

}
