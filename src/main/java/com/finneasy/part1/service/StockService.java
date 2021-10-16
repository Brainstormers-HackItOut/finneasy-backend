package com.finneasy.part1.service;

import com.finneasy.part1.entity.Stock;
import com.finneasy.part1.model.StockModel;

import java.util.List;

public interface StockService {
    StockModel createStock(StockModel stockModel);

    List<StockModel> getAllStocksOfUser(Long userId);
}
