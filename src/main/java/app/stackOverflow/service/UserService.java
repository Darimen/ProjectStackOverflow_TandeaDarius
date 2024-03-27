package app.stackOverflow.service;

import app.stackOverflow.model.User;
import app.stackOverflow.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(16384,
            8, 1, 64, 10);

    //create a new user
    public User signup(User user){
        if(user.getEmail() == null || user.getPassword() == null || user.getUsername() == null){
            throw new RuntimeException("Email, password and username are required");
        }

        if(userRepo.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("Email already exists");
        }

        if(userRepo.findByUsername(user.getUsername()) != null){
            throw new RuntimeException("Username already exists");
        }

        String regex = "[A-Za-z1-9_-]+@[A-Za-z1-9]+\\.*[A-Za-z1-9]*\\.[A-Za-z]+";

        Matcher matcher = Pattern.compile(regex).matcher(user.getEmail());

        if(!matcher.matches()){
            throw new RuntimeException("Invalid email");
        }

        if(user.getUsername().length() < 4){
            throw new RuntimeException("Username must be at least 4 characters long");
        }

        if(user.getPassword().length() < 8){
            throw new RuntimeException("Password must be at least 8 characters long");
        }

        if(userRepo.existsByEmailOrUsername(user.getEmail(), user.getUsername())){
            throw new RuntimeException("Email or username already exists");
        }
        user.setUId(BigInteger.valueOf(userRepo.count() + 1));
        //hash the password
        user.setCreatedAt(new Timestamp(new Date().getTime()));
        user.setLastUpdate(user.getCreatedAt());
        user.setPassword(encoder.encode(user.getPassword() + user.getCreatedAt().toString()));
        user.setBanned(false);
        user.setReason(null);
        return userRepo.save(user);
    }

    //get user info by email and password
    public User login(String email, String password, String username){
        User user = userRepo.findByEmail(email);

        if(user == null){
            user = userRepo.findByUsername(username);
        }

        if(user == null){
            throw new RuntimeException("User does not exist");
        }

        if(!encoder.matches(password + user.getCreatedAt().toString(), user.getPassword())){
            throw new RuntimeException("Invalid password");
        }

        if(user.isBanned()){
            throw new RuntimeException("User is banned");
        }

        return user;
    }

    @Transactional
    public User banUser(User user, String reason){

        if(user == null){
            throw new RuntimeException("User does not exist");
        }

        user.setBanned(true);
        user.setReason(reason);
        return userRepo.save(user);
    }

    @Transactional
    public User unbanUser(User user){
        if(user == null){
            throw new RuntimeException("User does not exist");
        }
        user.setBanned(false);
        user.setReason(null);
        return userRepo.save(user);
    }


    public User updateUser(User user) {

        User oldUser = userRepo.findByuId(user.getUId());

        if (oldUser == null) {
            throw new RuntimeException("User does not exist");
        }

        if (user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }

        if (user.getPhoneNr() != null) {
            oldUser.setPhoneNr(user.getPhoneNr());
        }

        if (user.getPhonePf() != null) {
            oldUser.setPhonePf(user.getPhonePf());
        }

        if (user.getFirstname() != null) {
            oldUser.setFirstname(user.getFirstname());
        }

        if (user.getLastname() != null) {
            oldUser.setLastname(user.getLastname());
        }

        if (user.isMod() != oldUser.isMod()) {
            oldUser.setMod(user.isMod());
        }

        if (user.getScore() != oldUser.getScore()) {
            oldUser.setScore(user.getScore());
        }

        if (user.getReason() != null) {
            oldUser.setReason(user.getReason());
        }

        oldUser.setLastUpdate(new Timestamp(new Date().getTime()));

        return userRepo.save(oldUser);
    }

    public User getUserById(BigInteger id){
        return userRepo.findByuId(id);
    }

    public User getUserByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public User getUserByUsername(String username){
        if (username == null) {
            throw new RuntimeException("Username is required");
        }
        return userRepo.findByUsername(username);
    }

    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Transactional
    public boolean deleteUser(BigInteger id){
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
            return true;
        }

        throw new RuntimeException("User does not exist");
    }
}
