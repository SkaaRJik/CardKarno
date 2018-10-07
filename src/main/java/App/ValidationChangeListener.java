package App;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import utils.Validator;

/**
 * Created by SkaaRJ on 06.04.2018.
 */
public class ValidationChangeListener implements ChangeListener {
    TextField textField;

    public ValidationChangeListener(TextField textField) {
        this.textField = textField;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        this.textField.setStyle("-fx-background-color: white");
        if(!Validator.isOnlyBoolean((String) newValue) ||  ((String) newValue).length() >= 2){
            AlertSingleton.getInstanse().showAlert("Поле должно содержать только 0 или 1");

            this.textField.setText((String) oldValue);
            return;
        }
    }
}
