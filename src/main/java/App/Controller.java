package App;

import cardKarno.CardKarno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class Controller {

    private GridPane variablesGrid;
    private GridPane cardKarnoGrid;

    @FXML
    private AnchorPane gridContainer;

    @FXML
    private ChoiceBox<Integer> countChooseBox;

    @FXML
    private TextField[][] input;
    private int rows;
    private int columns;

    @FXML
    private AnchorPane karnoTableContainer;



    public void init(){

        this.countChooseBox.getItems().add(2);
        this.countChooseBox.getItems().add(3);
        this.countChooseBox.getItems().add(4);
        this.gridContainer.getChildren().add(new GridPane());
        this.karnoTableContainer.getChildren().add(new GridPane());

        this.countChooseBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            generateTableOfTruth(newValue);
        });

        this.countChooseBox.getSelectionModel().select(0);
    }

    public void calculate(ActionEvent actionEvent) {
        boolean hasErrors = false;

        for (int i = 0; i < this.rows; i++){
            this.input[i][this.columns-1].setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 2px");
            if(this.input[i][this.columns-1].getText().length() == 0) {
                this.input[i][this.columns-1].setStyle("-fx-background-color: red; -fx-border-color: black; -fx-border-radius: 2px");
                hasErrors = true;
            }
        }

        if(hasErrors) {
            AlertSingleton.getInstanse().showAlert("Некоторые поля были не заполнены");
            return;
        }
        int truthTable[][] = new int[this.input.length][this.input[0].length-1];

        for (int i = 0; i < this.input.length; i++) {
            for (int j = 0; j < this.input[i].length-1; j++) {
                truthTable[i][j] = Integer.parseInt(this.input[i][j].getText());
            }
        }

        CardKarno cardKarno = new CardKarno();
        cardKarno.getKarnoTable(truthTable);
        truthTable = cardKarno.getZippedTruthTable();

        this.cardKarnoGrid = new GridPane();
        /*for (int i = 0; i < 2; i++) {
            variablesGrid.add(new Label(""+(char) (97+i)), i, 0);
        }*/
        //cardKarnoGrid.add(new Label("f"), columns, 0);
        for (int i = 0; i < truthTable.length; i++) {
            for (int j = 0; j < truthTable[0].length; j++) {
                TextField textField = new TextField();
                textField.setPrefWidth(30);
                textField.setPrefHeight(Region.USE_COMPUTED_SIZE);
                textField.setMinWidth(Region.USE_COMPUTED_SIZE);
                textField.setMinHeight(Region.USE_COMPUTED_SIZE);
                textField.setDisable(true);
                textField.setText(String.valueOf(truthTable[i][j]));
                this.cardKarnoGrid.add(textField, j, i+1);

            }
        }

        this.karnoTableContainer.getChildren().set(0, cardKarnoGrid);
    }

    private void generateTableOfTruth(int numberOfVariables){
        this.rows = (int) Math.pow(2,numberOfVariables);
        this.columns = numberOfVariables+1;
        this.input = new TextField[this.rows][columns+1];
        this.variablesGrid = new GridPane();

        for (int i = 0; i < columns-1; i++) {
            this.variablesGrid.add(new Label(""+(char) (97+i)), i, 0);
        }
        this.variablesGrid.add(new Label("f"), columns-1, 0);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < columns; j++) {
                TextField textField = new TextField();
                textField.setPrefWidth(30);
                textField.setPrefHeight(Region.USE_COMPUTED_SIZE);
                textField.setMinWidth(Region.USE_COMPUTED_SIZE);
                textField.setMinHeight(Region.USE_COMPUTED_SIZE);
                if(j != columns-1) {
                    textField.setDisable(true);
                } else {
                    textField.textProperty().addListener(new ValidationChangeListener(textField));
                }
                this.input[i][j]=textField;
                this.variablesGrid.add(textField, j, i+1);
            }
        }

        for (int i = 0; i < rows; i++) {
            int tmp = i;
            for (int j = columns-2; j >=0 ; j--) {
                this.input[i][j].setText(String.valueOf(tmp & 1));
                tmp >>= 1;
            }
        }

        this.gridContainer.getChildren().set(0, this.variablesGrid);
    }


}
