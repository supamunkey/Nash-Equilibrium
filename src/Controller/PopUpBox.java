package Controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUpBox {

    /**
     * Creates a popUp window that will display
     * the results of the {@link game.NashGame#printNashEqualitiesToGui(Label)}
     * function
     *
     * @param nashResultsLabel     The label that will be used to display
     *                             the Nash Equilibrium in the popUp window
     */
    public static void nashEquilibriumPopUpWindow(Label nashResultsLabel){
        Stage nashEquilibriumWinnerStage = new Stage();

        nashEquilibriumWinnerStage.initModality(Modality.APPLICATION_MODAL);
        nashEquilibriumWinnerStage.setTitle("Nash Winners!");
        nashEquilibriumWinnerStage.setMinWidth(300);

        if(nashResultsLabel.getText().isEmpty()){
            nashResultsLabel.setText("There are no Nash Equilibrium's!");
        }

        nashResultsLabel.getText();

        GridPane popUpWindowGridPane = new GridPane();
        popUpWindowGridPane.getChildren().add(nashResultsLabel);
        popUpWindowGridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(popUpWindowGridPane);

        nashEquilibriumWinnerStage.setScene(scene);
        nashEquilibriumWinnerStage.showAndWait();
    }
}
