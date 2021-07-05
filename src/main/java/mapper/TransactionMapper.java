package mapper;

import entity.Transaction;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TransactionMapper {
    int deleteByPrimaryKey(Integer transactionId);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(Integer transactionId);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);

    @Update("update Transaction set `change`=#{arg1} where transaction_id=#{arg0}")
    Integer changeChange(Integer TransactionId, double update);

    @Update("update Transaction set amount=#{arg1} where transaction_id=#{arg0}")
    Integer changeAmount(Integer TransactionId, double update);

    @Select("SELECT t.* FROM User u JOIN Transaction t USING (user_id) WHERE u.user_id  = #{arg0} and t.status = 4 ORDER BY t.transaction_id DESC LIMIT 5")
    List<Transaction> selectLatestFiveTransaction(Integer userId);

    @Select("SELECT * FROM Transaction")
    List<Transaction> selectAllTransaction();

    @Select("SELECT * FROM Transaction where status!=4 and status!=0")
    List<Transaction> selectAllCancelledTransaction();
}