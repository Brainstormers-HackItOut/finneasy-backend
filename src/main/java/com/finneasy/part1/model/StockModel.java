package com.finneasy.part1.model;

import com.finneasy.part1.entity.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockModel {

    private Long id;

    private String companyName;

    private String companyCode;

    private String stockPrice;

    private Integer quantity;

    private TransactionType type;

    private Long userId;

}