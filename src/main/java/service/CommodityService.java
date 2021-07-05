package service;

import entity.Commodity;
import entity.EnvironmentConstant;
import mapper.CommodityMapper;
import mapper.CommodityMapperImpl;

import java.util.List;

public class CommodityService {
    private CommodityMapper commodityMapper;

    public CommodityService() {
        this.commodityMapper = new CommodityMapperImpl();
    }

    public int deleteCommodity(Integer commodityId){
        return commodityMapper.deleteByPrimaryKey(commodityId);
    }

    public Commodity getCommodity(Integer commodityId){
        return commodityMapper.selectByPrimaryKey(commodityId);
    }

    public List<Commodity> getAllCommodities(){
        return commodityMapper.selectAll();
    }

    public boolean checkCommodityQuantity(int commodityId,int need){
        if(this.commodityMapper.selectQuantityByCommodity(commodityId)-need<0){
            return false;
        }else{
            return true;
        }
    }

    public int updateSoldNumber(int commodityId, int need){
        return this.commodityMapper.updateQuantityOfCommodity(commodityId,need);
    }

    public List<Commodity> getAllDrinks() {
        return commodityMapper.selectByCategoryID(EnvironmentConstant.DRINK);
    }

    public List<Commodity> getAllChocolates() {
        return commodityMapper.selectByCategoryID(EnvironmentConstant.CHOCOLATE);
    }

    public List<Commodity> getAllLollies() {
        return commodityMapper.selectByCategoryID(EnvironmentConstant.LOLLIES);
    }

    public List<Commodity> getAllChips() {
        return commodityMapper.selectByCategoryID(EnvironmentConstant.CHIPS);
    }

    public int updateSelective(Commodity commodity){
        return commodityMapper.updateByPrimaryKeySelective(commodity);
    }

//    public List<Transaction> getHistoryPurchase(int userId){
//        return commodityMapper.selectLatestFivePurchase(userId);
//    }

//    public List<Commodity> getByOrderId(int order_id){return commodityMapper.selectByOrderId(order_id);}

    public int addCommodity(Commodity commodity){
        return commodityMapper.insertSelective(commodity);
    }

}
