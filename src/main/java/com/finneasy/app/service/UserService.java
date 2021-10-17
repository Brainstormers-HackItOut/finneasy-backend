package com.finneasy.app.service;

import com.finneasy.app.model.UserModel;
import com.finneasy.app.entity.User;

public interface UserService {

    User registerUser(UserModel userModel);

    User getUserByPhoneNumber(String phoneNumber);

    User getUser(Long id);

    User findByReferral(String referralCode);

    User saveUser(User user);
}
