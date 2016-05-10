package com.energizeglobal.services.impl;

import com.energizeglobal.exception.DatabaseException;
import com.energizeglobal.model.User;
import com.energizeglobal.repositories.UserRepository;
import com.energizeglobal.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Company: WeDooApps
 * Date: 5/8/16
 * <p/>
 * Created by Adam Madoyan.
 */

@Service
public class UserService implements IUserService{

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;


    @Override
    public User get(Long id) throws DatabaseException {
        try {
            return userRepository.findOne(id);
        } catch (RuntimeException e) {
            logger.error("Unable to get user by id : " + id, e);
            throw new DatabaseException("Unable to get user by id : " + id);
        }
    }

    public void add(User user) throws DatabaseException {
        try {
            userRepository.save(user);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            throw new DatabaseException("Unable to add user");
        }
    }

    @Override
    public User findByEmail(String email) throws EntityNotFoundException {
        return null;
    }

}
