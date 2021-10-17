package com.finneasy.app.service;

import com.finneasy.app.entity.Reward;
import com.finneasy.app.entity.User;
import com.finneasy.app.repository.RewardRepository;
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
            userService.saveUser(user);
            return Boolean.TRUE;
        }

        userService.saveUser(user);
        return Boolean.FALSE;
    }

    @Override
    public List<Reward> getAllRewards() {

        List<Reward> rewardList = (List<Reward>) rewardRepository.findAll();

        Collections.sort(rewardList, new Comparator<Reward>() {
            @Override
            public int compare(Reward o1, Reward o2) {
                if(o1.getMilestoneNumber()<o2.getMilestoneNumber())
                    return -1;
                return 1;
            }
        });

        return rewardList;
    }

    @Override
    public Reward findRewardByMilestone(Integer milestoneNumber) {
        return rewardRepository.findByMilestoneNumber(milestoneNumber);
    }

    @Override
    public void userReferral(String referralCode) {
        User user = userService.findByReferral(referralCode);
        incrementMilestone(user.getId());
    }
}
