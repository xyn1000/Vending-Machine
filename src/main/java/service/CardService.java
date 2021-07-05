package service;

import entity.Card;
import mapper.*;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CardService {
    private CardMapper cardMapper;
    private UserMapper userMapper;
    public  List<Card> defaultCards = new ArrayList<>();


    public CardService(){
        this.cardMapper = new CardMapperImpl();
        this.userMapper = new UserMapperImpl();
        defaultValue();
    }

    public String readFile(String path){
        String lastStr = "";
        URL res = getClass().getClassLoader().getResource(path);
        File file = null;
        try {
            assert res != null;
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        BufferedReader reader = null;
        try{
            FileInputStream in = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String tempString = null;
            while((tempString = reader.readLine())!=null){
                lastStr = lastStr + tempString;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }
                catch(IOException ignored){
                }
            }
        }
        return lastStr;
    }

    public void defaultValue() {
        String path = "credit_cards.json";
        String get = readFile(path);
        JSONArray jsonArray = JSONArray.fromObject(get);
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = String.valueOf(jsonObject.get("name"));
            String number = String.valueOf(jsonObject.get("number"));
            int num = Integer.parseInt(number);
            Card card = new Card();
            card.setNumber(num);
            card.setName(name);
            defaultCards.add(card);
        }
    }
//    public int addNewCard(Card card){
//        return cardMapper.insert(card);
//    }

    public Card checkOut(String cardName, Integer cardNumber){

        for (int i = 0; i < defaultCards.size(); i++){
            if (defaultCards.get(i).getName().equals(cardName)){
                if (defaultCards.get(i).getNumber().equals(cardNumber)) {
//                    if (UserSession.getCurrentUser().getUserId()!=0){
//                        User user = new User();
//                        user.setUserId(UserSession.getCurrentUser().getUserId());
//                        user.setCard(defaultCards.get(i).getNumber());
//                        userMapper.updateByPrimaryKeySelective(user);
//                    }
                    return defaultCards.get(i);
                }
            }
        }
//        Card card = cardMapper.selectByPrimaryKey(cardNumber);
//        if (card != null) {
//            if (card.getName().equals(cardName)) {
//                if (UserSession.getCurrentUser().getUserId()!=0){
//                    User user = new User();
//                    user.setUserId(UserSession.getCurrentUser().getUserId());
//                    user.setCard(card.getNumber());
//                    userMapper.updateByPrimaryKeySelective(user);
//                }
//                return card;
//            }
//            return null;
//        }
        return null;
    }

    public String getCardNameByNumber(int number){
        for (Card card:defaultCards) {
            if (card.getNumber() == number){
                return card.getName();
            }
        }
        return null;
    }
}
