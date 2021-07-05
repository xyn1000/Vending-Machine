package test.entity;

import entity.Money;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/** 
* Money Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 4, 2020</pre> 
* @version 1.0 
*/ 
public class MoneyTest {

/** 
* 
* Method: getMoneyId() 
* 
*/ 
@Test
public void testGetMoneyId() throws Exception { 
//TODO: Test goes here...
    Money money =  new Money();
    money.setMoneyId(0);
    int id = money.getMoneyId();
    Assert.assertEquals(0, id);

} 

/** 
* 
* Method: setMoneyId(Integer moneyId) 
* 
*/ 
@Test
public void testSetMoneyId() throws Exception { 
//TODO: Test goes here...
    Money money =  new Money();
    money.setMoneyId(0);
    int id = money.getMoneyId();
    Assert.assertEquals(0, id);
} 

/** 
* 
* Method: getValue() 
* 
*/ 
@Test
public void testGetValue() throws Exception { 
//TODO: Test goes here...
    Money money =  new Money();
    money.setValue(10.0);
    double value = money.getValue();
    Assert.assertEquals(10.0, value, 0);
} 

/** 
* 
* Method: setValue(Integer value) 
* 
*/ 
@Test
public void testSetValue() throws Exception { 
//TODO: Test goes here...
    Money money =  new Money();
    money.setValue(10.0);
    double value = money.getValue();
    Assert.assertEquals(10.0, value, 0);
} 

/** 
* 
* Method: getQuantity() 
* 
*/ 
@Test
public void testGetQuantity() throws Exception { 
//TODO: Test goes here...
    Money  money = new Money();
    money.setQuantity(5);
    int quantity = money.getQuantity();
    Assert.assertEquals(5, quantity);
} 

/** 
* 
* Method: setQuantity(Integer quantity) 
* 
*/ 
@Test
public void testSetQuantity() throws Exception { 
//TODO: Test goes here...
    Money money = new Money();
    money.setQuantity(5);
    int quantity = money.getQuantity();
    Assert.assertEquals(5, quantity);
} 

/**
*
* Method: toString()
*
*/
@Test
public void testToString() throws Exception {
//TODO: Test goes here...
    Money money = new Money();
    money.setQuantity(5);
    money.setMoneyId(0);
    money.setValue(10.0);
    Assert.assertNotNull(money.toString());
}



} 
