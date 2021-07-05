package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static Stage primaryStage;
    public static Stage secondaryStage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        App.primaryStage = primaryStage;
        Parent parent = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene scene = new Scene(parent);

        primaryStage.setTitle("Vending Machine");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
