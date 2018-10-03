package App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Controller {

    private GridPane variablesGrid;

    @FXML
    private AnchorPane gridContainer;

    @FXML
    private ChoiceBox<Integer> countChooseBox;

    @FXML
    private TextField[][] input;

    public void init(){

        countChooseBox.getItems().add(2);
        countChooseBox.getItems().add(3);
        countChooseBox.getItems().add(4);
        gridContainer.getChildren().add(new GridPane());



        countChooseBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int rows = (int) Math.pow(2,newValue);
            int columns = newValue;
            input = new TextField[rows][columns+1];
            variablesGrid = new GridPane();

            for (int i = 0; i < columns; i++) {
                variablesGrid.add(new Label(""+(char) (97+i)), i, 0);
            }
            variablesGrid.add(new Label("f"), columns, 0);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns+1; j++) {
                    TextField textField = new TextField();
                    if(j != columns) {
                        textField.setDisable(true);
                    }
                    input[i][j]=textField;
                    variablesGrid.add(textField, j, i+1);
                }
            }

            for (int i = 0; i < rows; i++) {
                int tmp = i;
                for (int j = columns-1; j >=0 ; j--) {
                    input[i][j].setText(String.valueOf(tmp & 1));
                    tmp >>= 1;
                }
            }

            gridContainer.getChildren().set(0, variablesGrid);
        });
    }

    public void solute(ActionEvent actionEvent) {
        
    }
}
