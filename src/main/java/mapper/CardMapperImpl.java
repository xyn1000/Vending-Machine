package mapper;

import entity.Card;
import util.SQLSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class CardMapperImpl implements CardMapper{

//    @Override
//    public int deleteByPrimaryKey(Integer number) {
//        return 0;
//    }


//    @Override
//    public int insert(Card record) {
//        SqlSession session = SQLSessionUtil.openSqlSession();
//        CardMapper CardMapper = session.getMapper(CardMapper.class);
//        int rs = CardMapper.insert(record);
//        session.commit();
//        session.close();
//        return rs;
//    }

//    @Override
//    public int insertSelective(Card record){
//        SqlSession session = SQLSessionUtil.openSqlSession();
//        CardMapper CardMapper = session.getMapper(CardMapper.class);
//        int rs = CardMapper.insert(record);
//        session.commit();
//        session.close();
//        return rs;
//    }

    @Override
    public Card selectByPrimaryKey(Integer number){
        SqlSession session = SQLSessionUtil.openSqlSession();
        CardMapper cardMapper = session.getMapper(CardMapper.class);
        Card card = cardMapper.selectByPrimaryKey(number);
        session.commit();
        session.close();
        return card;
    }

//    @Override
//    public int updateByPrimaryKeySelective(Card record){
//        SqlSession session = SQLSessionUtil.openSqlSession();
//        CardMapper cardMapper = session.getMapper(CardMapper.class);
//        int rs = cardMapper.updateByPrimaryKeySelective(record);
//        session.commit(true);
//        session.close();
//        return rs;
//    }

    @Override
    public int updateByPrimaryKey(Card record){
        SqlSession session = SQLSessionUtil.openSqlSession();
        CardMapper cardMapper = session.getMapper(CardMapper.class);
        int rs = cardMapper.updateByPrimaryKey(record);
        session.commit(true);
        session.close();
        return rs;
    }

}
