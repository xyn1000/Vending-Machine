package test.entity;

import entity.Commodity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/** 
* Commodity Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 4, 2020</pre> 
* @version 1.0 
*/ 
public class CommodityTest {

/** 
* 
* Method: getCommodityId() 
* 
*/ 
@Test
public void testGetCommodityId() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setCommodityId(1);
    int num = commodity.getCommodityId();
    Assert.assertEquals(1, num);
} 

/** 
* 
* Method: setCommodityId(Integer commodityId) 
* 
*/ 
@Test
public void testSetCommodityId() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setCommodityId(1);
    int num = commodity.getCommodityId();
    Assert.assertEquals(1, num);
} 

/** 
* 
* Method: getName() 
* 
*/ 
@Test
public void testGetName() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setName("Cola");
    String name = commodity.getName();
    Assert.assertEquals("Cola", name);
} 

/** 
* 
* Method: setName(String name) 
* 
*/ 
@Test
public void testSetName() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setName("Cola");
    String name = commodity.getName();
    Assert.assertEquals("Cola", name);
    commodity.setName(null);
    Assert.assertNull(commodity.getName());
} 

/** 
* 
* Method: getPrice() 
* 
*/ 
@Test
public void testGetPrice() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setPrice(1.5);
    Double price = commodity.getPrice();
    Double test = 1.5;
    Assert.assertEquals(test, price);
} 

/** 
* 
* Method: setPrice(Double price) 
* 
*/ 
@Test
public void testSetPrice() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setPrice(1.5);
    Double price = commodity.getPrice();
    Double test = 1.5;
    Assert.assertEquals(test, price);
} 

/** 
* 
* Method: getSoldNumber() 
* 
*/ 
@Test
public void testGetSoldNumber() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setSoldNumber(15);
    int soldNumber = commodity.getSoldNumber();
    Assert.assertEquals(15,soldNumber);
} 

/** 
* 
* Method: setSoldNumber(Integer soldNumber) 
* 
*/ 
@Test
public void testSetSoldNumber() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setSoldNumber(15);
    int soldNumber = commodity.getSoldNumber();
    Assert.assertEquals(15,soldNumber);

} 

/** 
* 
* Method: getQuantity() 
* 
*/ 
@Test
public void testGetQuantity() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setQuantity(0);
    int quantity  = commodity.getQuantity();
    Assert.assertEquals(0, quantity);
} 

/** 
* 
* Method: setQuantity(Integer quantity) 
* 
*/ 
@Test
public void testSetQuantity() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setQuantity(0);
    int quantity  = commodity.getQuantity();
    Assert.assertEquals(0, quantity);
} 

/** 
* 
* Method: getCategory() 
* 
*/ 
@Test
public void testGetCategory() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setCategory(1);
    int category = commodity.getCategory();
    Assert.assertEquals(1, category);
} 

/** 
* 
* Method: setCategory(Integer category) 
* 
*/ 
@Test
public void testSetCategory() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setCategory(1);
    int category = commodity.getCategory();
    Assert.assertEquals(1, category);


} 

/** 
* 
* Method: getPicAddress() 
* 
*/ 
@Test
public void testGetPicAddress() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setPicAddress("222");
    String address = commodity.getPicAddress();
    Assert.assertEquals("222", address);

} 

/** 
* 
* Method: setPicAddress(String picAddress) 
* 
*/ 
@Test
public void testSetPicAddress() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setPicAddress("222");
    String address = commodity.getPicAddress();
    Assert.assertEquals("222", address);
    commodity.setPicAddress(null);
    Assert.assertNull(commodity.getPicAddress());
} 

/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setSoldNumber(12);
    commodity.setCategory(1);
    commodity.setName("chips");
    commodity.setPrice(1.5);
    commodity.setQuantity(15);
    commodity.setCommodityId(1);
    commodity.setPicAddress("111");
    Assert.assertNotNull(commodity.toString());

} 


} 
