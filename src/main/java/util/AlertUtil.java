package util;

import javafx.scene.control.Alert;

public class AlertUtil {
    private final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    public AlertUtil(String alertTitle, String content) {
        alert.setTitle(alertTitle);
        alert.setContentText(content);
    }

    public void showAndWait() {
        alert.showAndWait();
    }

    public void show() {
        alert.show();
    }
}
