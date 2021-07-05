package test.mapper;

import entity.Order;
import mapper.OrderMapper;
import mapper.OrderMapperImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/** 
* OrderMapperImpl Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 5, 2020</pre> 
* @version 1.0 
*/ 
public class OrderMapperImplTest {


/** 
* 
* Method: insert(Order record) 
* 
*/ 
@Test
public void testInsert() throws Exception {
    Order record = new Order();
    record.setId(4444);
    record.setQuantity(5);
    record.setCommodityId(3);
    record.setTransactionId(15);
    OrderMapperImpl orderMapperImpl = new OrderMapperImpl();
    Assert.assertNotNull(orderMapperImpl.insert(record));
    Assert.assertEquals(1, orderMapperImpl.deleteByPrimaryKey(4444));
}

/** 
* 
* Method: insertSelective(Order record) 
* 
*/ 
@Test
public void testInsertSelective() throws Exception {
    Order record = new Order();
    record.setId(5555);
    record.setQuantity(5);
    record.setCommodityId(3);
    record.setTransactionId(15);
    OrderMapperImpl orderMapperImpl = new OrderMapperImpl();
    Assert.assertNotNull(orderMapperImpl.insertSelective(record));
    orderMapperImpl.deleteByPrimaryKey(5555);
} 

/** 
* 
* Method: selectByPrimaryKey(Integer id) 
* 
*/ 
@Test
public void testSelectByPrimaryKey() throws Exception {
    OrderMapperImpl orderMapperImpl = new OrderMapperImpl();
    Assert.assertNull(orderMapperImpl.selectByPrimaryKey(14));
} 

/** 
* 
* Method: updateByPrimaryKeySelective(Order record) 
* 
*/ 
@Test
public void testUpdateByPrimaryKeySelective() throws Exception {
    Order record = new Order();
    record.setId(223);
    record.setQuantity(5);
    record.setCommodityId(3);
    record.setTransactionId(15);
    OrderMapperImpl orderMapperImpl = new OrderMapperImpl();
    Assert.assertEquals(0, orderMapperImpl.updateByPrimaryKeySelective(record));
} 

/** 
* 
* Method: updateByPrimaryKey(Order record) 
* 
*/ 
@Test
public void testUpdateByPrimaryKey() throws Exception {
    Order record = new Order();
    record.setId(223);
    record.setQuantity(5);
    record.setCommodityId(3);
    record.setTransactionId(15);
    OrderMapperImpl orderMapperImpl = new OrderMapperImpl();
    Assert.assertEquals(0, orderMapperImpl.updateByPrimaryKey(record));
}

@Test
    public void testSelectByTransactionId() throws Exception{
    OrderMapperImpl orderMapperImpl = new OrderMapperImpl();
    Assert.assertNotNull(orderMapperImpl.selectByTransactionId(100));
}


} 
