package test.service; 

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import service.CardService;

/** 
* CardService Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 4, 2020</pre> 
* @version 1.0 
*/ 
public class CardServiceTest {
/** 
* 
* Method: defaultValue() 
* 
*/ 
@Test
public void testDefaultValue() throws Exception {
    CardService cardService = new CardService();
    cardService.defaultValue();
    Assert.assertNotNull(cardService.defaultCards);
} 

/** 
* 
* Method: checkOut(String cardName, Integer cardNumber) 
* 
*/ 
@Test
public void testCheckOut() throws Exception {
    CardService cardService = new CardService();
    Assert.assertNull(cardService.checkOut("Ethan", 123321));
    Assert.assertNotNull(cardService.checkOut("Kasey", 60146));

}

    @Test
    public void testGetCardNameByNumber() throws Exception {
        CardService cardService = new CardService();
        Assert.assertNull(cardService.getCardNameByNumber(111111));
        Assert.assertNotNull(cardService.getCardNameByNumber(60146));

    }

} 
