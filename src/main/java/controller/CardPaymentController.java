package controller;

import Application.App;
import entity.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.util.Duration;
import service.AuthService;
import service.CardService;
import service.CommodityService;
import service.TransactionService;
import util.AlertUtil;
import util.UserSession;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;


public class CardPaymentController {

    private final CardService cardService = new CardService();
    private final CommodityService commodityService = new CommodityService();
    private final TransactionService transactionService = new TransactionService();
    private final AuthService authService = new AuthService();
    private int cTime = 120;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button confirmBtn;

    @FXML
    private TextField cardNameFlD;

    @FXML
    private PasswordField cardNumberFlD;

    @FXML
    private Label countDownLabel;

    private Timeline animation;
    private int countDown = 120;

    @FXML
    public void handleCardPayment(ActionEvent event) throws IOException{

        String cardName = cardNameFlD.getText();
        String cardNumber = cardNumberFlD.getText();
        Integer num;
        try{
            num = Integer.parseInt(cardNumber);
        } catch (NumberFormatException e) {
            new AlertUtil("Wrong Input Format", "Please check your input format").showAndWait();
            return;
        }

        Card card = cardService.checkOut(cardName, num);

        if (card == null){
            new AlertUtil("Card Authentication Failed", "Incorrect CardName or CardNumber.").show();
            cardNumberFlD.clear();
            cardNumberFlD.clear();
        } else {
            Map<Integer,Commodity> map = UserSession.getShoppingCart();
            for (Integer ids:map.keySet()) {
                commodityService.updateSoldNumber(ids, map.get(ids).getQuantity());
            }
            UserSession.getShoppingCart().clear();
            Transaction temp = new Transaction();
            temp.setTransactionId(UserSession.getCurrentTransaction());
            temp.setStatus(EnvironmentConstant.TRANSACTION_SUCCESS);
            transactionService.updateTransaction(temp);
            UserSession.completeTransaction();
            User user = new User();
            user.setUserId(UserSession.getCurrentUser().getUserId());
            user.setCard(num);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Would you like us to store your card details for future purchase?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES){
                authService.updateUser(user);
                UserSession.getCurrentUser().setCard(num);
            }
            animation.stop();
            new AlertUtil("Payment Success!", "Thank you for shopping with us!").showAndWait();
            App.secondaryStage.close();
            Parent parent = FXMLLoader.load(getClass().getResource("/view/CustomerSystem.fxml"));
            Scene scene = new Scene(parent);
            App.primaryStage.setScene(scene);
        }
    }

    @FXML
    public void handleCancelPayment(ActionEvent event) {
        Transaction temp = new Transaction();
        temp.setTransactionId(UserSession.getCurrentTransaction());
        temp.setStatus(EnvironmentConstant.TRANSACTION_CANCEL);
        transactionService.updateTransaction(temp);
        UserSession.completeTransaction();
        animation.stop();
        new AlertUtil("Payment Failed!", "Transaction Cancelled by user.").showAndWait();
        App.secondaryStage.close();
    }

    @FXML
    public void initialize(){
        if (UserSession.getCurrentUser().getCard()!=null) {
            Integer cardNo = UserSession.getCurrentUser().getCard();
            String name = cardService.getCardNameByNumber(cardNo);
            cardNameFlD.setText(name);
            cardNumberFlD.setText(String.valueOf(cardNo));
        }
        animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> updateCountDown()));
        animation.setCycleCount(cTime+1);
        animation.setOnFinished(e -> timeOver());
        animation.play();
    }

    private void timeOver() {
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


