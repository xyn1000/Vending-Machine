package test.entity;

import entity.Order;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/** 
* Order Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 4, 2020</pre> 
* @version 1.0 
*/ 
public class OrderTest {

/** 
* 
* Method: getId() 
* 
*/ 
@Test
public void testGetId() throws Exception {
    Order order = new Order();
    order.setId(1);
    int id = order.getId();
    Assert.assertEquals(1, id);
} 

/** 
* 
* Method: setId(Integer id) 
* 
*/ 
@Test
public void testSetId() throws Exception {
    Order order = new Order();
    order.setId(1);
    int id = order.getId();
    Assert.assertEquals(1, id);
} 

/** 
* 
* Method: getQuantity() 
* 
*/ 
@Test
public void testGetQuantity() throws Exception { 
    Order order = new Order();
    order.setQuantity(1);
    int quantity = order.getQuantity();
    Assert.assertEquals(1, quantity);
} 

/** 
* 
* Method: setQuantity(Integer quantity) 
* 
*/ 
@Test
public void testSetQuantity() throws Exception {
    Order order = new Order();
    order.setQuantity(1);
    int quantity = order.getQuantity();
    Assert.assertEquals(1, quantity);
} 

/** 
* 
* Method: getCommodityId() 
* 
*/ 
@Test
public void testGetCommodityId() throws Exception { 
    Order order = new Order();
    order.setCommodityId(1);
    int commodityId = order.getCommodityId();
    Assert.assertEquals(1, commodityId);
} 

/** 
* 
* Method: setCommodityId(Integer commodityId) 
* 
*/ 
@Test
public void testSetCommodityId() throws Exception {
    Order order = new Order();
    order.setCommodityId(1);
    int commodityId = order.getCommodityId();
    Assert.assertEquals(1, commodityId);
} 





/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception {
    Order order =  new Order();
    order.setId(1);
    order.setQuantity(5);
    order.setCommodityId(2);
    Assert.assertNotNull(order.toString());
} 


} 
