package com.finneasy.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private String companyCode;

    private String stockPrice;

    private Integer quantity;

    private TransactionType type;

    @ManyToOne
    private User user;

}
