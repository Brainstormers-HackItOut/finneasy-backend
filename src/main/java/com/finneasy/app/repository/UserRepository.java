package com.finneasy.app.repository;

import com.finneasy.app.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByPhoneNumber(String phoneNumber);
    User findByReferralCode(String referralCode);
}
