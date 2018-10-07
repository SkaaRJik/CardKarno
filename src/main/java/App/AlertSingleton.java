package App;

import javafx.scene.control.Alert;

public class AlertSingleton {

    private static AlertSingleton instanse = null;
    private static Alert alert = null;
    public static AlertSingleton getInstanse() {
        if(instanse == null) instanse = new AlertSingleton();
        return instanse;
    }

    public void showAlert(String errorText){
        if(alert == null) this.alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(errorText);
        alert.showAndWait();
    }


}
