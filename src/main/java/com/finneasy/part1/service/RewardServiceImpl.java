package com.finneasy.part1.service;

import com.finneasy.part1.entity.Reward;
import com.finneasy.part1.entity.User;
import com.finneasy.part1.repository.RewardRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService{

    private UserService userService;
    private RewardRepository rewardRepository;

    public RewardServiceImpl(UserService userService, RewardRepository rewardRepository) {
        this.userService = userService;
        this.rewardRepository = rewardRepository;
    }

    @Override
    public Boolean incrementMilestone(Long userId) {
        List<Reward> rewardList = getAllRewards();

        List<Integer> milestones = rewardList.stream().map(reward -> reward.getMilestoneNumber()).collect(Collectors.toList());
        Collections.sort(milestones);

        User user = userService.getUser(userId);
        user.incrementCounter();

        Integer index = Collections.binarySearch(milestones, user.getMilestoneCounter());

        if(index>=0){
            user.setMilestoneNumber(user.getMilestoneCounter());
            user.addCoins(findRewardByMilestone(milestones.get(index)).getCoins());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<Reward> getAllRewards() {
        return (List<Reward>) rewardRepository.findAll();
    }

    @Override
    public Reward findRewardByMilestone(Integer milestoneNumber) {
        return rewardRepository.findByMilestoneNumber(milestoneNumber);
    }
}
