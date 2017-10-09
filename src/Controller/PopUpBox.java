package Controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class PopUpBox {

    /**
     * Creates a popUp window that will display
     * the results of the {@link game.NashGame#printNashEqualitiesToGui(Label)}
     * function
     *
     * @param listOfLabeledNashResults     The List of labels that will be used to display
     *                                     the Nash Equilibrium in the popUp window
     */
    public static void nashEquilibriumPopUpWindow(List<Label> listOfLabeledNashResults){
        Stage nashEquilibriumWinnerStage = new Stage();
        GridPane popUpWindowGridPane = new GridPane();

        nashEquilibriumWinnerStage.initModality(Modality.APPLICATION_MODAL);
        nashEquilibriumWinnerStage.setTitle("Nash Winners!");
        nashEquilibriumWinnerStage.setMinWidth(300);

        for (int i = 0; i < listOfLabeledNashResults.size(); i++) {
            if(listOfLabeledNashResults.get(i).getText().isEmpty()){
                listOfLabeledNashResults.get(i).setText("There are no Nash Equilibrium's!");
            }
            else {
                listOfLabeledNashResults.get(i).getText();
                popUpWindowGridPane.getChildren().add(listOfLabeledNashResults.get(i));
                popUpWindowGridPane.setAlignment(Pos.CENTER);
                GridPane.setConstraints(listOfLabeledNashResults.get(i), 0, i + 1);
            }
        }


        Scene scene = new Scene(popUpWindowGridPane);

        nashEquilibriumWinnerStage.setScene(scene);
        nashEquilibriumWinnerStage.showAndWait();
    }
}
