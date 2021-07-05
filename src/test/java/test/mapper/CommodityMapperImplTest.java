package test.mapper; 

import entity.Commodity;
import mapper.CommodityMapperImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import service.CommodityService;

import static org.junit.jupiter.api.Assertions.*;

/** 
* CommodityMapperImpl Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 5, 2020</pre> 
* @version 1.0 
*/ 
public class CommodityMapperImplTest {


/** 
* 
* Method: insertSelective(Commodity record)
* Method: deleteByPrimaryKey(Integer commodityId)
* 
*/ 
@Test
public void testInsertSelective() throws Exception {
    CommodityService commodityService  = new CommodityService();
    CommodityMapperImpl commodityMapperImpl = new CommodityMapperImpl();
    Commodity commodity  = new Commodity();
    commodity.setCommodityId(333);
    commodity.setName("Test");
    commodity.setPrice(3.5);
    commodity.setQuantity(7);
    commodity.setCategory(3);
    int num = commodityMapperImpl.insert(commodity);
    Assert.assertEquals(0, num);
    int num2 = commodityMapperImpl.insertSelective(commodity);
    Assert.assertNotNull(num2);
    assertEquals(commodityMapperImpl.deleteByPrimaryKey(333),1);
    commodityMapperImpl.insertSelective(commodity);
    assertEquals(commodityService.deleteCommodity(333), 1);


} 

/** 
* 
* Method: selectAll() 
* 
*/ 
@Test
public void testSelectAll() throws Exception {
    CommodityMapperImpl commodityMapper = new CommodityMapperImpl();
    Assert.assertNotNull(commodityMapper.selectAll());
} 

/** 
* 
* Method: selectByPrimaryKey(Integer commodityId) 
* 
*/ 
@Test
public void testSelectByPrimaryKey() throws Exception { 
    CommodityMapperImpl commodityMapper = new CommodityMapperImpl();
    Assert.assertNotNull(commodityMapper.selectByPrimaryKey(1));
} 

/** 
* 
* Method: updateByPrimaryKeySelective(Commodity record) 
* 
*/ 
@Test
public void testUpdateByPrimaryKeySelective() throws Exception {
    CommodityMapperImpl commodityMapperImpl = new CommodityMapperImpl();
    Commodity commodity  = new Commodity();
    commodity.setCommodityId(333);
    commodity.setName("Test2");
    commodity.setPrice(1.5);
    commodity.setQuantity(10);
    commodity.setCategory(3);
    Assert.assertNotNull(commodityMapperImpl.updateByPrimaryKeySelective(commodity));
    commodityMapperImpl.deleteByPrimaryKey(333);
} 

/** 
* 
* Method: updateByPrimaryKey(Commodity record) 
* 
*/ 
@Test
public void testUpdateByPrimaryKey() throws Exception {
    CommodityMapperImpl commodityMapperImpl = new CommodityMapperImpl();
    Commodity commodity  = new Commodity();
    commodity.setCommodityId(333);
    commodity.setName("Test2");
    commodity.setPrice(1.5);
    commodity.setQuantity(10);
    commodity.setCategory(3);
    Assert.assertNotNull(commodityMapperImpl.updateByPrimaryKey(commodity));
    commodityMapperImpl.deleteByPrimaryKey(333);
} 

/** 
* 
* Method: selectQuantityByCommodity(Integer commodityId) 
* 
*/ 
@Test
public void testSelectQuantityByCommodity() throws Exception {
    CommodityMapperImpl commodityMapperImpl = new CommodityMapperImpl();
    Assert.assertNotNull(commodityMapperImpl.selectQuantityByCommodity(1));
} 

/** 
* 
* Method: updateQuantityOfCommodity(Integer commodityId, int update) 
* 
*/ 
@Test
public void testUpdateQuantityOfCommodity() throws Exception {
    CommodityMapperImpl commodityMapperImpl = new CommodityMapperImpl();
    Commodity commodity  = new Commodity();
    commodity.setCommodityId(333);
    commodity.setName("Test2");
    commodity.setPrice(1.5);
    commodity.setQuantity(10);
    commodity.setCategory(3);
    Assert.assertNotNull(commodityMapperImpl.updateQuantityOfCommodity(333, 15));
} 

/** 
* 
* Method: selectByCategoryID(Integer categoryId) 
* 
*/ 
@Test
public void testSelectByCategoryID() throws Exception { 
    CommodityMapperImpl commodityMapper = new CommodityMapperImpl();
    Assert.assertNotNull(commodityMapper.selectByCategoryID(3));
} 


} 
