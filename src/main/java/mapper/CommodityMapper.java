package mapper;

import entity.Commodity;
import entity.Transaction;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CommodityMapper {
    int deleteByPrimaryKey(Integer commodityId);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Integer commodityId);

    List<Commodity> selectAll();

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);

    @Select("select quantity from Commodity where commodity_id=#{arg0}")
    Integer selectQuantityByCommodity(Integer commodityId);

    @Update("update Commodity set sold_number=sold_number+#{arg1},quantity=quantity-#{arg1} where commodity_id=#{arg0}")
    Integer updateQuantityOfCommodity(Integer commodityId, int update);

    @Select("SELECT * FROM Commodity where category=#{arg0}")
    List<Commodity> selectByCategoryID(Integer categoryId);

}