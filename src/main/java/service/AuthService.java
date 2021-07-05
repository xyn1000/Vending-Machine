package service;


import entity.User;
import mapper.UserMapper;
import mapper.UserMapperImpl;
import util.MD5Util;

import java.util.List;

/**
 * The service for authentication, e.g. login and register
 *
 * @author <Kelly Huang>
 * @since <pre>Nov. 2, 2020</pre>
 * @version 1.0
 */
public class AuthService {
    private final UserMapper userMapper;

    public AuthService() {
        this.userMapper = new UserMapperImpl();
    }

    public User login(String username, String password) {
        // Finding user with matching username
        User user = userMapper.selectByUserName(username);
        // If matching user not exists, return null
        if (user == null) {
            return null;
        }
        // Generates hashed password
        String salt = user.getSalt();
        String hash = MD5Util.md5(password + salt);

        // Check whether the password is matching or nor
        if (hash.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    public User getAnonymousUser(){
        return userMapper.selectByPrimaryKey(0);
    }

    public int register(User user) {
        return userMapper.insertSelective(user);
    }

    public List<User> getAllUser() {
        return userMapper.selectAll();
    }

    public User getUserById(int id){
        return userMapper.selectByPrimaryKey(id);
    }

    public int updateUser(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public int removeUser(int uid){
        return userMapper.deleteByPrimaryKey(uid);
    }
}
