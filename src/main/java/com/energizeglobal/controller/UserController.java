package com.energizeglobal.controller;

import com.energizeglobal.exception.DatabaseException;
import com.energizeglobal.model.ResponseDTO;
import com.energizeglobal.model.User;
import com.energizeglobal.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

/**
 * Company: WeDooApps
 * Date: 5/8/16
 * <p/>
 * Created by Adam Madoyan.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseDTO create(@RequestBody User user) {
        try {
            userService.add(user);
        } catch (DatabaseException e) {
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "error");
        }
        return new ResponseDTO(HttpStatus.CREATED, "success");
    }

    @RequestMapping(value = "/exist/{email}", method = RequestMethod.POST)
    public ResponseDTO isEmailExist(@PathVariable String email) {
        try {
            return userService.isEmailExist(email) ?
                    new ResponseDTO(OK) :
                    new ResponseDTO(HttpStatus.NOT_FOUND);
        } catch (DatabaseException e) {
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "error");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable(value = "id") Long id) {
        User user;
        try {
            user = userService.get(id);
        } catch (DatabaseException e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        return new ResponseEntity<>(user, OK);
    }

    @RequestMapping(value = "/me", method = RequestMethod.POST)
    public ResponseEntity<User> getUserByEmailPassword(@RequestParam(value = "email") String email,
                                                       @RequestParam(value = "password") String password) {
        User user;
        try {
            user = userService.findByEmailAndPassword(email, password);
        } catch (Exception e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
        return new ResponseEntity<>(user, OK);
    }


}
