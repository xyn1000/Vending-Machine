package controller;

import Application.App;
import entity.Commodity;
import entity.EnvironmentConstant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import service.CommodityService;
import util.AlertUtil;
import util.UserSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommodityChangeController {

    private CommodityService commodityService = new CommodityService();
    private Map<String, Integer> categoryMap = new HashMap<>();

    @FXML
    private TextField nameFld;

    @FXML
    private TextField priceFld;

    @FXML
    private TextField quantityFld;

    @FXML
    private ChoiceBox<String> categoryChoice;

    @FXML
    public void initialize() {
        initializeCategoryMap();
        Commodity commodity = commodityService.getCommodity(UserSession.getCurrentCommodity());
        if (commodity == null){
            return;
        }
        nameFld.setText(commodity.getName());
        priceFld.setText(String.valueOf(commodity.getPrice()));
        quantityFld.setText(String.valueOf(commodity.getQuantity()));
        List<String> categoryList = new ArrayList<>(categoryMap.keySet());
        ObservableList<String> temp = FXCollections.observableArrayList(categoryList);
        categoryChoice.setItems(temp);
    }

    private void initializeCategoryMap(){
        categoryMap.clear();
        categoryMap.put("DRINKS",3);
        categoryMap.put("CHOCOLATES",4);
        categoryMap.put("LOLLIES",5);
        categoryMap.put("CHIPS",6);
    }


    @FXML
    public void handleCancel(){
        App.secondaryStage.close();
        UserSession.setCurrentCommodity(-1);
    }

    @FXML
    public void handleConfirm() throws IOException {
        String name = nameFld.getText();
        double price;
        int quantity;
        try{
            price = Double.parseDouble(priceFld.getText());
            quantity = Integer.parseInt(quantityFld.getText());
        }catch(NumberFormatException e) {
            new AlertUtil("Error!","Please check user input!").show();
            return;
        }

        if (quantity > 15) {
            new AlertUtil("ERROR","The vending machine can maintain up to 15 items of each product.").show();
            return;
        }

        if (price <= 0) {
            new AlertUtil("ERROR", "Invalid Price!").show();
            return;
        }

        int category = categoryMap.get(categoryChoice.getValue());
        int id = UserSession.getCurrentCommodity();
        Commodity commodity = new Commodity();
        commodity.setCategory(category);
        commodity.setQuantity(quantity);
        commodity.setName(name);
        commodity.setPrice(price);
        commodity.setCommodityId(id);
        commodityService.updateSelective(commodity);
        UserSession.setCurrentCommodity(-1);
        new AlertUtil("Success!","Commodity Update Success").showAndWait();
        App.secondaryStage.close();

        Parent parent;
        if (UserSession.getCurrentUser().getRole() == EnvironmentConstant.ROLE_OWNER){
            parent = FXMLLoader.load(getClass().getResource("/view/Owner.fxml"));
        } else {
            parent = FXMLLoader.load(getClass().getResource("/view/Seller.fxml"));
        }
        App.primaryStage.setScene(new Scene(parent));
    }
}
