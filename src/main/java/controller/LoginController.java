package controller;

import Application.App;
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
import service.AuthService;
import util.AlertUtil;
import util.UserSession;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static entity.EnvironmentConstant.*;

/**
 * The controller for login page
 *
 * @author <Kelly Huang>
 * @since <pre>Nov. 2, 2020</pre>
 * @version 1.0
 */
public class LoginController {

    private AuthService authService = new AuthService();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userNameFld;

    @FXML
    private PasswordField userPasswordFld;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label userPasswordLabel;

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;

    @FXML
    private Button guestBtn;

    @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        // Check whether a user already exists with given username and passwords
        String userName = userNameFld.getText();
        String password = userPasswordFld.getText();
        User user = authService.login(userName, password);
        if (user != null) {
            // If matching user exists, login successful, start the scene
            UserSession.setCurrentUser(user);
            UserSession.flushCommodities();
            Parent parent = null;
            switch (user.getRole()) {
                case ROLE_SELLER:
                    parent = FXMLLoader.load(getClass().getResource("/view/Seller.fxml"));
                    break;
                case ROLE_OWNER:
                    parent = FXMLLoader.load(getClass().getResource("/view/Owner.fxml"));
                    break;
                case ROLE_CASHIER:
                    parent = FXMLLoader.load(getClass().getResource("/view/Cashier.fxml"));
                    break;
                default:
                    parent = FXMLLoader.load(getClass().getResource("/view/CustomerSystem.fxml"));
                    break;
            }

            assert parent != null;
            Scene scene = new Scene(parent);
            App.primaryStage.setScene(scene);
        } else {
            // Matching user not exists, login fail
            new AlertUtil("Authentication Failed", "Incorrect UserName or Password.").show();
        }
    }

    @FXML
    public void handleRegister(ActionEvent event) throws IOException {
        // If user want to register, direct to register scene
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));
        Scene scene = new Scene(parent);
        App.primaryStage.setScene(scene);
        App.primaryStage.setTitle("Registration");
        App.primaryStage.show();
    }

    @FXML
    public void handleGuestLogin(ActionEvent event) throws IOException {
        // Start Customer system directly without login
        UserSession.setCurrentUser(authService.getAnonymousUser());
        UserSession.flushCommodities();
        Parent parent = FXMLLoader.load(getClass().getResource("/view/CustomerSystem.fxml"));
        Scene scene = new Scene(parent);
        App.primaryStage.setScene(scene);
    }
}