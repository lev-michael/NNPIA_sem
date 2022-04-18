package cz.upce.fei.nnpia.nnpia_sem.app.user.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.config.ApiResponse;
import cz.upce.fei.nnpia.nnpia_sem.app.config.StausEnum;
import cz.upce.fei.nnpia.nnpia_sem.app.user.dto.UsernameDto;
import cz.upce.fei.nnpia.nnpia_sem.app.user.entity.User;
import cz.upce.fei.nnpia.nnpia_sem.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/{id}")
    private ApiResponse<User> findUser(@PathVariable Long id) {
        User user = this.userService.getUser(id);
        return new ApiResponse<>(HttpStatus.OK.value(), user != null ? StausEnum.SUCCESS : StausEnum.NOT_FOUND, user);
    }

    @PostMapping("/info")
    private ApiResponse<User> getUserInfo(@RequestBody UsernameDto dto) {
        User user = this.userService.getUserByUsername(dto.getUsername());
        return new ApiResponse<>(HttpStatus.OK.value(), user != null ? StausEnum.SUCCESS : StausEnum.NOT_FOUND, user);
    }
}
