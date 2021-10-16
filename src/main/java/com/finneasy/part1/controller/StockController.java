package com.finneasy.part1.controller;

import com.finneasy.part1.model.StockModel;
import com.finneasy.part1.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/add")
    public ResponseEntity<StockModel> addOrder(@RequestBody StockModel stockModel){
        return ResponseEntity.ok(stockService.createStock(stockModel));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<StockModel>> getAllStocksOfUser(@PathVariable Long userId){
        return ResponseEntity.ok(stockService.getAllStocksOfUser(userId));
    }
}
