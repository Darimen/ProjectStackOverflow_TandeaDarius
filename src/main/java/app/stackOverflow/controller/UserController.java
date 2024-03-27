package app.stackOverflow.controller;

import app.stackOverflow.model.User;
import app.stackOverflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigInteger;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // signup
    @PostMapping("/signup/")
    public User signup(@RequestBody User user) {
        for(Field field : user.getClass().getDeclaredFields()){
            field.setAccessible(true);

            try {
                System.out.println(field.getName() + " - " + field.get(user));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return userService.signup(user);
    }

    // login
    @PostMapping("/login/")
    public User login(@RequestBody String email, String password, String username) {
        return userService.login(email, password, username);
    }

    // get all users
    @GetMapping("/all/")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //delete user
    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable String id){
        return userService.deleteUser(BigInteger.valueOf(Long.parseLong(id)));
    }

    @PostMapping("/update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable String id){
        user.setUId(BigInteger.valueOf(Long.parseLong(id)));
        return userService.updateUser(user);
    }

}
