package mapper;

import entity.Money;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MoneyMapper {
    int deleteByPrimaryKey(Integer moneyId);

    int insert(Money record);

    int insertSelective(Money record);

    @Select("SELECT * FROM Money")
    List<Money> selectAll();

    Money selectByPrimaryKey(Integer moneyId);

    int updateByPrimaryKeySelective(Money record);

    int updateByPrimaryKey(Money record);

    @Update("update Money set quantity=quantity+#{arg1} where money_id=#{arg0}")
    Integer AddQuantityOfCommodity(Integer moneyId, int update);

    @Update("update Money set quantity=quantity-#{arg1} where money_id=#{arg0}")
    Integer MinusQuantityOfCommodity(Integer moneyId, int update);
}