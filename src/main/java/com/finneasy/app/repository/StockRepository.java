package com.finneasy.app.repository;

import com.finneasy.app.entity.Stock;
import com.finneasy.app.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {
    List<Stock> findAllByUser(User user);
}
