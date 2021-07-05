package test.mapper;

import entity.Commodity;
import entity.Money;
import mapper.CommodityMapperImpl;
import mapper.MoneyMapper;
import mapper.MoneyMapperImpl;
import mapper.UserMapperImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import service.CommodityService;
import service.MoneyService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class MoneyMapperImplTest {
    /**
     *
     * Method: insertSelective(Money record)
     * Method: deleteByPrimaryKey(Integer moneyId)
     *
     */
    @Test
    public void testInsertSelectiveDelete() throws Exception {
        MoneyService moneyService=new MoneyService();
        MoneyMapperImpl moneyMapperImpl=new MoneyMapperImpl();
        Money money=new Money();
        money.setMoneyId(1000);
        money.setValue(10000.0);
        money.setQuantity(10000);
        int num=moneyMapperImpl.insert(money);
        Assert.assertEquals(0,num);
        int num2=moneyMapperImpl.insertSelective(money);
        Assert.assertNotNull(num2);
        assertEquals(moneyMapperImpl.deleteByPrimaryKey(1000),1);

    }

    /**
     *
     * Method: selectByPrimaryKey(Integer moneyId)
     *
     */
    @Test
    public void testSelectByPrimaryKey() throws Exception {
        MoneyMapperImpl moneyMapper = new MoneyMapperImpl();
        Assert.assertNotNull(moneyMapper.selectByPrimaryKey(1));
    }

    /**
     *
     * Method: updateByPrimaryKeySelective(Money record)
     *
     */
    @Test
    public void testUpdateByPrimaryKeySelective() throws Exception{
        MoneyMapper moneyMapperImpl=new MoneyMapperImpl();
        Money money=new Money();
        money.setMoneyId(1000);
        money.setValue(10000.0);
        money.setQuantity(10000);
        Assert.assertNotNull(moneyMapperImpl.updateByPrimaryKeySelective(money));
        moneyMapperImpl.deleteByPrimaryKey(1000);

    }

    @Test
    public void testUpdateByPrimaryKey() throws Exception {
        MoneyMapper moneyMapperImpl=new MoneyMapperImpl();
        Money money=new Money();
        money.setMoneyId(1000);
        money.setValue(10000.0);
        money.setQuantity(10000);
        Assert.assertNotNull(moneyMapperImpl.updateByPrimaryKey(money));
        moneyMapperImpl.deleteByPrimaryKey(1000);
    }

//    @Test
//    public void testSelectAll() throws Exception {
//        MoneyMapper moneyMapperImpl=new MoneyMapperImpl();
//        List<Money> moneyList=new ArrayList<Money>();
//        moneyList=moneyMapperImpl.selectAll();
//        Money money=moneyMapperImpl.selectByPrimaryKey(1);
//        assertEquals(5,money.getQuantity());
//    }

    @Test
    public void testAddQuantityOfCommodity() throws Exception{
        MoneyMapper moneyMapper=new MoneyMapperImpl();
        Money money=new Money();
        money.setMoneyId(1000);
        money.setValue(10000.0);
        money.setQuantity(100);
        Integer rs=moneyMapper.AddQuantityOfCommodity(1000,100);
        assertNotNull(rs);
        moneyMapper.deleteByPrimaryKey(1000);

    }

    @Test
    public void testMinusQuantityOfCommodity() throws Exception {
        MoneyMapper moneyMapper=new MoneyMapperImpl();
        Money money=new Money();
        money.setMoneyId(1000);
        money.setValue(10000.0);
        money.setQuantity(100);
        Integer rs=moneyMapper.MinusQuantityOfCommodity(1000,100);
        assertNotNull(rs);
        moneyMapper.deleteByPrimaryKey(1000);
    }

    /**
     *
     * Method: CheckSelectAll()
     *
     */
    @Test
    public void testSelectAll() throws Exception{
        MoneyMapper moneyMapper = new MoneyMapperImpl();
        Assert.assertNotNull(moneyMapper.selectAll());
    }
}
