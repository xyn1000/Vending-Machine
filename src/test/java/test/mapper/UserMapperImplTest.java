package test.mapper;

import entity.User;
import mapper.UserMapper;
import mapper.UserMapperImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import util.MD5Util;

/** 
* UserMapperImpl Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 5, 2020</pre> 
* @version 1.0 
*/ 
public class UserMapperImplTest { 


/** 
* 
* Method: insertSelective(User record) 
* 
*/ 
@Test
public void testInsertSelective() throws Exception {
    User test = new User();
    UserMapperImpl userMapper = new UserMapperImpl();
    test.setUsername("test_select");
    test.setUserId(123321);
    String salt = MD5Util.salt();
    test.setSalt(salt);
    test.setPassword("123456");
    Assert.assertNotNull(userMapper.insertSelective(test));
    userMapper.deleteByPrimaryKey(123321);
}

/** 
* 
* Method: updateByPrimaryKeySelective(User record) 
* 
*/ 
@Test
public void testUpdateByPrimaryKeySelective() throws Exception {
    User test = new User();
    test.setUserId(1111);
    test.setUsername("test_update");
    String salt = MD5Util.salt();
    test.setSalt(salt);
    test.setPassword("123456");
    UserMapperImpl userMapper = new UserMapperImpl();
    Assert.assertEquals(0, userMapper.updateByPrimaryKeySelective(test));
    userMapper.deleteByPrimaryKey(1111);
}

/** 
* 
* Method: deleteByPrimaryKey(Integer userId) 
* 
*/ 
@Test
public void testDeleteByPrimaryKey() throws Exception {
    UserMapperImpl userMapper = new UserMapperImpl();
    Assert.assertEquals(0, userMapper.deleteByPrimaryKey(1));
} 

/** 
* 
* Method: insert(User record) 
* 
*/ 
@Test
public void testInsert() throws Exception {
    UserMapperImpl userMapper = new UserMapperImpl();
    User test = new User();
    test.setUsername("testcase");
    test.setUserId(1111111);
    String salt = MD5Util.salt();
    test.setSalt(salt);
    test.setPassword("123456");
    test.setRole(2);
    Assert.assertNotNull(userMapper.insert(test));
    Assert.assertNotNull(userMapper.deleteByPrimaryKey(1111111));
    userMapper.deleteByPrimaryKey(1111111);

} 

/** 
* 
* Method: selectByPrimaryKey(Integer userId) 
* 
*/ 
@Test
public void testSelectByPrimaryKey() throws Exception {
    UserMapperImpl userMapper = new UserMapperImpl();
    Assert.assertNotNull(userMapper.selectByPrimaryKey(0));
} 

/** 
* 
* Method: updateByPrimaryKey(User record) 
* 
*/ 
@Test
public void testUpdateByPrimaryKey() throws Exception {
    UserMapperImpl userMapper = new UserMapperImpl();
    User user = new User();
    Assert.assertEquals(0, userMapper.updateByPrimaryKey(user));
} 

/** 
* 
* Method: selectByUserName(String username) 
* 
*/ 
@Test
public void testSelectByUserName() throws Exception {
    UserMapperImpl userMapper = new UserMapperImpl();
    Assert.assertNotNull(userMapper.selectByUserName("Demo"));
} 

/** 
* 
* Method: CheckExistUser(String username) 
* 
*/ 
@Test
public void testCheckExistUser() throws Exception {
    UserMapperImpl userMapper = new UserMapperImpl();
    Assert.assertNotNull(userMapper.CheckExistUser("Ethan"));
}

    /**
     *
     * Method: CheckSelectAll()
     *
     */
    @Test
    public void testSelectAll() throws Exception{
    UserMapperImpl userMapper = new UserMapperImpl();
    Assert.assertNotNull(userMapper.selectAll());
}


} 
