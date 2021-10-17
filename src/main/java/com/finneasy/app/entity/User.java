package com.finneasy.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private String firstName;

    private String lastName;

    private String emailId;

    private String referralCode;

    @JsonIgnore
    private String password;

    private Integer totalRewards;

    private Integer milestoneCounter;

    private Integer milestoneNumber;

    public User() {
    }

    public User(String firstName, String lastName, String phoneNumber, String emailId,String referralCode, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.referralCode = referralCode;
        this.password = password;
        this.totalRewards = 10;
        this.milestoneCounter = 1;
        this.milestoneNumber = 1;
    }

    public void incrementCounter(){
        milestoneCounter = milestoneCounter + 1;
    }

    public void addCoins(Integer coins){
        totalRewards += coins;
    }
}
