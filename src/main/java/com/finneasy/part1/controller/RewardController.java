package com.finneasy.part1.controller;

import com.finneasy.part1.entity.Reward;
import com.finneasy.part1.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
