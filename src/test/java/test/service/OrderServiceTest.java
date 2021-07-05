package test.service;

import entity.Order;
import mapper.OrderMapper;
import mapper.OrderMapperImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import service.OrderService;

/** 
* OrderService Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 5, 2020</pre> 
* @version 1.0 
*/ 
public class OrderServiceTest {

/** 
* 
* Method: addOrder(Order order) 
* 
*/ 
@Test
public void testAddOrder() throws Exception { 
    OrderService orderService = new OrderService();
    OrderMapper orderMapper=new OrderMapperImpl();
    Assert.assertNotNull(orderService.orderMapper);
    Order order = new Order();
    order.setId(3333);
    order.setQuantity(15);
    order.setTransactionId(15);
    order.setCommodityId(3);
    Assert.assertNotNull(orderService.addOrder(order));
    orderMapper.deleteByPrimaryKey(3333);
    Assert.assertNotNull(orderService.getByTransactionId(20));
} 


} 
