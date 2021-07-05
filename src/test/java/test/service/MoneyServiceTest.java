package test.service;

import entity.Money;
import mapper.MoneyMapperImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import service.CommodityService;
import service.MoneyService;

public class MoneyServiceTest {
    /**
     * Method: getQuantity(Integer MoneyId)
     */
    @Test
    public void testGetQuantity() throws Exception {
        MoneyService moneyService = new MoneyService();
        Assert.assertNotNull(moneyService.getQuantity(1));
    }

    /**
     * Method: addQuantity(Integer MoneyId, int quantity)
     */
    @Test
    public void testAddQuantity() throws Exception {
        MoneyService moneyService = new MoneyService();
        MoneyMapperImpl moneyMapperImpl=new MoneyMapperImpl();
        Money money=new Money();
        money.setMoneyId(1000);
        money.setValue(10000.0);
        money.setQuantity(100);
        moneyMapperImpl.insertSelective(money);
        moneyService.addQuantity(1000,100);
        Assert.assertEquals(200,moneyService.getQuantity(1000));
        moneyMapperImpl.deleteByPrimaryKey(1000);

    }

    /**
     * Method: minusQuantity(Integer MoneyId, int quantity)
     */
    @Test
    public void testMinusQuantity() throws Exception {
        MoneyService moneyService = new MoneyService();
        MoneyMapperImpl moneyMapperImpl=new MoneyMapperImpl();
        Money money=new Money();
        money.setMoneyId(1000);
        money.setValue(10000.0);
        money.setQuantity(100);
        moneyMapperImpl.insertSelective(money);
        moneyService.minusQuantity(1000,100);
        Assert.assertEquals(0,moneyService.getQuantity(1000));
        Assert.assertNotNull(moneyService.updateByMid(money));
        moneyMapperImpl.deleteByPrimaryKey(1000);

    }
    /**
     * Method: getAllMoney()
     */
    @Test
    public void testGetAll() throws Exception{
        MoneyService moneyService = new MoneyService();
        Assert.assertNotNull(moneyService.getAllMoney());
    }


}
