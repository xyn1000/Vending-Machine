package test.mapper;

import entity.Transaction;
import mapper.TransactionMapperImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

/** 
* TransactionMapperImpl Tester. 
* 
* @author <Yichen Zhang>
* @since <pre>Nov. 5, 2020</pre> 
* @version 1.0 
*/ 
public class TransactionMapperImplTest { 


/** 
* 
* Method: deleteByPrimaryKey(Integer transactionId) 
* 
*/ 
@Test
public void testDeleteByPrimaryKey() throws Exception { 
    TransactionMapperImpl transactionMapper = new TransactionMapperImpl();
    Assert.assertEquals(0, transactionMapper.deleteByPrimaryKey(1));
} 

/** 
* 
* Method: insert(Transaction record) 
* 
*/ 
@Test
public void testInsert() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: insertSelective(Transaction record) 
* 
*/ 
@Test
public void testInsertSelective() throws Exception {
    TransactionMapperImpl transactionMapper = new TransactionMapperImpl();
    Date ss = new Date();
    Transaction transaction = new Transaction();
    transaction.setUserId(8);
    transaction.setPayment(Boolean.TRUE);
    transaction.setStatus(0);
    transaction.setTime(ss);
    transaction.setPrice(10.5);
    transaction.setTransactionId(111111);
    Assert.assertNotNull(transactionMapper.insertSelective(transaction));
    Assert.assertEquals(0, transactionMapper.insert(transaction));
    transactionMapper.deleteByPrimaryKey(111111);

} 

/** 
* 
* Method: selectByPrimaryKey(Integer transactionId) 
* 
*/ 
@Test
public void testSelectByPrimaryKey() throws Exception {
    TransactionMapperImpl transactionMapper = new TransactionMapperImpl();
    Assert.assertNull(transactionMapper.selectByPrimaryKey(10));
} 

/** 
* 
* Method: updateByPrimaryKeySelective(Transaction record) 
* 
*/ 
@Test
public void testUpdateByPrimaryKeySelective() throws Exception {
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
    Assert.assertNotNull(transactionMapper.updateByPrimaryKeySelective(transaction));
    transactionMapper.deleteByPrimaryKey(10000);
}

/** 
* 
* Method: updateByPrimaryKey(Transaction record) 
* 
*/ 
@Test
public void testUpdateByPrimaryKey() throws Exception {
    TransactionMapperImpl transactionMapper = new TransactionMapperImpl();
    Transaction transaction = new Transaction();
    Assert.assertEquals(0, transactionMapper.updateByPrimaryKey(transaction));
}
/**
 *
 * Method: selectTransaction()
 *
 */
    @Test
    public void testSelectAllCanceledTransaction() throws Exception {
        TransactionMapperImpl transactionMapper = new TransactionMapperImpl();
        Assert.assertNotNull(transactionMapper.selectAllCancelledTransaction());
        Assert.assertNotNull(transactionMapper.selectAllTransaction());
        Assert.assertNotNull(transactionMapper.selectLatestFiveTransaction(0));
    }

    /**
     *
     * Method: testChangeAmountAndChange(Integer Transaction_id, double update)
     *
     */
    @Test
    public void testChangeAmountAndChange() throws Exception {
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
        Assert.assertNotNull(transactionMapper.changeAmount(10000, 4.5));
        Assert.assertNotNull(transactionMapper.changeChange(10000, 4.5));
        transactionMapper.deleteByPrimaryKey(10000);
    }
} 
