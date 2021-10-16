package com.finneasy.part1.service;

import com.finneasy.part1.entity.Reward;

import java.util.List;

public interface RewardService {

    Boolean incrementMilestone(Long userId);

    List<Reward> getAllRewards();

    Reward findRewardByMilestone(Integer milestoneNumber);

    void userReferral(String referralCode);
}
