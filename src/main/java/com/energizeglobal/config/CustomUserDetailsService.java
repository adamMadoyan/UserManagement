package com.energizeglobal.config;

import com.energizeglobal.exception.DatabaseException;
import com.energizeglobal.model.User;
import com.energizeglobal.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;

        try {
            user = userService.findByEmail(username);
        } catch (EntityNotFoundException | DatabaseException e) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }
        return new HelperSecurityDetails(user.getEmail(), user.getPassword(), true, user.getId(), user.isAdmin());
    }
}
