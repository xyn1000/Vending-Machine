package test.mapper;

import entity.Card;
import mapper.CardMapperImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** 
* CardMapperImpl Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 4, 2020</pre> 
* @version 1.0 
*/ 
public class CardMapperImplTest {

/** 
* 
* Method: selectByPrimaryKey(Integer number) 
* 
*/ 
@Test
public void testSelectByPrimaryKey() throws Exception { 
    CardMapperImpl cardMapperImpl = new CardMapperImpl();
    Assert.assertNull(cardMapperImpl.selectByPrimaryKey(1));
} 



/** 
* 
* Method: updateByPrimaryKey(Card record) 
* 
*/ 
@Test
public void testUpdateByPrimaryKey() throws Exception {
    CardMapperImpl cardMapperImpl = new CardMapperImpl();
    Card card = new Card();
    Assert.assertNotNull(cardMapperImpl.updateByPrimaryKey(card));

} 


} 
