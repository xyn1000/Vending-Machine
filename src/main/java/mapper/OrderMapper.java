package mapper;

import entity.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    @Select("SELECT * FROM `Order` WHERE transaction_id = #{arg0}")
    List<Order> selectByTransactionId(Integer transaction_id);
}