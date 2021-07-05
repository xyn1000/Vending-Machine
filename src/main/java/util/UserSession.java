package util;

import entity.Commodity;
import entity.User;
import service.CommodityService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserSession {
    private static User currentUser;
    private static Map<Integer, Commodity> allCommodities = new HashMap<>();
    private static Map<Integer, Commodity> shoppingCart = new HashMap<>();
    private static CommodityService commodityService = new CommodityService();
    private static int currentTransaction = -1;
    private static int currentCommodity = -1;
    private static boolean welcome = false;

    public UserSession() {
        flushCommodities();
    }

    public static void setCurrentUser(User currentUser) {
        UserSession.currentUser = currentUser;
    }

    public static void addToCart(Commodity commodity){
        shoppingCart.put(commodity.getCommodityId(),commodity);
    }

    public static void flushCommodities(){
        allCommodities.clear();
        List<Commodity> commodities = commodityService.getAllCommodities();
        for (Commodity commodity: commodities){
            allCommodities.put(commodity.getCommodityId(),commodity);
        }
    }

    public static double getCartAmount(){
        List<Commodity> commodities = new ArrayList<>(shoppingCart.values());
        double total = 0;
        for (Commodity commodity:commodities) {
            total += commodity.getPrice() * commodity.getQuantity();
        }
        return total;
    }

    public static Map<Integer, Commodity> getAllCommodities() {
        return allCommodities;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static Map<Integer, Commodity> getShoppingCart(){
        return UserSession.shoppingCart;
    }

    public static void logout() {
        currentUser = null;
        shoppingCart.clear();
        allCommodities.clear();
        completeTransaction();
        currentCommodity = -1;
        welcome = false;
    }

    public static void completeTransaction(){
        currentTransaction = -1;
    }

    public static int getCurrentTransaction() {
        return currentTransaction;
    }

    public static void setCurrentTransaction(int currentTransaction) {
        UserSession.currentTransaction = currentTransaction;
    }

    public static int getCurrentCommodity() {
        return currentCommodity;
    }

    public static void setCurrentCommodity(int currentCommodity) {
        UserSession.currentCommodity = currentCommodity;
    }

    public static boolean isWelcome() {
        return welcome;
    }

    public static void setWelcome(boolean welcome) {
        UserSession.welcome = welcome;
    }
}
