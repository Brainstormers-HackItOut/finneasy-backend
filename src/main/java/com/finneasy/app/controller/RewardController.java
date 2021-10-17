package com.finneasy.app.controller;

import com.finneasy.app.entity.Reward;
import com.finneasy.app.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reward")
public class RewardController {

    private RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reward>> getAllReward(){
        return ResponseEntity.ok(rewardService.getAllRewards());
    }

    @GetMapping("/referral/{referralCode}")
    public ResponseEntity<Void> referralUsed(@PathVariable String referralCode){
        rewardService.userReferral(referralCode);
        return ResponseEntity.ok().build();
    }
}
