package mapper;

import entity.Commodity;
import entity.Money;
import org.apache.ibatis.session.SqlSession;
import util.SQLSessionUtil;

import java.util.List;

public class MoneyMapperImpl implements MoneyMapper {
    @Override
    public int deleteByPrimaryKey(Integer moneyId){
        SqlSession session = SQLSessionUtil.openSqlSession();
        MoneyMapper moneyMapper = session.getMapper(MoneyMapper.class);
        int rs = moneyMapper.deleteByPrimaryKey(moneyId);
        session.commit(true);
        session.close();
        return rs;
    };

    @Override
    public int insert(Money record){
        return 0;
    };

    @Override
    public int insertSelective(Money record){
        SqlSession session = SQLSessionUtil.openSqlSession();
        MoneyMapper moneyMapper = session.getMapper(MoneyMapper.class);
        int rs = moneyMapper.insertSelective(record);
        session.commit(true);
        session.close();
        return rs;
    };

    @Override
     public Money selectByPrimaryKey(Integer moneyId){
        SqlSession session = SQLSessionUtil.openSqlSession();
        MoneyMapper moneyMapper = session.getMapper(MoneyMapper.class);
        Money rs = moneyMapper.selectByPrimaryKey(moneyId);
        session.commit(true);
        session.close();
        return rs;
    };

    @Override
    public int updateByPrimaryKeySelective(Money record){
        SqlSession session = SQLSessionUtil.openSqlSession();
        MoneyMapper moneyMapper = session.getMapper(MoneyMapper.class);
        int rs = moneyMapper.updateByPrimaryKeySelective(record);
        session.commit(true);
        session.close();
        return rs;
    };

    @Override
    public int updateByPrimaryKey(Money record){
        SqlSession session = SQLSessionUtil.openSqlSession();
        MoneyMapper moneyMapper = session.getMapper(MoneyMapper.class);
        int rs = moneyMapper.updateByPrimaryKey(record);
        session.commit(true);
        session.close();
        return rs;
    };

    @Override
    public List<Money> selectAll(){
        SqlSession session = SQLSessionUtil.openSqlSession();
        MoneyMapper moneyMapper = session.getMapper(MoneyMapper.class);
        List<Money> rs = moneyMapper.selectAll();
        session.commit();
        session.close();
        return rs;
    }

    @Override
    public Integer AddQuantityOfCommodity(Integer moneyId, int update){
        SqlSession session = SQLSessionUtil.openSqlSession();
        MoneyMapper moneyMapper = session.getMapper(MoneyMapper.class);
        int rs = moneyMapper.AddQuantityOfCommodity(moneyId, update);
        session.commit(true);
        session.close();
        return rs;
    };

    @Override
    public Integer MinusQuantityOfCommodity(Integer moneyId, int update){
        SqlSession session = SQLSessionUtil.openSqlSession();
        MoneyMapper moneyMapper = session.getMapper(MoneyMapper.class);
        int rs = moneyMapper.MinusQuantityOfCommodity(moneyId, update);
        session.commit(true);
        session.close();
        return rs;
    }
}
