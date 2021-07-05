package mapper;

import entity.Commodity;
import entity.Transaction;
import org.apache.ibatis.session.SqlSession;
import util.SQLSessionUtil;

import java.util.List;

public class CommodityMapperImpl implements CommodityMapper{
    @Override
    public int deleteByPrimaryKey(Integer commodityId) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        CommodityMapper commodityMapper = session.getMapper(CommodityMapper.class);
        int rs = commodityMapper.deleteByPrimaryKey(commodityId);
        session.commit(true);
        session.close();
        return rs;
    }

    @Override
    public int insert(Commodity record) {
        //please use insertSelective
        return 0;
    }

    @Override
    public int insertSelective(Commodity record) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        CommodityMapper commodityMapper = session.getMapper(CommodityMapper.class);
        int rs = commodityMapper.insertSelective(record);
        session.commit(true);
        session.close();
        return rs;
    }

    @Override
    public List<Commodity> selectAll() {
        SqlSession session = SQLSessionUtil.openSqlSession();
        CommodityMapper commodityMapper = session.getMapper(CommodityMapper.class);
        List<Commodity> rs = commodityMapper.selectAll();
        session.commit();
        session.close();
        return rs;
    }

    @Override
    public Commodity selectByPrimaryKey(Integer commodityId) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        CommodityMapper commodityMapper = session.getMapper(CommodityMapper.class);
        Commodity commodity = commodityMapper.selectByPrimaryKey(commodityId);
        session.commit();
        session.close();
        return commodity;
    }

    @Override
    public int updateByPrimaryKeySelective(Commodity record) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        CommodityMapper commodityMapper = session.getMapper(CommodityMapper.class);
        int rs = commodityMapper.updateByPrimaryKeySelective(record);
        session.commit(true);
        session.close();
        return rs;
    }

    @Override
    public int updateByPrimaryKey(Commodity record) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        CommodityMapper commodityMapper = session.getMapper(CommodityMapper.class);
        int rs = commodityMapper.updateByPrimaryKey(record);
        session.commit(true);
        session.close();
        return rs;
    }

    @Override
    public Integer selectQuantityByCommodity(Integer commodityId){
        SqlSession session = SQLSessionUtil.openSqlSession();
        CommodityMapper commodityMapper = session.getMapper(CommodityMapper.class);
        int quantity = commodityMapper.selectQuantityByCommodity(commodityId);
        session.commit();
        session.close();
        return quantity;
    }

    @Override
    public Integer updateQuantityOfCommodity(Integer commodityId, int update){
        SqlSession session = SQLSessionUtil.openSqlSession();
        CommodityMapper commodityMapper = session.getMapper(CommodityMapper.class);
        int rs = commodityMapper.updateQuantityOfCommodity(commodityId,update);
        session.commit();
        session.close();
        return rs;
    }

    @Override
    public List<Commodity> selectByCategoryID(Integer categoryId) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        CommodityMapper commodityMapper = session.getMapper(CommodityMapper.class);
        List<Commodity> rs = commodityMapper.selectByCategoryID(categoryId);
        session.commit();
        session.close();
        return rs;
    }

}
