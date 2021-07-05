package mapper;

import entity.Order;
import org.apache.ibatis.session.SqlSession;
import util.SQLSessionUtil;

import java.util.List;

public class OrderMapperImpl implements OrderMapper{
    @Override
    public int deleteByPrimaryKey(Integer id) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        int rs = orderMapper.deleteByPrimaryKey(id);
        session.commit(true);
        session.close();
        return rs;
    }

    @Override
    public int insert(Order record) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        int rs = orderMapper.insert(record);
        session.commit(true);
        session.close();
        return rs;
    }

    @Override
    public int insertSelective(Order record) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        int rs = orderMapper.insertSelective(record);
        session.commit(true);
        session.close();
        return rs;
    }

    @Override
    public Order selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Order record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Order record) {
        return 0;
    }

    @Override
    public List<Order> selectByTransactionId(Integer transaction_id) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        List<Order> rs = orderMapper.selectByTransactionId(transaction_id);
        session.commit(true);
        session.close();
        return rs;
    }



}
