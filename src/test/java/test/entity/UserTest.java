package test.entity;

import entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/** 
* User Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 4, 2020</pre> 
* @version 1.0 
*/ 
public class UserTest { 

/** 
* 
* Method: getUserId() 
* 
*/ 
@Test
public void testGetUserId() throws Exception { 
    User user = new User();
    user.setUserId(0);
    int userId = user.getUserId();
    Assert.assertEquals(0, userId);
} 

/** 
* 
* Method: setUserId(Integer userId) 
* 
*/ 
@Test
public void testSetUserId() throws Exception {
    User user = new User();
    user.setUserId(0);
    int userId = user.getUserId();
    Assert.assertEquals(0, userId);
} 

/** 
* 
* Method: getSalt() 
* 
*/ 
@Test
public void testGetSalt() throws Exception {
    User user = new User();
    user.setSalt("LEFT");
    String salt = user.getSalt();
    Assert.assertEquals("LEFT", salt);
    user.setSalt(null);
    Assert.assertNull(user.getSalt());
} 

/** 
* 
* Method: setSalt(String salt) 
* 
*/ 
@Test
public void testSetSalt() throws Exception {
    User user = new User();
    user.setSalt("LEFT");
    String salt = user.getSalt();
    Assert.assertEquals("LEFT", salt);
    user.setSalt(null);
    Assert.assertNull(user.getSalt());
} 

/** 
* 
* Method: getUsername() 
* 
*/ 
@Test
public void testGetUsername() throws Exception { 
    User user = new User();
    user.setUsername("Ethan");
    String username = user.getUsername();
    Assert.assertEquals("Ethan", username);
    user.setUsername(null);
    Assert.assertNull(user.getUsername());
} 

/** 
* 
* Method: setUsername(String username) 
* 
*/ 
@Test
public void testSetUsername() throws Exception {
    User user = new User();
    user.setUsername("Ethan");
    String username = user.getUsername();
    Assert.assertEquals("Ethan", username);
    user.setUsername(null);
    Assert.assertNull(user.getUsername());
} 

/** 
* 
* Method: getPassword() 
* 
*/ 
@Test
public void testGetPassword() throws Exception {
    User user = new User();
    user.setPassword("123321");
    String password = user.getPassword();
    Assert.assertEquals("123321", password);
    user.setPassword(null);
    Assert.assertNull(user.getPassword());
} 

/** 
* 
* Method: setPassword(String password) 
* 
*/ 
@Test
public void testSetPassword() throws Exception {
    User user = new User();
    user.setPassword("123321");
    String password = user.getPassword();
    Assert.assertEquals("123321", password);
    user.setPassword(null);
    Assert.assertNull(user.getPassword());
} 

/** 
* 
* Method: getCard() 
* 
*/ 
@Test
public void testGetCard() throws Exception {
    User user = new User();
    user.setCard(123321);
    int card = user.getCard();
    Assert.assertEquals(123321, card);
} 

/** 
* 
* Method: setCard(Integer card) 
* 
*/ 
@Test
public void testSetCard() throws Exception { 
    User user = new User();
    user.setCard(123321);
    int card = user.getCard();
    Assert.assertEquals(123321, card);
} 

/** 
* 
* Method: getRole() 
* 
*/ 
@Test
public void testGetRole() throws Exception { 
    User user = new User();
    user.setRole(1);
    int role = user.getRole();
    Assert.assertEquals(1, role);
} 

/** 
* 
* Method: setRole(Integer role) 
* 
*/ 
@Test
public void testSetRole() throws Exception {
    User user = new User();
    user.setRole(1);
    int role = user.getRole();
    Assert.assertEquals(1, role);
} 

/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception { 
    User user = new User();
    user.setRole(1);
    user.setCard(123321);
    user.setSalt("LEFT");
    user.setUserId(123);
    user.setUsername("Ethan");
    user.setUserId(123);
    Assert.assertNotNull(user.toString());

} 




} 
