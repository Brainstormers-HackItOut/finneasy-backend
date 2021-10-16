package com.finneasy.part1.service;

import com.finneasy.part1.model.UserModel;
import com.finneasy.part1.entity.User;
import com.finneasy.part1.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RewardService rewardService;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,RewardService rewardService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.rewardService  = rewardService;
    }

    @Override
    public User registerUser(UserModel userModel) {
            User user = new User(userModel.getFirstName(),
                    userModel.getLastName(),
                    userModel.getPhoneNumber(),
                    userModel.getEmailId(),
                    getReferralCode(userModel.getFirstName(),userModel.getPhoneNumber()),
                    bCryptPasswordEncoder.encode(userModel.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Void userReferral(String referralCode) {
        User user = userRepository.findByReferralCode(referralCode);
        rewardService.incrementMilestone(user.getId());
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = getUserByPhoneNumber(phoneNumber);

        if(user == null){
            throw new UsernameNotFoundException("User with phone number: " + phoneNumber +" not found");
        } else {
            org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(
                    user.getPhoneNumber(),
                    user.getPassword(),
                    Collections.emptyList()
            );
            return springUser;
        }
    }

    private String getReferralCode(String firstName , String phoneNumber){
        return firstName.toLowerCase().substring(0,3).concat(phoneNumber.substring(phoneNumber.length()-4,phoneNumber.length()-1));
    }
}
