package com.finneasy.part1.service;

import com.finneasy.part1.model.UserModel;
import com.finneasy.part1.entity.User;

public interface UserService {

    User registerUser(UserModel userModel);

    User getUserByPhoneNumber(String phoneNumber);

    User getUser(Long id);

}
