package test.service; 

import entity.Transaction;
import mapper.TransactionMapperImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import service.TransactionService;

import java.util.Date;

/** 
* TransactionService Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 5, 2020</pre> 
* @version 1.0 
*/ 
public class TransactionServiceTest {
/** 
* 
* Method: addTransaction(Transaction record) 
* 
*/ 
@Test
public void testAddTransaction() throws Exception {
    TransactionMapperImpl transactionMapper = new TransactionMapperImpl();
    TransactionService transactionService = new TransactionService();
    Date ss = new Date();
    Transaction transaction = new Transaction();
    transaction.setUserId(8);
    transaction.setPayment(Boolean.TRUE);
    transaction.setStatus(0);
    transaction.setTime(ss);
    transaction.setPrice(10.5);
    transaction.setTransactionId(22222);
    Assert.assertNotNull(transactionService.addTransaction(transaction));
    transactionMapper.deleteByPrimaryKey(22222);

} 

/** 
* 
* Method: updateTransaction(Transaction transaction) 
* 
*/ 
@Test
public void testUpdateTransaction() throws Exception {
    TransactionMapperImpl transactionMapper = new TransactionMapperImpl();
    TransactionService transactionService = new TransactionService();
    Date ss = new Date();
    Transaction transaction = new Transaction();
    transaction.setUserId(8);
    transaction.setPayment(Boolean.TRUE);
    transaction.setStatus(0);
    transaction.setTime(ss);
    transaction.setPrice(10.5);
    transaction.setTransactionId(111111);
    Assert.assertNotNull(transactionService.updateTransaction(transaction));
    transactionMapper.deleteByPrimaryKey(111111);


}
/**
 *
 * Method: getTransaction()
 *
 */
    @Test
    public void testGetTransaction() throws Exception {
        TransactionService transactionService = new TransactionService();
        Assert.assertNotNull(transactionService.getAllTransaction());
        Assert.assertNotNull(transactionService.getAllCancelledTransaction());
        Assert.assertNotNull(transactionService.selectLatestFiveTransaction(0));
    }

    /**
     *
     * Method: changeChangeAndAmount
     *
     */
    @Test
    public void testChangeChangeAndAmount() throws Exception {
        TransactionService transactionService = new TransactionService();
        TransactionMapperImpl transactionMapper = new TransactionMapperImpl();
        Transaction transaction = new Transaction();
        transaction.setTransactionId(10000);
        transaction.setUserId(100);
        transaction.setPayment(true);
        transaction.setStatus(0);
        Date date = new Date();
        date.getTime();
        transaction.setTime(date);
        transaction.setPrice(10.5);
        transactionMapper.insertSelective(transaction);
        Assert.assertNotNull(transactionService.changeAmount(10000, 4.5));
        Assert.assertNotNull(transactionService.changeChange(10000, 4.5));
        transactionMapper.deleteByPrimaryKey(10000);
    }
} 
