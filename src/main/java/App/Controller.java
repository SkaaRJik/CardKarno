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
    private TableView<String> cardKarnoGrid;

    @FXML
    private AnchorPane gridContainer;

    @FXML
    private ChoiceBox<Integer> countChooseBox;

    @FXML
    private TextField[][] input;

    @FXML
    private AnchorPane karnoTableContainer;

    public void init(){

        this.countChooseBox.getItems().add(2);
        this.countChooseBox.getItems().add(3);
        this.countChooseBox.getItems().add(4);
        this.gridContainer.getChildren().add(new GridPane());
        this.karnoTableContainer.getChildren().add(new GridPane());



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
                    textField.setPrefWidth(25);
                    textField.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    textField.setMinWidth(Region.USE_COMPUTED_SIZE);
                    textField.setMinHeight(Region.USE_COMPUTED_SIZE);
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

    public void calculate(ActionEvent actionEvent) {

        int truthTable[][] = new int[this.input.length][this.input[0].length];

        for (int i = 0; i < this.input.length; i++) {
            for (int j = 0; j < this.input[i].length; j++) {
                truthTable[i][j] = Integer.parseInt(this.input[i][j].getText());
            }
        }

        CardKarno cardKarno = new CardKarno();
        cardKarno.karnoTableBuilder(truthTable);
        truthTable = cardKarno.getZippedTruthTable();

        this.cardKarnoGrid = new TableView();
        /*for (int i = 0; i < 2; i++) {
            variablesGrid.add(new Label(""+(char) (97+i)), i, 0);
        }*/
        //cardKarnoGrid.add(new Label("f"), columns, 0);
        for (int i = 0; i < truthTable[0].length; i++) {
            TableColumn<String, String> columnTable = new TableColumn<>();
            for (int j = 0; j < truthTable.length; j++) {
                Cell<String> cell = new Cell<>();
                cell.setItem(String.valueOf(truthTable[j][i]));
                /*TextField textField = new TextField();
                textField.setPrefWidth(25);
                textField.setPrefHeight(Region.USE_COMPUTED_SIZE);
                textField.setMinWidth(Region.USE_COMPUTED_SIZE);
                textField.setMinHeight(Region.USE_COMPUTED_SIZE);
                textField.setDisable(true);
                textField.setText(String.valueOf(truthTable[i][j]));
                this.cardKarnoGrid.add(textField, j, i+1);*/

            }
        }




        this.karnoTableContainer.getChildren().set(0, cardKarnoGrid);



    }
}
