package test.entity;


import entity.Card;
import entity.EnvironmentConstant;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/** 
* Card Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 4, 2020</pre> 
* @version 1.0 
*/ 
public class CardTest { 
    

/** 
* 
* Method: getNumber() 
* 
*/ 
@Test
public void testGetNumber() throws Exception {
    EnvironmentConstant environmentConstant = new EnvironmentConstant();
    Assert.assertNotNull(environmentConstant);
    Card card = new Card();
    card.setNumber(1);
    int number = card.getNumber();
    Assert.assertEquals(1, number);
}

/** 
* 
* Method: setNumber(Integer number) 
* 
*/ 
@Test
public void testSetNumber() throws Exception {
    Card card = new Card();
    card.setNumber(1);
    int number = card.getNumber();
    Assert.assertEquals(1, number);
} 

/** 
* 
* Method: getName() 
* 
*/ 
@Test
public void testGetName() throws Exception { 
//TODO: Test goes here...
    Card card = new Card();
    card.setName("Ethan");
    String name  = card.getName();
    Assert.assertEquals("Ethan", name);
} 

/** 
* 
* Method: setName(String name) 
* 
*/ 
@Test
public void testSetName() throws Exception { 
//TODO: Test goes here...
    Card card = new Card();
    card.setName("Ethan");
    String name  = card.getName();
    Assert.assertEquals("Ethan", name);
    card.setName(null);
    Assert.assertNull(card.getName());
}

/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception { 
//TODO: Test goes here...
    Card card = new Card();
    card.setName("Ethan");
    card.setNumber(1);
    Assert.assertNotNull(card.toString());
} 


} 
