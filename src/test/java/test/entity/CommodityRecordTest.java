package test.entity;

import entity.CommodityRecord;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/** 
* CommodityRecord Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 4, 2020</pre> 
* @version 1.0 
*/ 
public class CommodityRecordTest { 



/** 
* 
* Method: getId() 
* 
*/ 
@Test
public void testGetId() throws Exception { 

    CommodityRecord commodityRecord = new CommodityRecord();
    commodityRecord.setId(1);
    int id = commodityRecord.getId();
    Assert.assertEquals(1, id);

} 

/** 
* 
* Method: setId(Integer id) 
* 
*/ 
@Test
public void testSetId() throws Exception { 

    CommodityRecord commodityRecord = new CommodityRecord();
    commodityRecord.setId(1);
    int id = commodityRecord.getId();
    Assert.assertEquals(1, id);
} 

/** 
* 
* Method: getUserId() 
* 
*/ 
@Test
public void testGetUserId() throws Exception {
    CommodityRecord commodityRecord = new CommodityRecord();
    commodityRecord.setUserId(0);
    int id = commodityRecord.getUserId();
    Assert.assertEquals(0, id);
} 

/** 
* 
* Method: setUserId(Integer userId) 
* 
*/ 
@Test
public void testSetUserId() throws Exception {
    CommodityRecord commodityRecord = new CommodityRecord();
    commodityRecord.setUserId(0);
    int id = commodityRecord.getUserId();
    Assert.assertEquals(0, id);
} 

/** 
* 
* Method: getTransactionId() 
* 
*/ 
@Test
public void testGetTransactionId() throws Exception {
    CommodityRecord commodityRecord = new CommodityRecord();
    commodityRecord.setTransactionId(0);
    int id = commodityRecord.getTransactionId();
    Assert.assertEquals(0, id);
} 

/** 
* 
* Method: setTransactionId(Integer transactionId) 
* 
*/ 
@Test
public void testSetTransactionId() throws Exception {
    CommodityRecord commodityRecord = new CommodityRecord();
    commodityRecord.setTransactionId(0);
    int id = commodityRecord.getTransactionId();
    Assert.assertEquals(0, id);
} 

/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception {
    CommodityRecord commodityRecord = new CommodityRecord();
    commodityRecord.setTransactionId(0);
    commodityRecord.setId(0);
    commodityRecord.setUserId(0);
    Assert.assertNotNull(commodityRecord.toString());
} 




} 
