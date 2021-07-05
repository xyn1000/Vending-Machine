package test.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import service.CommodityService;

/** 
* CommodityService Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 5, 2020</pre> 
* @version 1.0 
*/ 
public class CommodityServiceTest {

    /**
     * Method: getCommodity(Integer commodityId)
     */
    @Test
    public void testGetCommodity() throws Exception {
        CommodityService commodityService = new CommodityService();
        Assert.assertNotNull(commodityService.getCommodity(3));
    }

    /**
     * Method: getAllCommodities()
     */
    @Test
    public void testGetAllCommodities() throws Exception {
        CommodityService commodityService = new CommodityService();
        Assert.assertNotNull(commodityService.getAllCommodities());
    }

    /**
     * Method: checkCommodityQuantity(int commodityId, int need)
     */
    @Test
    public void testCheckCommodityQuantity() throws Exception {
        CommodityService commodityService = new CommodityService();
        Assert.assertEquals(Boolean.TRUE, commodityService.checkCommodityQuantity(3, 1));
        Assert.assertEquals(Boolean.FALSE, commodityService.checkCommodityQuantity(3, 10000));
    }

    /**
     * Method: updateSoldNumber(int commodityId, int need)
     */
    @Test
    public void testUpdateSoldNumber() throws Exception {
        CommodityService commodityService = new CommodityService();
        Assert.assertNotNull(commodityService.updateSoldNumber(1, 0));
    }

    /**
     * Method: getAllFunctions
     */
    @Test
    public void testGetAllFunctions() throws Exception {
        CommodityService commodityService = new CommodityService();
        Assert.assertNotNull(commodityService.getAllChips());
        Assert.assertNotNull(commodityService.getAllChocolates());
        Assert.assertNotNull(commodityService.getAllDrinks());
        Assert.assertNotNull(commodityService.getAllLollies());
    }

}