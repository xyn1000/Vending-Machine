package test.entity;

import entity.Transaction;
import org.apache.commons.collections.functors.TruePredicate;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

/** 
* Transaction Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 4, 2020</pre> 
* @version 1.0 
*/ 
public class TransactionTest { 


/** 
* 
* Method: getTransactionId() 
* 
*/ 
@Test
public void testGetTransactionId() throws Exception { 
    Transaction transaction = new Transaction();
    transaction.setTransactionId(0);
    int transactionId = transaction.getTransactionId();
    Assert.assertEquals(0,transactionId);
} 

/** 
* 
* Method: setTransactionId(Integer transactionId) 
* 
*/ 
@Test
public void testSetTransactionId() throws Exception {
    Transaction transaction = new Transaction();
    transaction.setTransactionId(0);
    int transactionId = transaction.getTransactionId();
    Assert.assertEquals(0,transactionId);
} 

/** 
* 
* Method: getUserId() 
* 
*/ 
@Test
public void testGetUserId() throws Exception {
    Transaction transaction = new Transaction();
    transaction.setUserId(0);
    int userId = transaction.getUserId();
    Assert.assertEquals(0,userId);
} 

/** 
* 
* Method: setUserId(Integer userId) 
* 
*/ 
@Test
public void testSetUserId() throws Exception {
    Transaction transaction = new Transaction();
    transaction.setUserId(0);
    int userId = transaction.getUserId();
    Assert.assertEquals(0,userId);
} 

/** 
* 
* Method: getPayment() 
* 
*/ 
@Test
public void testGetPayment() throws Exception {
    Transaction transaction = new Transaction();
    transaction.setPayment(Boolean.TRUE);
    Boolean payment = transaction.getPayment();
    Assert.assertEquals(Boolean.TRUE, payment);
} 

/** 
* 
* Method: setPayment(Boolean payment) 
* 
*/ 
@Test
public void testSetPayment() throws Exception {
    Transaction transaction = new Transaction();
    transaction.setPayment(Boolean.TRUE);
    Boolean payment = transaction.getPayment();
    Assert.assertEquals(Boolean.TRUE, payment);
} 

/** 
* 
* Method: getStatus() 
* 
*/ 
@Test
public void testGetStatus() throws Exception {
    Transaction transaction = new Transaction();
    transaction.setStatus(0);
    int status = transaction.getStatus();
    Assert.assertEquals(0, status);
} 

/** 
* 
* Method: setStatus(Boolean status) 
* 
*/ 
@Test
public void testSetStatus() throws Exception {
    Transaction transaction = new Transaction();
    transaction.setStatus(1);
    int status = transaction.getStatus();
    Assert.assertEquals(1, status);
} 

/** 
* 
* Method: getTime() 
* 
*/ 
@Test
public void testGetTime() throws Exception {
    Date ss = new Date();
    Transaction transaction = new Transaction();
    transaction.setTime(ss);
    Assert.assertNotNull(transaction.getTime());
} 

/** 
* 
* Method: setTime(Date time) 
* 
*/ 
@Test
public void testSetTime() throws Exception { 
    Date ss = new Date();
    Transaction transaction = new Transaction();
    transaction.setTime(ss);
    Assert.assertNotNull(transaction.getTime());
} 

/** 
* 
* Method: getPrice() 
* 
*/ 
@Test
public void testGetPrice() throws Exception {
    Transaction transaction = new Transaction();
    transaction.setPrice(1.2);
    Double test = 1.2;
    Double price = transaction.getPrice();
    Assert.assertEquals(test, price);
} 

/** 
* 
* Method: setPrice(Double price) 
* 
*/ 
@Test
public void testSetPrice() throws Exception {
    Transaction transaction = new Transaction();
    transaction.setPrice(1.2);
    Double test = 1.2;
    Double price = transaction.getPrice();
    Assert.assertEquals(test, price);
} 

/** 
* 
* Method: getChange() 
* 
*/ 
@Test
public void testGetChange() throws Exception {
    Transaction transaction = new Transaction();
    transaction.setChange(3.8);
    Double test = 3.8;
    Double change = transaction.getChange();
    Assert.assertEquals(test, change);
} 

/** 
* 
* Method: setChange(Double change) 
* 
*/ 
@Test
public void testSetChange() throws Exception {
    Transaction transaction = new Transaction();
    transaction.setChange(3.8);
    Double test = 3.8;
    Double change = transaction.getChange();
    Assert.assertEquals(test, change);
} 

/** 
* 
* Method: getAmount() 
* 
*/ 
@Test
public void testGetAmount() throws Exception {
    Transaction transaction = new Transaction();
    transaction.setAmount(2.5);
    Double test = 2.5;
    Double amount = transaction.getAmount();
    Assert.assertEquals(test, amount);
} 

/** 
* 
* Method: setAmount(Double amount) 
* 
*/ 
@Test
public void testSetAmount() throws Exception {
    Transaction transaction = new Transaction();
    transaction.setAmount(2.5);
    Double test = 2.5;
    Double amount = transaction.getAmount();
    Assert.assertEquals(test, amount);
} 

/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception {
    Date ss = new Date();
    Transaction transaction = new Transaction();
    transaction.setTransactionId(1);
    transaction.setUserId(10);
    transaction.setPayment(Boolean.TRUE);
    transaction.setStatus(1);
    transaction.setPrice(1.2);
    transaction.setChange(3.8);
    transaction.setAmount(4.0);
    transaction.setTime(ss);
    Assert.assertNotNull(transaction.toString());

} 



} 
