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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import mapper.UserMapper;
import mapper.UserMapperImpl;
import service.*;
import util.AlertUtil;
import util.CSVUtil;
import util.MD5Util;
import util.UserSession;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class OwnerController {

    private final UserMapper userMapper = new UserMapperImpl();
    private final Map<Integer, String> roleMap = new HashMap<>();
    private final AuthService userService = new AuthService();
    List<UserRow> userRows = new ArrayList<>();
    private final AuthService authService = new AuthService();

    private final Map<String, Integer> categoryMap = new HashMap<>();
    private final List<String> categoryList = new ArrayList<>();
    private final List<SellerController.CommodityView> commodityViewList = new ArrayList<>();
    private final CommodityService commodityService = new CommodityService();

    private final MoneyService moneyService = new MoneyService();
    private final List<CashierController.MoneyRow> moneyRowList = new ArrayList<>();
    private final OrderService orderService = new OrderService();
    private final TransactionService transactionService = new TransactionService();

    @FXML
    public void handleAddUser(ActionEvent event){
        String userName = userNameFld.getText();
        String password = passwordFld.getText();
        String confirm = confirmFld.getText();
        if (!password.equals(confirm)) {
            // Situation of passwords not matching
            AlertUtil alert = new AlertUtil("Alert", "Passwords do not match!");
            alert.show();
            passwordFld.clear();
            confirmFld.clear();

        } else if (userName.isEmpty() || password.isEmpty()) {
            // Situation of not input proper password or username
            AlertUtil alert = new AlertUtil("Error", "Please fill in the form!");
            alert.show();
            passwordFld.clear();
            confirmFld.clear();

        } else if (userMapper.CheckExistUser(userName) != 0) {
            // Situation of a username already exists in system

            AlertUtil alert = new AlertUtil("Error", "Username already exists, please change another username!");
            alert.show();
            passwordFld.clear();
            confirmFld.clear();
        }
        else{
            String salt = MD5Util.salt();
            String hash = MD5Util.md5(password + salt);
            User user = new User();
            user.setUsername(userName);
            user.setSalt(salt);
            user.setPassword(hash);
            String role = roleChoiceBox.getValue();

            if ("CASHIER".equals(role)){
                user.setRole(EnvironmentConstant.ROLE_CASHIER);
            }
            else if ("OWNER".equals(role)){
                user.setRole(EnvironmentConstant.ROLE_OWNER);
            }
            else if ("SELLER".equals(role)){
                user.setRole(EnvironmentConstant.ROLE_SELLER);
            }
            else if("CUSTOMER".equals(role)){
                user.setRole(EnvironmentConstant.ROLE_CUSTOMER);
            }
            int rs = authService.register(user);
            if (rs == 1) {
                new AlertUtil("SUCCESS", "New User Add!").show();
            }
            else{
                new AlertUtil("Error", "New User Add Failed!");
            }
        }
        setUserUI();
    }

    @FXML
    private Label userWelcomeLabel;

    @FXML
    private Label timeInfoLabel;

    @FXML
    private TableView<UserRow> userTable;

    @FXML
    private TableColumn<UserRow, String> uidCol;

    @FXML
    private TableColumn<UserRow, String> userNameCol;

    @FXML
    private TableColumn<UserRow, String> roleCol;

    @FXML
    private TableColumn<UserRow, Void> deleteUserCol;

    @FXML
    private ChoiceBox<String> roleChoiceBox;

    @FXML
    private Button submitChangeBtn;

    @FXML
    private TableView<CashierController.MoneyRow> moneyTable;

    @FXML
    private TableColumn<CashierController.MoneyRow, String> midCol;

    @FXML
    private TableColumn<CashierController.MoneyRow, String> mvalueCol;

    @FXML
    private TableColumn<CashierController.MoneyRow, String> mquantityCol;

    @FXML
    private Button logOutBtn;

    @FXML
    private TableView<SellerController.CommodityView> commodityTable;

    @FXML
    private TableColumn<SellerController.CommodityView, String> barcodeCol;

    @FXML
    private TableColumn<SellerController.CommodityView, String> nameCol;

    @FXML
    private TableColumn<SellerController.CommodityView, String> priceCol;

    @FXML
    private TableColumn<SellerController.CommodityView,String> quantityCol;

    @FXML
    private TableColumn<SellerController.CommodityView, String> categoryCol;

    @FXML
    private TableColumn<SellerController.CommodityView, Void> deleteCol;

    @FXML
    private TextField userNameFld;

    @FXML
    private PasswordField passwordFld;

    @FXML
    private PasswordField confirmFld;

    @FXML
    private TableColumn<SellerController.CommodityView, Void> modifyCol;

    @FXML
    public void initialize() {
        userWelcomeLabel.setText("Welcome, "+ UserSession.getCurrentUser().getUsername());
        timeInfoLabel.setText(dateNow());
        initializeCategoryMap();
        initializeSellerTable();
        initializeRole();
        setUserUI();
        setMoneyUI();
    }

    private void initializeCategoryMap(){
        categoryMap.clear();
        categoryList.clear();
        categoryMap.put( "DRINKS",3);
        categoryList.add("DRINKS");
        categoryList.add("CHOCOLATES");
        categoryList.add("LOLLIES");
        categoryList.add("CHIPS");
        categoryMap.put("CHOCOLATES",4);
        categoryMap.put("LOLLIES",5);
        categoryMap.put("CHIPS",6);
    }

    private void initializeSellerTable(){
        commodityViewList.clear();
        List<Commodity> commodityList = commodityService.getAllCommodities();
        for (Commodity commodity : commodityList){
            String barCode = String.valueOf(commodity.getCommodityId());
            String name = commodity.getName();
            String price = String.valueOf(commodity.getPrice());
            String category = String.valueOf(commodity.getCategory());
            String quantity = String.valueOf(commodity.getQuantity());
            SellerController.CommodityView temp = new SellerController.CommodityView(barCode, name, price, category, quantity);
            commodityViewList.add(temp);
        }
        barcodeCol.setCellValueFactory(new PropertyValueFactory<>("barCode"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        Callback<TableColumn<SellerController.CommodityView, Void>, TableCell<SellerController.CommodityView, Void>> cellFactory = new Callback<TableColumn<SellerController.CommodityView, Void>, TableCell<SellerController.CommodityView, Void>>() {
            @Override
            public TableCell<SellerController.CommodityView, Void> call(final TableColumn<SellerController.CommodityView, Void> param) {
                final TableCell<SellerController.CommodityView, Void> cell = new TableCell<SellerController.CommodityView, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            SellerController.CommodityView CartRow = getTableView().getItems().get(getIndex());
                            int barcode = Integer.parseInt(CartRow.getBarCode());
                            commodityService.deleteCommodity(barcode);
                            getTableView().getItems().remove(CartRow);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        deleteCol.setCellFactory(cellFactory);
        Callback<TableColumn<SellerController.CommodityView, Void>, TableCell<SellerController.CommodityView, Void>> newCellFactory = new Callback<TableColumn<SellerController.CommodityView, Void>, TableCell<SellerController.CommodityView, Void>>() {
            @Override
            public TableCell<SellerController.CommodityView, Void> call(final TableColumn<SellerController.CommodityView, Void> param) {
                final TableCell<SellerController.CommodityView, Void> cell = new TableCell<SellerController.CommodityView, Void>() {

                    private final Button btn = new Button("Modify");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            SellerController.CommodityView CartRow = getTableView().getItems().get(getIndex());
                            int barcode = Integer.parseInt(CartRow.getBarCode());
                            UserSession.setCurrentCommodity(barcode);
                            Parent parent = null;
                            try {
                                parent = FXMLLoader.load(getClass().getResource("/view/ChangeCommodity.fxml"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Scene scene = new Scene(parent);
                            App.secondaryStage.setScene(scene);
                            App.secondaryStage.setTitle("Modify Commodity Details");
                            App.secondaryStage.show();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        modifyCol.setCellFactory(newCellFactory);

        ObservableList<SellerController.CommodityView> commodityViews = FXCollections.observableArrayList(commodityViewList);
        commodityTable.setItems(commodityViews);
    }

    private void setMoneyUI(){
        List<Money> moneyList = moneyService.getAllMoney();
        moneyRowList.clear();
        for (int i = 0; i < moneyList.size(); i++) {
            String mid = String.valueOf(moneyList.get(i).getMoneyId());
            String mValue = String.valueOf(moneyList.get(i).getValue());
            String mQuantity = String.valueOf(moneyList.get(i).getQuantity());
            moneyRowList.add(new CashierController.MoneyRow(mid, mValue, mQuantity));
        }
        midCol.setCellValueFactory(new PropertyValueFactory<>("mid"));
        mvalueCol.setCellValueFactory(new PropertyValueFactory<>("mValue"));
        mquantityCol.setCellValueFactory(new PropertyValueFactory<>("mQuantity"));
        ObservableList<CashierController.MoneyRow> moneyUI = FXCollections.observableArrayList(moneyRowList);
        moneyTable.setItems(moneyUI);
        moneyTable.setEditable(true);
        mquantityCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    public void handleSubmit(ActionEvent event) {
        ObservableList<CashierController.MoneyRow> rows = moneyTable.getItems();
        for (CashierController.MoneyRow row:rows) {
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

    private String dateNow(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(d);
        return dateStr;
    }

    private void initializeRole(){
        this.roleMap.put(1,"OWNER");
        this.roleMap.put(2,"SELLER");
        this.roleMap.put(3,"CUSTOMER");
        this.roleMap.put(4,"CASHIER");
        List<String> roleList = new ArrayList<>(roleMap.values());
        ObservableList<String> roleUI = FXCollections.observableArrayList(roleList);
        roleChoiceBox.setItems(roleUI);
    }

    private void setUserUI(){
        List<User> userList = userService.getAllUser();
        userRows.clear();
        for (int i = 0; i < userList.size(); i++) {
            String uid = String.valueOf(userList.get(i).getUserId());
            String userName = String.valueOf(userList.get(i).getUsername());
            int roleInt = userList.get(i).getRole();
            String role = roleMap.get(roleInt);
            userRows.add(new UserRow(uid, userName, role));
        }
        uidCol.setCellValueFactory(new PropertyValueFactory<>("uid"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        Callback<TableColumn<UserRow, Void>, TableCell<UserRow, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<UserRow, Void> call(final TableColumn<UserRow, Void> param) {
                final TableCell<UserRow, Void> cell = new TableCell<UserRow, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            UserRow userRow = getTableView().getItems().get(getIndex());
                            int userId = Integer.parseInt(userRow.getUid());
                            userService.removeUser(userId);
                            getTableView().getItems().remove(userRow);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        deleteUserCol.setCellFactory(cellFactory);
        ObservableList<UserRow> userUI = FXCollections.observableArrayList(userRows);
        userTable.setItems(userUI);
    }

    @FXML
    public void handleCancelledTransaction(ActionEvent event){
        List<Transaction> transactionList = transactionService.getAllCancelledTransaction();
        List<String> head = new ArrayList<>();
        head.add("Transaction ID");
        head.add("User Name");
        head.add("Payment Method");
        head.add("Transaction Status");
        head.add("Transaction Time");
        head.add("Price");
        head.add("Change");
        head.add("Customer Payment");
        head.add("Commodity Details");
        List<List<String>> data = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            List<String> temp = new ArrayList<>();
            temp.add(String.valueOf(transaction.getTransactionId()));
            temp.add(userService.getUserById(transaction.getUserId()).getUsername());
            temp.add(transaction.getPayment()== EnvironmentConstant.CARD_PAYMENT ? "CARD":"CASH");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(transaction.getTime());
            temp.add(strDate);
            temp.add(transaction.getPrice() + "$");
            temp.add(transaction.getChange()==null ? "NULL":transaction.getChange()+"");
            temp.add(transaction.getAmount()==null ? "NULL":transaction.getAmount()+"");
            List<Order> orders = orderService.getByTransactionId(transaction.getTransactionId());
            StringBuilder sb = new StringBuilder();
            for (Order order: orders){
                sb.append("(");
                int commodityId = order.getCommodityId();
                sb.append(commodityService.getCommodity(commodityId).getName()).append(", ");
                sb.append(order.getQuantity()).append("), ");
            }
            temp.add(sb.toString());
            data.add(temp);
        }
        File directory = new File("");
        CSVUtil.createCSV(head, data, directory.getAbsolutePath().concat("/build/reports/"), "Cancelled_Transaction_Summary");
        new AlertUtil("SUCCESS","Summary Created!").show();
    }

    @FXML
    public void handleUserList(ActionEvent event){
        List<User> userList = userService.getAllUser();
        List<String> head = new ArrayList<>();
        head.add("User ID");
        head.add("User Name");
        head.add("Role");
        List<List<String>> data = new ArrayList<>();
        for (User user: userList) {
            List<String> temp = new ArrayList<>();
            temp.add(user.getUserId()+"");
            temp.add(user.getUsername());
            int role = user.getRole();
            temp.add(roleMap.get(role));
            data.add(temp);
        }
        File directory = new File("");
        CSVUtil.createCSV(head, data, directory.getAbsolutePath().concat("/build/reports/"), "User_List");
        new AlertUtil("SUCCESS","Summary Created!").show();
    }

    @FXML
    public void handleLogOut(ActionEvent event) throws IOException {
        UserSession.logout();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene scene = new Scene(parent);
        App.primaryStage.setScene(scene);
    }

    @FXML
    public void handleItemSummary(ActionEvent event) throws IOException {
        List<String> head = new ArrayList<>();
        // details: item name, item code, item category, item quantity and item price
        head.add("name");
        head.add("code");
        head.add("category");
        head.add("quantity");
        head.add("price");

        List<List<String>> data = new ArrayList<>();
        List<Commodity> commodities = new CommodityService().getAllCommodities();
        for (Commodity c : commodities) {
            if (c.getQuantity() != 0) {
                // If current available
                List<String> temp = new ArrayList<>();
                temp.add(c.getName());
                temp.add(c.getCommodityId().toString());
                temp.add(c.getCategory().toString());
                temp.add(c.getQuantity().toString());
                temp.add(c.getPrice().toString());
                data.add(temp);
            }
        }
        File directory = new File("");
        CSVUtil.createCSV(head,data,directory.getAbsolutePath().concat("/build/reports/"),"Shopping_Summary");
        new AlertUtil("SUCCESS","Summary Created!").show();
    }

    @FXML
    private void checkCommoditySummary(ActionEvent event) throws IOException {
        List<Commodity> commodityList = commodityService.getAllCommodities();
        List<String> head = new ArrayList<>();
        head.add("Code");
        head.add("Name");
        head.add("Sold_Number");
        List<List<String>> data = new ArrayList<>();
        for (Commodity commodity : commodityList){
            int number  = commodity.getCommodityId();
            List<String> value = new ArrayList<>();
            String code = Integer.toString(number);
            String name = commodity.getName();
            int num = commodity.getSoldNumber();
            String soldNumber = Integer.toString(num);
            value.add(code);
            value.add(name);
            value.add(soldNumber);
            data.add(value);
        }
        File directory = new File("");
        CSVUtil.createCSV(head, data, directory.getAbsolutePath().concat("/build/reports/"), "Check_Summary");
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

    public static class UserRow{
        private final SimpleStringProperty uid;
        private final SimpleStringProperty userName;
        private final SimpleStringProperty role;

        public UserRow(String uid, String userName, String role) {
            this.uid = new SimpleStringProperty(uid);
            this.userName = new SimpleStringProperty(userName);
            this.role = new SimpleStringProperty(role);
        }

        public String getUid() {
            return uid.get();
        }

        public SimpleStringProperty uidProperty() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid.set(uid);
        }

        public String getUserName() {
            return userName.get();
        }

        public SimpleStringProperty userNameProperty() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName.set(userName);
        }

        public String getRole() {
            return role.get();
        }

        public SimpleStringProperty roleProperty() {
            return role;
        }

        public void setRole(String role) {
            this.role.set(role);
        }
    }
}
