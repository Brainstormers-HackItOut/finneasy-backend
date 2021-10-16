package com.feasy.part1.service;

import com.feasy.part1.dto.UserDto;
import com.feasy.part1.entity.User;

public interface UserService {

    User registerUser(UserDto userDto);

    User getUserByPhoneNumber(String phoneNumber);

}
