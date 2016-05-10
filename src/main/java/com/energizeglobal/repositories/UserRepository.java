package com.energizeglobal.repositories;

import com.energizeglobal.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Company: WeDooApps
 * Date: 5/8/16
 * <p/>
 * Created by Adam Madoyan.
 */

public interface UserRepository extends CrudRepository<User, Long> {}
