package com.nttdatacasefirst.stockAPI.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessAddModel {
    private String processType;
    private int dividentRate;
    private int dividentYear;

}
