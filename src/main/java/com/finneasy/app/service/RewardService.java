package com.finneasy.app.service;

import com.finneasy.app.entity.Reward;

import java.util.List;

public interface RewardService {

    Boolean incrementMilestone(Long userId);

    List<Reward> getAllRewards();

    Reward findRewardByMilestone(Integer milestoneNumber);

    void userReferral(String referralCode);
}
