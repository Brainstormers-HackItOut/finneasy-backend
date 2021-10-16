package com.finneasy.part1.service;

import com.finneasy.part1.dto.UserDto;
import com.finneasy.part1.entity.User;

public interface UserService {

    User registerUser(UserDto userDto);

    User getUserByPhoneNumber(String phoneNumber);

}
