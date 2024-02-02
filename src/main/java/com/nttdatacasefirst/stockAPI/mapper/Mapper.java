package com.nttdatacasefirst.stockAPI.mapper;

import com.nttdatacasefirst.stockAPI.dtos.CapitalIncreaseAddModel;
import com.nttdatacasefirst.stockAPI.dtos.ProcessAddModel;
import com.nttdatacasefirst.stockAPI.dtos.ShareholderAddModel;
import com.nttdatacasefirst.stockAPI.dtos.ShareholderUpdateModel;
import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;
import com.nttdatacasefirst.stockAPI.entity.Process;
import com.nttdatacasefirst.stockAPI.entity.ShareHolder;
import com.nttdatacasefirst.stockAPI.entity.enums.InvestorType;
import com.nttdatacasefirst.stockAPI.entity.enums.ProcessType;
import org.springframework.stereotype.Component;

@Component
public class Mapper{


    public Process toProcessAdd(ProcessAddModel addModel){
        Process newProcess = new Process();

        newProcess.setProcessType(ProcessType.valueOf(addModel.getProcessType()));
        newProcess.setDividentRate(addModel.getDividentRate());
        newProcess.setDividendYear(addModel.getDividentYear());

        return newProcess;
    }
}
