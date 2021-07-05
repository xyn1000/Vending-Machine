package test.service;


import entity.User;
import org.junit.jupiter.api.Test;
import service.AuthService;
import util.MD5Util;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/** 
* AuthService Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 4, 2020</pre> 
* @version 1.0 
*/ 
public class AuthServiceTest {
/** 
* 
* Method: login(String username, String password) 
* 
*/ 
@Test
public void testLogin() throws Exception { 
    AuthService authService = new AuthService();
    User user = authService.login("Demo", "demo");
    assertNotNull(user);
    assertEquals("Demo",user.getUsername());
    assertNull(authService.login("testerw", "asdadaswffds"));
    assertNull(authService.login("Bruce", "asdadaswffds"));


} 

/** 
* 
* Method: getAnonymousUser() 
* 
*/ 
@Test
public void testGetAnonymousUser() throws Exception { 
    AuthService authService = new AuthService();
    assertNotNull(authService.getAnonymousUser());
} 

/** 
* 
* Method: register(User user) 
* 
*/ 
@Test
public void testRegister() throws Exception {
    AuthService a = new AuthService();
    User u = new User();
    String salt = MD5Util.salt();
    u.setSalt(salt);
    u.setUserId(333);
    u.setUsername("Ethan");
    u.setPassword("123321");
    assertNotNull(a.register(u));
    assertNotNull(a.getUserById(333));
    assertNotNull(a.removeUser(333));
}
/**
 *
 * Method: getAll()
 *
 */
    @Test
    public void testGetAll() throws Exception{
        AuthService a = new AuthService();
        assertNotNull(a.getAllUser());
    }




} 
