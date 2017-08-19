package com.app.dao;

import com.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data access object for {@link com.app.entities.User}
 */
public interface UserDao extends JpaRepository<User, String> {
}
