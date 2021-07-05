package test.util; 

import entity.Commodity;
import entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import util.MD5Util;
import util.UserSession;


/** 
* UserSession Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 5, 2020</pre> 
* @version 1.0 
*/ 
public class UserSessionTest {

/** 
* 
* Method: testUserSession()
* 
*/ 
@Test
public void testUserSession() throws Exception {
    UserSession userSession = new UserSession();
    User u = new User();
    String salt = MD5Util.salt();
    u.setSalt(salt);
    u.setUsername("Ethan");
    u.setPassword("123321");
    userSession.setCurrentUser(u);
    Assert.assertNotNull(userSession.getCurrentUser());
    userSession.logout();
    Assert.assertEquals(0, userSession.getShoppingCart().size());
    Assert.assertNull(userSession.getCurrentUser());
    Assert.assertEquals(-1, userSession.getCurrentTransaction());
} 

/** 
* 
* Method: addToCart(Commodity commodity) 
* 
*/ 
@Test
public void testAddToCart() throws Exception {
    Commodity commodity  = new Commodity();
    commodity.setCommodityId(333);
    commodity.setName("Test2");
    commodity.setPrice(1.5);
    commodity.setQuantity(10);
    commodity.setCategory(3);
    UserSession userSession = new UserSession();
    UserSession.addToCart(commodity);
    Assert.assertNotNull(userSession.getCartAmount());
    Assert.assertNotNull(userSession.getShoppingCart());
    userSession.flushCommodities();
    Assert.assertNotNull(userSession.getAllCommodities());
    userSession.setCurrentCommodity(333);
    Assert.assertEquals(333, userSession.getCurrentCommodity());
    userSession.setWelcome(Boolean.TRUE);
    Assert.assertNotNull(userSession.isWelcome());


} 

/** 
* 
* Method: flushCommodities() 
* 
*/ 
@Test
public void testFlushCommodities() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCartAmount() 
* 
*/ 
@Test
public void testGetCartAmount() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getAllCommodities() 
* 
*/ 
@Test
public void testGetAllCommodities() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCurrentUser() 
* 
*/ 
@Test
public void testGetCurrentUser() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getShoppingCart() 
* 
*/ 
@Test
public void testGetShoppingCart() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: logout() 
* 
*/ 
@Test
public void testLogout() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: getCurrentTransaction() 
* 
*/ 
@Test
public void testGetCurrentTransaction() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setCurrentTransaction(int currentTransaction) 
* 
*/ 
@Test
public void testSetCurrentTransaction() throws Exception { 
    UserSession userSession = new UserSession();
    userSession.setCurrentTransaction(1);
    Assert.assertEquals(1, userSession.getCurrentTransaction());
} 


} 
