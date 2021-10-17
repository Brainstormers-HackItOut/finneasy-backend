package com.finneasy.app.repository;

import com.finneasy.app.entity.Reward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends CrudRepository<Reward, Long> {

    Reward findByMilestoneNumber(Integer milestoneNumber);

}
