package com.finneasy.part1.repository;

import com.finneasy.part1.entity.Reward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends CrudRepository<Reward, Long> {

    Reward findByMilestoneNumber(Integer milestoneNumber);

}
