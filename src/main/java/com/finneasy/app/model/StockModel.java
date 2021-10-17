package com.finneasy.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.finneasy.app.entity.Reward;
import com.finneasy.app.entity.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockModel {

    private Long id;

    private String companyName;

    private String companyCode;

    private String stockPrice;

    private Integer quantity;

    private TransactionType type;

    private Long userId;

    private Reward reward;

}
