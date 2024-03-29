package com.finneasy.app.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserModel {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String emailId;

    private String password;

    private Integer totalRewards;

    private Integer milestoneCounter;

    private Integer milestoneNumber;

}
