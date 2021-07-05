package mapper;

import entity.Transaction;
import org.apache.ibatis.session.SqlSession;
import util.SQLSessionUtil;

import java.util.List;

public class TransactionMapperImpl implements TransactionMapper{
    @Override
    public int deleteByPrimaryKey(Integer transactionId) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        TransactionMapper transactionMapper = session.getMapper(TransactionMapper.class);
        int rs = transactionMapper.deleteByPrimaryKey(transactionId);
        session.commit(true);
        session.close();
        return rs;
    }

    @Override
    public int insert(Transaction record) {
        return 0;
    }

    @Override
    public int insertSelective(Transaction record) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        TransactionMapper transactionMapper = session.getMapper(TransactionMapper.class);
        int rs = transactionMapper.insertSelective(record);
        session.commit(true);
        session.close();
        return rs;
    }

    @Override
    public Transaction selectByPrimaryKey(Integer transactionId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Transaction record) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        TransactionMapper transactionMapper = session.getMapper(TransactionMapper.class);
        int rs = transactionMapper.updateByPrimaryKeySelective(record);
        session.commit(true);
        session.close();
        return rs;
    }

    @Override
    public int updateByPrimaryKey(Transaction record) {
        return 0;
    }

    @Override
    public Integer changeChange(Integer transaction_Id, double update){
        SqlSession session = SQLSessionUtil.openSqlSession();
        TransactionMapper transactionMapper = session.getMapper(TransactionMapper.class);
        int rs = transactionMapper.changeChange(transaction_Id, update);
        session.commit(true);
        session.close();
        return rs;
    }

    @Override
    public Integer changeAmount(Integer transaction_Id, double update){
        SqlSession session = SQLSessionUtil.openSqlSession();
        TransactionMapper transactionMapper = session.getMapper(TransactionMapper.class);
        int rs = transactionMapper.changeAmount(transaction_Id, update);
        session.commit(true);
        session.close();
        return rs;
    }
    @Override
    public List<Transaction> selectLatestFiveTransaction(Integer userId) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        TransactionMapper transactionMapper = session.getMapper(TransactionMapper.class);
        List<Transaction> rs = transactionMapper.selectLatestFiveTransaction(userId);
        session.commit();
        session.close();
        return rs;
    }

    @Override
    public List<Transaction> selectAllTransaction() {
        SqlSession session = SQLSessionUtil.openSqlSession();
        TransactionMapper transactionMapper = session.getMapper(TransactionMapper.class);
        List<Transaction> rs = transactionMapper.selectAllTransaction();
        session.commit();
        session.close();
        return rs;
    }

    @Override
    public List<Transaction> selectAllCancelledTransaction() {
        SqlSession session = SQLSessionUtil.openSqlSession();
        TransactionMapper transactionMapper = session.getMapper(TransactionMapper.class);
        List<Transaction> rs = transactionMapper.selectAllCancelledTransaction();
        session.commit();
        session.close();
        return rs;
    }
}
