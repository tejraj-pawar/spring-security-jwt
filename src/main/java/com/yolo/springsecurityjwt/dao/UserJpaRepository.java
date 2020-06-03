/*
 * This interface extends JpaRepository interface and provide methods to perform operations on user table in MySQL DB.
 */

package com.yolo.springsecurityjwt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yolo.springsecurityjwt.model.User;

public interface UserJpaRepository extends JpaRepository<User, String>{
}
