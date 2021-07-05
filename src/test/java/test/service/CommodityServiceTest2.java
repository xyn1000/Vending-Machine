package test.service;

import entity.Commodity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import service.CommodityService;

public class CommodityServiceTest2 {
    /**
     * Method: addCommodity(Commodity commodity)
     */
    @Test
    public void testAddCommodity() throws Exception {
        CommodityService commodityService = new CommodityService();
        Commodity commodity=new Commodity();
        commodity.setCommodityId(2030);
        commodity.setName("FakeCola");
        commodity.setPrice(3.0);
        commodity.setCategory(3);

        commodityService.addCommodity(commodity);
        Assert.assertNotNull(commodityService.getCommodity(2030));
        Assert.assertNotNull(commodityService.updateSelective(commodity));
        commodityService.deleteCommodity(2030);
    }
}
