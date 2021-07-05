package service;

import entity.Transaction;
import mapper.TransactionMapper;
import mapper.TransactionMapperImpl;

import java.util.List;

public class TransactionService {
    private TransactionMapper transactionMapper;

    public TransactionService() {
        this.transactionMapper=new TransactionMapperImpl();
    }

    public int addTransaction(Transaction record){
        transactionMapper.insertSelective(record);
        return record.getTransactionId();
    }

    public int updateTransaction(Transaction transaction){
        return transactionMapper.updateByPrimaryKeySelective(transaction);
    }

    public int changeAmount(int transaction_id, double amount){
        return transactionMapper.changeAmount(transaction_id, amount);
    }

    public int changeChange(int transaction_id, double change){
        return transactionMapper.changeChange(transaction_id, change);
    }

    public List<Transaction> selectLatestFiveTransaction(int user_id){
        return transactionMapper.selectLatestFiveTransaction(user_id);
    }

    public List<Transaction> getAllTransaction(){
        return transactionMapper.selectAllTransaction();
    }

    public List<Transaction> getAllCancelledTransaction(){
        return transactionMapper.selectAllCancelledTransaction();
    }

}
