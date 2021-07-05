package test.entity;

import entity.Role;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/** 
* Role Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 4, 2020</pre> 
* @version 1.0 
*/ 
public class RoleTest {


/** 
* 
* Method: getRoleId() 
* 
*/ 
@Test
public void testGetRoleId() throws Exception {
    Role role = new Role();
    role.setRoleId(0);
    int roleId = role.getRoleId();
    Assert.assertEquals(0, roleId);

} 

/** 
* 
* Method: setRoleId(Integer roleId) 
* 
*/ 
@Test
public void testSetRoleId() throws Exception {
    Role role = new Role();
    role.setRoleId(0);
    int roleId = role.getRoleId();
    Assert.assertEquals(0, roleId);
} 

/** 
* 
* Method: getRoleName() 
* 
*/ 
@Test
public void testGetRoleName() throws Exception {
    Role role = new Role();
    role.setRoleName("User");
    String roleName = role.getRoleName();
    Assert.assertEquals("User", roleName);
} 

/** 
* 
* Method: setRoleName(String roleName) 
* 
*/ 
@Test
public void testSetRoleName() throws Exception {
    Role role = new Role();
    role.setRoleName("User");
    String roleName = role.getRoleName();
    Assert.assertEquals("User", roleName);
    role.setRoleName(null);
    Assert.assertNull(role.getRoleName());
} 

/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception {
    Role role = new Role();
    role.setRoleId(0);
    role.setRoleName("User");
    Assert.assertNotNull(role.toString());
} 




} 
