package com.energizeglobal.services;

import com.energizeglobal.exception.DatabaseException;
import com.energizeglobal.model.User;

import javax.persistence.EntityNotFoundException;

/**
 * Company: WeDooApps
 * Date: 5/8/16
 * <p>
 * Created by Adam Madoyan.
 */
public interface IUserService {

    User get(Long id) throws DatabaseException;

    void add(User user) throws DatabaseException;

    User findByEmail(String email) throws EntityNotFoundException, DatabaseException;

    boolean isEmailExist(String email) throws DatabaseException;

}
