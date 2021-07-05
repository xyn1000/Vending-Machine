package controller;

import Application.App;
import entity.EnvironmentConstant;
import entity.Commodity;
import entity.Order;
import entity.Transaction;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import service.CommodityService;
import service.OrderService;
import service.TransactionService;
import util.AlertUtil;
import util.UserSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CustomerController {
    private CommodityService commodityService = new CommodityService();
    private TransactionService transactionService = new TransactionService();
    private OrderService orderService = new OrderService();
    private List<CommodityRow> commodityRows = new ArrayList<>();
    private List<CartRow> cartRows = new ArrayList<>();

    public static class CommodityRow{
        private SimpleStringProperty barCode;
        private SimpleStringProperty name;
        private SimpleDoubleProperty price;
        private SimpleIntegerProperty remain;
        private SimpleStringProperty quantity;

        public CommodityRow(String barCode, String name, double price, int remain, int quantity) {
            this.barCode = new SimpleStringProperty(barCode);
            this.name = new SimpleStringProperty(name);
            this.price = new SimpleDoubleProperty(price);
            this.remain = new SimpleIntegerProperty(remain);
            this.quantity = new SimpleStringProperty(String.valueOf(quantity));
        }

        public String getBarCode() {
            return barCode.get();
        }

        public SimpleStringProperty barCodeProperty() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode.set(barCode);
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public int getRemain() {
            return remain.get();
        }

        public SimpleIntegerProperty remainProperty() {
            return remain;
        }

        public void setRemain(int remain) {
            this.remain.set(remain);
        }

        public String getQuantity() {
            return quantity.get();
        }

        public SimpleStringProperty quantityProperty() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity.set(quantity);
        }

        public double getPrice() {
            return price.get();
        }

        public SimpleDoubleProperty priceProperty() {
            return price;
        }

        public void setPrice(double price) {
            this.price.set(price);
        }
    }

    public static class CartRow{
        private SimpleStringProperty barCode;
        private SimpleStringProperty name;
        private SimpleDoubleProperty price;
        private SimpleIntegerProperty quantity;

        public CartRow(String barCode, String name, double price, int quantity) {
            this.barCode = new SimpleStringProperty(barCode);
            this.name = new SimpleStringProperty(name);
            this.price = new SimpleDoubleProperty(price);
            this.quantity = new SimpleIntegerProperty(quantity);
        }

        public String getBarCode() {
            return barCode.get();
        }

        public SimpleStringProperty barCodeProperty() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode.set(barCode);
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public double getPrice() {
            return price.get();
        }

        public SimpleDoubleProperty priceProperty() {
            return price;
        }

        public void setPrice(double price) {
            this.price.set(price);
        }

        public int getQuantity() {
            return quantity.get();
        }

        public SimpleIntegerProperty quantityProperty() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity.set(quantity);
        }
    }

    @FXML
    private Button DrinkBtn;

    @FXML
    private Button LolliesBtn;

    @FXML
    private Button ChocoBtn;

    @FXML
    private Button ChipsBtn;

    @FXML
    private Label userLabel;

    @FXML
    private Label timeInfoLabel;

    @FXML
    private TableView<CommodityRow> infoTabTable;

    @FXML
    private TableColumn<CommodityRow, String> barCodeCol;

    @FXML
    private TableColumn<CommodityRow, String> nameCol;

    @FXML
    private TableColumn<CommodityRow, Double> priceCol;

    @FXML
    private TableColumn<CommodityRow, Integer> remainCol;

    @FXML
    private TableColumn<CommodityRow, String> quantityCol;
    
    @FXML
    private TableView<CartRow> cartTable;

    @FXML
    private TableColumn<CartRow, String> cartBarCodeCol;

    @FXML
    private TableColumn<CartRow, String> cartNameCol;

    @FXML
    private TableColumn<CartRow, Double> cartPriceCol;

    @FXML
    private TableColumn<CartRow, Integer> cartQuantityCol;
    
    @FXML
    private TableColumn<CartRow, Void> cartActionCol;

    @FXML
    private Label totalAmountLabel;




    @FXML
    public void handleDrink() {
        setCommodityUI(commodityService.getAllDrinks());
    }

    @FXML
    public void handleChocolate() {
        setCommodityUI(commodityService.getAllChocolates());
    }

    @FXML
    public void handleLollies() {
        setCommodityUI(commodityService.getAllLollies());
    }

    @FXML
    public void handleChips() {
        setCommodityUI(commodityService.getAllChips());
    }

    @FXML
    public void handleAddToCart() {
        ObservableList<CommodityRow> rows = infoTabTable.getItems();
        for (CommodityRow row:rows) {
            int temp = -1;
            try{
                temp = Integer.parseInt(row.getQuantity());
            } catch (NumberFormatException e){
                String commodityName = row.getName();
                new AlertUtil("Error","Invalid Input for "+ commodityName).showAndWait();
            }
            if(temp > 0) {
                if (temp <= row.getRemain()){
                    Integer barcode = Integer.valueOf(row.getBarCode());
                    Commodity commodity  = UserSession.getAllCommodities().get(barcode);
                    commodity.setQuantity(temp);
                    UserSession.addToCart(commodity);
                } else {
                    String commodityName = row.getName();
                    int amount = row.getRemain();
                    new AlertUtil("Error","Not Enough" +commodityName+", selection failed. Only "+amount+" commodities left.").showAndWait();
                }
            }
        }
        setCartUI();
        setCommodityUI(commodityService.getAllCommodities());
        new AlertUtil("Add Success!","Commodities added to cart!").show();
    }

    @FXML
    public void initialize() throws IOException {
        userLabel.setText("Welcome, "+ UserSession.getCurrentUser().getUsername());
        timeInfoLabel.setText(dateNow());
        UserSession.flushCommodities();
        List<Commodity> commodityList = new ArrayList<>(UserSession.getAllCommodities().values());
        setCommodityUI(commodityList);
        setCartUI();
        if (!UserSession.isWelcome()){
            showHistoryPurchase();
            UserSession.setWelcome(true);
        }
    }



    private void showHistoryPurchase() throws IOException {
        List<Transaction> transactions = transactionService.selectLatestFiveTransaction(UserSession.getCurrentUser().getUserId());
        if(transactions == null || transactions.size() == 0) {
            new AlertUtil("Welcome!","Welcome, new customer!").showAndWait();
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/CustomerHistory.fxml"));
            Scene scene = new Scene(parent);
            App.secondaryStage.setScene(scene);
            App.secondaryStage.setTitle("Purchase History");
            App.secondaryStage.show();
        }
    }

    private String dateNow(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

    @FXML
    public void handleLogOut() throws IOException {
        UserSession.logout();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene scene = new Scene(parent);
        App.primaryStage.setScene(scene);
    }

    private void setCommodityUI(List<Commodity> commodityList){
        commodityRows.clear();
        for (int i = 0; i < commodityList.size(); i++) {
            String commodityID = String.valueOf(commodityList.get(i).getCommodityId());
            String name = commodityList.get(i).getName();
            double price = commodityList.get(i).getPrice();
            int remain = commodityList.get(i).getQuantity();
            commodityRows.add(new CommodityRow(commodityID,name,price,remain,0));
        }
        barCodeCol.setCellValueFactory(new PropertyValueFactory<>("barCode"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        remainCol.setCellValueFactory(new PropertyValueFactory<>("remain"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ObservableList<CommodityRow> commodityUI = FXCollections.observableArrayList(commodityRows);
        infoTabTable.setItems(commodityUI);
        infoTabTable.setEditable(true);
        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void setCartUI(){
        List<Commodity> commodityList = new ArrayList<>(UserSession.getShoppingCart().values());
        cartRows.clear();
        for (int i = 0; i < commodityList.size(); i++) {
            String commodityID = String.valueOf(commodityList.get(i).getCommodityId());
            String name = commodityList.get(i).getName();
            double price = commodityList.get(i).getPrice();
            // number of commodities in need
            int quantity = commodityList.get(i).getQuantity();
            cartRows.add(new CartRow(commodityID,name,price,quantity));
        }
        cartBarCodeCol.setCellValueFactory(new PropertyValueFactory<>("barCode"));
        cartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        cartQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Callback<TableColumn<CartRow, Void>, TableCell<CartRow, Void>> cellFactory = new Callback<TableColumn<CartRow, Void>, TableCell<CartRow, Void>>() {
            @Override
            public TableCell<CartRow, Void> call(final TableColumn<CartRow, Void> param) {
                final TableCell<CartRow, Void> cell = new TableCell<CartRow, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            CartRow CartRow = getTableView().getItems().get(getIndex());
                            int barcode = Integer.parseInt(CartRow.getBarCode());
                            UserSession.getShoppingCart().remove(barcode);
                            getTableView().getItems().remove(CartRow);
                            totalAmountLabel.setText("Total: " + UserSession.getCartAmount());
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
        cartActionCol.setCellFactory(cellFactory);
        ObservableList<CartRow> cartUI = FXCollections.observableArrayList(cartRows);
        cartTable.setItems(cartUI);
        totalAmountLabel.setText("Total: " + UserSession.getCartAmount());
    }


    @FXML
    public void handleCardPay() throws IOException {
        if (UserSession.getShoppingCart().size()==0){
            new AlertUtil("Error", "The shopping cart is empty!").showAndWait();
            return;
        }
        Transaction transaction = new Transaction();
        transaction.setUserId(UserSession.getCurrentUser().getUserId());
        transaction.setPrice(UserSession.getCartAmount());
        transaction.setPayment(EnvironmentConstant.CARD_PAYMENT);
        transaction.setStatus(EnvironmentConstant.TRANSACTION_IN_PROGRESS);
        transaction.setTime(new Date());
        transactionService.addTransaction(transaction);
        int transactionID = transaction.getTransactionId();
        UserSession.setCurrentTransaction(transactionID);
        Map<Integer, Commodity> map = UserSession.getShoppingCart();

        for (Integer id:map.keySet()) {
            Order order = new Order();
            order.setCommodityId(id);
            order.setTransactionId(transactionID);
            order.setQuantity(map.get(id).getQuantity());
            orderService.addOrder(order);
        }

        Parent parent = FXMLLoader.load(getClass().getResource("/view/CardPayment.fxml"));
        Scene scene = new Scene(parent);
        App.secondaryStage.setScene(scene);
        App.secondaryStage.setTitle("Card Payment");
        App.secondaryStage.show();
    }

    @FXML
    public void handleCashPay() throws IOException {
        if (UserSession.getShoppingCart().size()==0) {
            new AlertUtil("Error", "The shopping cart is empty!").showAndWait();
            return;
        }
        Transaction transaction = new Transaction();
        transaction.setUserId(UserSession.getCurrentUser().getUserId());
        transaction.setPrice(UserSession.getCartAmount());
        transaction.setPayment(EnvironmentConstant.CASH_PAYMENT);
        transaction.setStatus(EnvironmentConstant.TRANSACTION_IN_PROGRESS);
        transaction.setTime(new Date());
        transactionService.addTransaction(transaction);
        int transactionID = transaction.getTransactionId();
        UserSession.setCurrentTransaction(transactionID);
        Map<Integer, Commodity> map = UserSession.getShoppingCart();
        for (Integer id:map.keySet()) {
            Order order = new Order();
            order.setCommodityId(id);
            order.setTransactionId(transactionID);
            order.setQuantity(map.get(id).getQuantity());
            orderService.addOrder(order);
        }
        Parent parent = FXMLLoader.load(getClass().getResource("/view/CashPayment.fxml"));
        Scene scene = new Scene(parent);
        App.secondaryStage.setScene(scene);
        App.secondaryStage.setTitle("Cash Payment");
        App.secondaryStage.show();

    }

    @FXML
    public void handleClearCart() {
        cartRows.clear();
        UserSession.getShoppingCart().clear();
        ObservableList<CartRow> cartUI = FXCollections.observableArrayList(cartRows);
        cartTable.setItems(cartUI);
        totalAmountLabel.setText("Total: " + 0);
    }
}
