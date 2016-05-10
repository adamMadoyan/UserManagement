package com.energizeglobal.controller;

import com.energizeglobal.exception.DatabaseException;
import com.energizeglobal.model.User;
import com.energizeglobal.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute User user, Model model) {
        try {
            userService.add(user);
            model.addAttribute("success", "Your account is created successfully!");
        } catch (DatabaseException e) {
            model.addAttribute("error", "Unable to add User, Please try later!");
        }
        return "home";
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
