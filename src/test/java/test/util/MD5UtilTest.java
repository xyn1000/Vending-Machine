package test.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static util.MD5Util.md5;
import static util.MD5Util.salt;

import util.MD5Util;

/** 
* MD5Util Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 4, 2020</pre> 
* @version 1.0 
*/ 
public class MD5UtilTest { 


/** 
* 
* Method: md5(String s) 
* 
*/ 
@Test
public void testMd5() throws Exception { 

    String text = "test";
    String md5 = md5(text);
    Assert.assertEquals(md5.toUpperCase(), "098f6bcd4621d373cade4e832627b4f6".toUpperCase());
    MD5Util md52 = new MD5Util();
    Assert.assertEquals(md52.getClass(), md52.getClass());
} 

/** 
* 
* Method: salt() 
* 
*/ 
@Test
public void testSalt() throws Exception {

        Assert.assertNotNull(salt());

} 


/** 
* 
* Method: toHex(byte[] bytes) 
* 
*/ 
@Test
public void testToHex() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = MD5Util.getClass().getMethod("toHex", byte[].class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
