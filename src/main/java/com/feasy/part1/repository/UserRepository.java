package com.feasy.part1.repository;

import com.feasy.part1.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByPhoneNumber(String phoneNumber);
}
