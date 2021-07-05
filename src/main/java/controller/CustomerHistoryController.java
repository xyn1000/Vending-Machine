package controller;

import Application.App;
import entity.Commodity;
import entity.Order;
import entity.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.CommodityService;
import service.OrderService;
import service.TransactionService;
import util.UserSession;

import java.util.ArrayList;
import java.util.List;

public class CustomerHistoryController {

    private List<CustomerController.CartRow> historyRows = new ArrayList<>();
    private OrderService orderService = new OrderService();
    private TransactionService transactionService = new TransactionService();
    private CommodityService commodityService = new CommodityService();

    @FXML
    private Label historyWelcomeLabel;

    @FXML
    private TableView<CustomerController.CartRow> customerHistoryTbl;

    @FXML
    private TableColumn<CustomerController.CartRow, String> historyBarcodeCol;

    @FXML
    private TableColumn<CustomerController.CartRow, String> historyNameCol;

    @FXML
    private TableColumn<CustomerController.CartRow, Double> historyPriceCol;

    @FXML
    private TableColumn<CustomerController.CartRow, Integer> historyQuantityCol;

    @FXML
    private Button dismissBtn;


    @FXML
    public void initialize() {
        historyRows.clear();
        List<Transaction> transactions = transactionService.selectLatestFiveTransaction(UserSession.getCurrentUser().getUserId());
        for (Transaction t:transactions) {
            List<Order> order = orderService.getByTransactionId(t.getTransactionId());
            for(Order o: order) {
                int commodity_id = o.getCommodityId();
                Commodity commodities = commodityService.getCommodity(commodity_id);
                if (historyRows.size() >= 5){
                    break;
                }else {
                    String commodityID = String.valueOf(commodities.getCommodityId());
                    String name = commodities.getName();
                    double price = commodities.getPrice();
                    int quantity = commodities.getQuantity();
                    historyRows.add(new CustomerController.CartRow(commodityID,name,price,quantity));
                }
            }
        }
        historyBarcodeCol.setCellValueFactory(new PropertyValueFactory<>("barCode"));
        historyNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        historyPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        historyQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ObservableList<CustomerController.CartRow> historyView = FXCollections.observableArrayList(historyRows);
        customerHistoryTbl.setItems(historyView);
        historyWelcomeLabel.setText("Nice to meet you again, "+ UserSession.getCurrentUser().getUsername()+"!");
    }

    @FXML
    public void handleDismiss() {
        App.secondaryStage.close();
    }
}
