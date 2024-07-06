package app.stackOverflow.controller;

import app.stackOverflow.model.User;
import app.stackOverflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // signup
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        for(Field field : user.getClass().getDeclaredFields()){
            field.setAccessible(true);

            try {
                System.out.println(field.getName() + " - " + field.get(user));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        try {
            User createdUser = userService.signup(user);
            createdUser.setPassword(null);
            return ResponseEntity.ok(createdUser);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    // login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User loggedInUser = userService.login(user.getEmail(), user.getPassword(), user.getUsername());
            if (loggedInUser != null) {
                loggedInUser.setPassword(null);
                return ResponseEntity.ok(loggedInUser);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    // get all users
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        Iterable<User> users = userService.getAllUsers();

        for(User user : users){
            user.setPassword(null);
        }

        return ResponseEntity.ok(users);
    }

    //delete user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
         if(userService.deleteUser(BigInteger.valueOf(Long.parseLong(id))))
             return ResponseEntity.ok("User deleted successfully");
         else
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String id){
        user.setUId(BigInteger.valueOf(Long.parseLong(id)));
        User updatedUser = userService.updateUser(user);
        if(updatedUser == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<?> getUserById(@PathVariable String username){
        User user = userService.getUserByUsername(username);

        if(user == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");

        user.setPassword(null);
        return ResponseEntity.ok(user);
    }

}
