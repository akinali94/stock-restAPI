package com.nttdatacasefirst.stockAPI.service.impl;

import com.nttdatacasefirst.stockAPI.dtos.StockAddModel;
import com.nttdatacasefirst.stockAPI.dtos.StockGetModel;
import com.nttdatacasefirst.stockAPI.entity.CapitalIncrease;
import com.nttdatacasefirst.stockAPI.entity.ShareHolder;
import com.nttdatacasefirst.stockAPI.entity.Stock;
import com.nttdatacasefirst.stockAPI.exceptions.CapitalIncreaseIsNotEnoughException;
import com.nttdatacasefirst.stockAPI.exceptions.CapitalIncreaseNotFoundException;
import com.nttdatacasefirst.stockAPI.exceptions.NominalValueException;
import com.nttdatacasefirst.stockAPI.exceptions.StockNotFoundException;
import com.nttdatacasefirst.stockAPI.mapper.MapperStock;
import com.nttdatacasefirst.stockAPI.repository.StockRepository;
import com.nttdatacasefirst.stockAPI.service.CapitalIncreaseService;
import com.nttdatacasefirst.stockAPI.service.CouponService;
import com.nttdatacasefirst.stockAPI.service.OperationService;
import com.nttdatacasefirst.stockAPI.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

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
        if(addModel.getNominalValue().compareTo(new BigDecimal(0)) <= 0)
            throw new NominalValueException("Nominal Value Cannot Be equal or Less Than Zero");

        //instantiate new Stock
        Stock newStock = new Stock();

        //Check Capital increase is true
        CapitalIncrease checkedIncrease =
                serviceCapitalIncrease.findCapitalIncrease(Long.parseLong(addModel.getCapitalIncreaseArrNo()))
                        .orElseThrow(() -> new CapitalIncreaseNotFoundException("Capital Increase Not found in Stock Service"));

        //Set Capital Increase
        newStock.setCapitalIncrease(checkedIncrease);

        //Check NominalValue
        BigDecimal residual = checkedIncrease.getResidualValue();
        if(residual.compareTo(addModel.getNominalValue()) < 0)
            throw new CapitalIncreaseIsNotEnoughException("Capital Increase Is Not Enough to produce Stock");

        //Set Nominal Value
        newStock.setNominalValue(addModel.getNominalValue());

        //Serial number assignment
        List<Stock> getStockList =  repositoryStock.findByCapitalIncrease(newStock.getCapitalIncrease());
        if(getStockList.isEmpty())
            newStock.setSerialNo(1);
        else{
/*            int lastSerialNo = getStockList.stream().max(Comparator.comparing(Stock::getSerialNo)).get().getSerialNo();
            newStock.setSerialNo(lastSerialNo + 1);*/

            int newSerialNo = 1;
            for(Stock st : getStockList){
                if(st.getNominalValue().compareTo(addModel.getNominalValue()) >= 1){
                    st.setSerialNo(st.getSerialNo() + 1);
                } else{
                    newSerialNo = st.getSerialNo() + 1;
                }
            }

            newStock.setSerialNo(newSerialNo);
        }

        //Set null user
       newStock.setShareHolder(null);

        //Save Stock to Database
        Stock saved = repositoryStock.save(newStock);

        //Set Residual Value on Capital Increase
        serviceCapitalIncrease.findCapitalIncrease(newStock.getCapitalIncrease().getArrangementNo())
                .orElseThrow(() -> new CapitalIncreaseNotFoundException("Capital Increase Not Found, in Stock Service"))
                .setResidualValue(residual.subtract(newStock.getNominalValue()));

        //Create Coupons
        serviceCoupon.AddCouponFromStockService(saved);

        return mapperStock.toModelGet(saved);
    }

    @Override
    public List<StockGetModel> getStocksofCapitalIncrease(String arrNo) {
        Long arrNoLong = Long.parseLong(arrNo);
        CapitalIncrease findCapitalIncrease = serviceCapitalIncrease.findCapitalIncrease(arrNoLong)
                .orElseThrow(() -> new CapitalIncreaseNotFoundException("Capital Increase Not Found, in Stock Service"));


        return mapperStock.toModelGetList(repositoryStock.findByCapitalIncrease(findCapitalIncrease));
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
                .orElseThrow(() -> new CapitalIncreaseNotFoundException("Capital Increase Not Found, in Stock Service"));

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

    @Override
    public StockGetModel deleteStockById(String id){
        Stock findStock = repositoryStock.findById(Long.parseLong(id))
                .orElseThrow(() -> new StockNotFoundException("No stock found to be deleted"));

        StockGetModel mapped = mapperStock.toModelGet(findStock);

        BigDecimal residualValue = findStock.getCapitalIncrease().getResidualValue();
        findStock.getCapitalIncrease().setResidualValue(residualValue.add(findStock.getNominalValue()));

        //serviceCoupon.deleteCouponOfStock(findStock);
        //serviceOperation.deleteOperationOfStocks(findStock);
        repositoryStock.delete(findStock);

        return mapped;
    }

    @Override
    public void changeShareholderofStock(Stock stock, ShareHolder shareHolder){
        stock.setShareHolder(shareHolder);
    }

    @Override
    public Stock getStockById(String id){
        return repositoryStock.findById(Long.parseLong(id))
                .orElseThrow(() -> new StockNotFoundException("Stock Not Found When trying to search by ID"));
    }

    @Override
    public List<StockGetModel> getStocksofShareholder(Long regNo) {

        return mapperStock.toModelGetList(
                repositoryStock.findByShareHolder_RegistorNo(regNo)
        );
    }

    //Silinecek
    @Override
    public List<Stock> getAllStockEntity(){

        return repositoryStock.findAll();
    }
}
