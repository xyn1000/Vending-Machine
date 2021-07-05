package controller;

import Application.App;
import entity.Commodity;
import entity.EnvironmentConstant;
import entity.Order;
import entity.Transaction;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import service.CommodityService;
import service.MoneyService;
import service.OrderService;
import service.TransactionService;
import util.AlertUtil;
import util.UserSession;

import java.io.IOException;
import java.util.Map;


public class CashPaymentController {

    @FXML
    private TextField cent5Input;

    @FXML
    private TextField cent10Input;

    @FXML
    private TextField cent20Input;

    @FXML
    private TextField cent50Input;

    @FXML
    private TextField dollar1Input;

    @FXML
    private TextField dollar2Input;

    @FXML
    private TextField dollar5Input;

    @FXML
    private TextField dollar10Input;

    @FXML
    private TextField dollar20Input;

    @FXML
    private Label countDownLabel;

    private Timeline animation;
    private int countDown = 120;
    private final CommodityService commodityService = new CommodityService();
    private final TransactionService transactionService = new TransactionService();
    private final OrderService orderService = new OrderService();
    private int cTime = 120;

    @FXML
    private void handleCashPaid(ActionEvent actionEvent) throws IOException{
        MoneyService moneyService = new MoneyService();
        int cent5;
        int cent10;
        int cent20;
        int cent50;
        int dollar1;
        int dollar2;
        int dollar5;
        int dollar10;
        int dollar20;
        try{
            cent5 = Integer.parseInt(cent5Input.getText());
            cent10 = Integer.parseInt(cent10Input.getText());
            cent20 = Integer.parseInt(cent20Input.getText());
            cent50 = Integer.parseInt(cent50Input.getText());
            dollar1 = Integer.parseInt(dollar1Input.getText());
            dollar2 = Integer.parseInt(dollar2Input.getText());
            dollar5 = Integer.parseInt(dollar5Input.getText());
            dollar10 = Integer.parseInt(dollar10Input.getText());
            dollar20 = Integer.parseInt(dollar20Input.getText());
        } catch (NumberFormatException e) {
            new AlertUtil("ERROR","Please check input format!").showAndWait();
            return;
        }
            int quantity_cent5 = moneyService.getQuantity(1);
            moneyService.addQuantity(1, cent5);

            int quantity_cent10 = moneyService.getQuantity(2);
            moneyService.addQuantity(2, cent10);

            int quantity_cent20 = moneyService.getQuantity(3);
            moneyService.addQuantity(3, cent20);

            int quantity_cent50 = moneyService.getQuantity(4);
            moneyService.addQuantity(4, cent50);

            int quantity_dollar1 = moneyService.getQuantity(5);
            moneyService.addQuantity(5, dollar1);

            int quantity_dollar2 = moneyService.getQuantity(6);
            moneyService.addQuantity(6, dollar2);

            int quantity_dollar5 = moneyService.getQuantity(7);
            moneyService.addQuantity(7, dollar5);

            int quantity_dollar10 = moneyService.getQuantity(6);
            moneyService.addQuantity(8, dollar10);

            int quantity_dollar20 = moneyService.getQuantity(6);
            moneyService.addQuantity(9, dollar20);

        double amount = UserSession.getCartAmount();
        double payment = 0.05*cent5 + 0.1*cent10 + 0.2*cent20 + 0.5*cent50 + 1*dollar1 + 2*dollar2 + 5*dollar5 + 10*dollar10 + 20*dollar20;
        double[] value={20,10,5,2,1,0.5,0.2,0.1,0.05};
        int[] number = {quantity_dollar20, quantity_dollar10, quantity_dollar5, quantity_dollar2, quantity_dollar1, quantity_cent50, quantity_cent20, quantity_dollar10, quantity_dollar5};
        int[] count=new int[value.length];
        StringBuilder s= new StringBuilder();

        if (Double.parseDouble(String.format("%.3f",amount)) > Double.parseDouble(String.format("%.3f",payment))){
            new AlertUtil("Cash Change Failed", "Sorry you dont have enouth money").show();

        }
        else{
            double money=Double.parseDouble(String.format("%.3f",payment))-Double.parseDouble(String.format("%.3f",amount));
            double moneyChange=Double.parseDouble(String.format("%.3f",payment))-Double.parseDouble(String.format("%.3f",amount));
            for(int i=0;i<value.length;i++){
            while(Double.parseDouble(String.format("%.3f",money))>=value[i]){
                if (number[i]>0){
                    money-=value[i];
                    count[i]++;
                    number[i]--;
                }
                else{
                    break;
                }
            }
            if(count[i]!=0){
                s.append("number of notes:").append(count[i]).append(", note value:").append(value[i]).append("\n");
                moneyService.minusQuantity(9-i, count[i]);
            }
            }


            if (Double.parseDouble(String.format("%.3f",money))!=0){
                UserSession.getShoppingCart().clear();
                Transaction temp = new Transaction();
                temp.setTransactionId(UserSession.getCurrentTransaction());
                temp.setStatus(EnvironmentConstant.TRANSACTION_CHANGE_NOT_AVAILABLE);
                transactionService.updateTransaction(temp);
                UserSession.completeTransaction();
                new AlertUtil("Cash Change Failed", "Sorry there is not enough change for you").show();
            }
            else{

                Map<Integer,Commodity> map = UserSession.getShoppingCart();
                for (Integer ids:map.keySet()) {
                    commodityService.updateSoldNumber(ids, map.get(ids).getQuantity());
                }
                UserSession.getShoppingCart().clear();
                Transaction temp = new Transaction();
                temp.setTransactionId(UserSession.getCurrentTransaction());
                temp.setStatus(EnvironmentConstant.TRANSACTION_SUCCESS);
                temp.setChange(moneyChange);
                temp.setAmount(payment);
                transactionService.updateTransaction(temp);
                UserSession.completeTransaction();

                for (Integer id:map.keySet()) {
                    Order order = new Order();
                    order.setCommodityId(id);
                    order.setTransactionId(UserSession.getCurrentTransaction());
                    order.setQuantity(map.get(id).getQuantity());
                    orderService.addOrder(order);
                }

            new AlertUtil("Payment Success! here is your change:", s.toString()).showAndWait();
                animation.stop();
                App.secondaryStage.close();
                Parent parent = FXMLLoader.load(getClass().getResource("/view/CustomerSystem.fxml"));
                Scene scene = new Scene(parent);
                App.primaryStage.setScene(scene);
            }
    }}

    @FXML
    public void initialize(){
        animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> updateCountDown()));
        animation.setCycleCount(cTime+1);
        animation.setOnFinished(e -> timeOver());
        animation.play();
    }



    @FXML
    private void handleCancelPayment(ActionEvent actionEvent) throws IOException{
        Transaction temp = new Transaction();
        temp.setTransactionId(UserSession.getCurrentTransaction());
        temp.setStatus(EnvironmentConstant.TRANSACTION_CANCEL);
        transactionService.updateTransaction(temp);
        UserSession.completeTransaction();
        animation.stop();
        new AlertUtil("Payment Failed!", "Transaction Cancelled by user.").showAndWait();
        App.secondaryStage.close();
    }

    private void timeOver(){
        if (UserSession.getCurrentTransaction() == -1) {
            return;
        }

        UserSession.getShoppingCart().clear();
        Transaction temp = new Transaction();
        temp.setTransactionId(UserSession.getCurrentTransaction());
        temp.setStatus(EnvironmentConstant.TRANSACTION_OVERTIME);
        transactionService.updateTransaction(temp);
        UserSession.completeTransaction();
        new AlertUtil("Payment Failed!", "Time Over!").show();
        App.secondaryStage.close();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/CustomerSystem.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(parent);
        App.primaryStage.setScene(scene);
    }

    @FXML
    public void updateCountDown() {
        countDown--;
        countDownLabel.setText(String.valueOf(countDown));
        cTime--;
    }

}
