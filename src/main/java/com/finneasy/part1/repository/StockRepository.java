package com.finneasy.part1.repository;

import com.finneasy.part1.entity.Stock;
import com.finneasy.part1.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {
    List<Stock> findAllByUser(User user);
}
