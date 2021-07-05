package controller;

import Application.App;
import entity.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import service.*;
import util.AlertUtil;
import util.CSVUtil;
import util.UserSession;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CashierController {

    private final MoneyService moneyService = new MoneyService();
    private final List<MoneyRow> moneyRowList = new ArrayList<>();
    private final AuthService userService = new AuthService();
    private final OrderService orderService = new OrderService();
    private final TransactionService transactionService = new TransactionService();
    private final CommodityService commodityService = new CommodityService();

    public static class MoneyRow{
        private SimpleStringProperty mid;
        private SimpleStringProperty mValue;
        private SimpleStringProperty mQuantity;

        public MoneyRow(String mid, String mValue, String mQuantity){
            this.mid = new SimpleStringProperty(mid);
            this.mValue = new SimpleStringProperty(mValue);
            this.mQuantity = new SimpleStringProperty(mQuantity);
        }

        public String getMid() {
            return mid.get();
        }

        public SimpleStringProperty midProperty() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid.set(mid);
        }

        public String getmValue() {
            return mValue.get();
        }

        public SimpleStringProperty mValueProperty() {
            return mValue;
        }

        public void setmValue(String mValue) {
            this.mValue.set(mValue);
        }

        public String getmQuantity() {
            return mQuantity.get();
        }

        public SimpleStringProperty mQuantityProperty() {
            return mQuantity;
        }

        public void setmQuantity(String mQuantity) {
            this.mQuantity.set(mQuantity);
        }
    }

    @FXML
    private Button submitChangeBtn;

    @FXML
    private TableView<MoneyRow> moneyTable;

    @FXML
    private Label userWelcomeLabel;

    @FXML
    private Label timeInfoLabel;

    @FXML
    private TableColumn<MoneyRow, String> midCol;

    @FXML
    private TableColumn<MoneyRow, String> mvalueCol;

    @FXML
    private TableColumn<MoneyRow, String> mquantityCol;

    @FXML
    public void initialize(){
        userWelcomeLabel.setText("Welcome, "+ UserSession.getCurrentUser().getUsername());
        timeInfoLabel.setText(dateNow());
        setMoneyUI();
    }

    private String dateNow(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(d);
        return dateStr;
    }

    private void setMoneyUI(){
        List<Money> moneyList = moneyService.getAllMoney();
        moneyRowList.clear();
        for (int i = 0; i < moneyList.size(); i++) {
            String mid = String.valueOf(moneyList.get(i).getMoneyId());
            String mValue = String.valueOf(moneyList.get(i).getValue());
            String mQuantity = String.valueOf(moneyList.get(i).getQuantity());
            moneyRowList.add(new MoneyRow(mid, mValue, mQuantity));
        }
        midCol.setCellValueFactory(new PropertyValueFactory<>("mid"));
        mvalueCol.setCellValueFactory(new PropertyValueFactory<>("mValue"));
        mquantityCol.setCellValueFactory(new PropertyValueFactory<>("mQuantity"));
        ObservableList<MoneyRow> moneyUI = FXCollections.observableArrayList(moneyRowList);
        moneyTable.setItems(moneyUI);
        moneyTable.setEditable(true);
        mquantityCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    public void handleChangeSummary(ActionEvent event) {
        List<Money> moneyList = moneyService.getAllMoney();
        List<String> head = new ArrayList<>();
        head.add("Code");
        head.add("Money Value");
        head.add("Quantity Remain");
        List<List<String>> data = new ArrayList<>();
        for (Money money:moneyList) {
            List<String> temp = new ArrayList<>();
            temp.add(String.valueOf(money.getMoneyId()));
            if (money.getValue() < 1){
                temp.add(money.getValue()+"¢");
            } else {
                temp.add(money.getValue()+"$");
            }
            temp.add(String.valueOf(money.getQuantity()));
            data.add(temp);
        }
        File directory = new File("");
        CSVUtil.createCSV(head, data, directory.getAbsolutePath().concat("/build/reports/"), "Change_Summary");
        new AlertUtil("SUCCESS","Summary Created!").show();
    }

    @FXML
    public void checkTransaction(ActionEvent event) throws IOException{
        List<String> head = new ArrayList<>();
        // details:  transaction date and time, item sold, amount of money paid, returned change and payment method.
        head.add("time");
        head.add("items");
        head.add("amount");
        head.add("change");
        head.add("payment method");

        List<List<String>> data = new ArrayList<>();
        List<Transaction> transactions = new TransactionService().getAllTransaction();
        for (Transaction t: transactions){
            List<String> temp = new ArrayList<>();
            temp.add(t.getTime().toString());

            String items = "";
            List<Order> orders = new OrderService().getByTransactionId(t.getTransactionId());
            for (int i = 0; i < orders.size(); i ++){
                Commodity c = new CommodityService().getCommodity(orders.get(i).getCommodityId());
                if (i == orders.size() - 1){
                    items = items.concat(c.getName());
                }else{
                    items = items.concat(c.getName().concat(","));
                }
            }
            temp.add(items);

            if (t.getAmount() == null){
                temp.add("Not pay by Cash");
            }else{
                temp.add(t.getAmount().toString());
            }

            if (t.getChange() == null){
                temp.add("Not pay by Cash");
            }else{
                temp.add(t.getChange().toString());
            }

            String payment = t.getPayment() ? "Card" : "Cash";
            temp.add(payment);
            data.add(temp);
        }

        File directory = new File("");
        CSVUtil.createCSV(head,data,directory.getAbsolutePath().concat("/build/reports/"),"Transaction_Summary");
        new AlertUtil("SUCCESS","Summary Generated!").show();
    }

    @FXML
    public void handleSubmit(ActionEvent event) {
        ObservableList<MoneyRow> rows = moneyTable.getItems();
        for (MoneyRow row:rows) {
            int quantity = -1;
            int mid = -1;
            try{
                quantity = Integer.parseInt(row.getmQuantity());
                mid = Integer.parseInt(row.getMid());
            } catch (NumberFormatException e){
                String moneyName = row.getmValue();
                new AlertUtil("Error","Invalid Input for "+ moneyName + " Money.").showAndWait();
                return;
            }
            Money money = new Money();
            money.setMoneyId(mid);
            money.setQuantity(quantity);
            moneyService.updateByMid(money);
        }
        setMoneyUI();
        new AlertUtil("Success!","Money Quantity Changed!").show();
    }

    @FXML
    public void handleLogOut(ActionEvent event) throws IOException {
        UserSession.logout();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene scene = new Scene(parent);
        App.primaryStage.setScene(scene);
    }

}
