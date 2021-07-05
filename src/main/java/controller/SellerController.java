
package controller;

import Application.App;
import entity.Commodity;
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
import javafx.util.Callback;
import service.CommodityService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import util.AlertUtil;
import util.CSVUtil;
import util.UserSession;

public class SellerController {

    private Map<String, Integer> categoryMap = new HashMap<>();
    private List<String> categoryList = new ArrayList<>();
    private List<CommodityView> commodityViewList = new ArrayList<>();
    private CommodityService commodityService = new CommodityService();

    @FXML
    private Label userWelcomeLabel;

    @FXML
    private Label timeInfoLabel;

    @FXML
    private TextField priceFld;

    @FXML
    private TextField nameFld;

    @FXML
    private TextField quantityFld;

    @FXML
    private TextField barcodeFld;

    @FXML
    private Button logOutBtn;

    @FXML
    private TableView<CommodityView> commodityTable;

    @FXML
    private TableColumn<CommodityView, String> barcodeCol;

    @FXML
    private TableColumn<CommodityView, String> nameCol;

    @FXML
    private TableColumn<CommodityView, String> priceCol;

    @FXML
    private TableColumn<CommodityView,String> quantityCol;

    @FXML
    private TableColumn<CommodityView, String> categoryCol;

    @FXML
    private TableColumn<CommodityView, Void> deleteCol;

    @FXML
    private TableColumn<CommodityView, Void> modifyCol;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    public static class CommodityView{
        private SimpleStringProperty barCode;
        private SimpleStringProperty name;
        private SimpleStringProperty price;
        private SimpleStringProperty category;
        private SimpleStringProperty quantity;

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

        public String getPrice() {
            return price.get();
        }

        public SimpleStringProperty priceProperty() {
            return price;
        }

        public void setPrice(String price) {
            this.price.set(price);
        }

        public String getCategory() {
            return category.get();
        }

        public SimpleStringProperty categoryProperty() {
            return category;
        }

        public void setCategory(String category) {
            this.category.set(category);
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

        public CommodityView(String barCode, String name, String price, String category, String quantity) {
            this.barCode = new SimpleStringProperty(barCode);
            this.name = new SimpleStringProperty(name);
            this.price = new SimpleStringProperty(price);
            switch (category){
                case "3":
                    this.category = new SimpleStringProperty("DRINKS");
                    break;
                case "4":
                    this.category = new SimpleStringProperty("CHOCOLATES");
                    break;
                case "5":
                    this.category = new SimpleStringProperty("LOLLIES");
                    break;
                case "6":
                    this.category = new SimpleStringProperty("CHIPS");
                    break;
                default:
                    this.category = new SimpleStringProperty("ERROR");
                    break;
            }
            this.quantity = new SimpleStringProperty(quantity);
        }
    }

    @FXML
    public void initialize(){
        userWelcomeLabel.setText("Welcome, "+ UserSession.getCurrentUser().getUsername());
        timeInfoLabel.setText(dateNow());
        initializeCategoryMap();
        ObservableList<String> list = FXCollections.observableArrayList(categoryList);
        categoryChoiceBox.setItems(list);
        initializeSellerTable();
    }


    @FXML
    private void add_Commodity(){
        Commodity commodity = new Commodity();
        int commodity_id;
        Double commodity_price;
        int commodity_quantity;
        try{
            commodity_id= Integer.parseInt(barcodeFld.getText());
            commodity_price = Double.parseDouble(priceFld.getText());
            commodity_quantity = Integer.parseInt(quantityFld.getText());
        } catch(NumberFormatException e) {
            new AlertUtil("Error!","Please check user input!").show();
            return;
        }


        String commodity_name = nameFld.getText();
        String commodity_category = categoryChoiceBox.getValue();
        int commodity_category_id = 0;
        if ("CHOCOLATES".equals(commodity_category)){
            commodity_category_id = 4;
        }

        else if ("DRINKS".equals(commodity_category)){
            commodity_category_id = 3;
        }

        else if ("LOLLIES".equals(commodity_category)){
            commodity_category_id = 5;
        }

        else if("CHIPS".equals(commodity_category)){
            commodity_category_id = 6;
        }
        commodity.setCategory(commodity_category_id);
        commodity.setQuantity(commodity_quantity);
        commodity.setPrice(commodity_price);
        commodity.setName(commodity_name);
        commodity.setCommodityId(commodity_id);
        CommodityService commodityService = new CommodityService();
        commodityService.addCommodity(commodity);
        new AlertUtil("Success","New Commodity Added!").show();
        barcodeFld.clear();
        quantityFld.clear();
        priceFld.clear();
        nameFld.clear();
        initialize();
    }

    private String dateNow(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(d);
        return dateStr;
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
            CommodityView temp = new CommodityView(barCode, name, price, category, quantity);
            commodityViewList.add(temp);
        }
        barcodeCol.setCellValueFactory(new PropertyValueFactory<>("barCode"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        Callback<TableColumn<CommodityView, Void>, TableCell<CommodityView, Void>> cellFactory = new Callback<TableColumn<CommodityView, Void>, TableCell<CommodityView, Void>>() {
            @Override
            public TableCell<CommodityView, Void> call(final TableColumn<CommodityView, Void> param) {
                final TableCell<CommodityView, Void> cell = new TableCell<CommodityView, Void>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            CommodityView CartRow = getTableView().getItems().get(getIndex());
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
        Callback<TableColumn<CommodityView, Void>, TableCell<CommodityView, Void>> newCellFactory = new Callback<TableColumn<CommodityView, Void>, TableCell<CommodityView, Void>>() {
            @Override
            public TableCell<CommodityView, Void> call(final TableColumn<CommodityView, Void> param) {
                final TableCell<CommodityView, Void> cell = new TableCell<CommodityView, Void>() {

                    private final Button btn = new Button("Modify");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            CommodityView CartRow = getTableView().getItems().get(getIndex());
                            int barcode = Integer.parseInt(CartRow.getBarCode());
                            UserSession.setCurrentCommodity(barcode);
                            Parent parent = null;
                            try {
                                parent = FXMLLoader.load(getClass().getResource("/view/ChangeCommodity.fxml"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("ID:"+UserSession.getCurrentCommodity());
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

        ObservableList<CommodityView> commodityViews = FXCollections.observableArrayList(commodityViewList);
        commodityTable.setItems(commodityViews);
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

    @FXML
    public void handleItemSummary(ActionEvent event) throws IOException{
        List<String> head = new ArrayList<>();
        // details: item name, item code, item category, item quantity and item price
        head.add("name");
        head.add("code");
        head.add("category");
        head.add("quantity");
        head.add("price");

        List<List<String>> data = new ArrayList<>();
        List<Commodity> commodities = new CommodityService().getAllCommodities();
        for (Commodity c: commodities){
            if (c.getQuantity() != 0){
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
    private void checkSummary(ActionEvent event) throws IOException {
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
    public void handleLogOut(ActionEvent event) throws IOException {
        UserSession.logout();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene scene = new Scene(parent);
        App.primaryStage.setScene(scene);
    }
}
