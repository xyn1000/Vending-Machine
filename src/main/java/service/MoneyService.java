package service;

import entity.Money;
import mapper.MoneyMapper;
import mapper.MoneyMapperImpl;

import java.util.List;

public class MoneyService {
    private MoneyMapper moneyMapper;

    public MoneyService(){
        this.moneyMapper = new MoneyMapperImpl();
    }

    public int getQuantity(Integer MoneyId){
        Money money = moneyMapper.selectByPrimaryKey(MoneyId);
        return money.getQuantity();
    }

    public void addQuantity(Integer MoneyId, int quantity){
        moneyMapper.AddQuantityOfCommodity(MoneyId, quantity);
    }
    public void minusQuantity(Integer MoneyId, int quantity){
        moneyMapper.MinusQuantityOfCommodity(MoneyId, quantity);
    }

    public List<Money> getAllMoney(){
        return moneyMapper.selectAll();
    }

    public int updateByMid(Money money){
        return moneyMapper.updateByPrimaryKeySelective(money);
    }

}
