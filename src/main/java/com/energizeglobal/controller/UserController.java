package com.energizeglobal.controller;

import com.energizeglobal.exception.DatabaseException;
import com.energizeglobal.model.ResponseDTO;
import com.energizeglobal.model.User;
import com.energizeglobal.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Company: WeDooApps
 * Date: 5/8/16
 * <p>
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
                    new ResponseDTO(HttpStatus.OK) :
                    new ResponseDTO(HttpStatus.NOT_FOUND);
        } catch (DatabaseException e) {
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "error");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable(value = "id") Long id) {
        try {
            User user = userService.get(id);
            System.out.println(user);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return "home";
    }


}
