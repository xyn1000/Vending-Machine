package mapper;

import entity.Card;

public interface CardMapper {
//    int deleteByPrimaryKey(Integer number);
//
//    int insert(Card record);
//
//    int insertSelective(Card record);

    Card selectByPrimaryKey(Integer number);

//    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);
}