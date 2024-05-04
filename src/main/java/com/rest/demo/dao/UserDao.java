package com.rest.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rest.demo.entity.*;

@Repository
public interface UserDao extends CrudRepository<User, String>{

	Optional<User> findByusername(String username);

}
