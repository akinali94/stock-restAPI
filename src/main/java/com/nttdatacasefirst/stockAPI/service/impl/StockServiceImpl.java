package com.nttdatacasefirst.stockAPI.service.impl;

import com.nttdatacasefirst.stockAPI.dtos.StockAddModel;
import com.nttdatacasefirst.stockAPI.dtos.StockGetModel;
import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;
import com.nttdatacasefirst.stockAPI.entity.ShareHolder;
import com.nttdatacasefirst.stockAPI.entity.Stock;
import com.nttdatacasefirst.stockAPI.exceptions.CapitalIncreaseIsNotEnoughException;
import com.nttdatacasefirst.stockAPI.exceptions.CapitalIncreaseNotFoundException;
import com.nttdatacasefirst.stockAPI.exceptions.CouponNotFoundException;
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
import java.util.stream.Collectors;

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

        //instantiate new Stock
        Stock newStock = mapperStock.toAdd(addModel);

        //Check Capital increase is true
        Optional<CapitalIncrease> checkedIncrease =
                serviceCapitalIncrease.findCapitalIncrease(newStock.getCapitalIncrease().getArrangementNo());
        if(checkedIncrease.isEmpty()){
            throw new CapitalIncreaseNotFoundException("Capital Increase Not Found");
        }

        //Check NominalValue
        BigDecimal residual = checkedIncrease.get().getResidualValue();
        if(residual.compareTo(newStock.getNominalValue()) >= 0)
            throw new CapitalIncreaseIsNotEnoughException("Capital Increase Is Not Enough to produce Stock");


        //Serial number assignment
        List<Stock> getStockList =  repositoryStock.findByCapitalIncrease(newStock.getCapitalIncrease());
        if(getStockList.isEmpty())
            newStock.setSerialNo(1);
        else{
            int lastSerialNo = getStockList.stream().max(Comparator.comparing(Stock::getSerialNo)).get().getSerialNo();
            newStock.setSerialNo(lastSerialNo + 1);
        }

        //Set null user
        newStock.setShareHolder(null);

        //Save Stock to Database
        Stock saved = repositoryStock.save(newStock);

        //Set Residual Value on Capital Increase
        serviceCapitalIncrease.findCapitalIncrease(newStock.getCapitalIncrease().getArrangementNo()).get()
                .setResidualValue(residual.add(newStock.getNominalValue()));

        //Create Coupons
        serviceCoupon.AddCouponFromStockService(saved);

        return mapperStock.toModelGet(saved);
    }

    @Override
    public List<StockGetModel> getStocksofCapitalIncrease(String arrNo) {
        Long arrNoLong = Long.parseLong(arrNo);
        Optional<CapitalIncrease> findCapitalIncrease = serviceCapitalIncrease.findCapitalIncrease(arrNoLong);


        return mapperStock.toModelGetList(repositoryStock.findByCapitalIncrease(findCapitalIncrease.get()));
    }

    @Override
    public List<StockGetModel> getAllStocks() {
        List<Stock> getList = repositoryStock.findAll();
        if(getList.isEmpty())
            throw new StockNotFoundException("There is No Stock");
        return mapperStock.toModelGetList(getList);
    }

    @Override
    public List<Stock> getStocksofCapitalIncrement(String arrNo) {
        Long arrNoLong = Long.parseLong(arrNo);
        //List Stocks of a Capital Increase
        CapitalIncrease findCapitalIncrease = serviceCapitalIncrease.findCapitalIncrease(arrNoLong)
                .orElseThrow(() -> new CapitalIncreaseNotFoundException("Capital Increase Not Found"));

        return repositoryStock.findByCapitalIncrease(findCapitalIncrease);
    }

    @Override
    public Stock getAvailableStockForStockOperation(List<Stock> stockList) {

        return stockList.stream().filter(stock -> serviceCoupon.findNotUsedAndMinClippingNoCoupon(stock) != null).findFirst()
                .orElseThrow(() -> new StockNotFoundException("No available Stock"));

        /*
        for(Stock st : stockList){
            if(serviceCoupon.findNotUsedCoupon(st) != null)
                return st;
        }*/
    }

    @Override
    public void changeCouponToUsedInStockOperation(Stock stock){
        serviceCoupon.findNotUsedAndMinClippingNoCoupon(stock).setUsed(true);
    }

    @Override
    public Stock getAvailableStockForDividendOperation(List<Stock> stockList, ShareHolder shareholder){

       return stockList.stream().filter(stock -> serviceCoupon.findNotUsedandMinDividendYear(stock) != null
                       && stock.getShareHolder() == shareholder).findFirst()
                .orElseThrow(() -> new StockNotFoundException("No available Stock"));
    }

    @Override
    public void changeCouponToUsedInDividdentOperation(Stock stock){
        serviceCoupon.findNotUsedandMinDividendYear(stock).setUsed(true);
    }
}
