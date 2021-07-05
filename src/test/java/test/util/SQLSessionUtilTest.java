package test.util;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import util.SQLSessionUtil;

/** 
* SQLSessionUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>Nov. 4, 2020</pre> 
* @version 1.0 
*/ 
public class SQLSessionUtilTest {

/** 
* 
* Method: openSqlSession() 
* 
*/ 
@Test
public void testOpenSqlSession() throws Exception {
    SQLSessionUtil sql = new SQLSessionUtil();
    Assert.assertNotNull(sql);
}

} 
