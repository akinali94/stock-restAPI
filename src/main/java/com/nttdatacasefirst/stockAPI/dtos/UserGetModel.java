package com.nttdatacasefirst.stockAPI.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGetModel {
    private String userEmail;
    private String userRole;
    private String registerNo;
    private String title;
    private String address;
    private String phoneNo;
    private String InverstorType;

}
