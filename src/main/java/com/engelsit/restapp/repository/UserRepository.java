package com.engelsit.restapp.repository;

import com.engelsit.restapp.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
