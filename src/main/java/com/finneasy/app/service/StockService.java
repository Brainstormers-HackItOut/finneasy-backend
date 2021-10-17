package com.finneasy.app.service;

import com.finneasy.app.model.StockModel;

import java.util.List;

public interface StockService {
    StockModel createStock(StockModel stockModel);

    List<StockModel> getAllStocksOfUser(Long userId);
}
