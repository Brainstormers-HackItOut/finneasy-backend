package com.finneasy.part1.service;

import com.finneasy.part1.entity.Stock;
import com.finneasy.part1.entity.User;
import com.finneasy.part1.model.StockModel;
import com.finneasy.part1.repository.StockRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService{

    private UserService userService;
    private StockRepository stockRepository;
    private RewardService rewardService;

    public StockServiceImpl(UserService userService, StockRepository stockRepository, RewardService rewardService) {
        this.userService = userService;
        this.stockRepository = stockRepository;
        this.rewardService = rewardService;
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

        Boolean check = rewardService.incrementMilestone(user.getId());

        if(check == Boolean.TRUE){
            stockModel.setReward(rewardService.findRewardByMilestone(user.getMilestoneNumber()));
        }

        return stockModel;
    }

    @Override
    public List<StockModel> getAllStocksOfUser(Long userId) {
        User user = userService.getUser(userId);
        return stockRepository.findAllByUser(user).stream().map(stock -> stockToStockModel(stock)).collect(Collectors.toList());
    }

    private StockModel stockToStockModel(Stock stock){
        StockModel stockModel = new StockModel();
        BeanUtils.copyProperties(stock, stockModel);
        stockModel.setUserId(stock.getUser().getId());
        return stockModel;
    }
}
