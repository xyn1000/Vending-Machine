package mapper;

import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.SQLSessionUtil;

import java.util.List;

/**
 * The implementation for UserMapper
 *
 * @author <Kelly Huang>
 * @since <pre>Nov. 2, 2020</pre>
 * @version 1.0
 */
public class UserMapperImpl implements UserMapper{

    @Override
    public int insertSelective(User record){
        SqlSession session = SQLSessionUtil.openSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int rs = userMapper.insertSelective(record);
        session.commit();
        session.close();
        return rs;
    };

    @Override
    public int updateByPrimaryKeySelective(User record){
        SqlSession session = SQLSessionUtil.openSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int rs = userMapper.updateByPrimaryKeySelective(record);
        session.commit(true);
        session.close();
        return rs;
    };

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int rs = userMapper.deleteByPrimaryKey(userId);
        session.commit(true);
        session.close();
        return rs;
    }

    @Override
    public int insert(User record) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int rs = userMapper.insert(record);
        session.commit(true);
        session.close();
        return rs;

    }

    @Override
    public User selectByPrimaryKey(Integer userId) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(userId);
        session.commit();
        session.close();
        return user;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }

    @Override
    public User selectByUserName(String username) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        return userMapper.selectByUserName(username);
    }

    @Override
    public int CheckExistUser(String username) {
        SqlSession session = SQLSessionUtil.openSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        return userMapper.CheckExistUser(username);
    }

    @Override
    public List<User> selectAll() {
        SqlSession session = SQLSessionUtil.openSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> userList = userMapper.selectAll();
        session.commit();
        session.close();
        return userList;
    }
}
