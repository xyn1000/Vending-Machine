package mapper;

import entity.CommodityRecord;

public interface CommodityRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommodityRecord record);

    int insertSelective(CommodityRecord record);

    CommodityRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommodityRecord record);

    int updateByPrimaryKey(CommodityRecord record);
}