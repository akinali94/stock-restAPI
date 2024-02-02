package com.nttdatacasefirst.stockAPI.service.impl;

import com.nttdatacasefirst.stockAPI.dtos.StockAddModel;
import com.nttdatacasefirst.stockAPI.dtos.StockGetModel;
import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;
import com.nttdatacasefirst.stockAPI.entity.Stock;
import com.nttdatacasefirst.stockAPI.exceptions.CapitalIncreaseIsNotEnoughException;
import com.nttdatacasefirst.stockAPI.exceptions.CapitalIncreaseNotFoundException;
import com.nttdatacasefirst.stockAPI.exceptions.StockNotFoundException;
import com.nttdatacasefirst.stockAPI.mapper.MapperStock;
import com.nttdatacasefirst.stockAPI.repository.StockRepository;
import com.nttdatacasefirst.stockAPI.service.CapitalIncreaseService;
import com.nttdatacasefirst.stockAPI.service.CouponService;
import com.nttdatacasefirst.stockAPI.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository repositoryStock;
    private final MapperStock mapperStock;
    private final CapitalIncreaseService serviceCapitalIncrease;
    private final CouponService serviceCoupon;

    public StockServiceImpl(@Autowired StockRepository repositoryStock, @Autowired MapperStock mapperStock,
                            @Autowired CapitalIncreaseService serviceCapitalIncrease,
                            @Autowired CouponService serviceCoupon) {
        this.repositoryStock = repositoryStock;
        this.mapperStock = mapperStock;
        this.serviceCapitalIncrease = serviceCapitalIncrease;
        this.serviceCoupon = serviceCoupon;
    }

    @Override
    public StockGetModel addStock(StockAddModel addModel) {

        //Check Capital increase is true
        Optional<CapitalIncrease> checkedIncrease =
                serviceCapitalIncrease.findCapitalIncrease(addModel.getCapitalIncrease().getArrangementNo());
        if(checkedIncrease.isEmpty()){
            throw new CapitalIncreaseNotFoundException("Capital Increase Not Found");
        }

        //Check NominalValue
        BigDecimal residual = checkedIncrease.get().getResidualValue();
        if(residual.compareTo(addModel.getNominalValue()) >= 0)
            throw new CapitalIncreaseIsNotEnoughException("Capital Increase Is Not Enough to produce Stock");

        //instantiate new Stock
        Stock newStock = mapperStock.toAdd(addModel);
        newStock.setShareHolder(null);

        //Serial number assignment
        List<Stock> getStockList =  repositoryStock.findByCapitalIncrease(newStock.getCapitalIncrease());
        if(getStockList.isEmpty())
            newStock.setSerialNo(1);
        else{
            int lastSerialNo = getStockList.stream().max(Comparator.comparing(Stock::getSerialNo)).get().getSerialNo();
            newStock.setSerialNo(lastSerialNo + 1);
        }

        //Save Stock to Database
        Stock saved = repositoryStock.save(newStock);

        //Set Residual Value on Capital Increase
        serviceCapitalIncrease.findCapitalIncrease(addModel.getCapitalIncrease().getArrangementNo()).get()
                .setResidualValue(residual.add(addModel.getNominalValue()));


        //Create Coupons
        serviceCoupon.AddCouponFromStockService(saved);

        return mapperStock.toModelGet(saved);
    }

    @Override
    public List<StockGetModel> getStocksofCapitalIncrease(String arrNo) {
        Long arrNoLong = Long.parseLong(arrNo);
        Optional<CapitalIncrease> findCI = serviceCapitalIncrease.findCapitalIncrease(arrNoLong);


        return mapperStock.toModelGetList(repositoryStock.findByCapitalIncrease(findCI.get()));
    }

    @Override
    public List<StockGetModel> getAllStocks() {
        List<Stock> getList = repositoryStock.findAll();
        if(getList.isEmpty())
            throw new StockNotFoundException("There is No Stock");
        return mapperStock.toModelGetList(getList);
    }
}
