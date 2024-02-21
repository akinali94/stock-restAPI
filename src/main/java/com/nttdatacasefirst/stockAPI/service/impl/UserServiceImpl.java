package com.nttdatacasefirst.stockAPI.service.impl;

import com.nttdatacasefirst.stockAPI.dtos.*;
import com.nttdatacasefirst.stockAPI.entity.*;
import com.nttdatacasefirst.stockAPI.entity.enums.InvestorType;
import com.nttdatacasefirst.stockAPI.entity.enums.OperationType;
import com.nttdatacasefirst.stockAPI.exceptions.*;
import com.nttdatacasefirst.stockAPI.mapper.MapperUser;
import com.nttdatacasefirst.stockAPI.repository.UserRepository;
import com.nttdatacasefirst.stockAPI.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService /*UserDetailsService*/ {
    private final UserRepository repository;
    private final JwtService serviceJwt;
    private final MapperUser mapperUser;
    private final StockService serviceStock;
    private final CapitalIncreaseService serviceCapital;
    private final DividendDistributionService serviceDivident;
    private final OperationService serviceOperation;

    public UserServiceImpl(@Autowired UserRepository repository,
                           @Autowired JwtService serviceJwt,
                           @Autowired MapperUser mapperUser,
                           @Autowired StockService serviceStock,
                           @Autowired CapitalIncreaseService serviceCapital,
                           @Autowired DividendDistributionService serviceDivident,
                           @Autowired OperationService serviceOperation
                         ) {
        this.repository = repository;
        this.serviceJwt = serviceJwt;
        this.mapperUser = mapperUser;
        this.serviceStock = serviceStock;
        this.serviceCapital = serviceCapital;
        this.serviceDivident = serviceDivident;
        this.serviceOperation = serviceOperation;
    }
    /*    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }*/

    @Override
    public User getUserDirectly(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return repository.findByEmail(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }


    @Override
    public UserGetModel getUserInformation() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User getUser = repository.findByEmail(auth.getName()).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return mapperUser.toModelGet(getUser);
    }

    @Override
    public UserGetModel setShareholder(ShareholderUpdateByUserModel updateModel) {
        //find user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User updateUser = repository.findByEmail(auth.getName())
                .map(x -> {
                    x.getShareHolder().setTitle(updateModel.getTitle());
                    x.getShareHolder().setAddress(updateModel.getAddress());
                    x.getShareHolder().setPhoneNo(updateModel.getPhoneNo());
                    x.getShareHolder().setInvestorType(InvestorType.valueOf(updateModel.getInvestorType()));

                    return repository.save(x);
                }).orElseThrow(() -> new UserNotUpdatedException("User can not Updated"));

        return mapperUser.toModelGet(updateUser);

    }

    @Override
    public List<StockGetModel> getAllStocks() {

        return serviceStock.getStocksofShareholder(getUserDirectly().getId());
    }

    @Override
    public List<CapitalIncreaseGetModel> getAllCapital() {
        return serviceCapital.getAllCapitalIncrease() ;
    }

    @Override
    public List<DividendDistributionGetModel> getAllDivident() {
        return serviceDivident.getAllDividendDistribution();
    }

    @Override
    public List<OperationGetModel> getAllOperationsforShareholder() {
        return serviceOperation.getOperationsOfShareholder(getUserDirectly().getShareHolder());
    }

    @Override
    public OperationUserGetModel buyStockOperation(OperationUserAddModel addModel) {
        //Find Stock
        Stock findStock = serviceStock.getStockById(addModel.getStockID());
        //Find Shareholder
        User user = getUserDirectly();
        //Find CapitalIncrease
        CapitalIncrease capitalIncrease = serviceCapital
                .findCapitalIncrease(findStock.getCapitalIncrease().getArrangementNo())
                .orElseThrow(() -> new CapitalIncreaseNotFoundException("Capital Increase Not Found in Operations"));

        //Stock should have not an owner.
        if(findStock.getShareHolder() != null)
            throw new StockNotFoundException("Stock belongs to other Shareholder");

        //Shareholder should have enough money to buy this stock.
        if(findStock.getNominalValue().compareTo(user.getShareHolder().getAmountMoney()) > 0)
            throw new StockNotFoundException("Stock value exceed to the Shareholder money");
        //new Operation
        Operation newOperation = new Operation();

        //Stock Operations
        newOperation.setOperationType(OperationType.valueOf("STOCK"));
        newOperation.setStock(findStock);
        newOperation.setDate(new Date());
        newOperation.setShareHolder(user.getShareHolder());

        //Change coupon as used
        serviceStock.changeCouponToUsedInStockOperation(findStock);

        //Set Shareholder of Stock
        serviceStock.changeShareholderofStock(findStock, user.getShareHolder());

        //Set Shareholder Money
        subtractMoney(user, findStock.getNominalValue());
        /*serviceShareholder.subtractMoney(findShareholder,findStock.getNominalValue())*/

        return serviceOperation.saveOperation(newOperation);
    }

    @Override
    public OperationUserGetModel sellStockOperation(OperationUserAddModel addModel) {
        //Find Stock
        Stock findStock = serviceStock.getStockById(addModel.getStockID());
        //Find Shareholder
        User user = getUserDirectly();
        //Find CapitalIncrease
        CapitalIncrease capitalIncrease = serviceCapital
                .findCapitalIncrease(findStock.getCapitalIncrease().getArrangementNo())
                .orElseThrow(() -> new CapitalIncreaseNotFoundException("Capital Increase Not Found in Operations"));

        //Shareholder of Stock should same with user
        if(findStock.getShareHolder() != user.getShareHolder())
            throw new ShareholderandStockNotMatchException("Shareholder an Stock not Match!");

        //new Operation
        Operation newOperation = new Operation();

        //Stock Operations
        newOperation.setOperationType(OperationType.valueOf("DIVIDEND"));
        newOperation.setStock(findStock);
        newOperation.setDate(new Date());
        newOperation.setShareHolder(user.getShareHolder());

        //Change coupon as used
        serviceStock.changeCouponToUsedInStockOperation(findStock);

        //Set Shareholder of Stock
        serviceStock.changeShareholderofStock(findStock, user.getShareHolder());

        //Set Shareholder Money
        addMoney(user, findStock.getNominalValue());

        return serviceOperation.saveOperation(newOperation);
    }

    @Override
    public OperationUserGetModel dividendDistributionForUser(OperationUserAddModel addModel){

        //Find Stock
        Stock findStock = serviceStock.getStockById(addModel.getStockID());
        //Find Shareholder
        User user = getUserDirectly();
        //Find CapitalIncrease
        CapitalIncrease capitalIncrease = serviceCapital
                .findCapitalIncrease(findStock.getCapitalIncrease().getArrangementNo())
                .orElseThrow(() -> new CapitalIncreaseNotFoundException("Capital Increase Not Found in Operations"));

        //A Shareholder can make this operation only once.
        if(serviceOperation.getDividendOperationOfShareholder(user.getShareHolder()) != null)
            throw new OperationPerformedBeforeException("A Shareholder can make this operation only once");
        //new Operation
        Operation newOperation = new Operation();

        //Dividend Operations
        //Check DividendDistribution
        List<DividendDistribution> div = serviceDivident
                .getDividendDistributionByCapitalIncrementArrNo(capitalIncrease.getArrangementNo());
        if (div.isEmpty())
            throw new DividendDistributionNotFoundException("This capital Increase do not have any Dividend");

        //Find last DividendDistribution for this Capital Increase
        DividendDistribution maxSerialNoDividend = div.stream()
                .max(Comparator.comparing(DividendDistribution::getSerialNo))
                .orElseThrow(() ->
                                     new DividendDistributionNotFoundException("Dividend Dist Not Found when search max Serial No"));

        //Set DividendTotal
        double division = (double) maxSerialNoDividend.getDividentRate() / 100;
        BigDecimal getNominal = findStock.getNominalValue();
        BigDecimal divRate = new BigDecimal(division);
        BigDecimal divTotal = getNominal.multiply(divRate);


        newOperation.setOperationType(OperationType.valueOf("DIVIDEND"));

        newOperation.setStock(findStock);
        newOperation.setDividendDistribution(maxSerialNoDividend);
        newOperation.setDate(new Date());
        newOperation.setShareHolder(user.getShareHolder());
        newOperation.setDividentYear(maxSerialNoDividend.getDividendYear());
        newOperation.setDividendTotal(divTotal);

        serviceStock.changeCouponToUsedInDividdentOperation(findStock);

        //Add Money to Shareholder
        addMoney(user, divTotal);


        return serviceOperation.saveOperation(newOperation);
    }

    @Override
    public void subtractMoney(User user, BigDecimal money){
        user.getShareHolder().setAmountMoney(user.getShareHolder().getAmountMoney().subtract(money));
        repository.save(user);
    }

    @Override
    public void addMoney(User user, BigDecimal money){
        user.getShareHolder().setAmountMoney(user.getShareHolder().getAmountMoney().add(money));
        repository.save(user);
    }

    @Override
    public String addMoneyToRequest(ShareholderRequestModel request){
        User user = getUserDirectly();
        user.getShareHolder().setRequestMoney(request.getRequestMoney());

        try{
            repository.save(user);
            return "Money request is succesfully sent";
        } catch (Exception e){
            return "Money request did not sent succeffuly";
        }
    }
}
