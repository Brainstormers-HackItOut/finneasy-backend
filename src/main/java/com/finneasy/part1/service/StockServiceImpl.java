package com.finneasy.part1.service;

import com.finneasy.part1.entity.Stock;
import com.finneasy.part1.entity.User;
import com.finneasy.part1.model.StockModel;
import com.finneasy.part1.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService{

    private UserService userService;
    private StockRepository stockRepository;

    public StockServiceImpl(UserService userService, StockRepository stockRepository) {
        this.userService = userService;
        this.stockRepository = stockRepository;
    }

    @Override
    public StockModel createStock(StockModel stockModel) {
        User user = userService.getUser(stockModel.getUserId());
        Stock stock = new Stock();
        stock.setUser(user);
        stock.setStockPrice(stockModel.getStockPrice());
        stock.setCompanyCode(stockModel.getCompanyCode());
        stock.setQuantity(stockModel.getQuantity());
        stock.setCompanyName(stockModel.getCompanyName());
        stock.setType(stockModel.getType());
        stockRepository.save(stock);
        stockModel.setId(stock.getId());
        return stockModel;
    }

    @Override
    public List<Stock> getAllStocksOfUser(Long userId) {
        User user = userService.getUser(userId);
        return stockRepository.findAllByUser(user);
    }
}
