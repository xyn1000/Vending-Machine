package controller;

import Application.App;
import entity.Role;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mapper.UserMapper;
import mapper.UserMapperImpl;
import service.AuthService;
import util.AlertUtil;
import util.MD5Util;
import util.UserSession;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller for register page
 *
 * @author <Kelly Huang>
 * @since <pre>Nov. 2, 2020</pre>
 * @version 1.0
 */
public class RegisterController {
    private AuthService authService = new AuthService();
    private UserMapper  userMapper = new UserMapperImpl();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userNameFld;

    @FXML
    private PasswordField userPasswordFld;

    @FXML
    private PasswordField confirmPasswordFld;

    @FXML
    private Label userPasswordLabel;

    @FXML
    private Label userNameLabel;

    @FXML
    private Button confirmBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    void handleCancel(ActionEvent event) throws IOException {
        // Clear all fields when user cancel registration
        userPasswordFld.clear();
        confirmPasswordFld.clear();
        userNameFld.clear();
    }

    @FXML
    void handleRegister(ActionEvent event) throws IOException {
        // Get the data needed for register
        String userName = userNameFld.getText();
        String password = userPasswordFld.getText();
        String confirm = confirmPasswordFld.getText();

        if (!password.equals(confirm)) {
            // Situation of passwords not matching
            AlertUtil alert = new AlertUtil("Alert", "Passwords do not match!");
            alert.show();
            userPasswordFld.clear();
            confirmPasswordFld.clear();

        } else if (userName.isEmpty() || password.isEmpty()) {
            // Situation of not input proper password or username
            AlertUtil alert = new AlertUtil("Error", "Please fill in the form!");
            alert.show();
            userPasswordFld.clear();
            confirmPasswordFld.clear();

        } else if (userMapper.CheckExistUser(userName) != 0) {
            // Situation of a username already exists in system

            AlertUtil alert = new AlertUtil("Error", "Username already exists, please change another username!");
            alert.show();
            userPasswordFld.clear();
            confirmPasswordFld.clear();
        } else{
            // Generates a new user with hashed password
            String salt = MD5Util.salt();
            String hash = MD5Util.md5(password + salt);
            User user = new User();
            user.setUsername(userName);
            user.setSalt(salt);
            user.setPassword(hash);

            // Insert the user into database and start scene
            int rs = authService.register(user);
            if (rs == 1) {
                UserSession.setCurrentUser(user);
                UserSession.flushCommodities();
                Parent parent = FXMLLoader.load(getClass().getResource("/view/CustomerSystem.fxml"));
                Scene scene = new Scene(parent);
                App.primaryStage.setScene(scene);
            } else {
                new AlertUtil("Error", "Registration Failed!");
            }
        }
    }



}
