package com.Spendify.Spendify.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user/")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
            this.userService = userService;
    }

    @GetMapping()
    public List<UserDTO> getUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("{userId}")
    public UserDTO getUser(@PathVariable("userId") Long userId){
        return userService.getUser(userId);
    }
    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }
    @PatchMapping("{userId}")
    public void updateUser(@PathVariable("userId") Long userId,
                           UserUpdateRequest userUpdateRequest){
        userService.updateUser(userId, userUpdateRequest);
    }
//    @GetMapping()
//    public List<UserDTO> getUsers(){
//        return userService.getAllUsers();
//    }
//    @GetMapping()
//    public List<UserDTO> getUsers(){
//        return userService.getAllUsers();
//    }
}
