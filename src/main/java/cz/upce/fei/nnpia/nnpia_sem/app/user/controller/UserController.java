package cz.upce.fei.nnpia.nnpia_sem.app.user.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.user.dto.UsernameDto;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
import cz.upce.fei.nnpia.nnpia_sem.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    private List<User> findAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    private User findUser(@PathVariable Long id) {
        return this.userService.getUser(id);
    }

    @PostMapping("/info")
    private User getUserInfo(@RequestBody UsernameDto dto) {
        return this.userService.getUserByUsername(dto.getUsername());
    }
}
