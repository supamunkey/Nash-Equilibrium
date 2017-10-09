package Controller;

import game.NashGame;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GridPaneController {

    /**
     * Main Driver for GUI.
     * <P>
     *     Creates the GUI for the project. Based on the value
     *     returned from the {@link NashGame#boardLength()} function
     *     the GUI will expand and collapse accordingly.
     * </P>
     *
     * @param primaryStage                  The main Stage the the Gui
     *                                      will be displayed on.
     *
     * @param getNashButton                 The sole button in the GUI,
     *                                      that will be used to perform
     *                                      the {@link game.NashGame#printNashEqualitiesToGui(Label)}
     *                                      function.
     *
     * @param game                          The Game the will be used to retrieve
     *                                      game information such as boardSize and player
     *                                      info.
     */
    public static void guiController(Stage primaryStage, Button getNashButton, NashGame game){
        GridPane gridPaneLayout = new GridPane();
        gridPaneLayout.setPadding(new Insets(10,10,10,10));
        gridPaneLayout.setVgap(8);
        gridPaneLayout.setHgap(10);

        Label labelNashEquilibriumWinner = new Label();

        getNashButton.setOnAction(event -> {
            game.findNashEqualities();
            PopUpBox.nashEquilibriumPopUpWindow(game.printNashEqualitiesToGui(labelNashEquilibriumWinner));
        });

        Label playerOneName = new Label("Player 1");
        Label playerTwoName = new Label("Player 2");

        GridPane.setConstraints(playerOneName, 0, 5);
        GridPane.setConstraints(playerTwoName, 5, 0);
        GridPane.setConstraints(getNashButton, 0, 0);

        positionStrategiesInGui(game, gridPaneLayout);

        gridPaneLayout.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, BorderStroke.THICK)));
        gridPaneLayout.getChildren().addAll(playerOneName, playerTwoName, getNashButton);

        Scene scene = new Scene(gridPaneLayout, game.boardLength() * 200, game.boardLength() * 100);
        primaryStage.setScene(scene);
    }

    /**
     * Positions the Strategies of each player
     * into a cell within the gridPane
     *  @param game                             The NashGame for retrieving board
     *
     * @param gridPaneLayout                    The GridPane that the label will be set too
     */
    public static void positionStrategiesInGui(NashGame game, GridPane gridPaneLayout){
        for (int i = 0; i < game.boardLength(); i++) {
            for (int j = 0; j < game.boardLength(); j++) {
                Label labelStrategiesOnGameBoard = new Label(game.getBoard()[j][i].displayValuesInGui());
                labelStrategiesOnGameBoard.setContentDisplay(ContentDisplay.CENTER);
                labelStrategiesOnGameBoard.setBorder(new Border
                        (new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, BorderStroke.THICK)));

                GridPane.setConstraints(labelStrategiesOnGameBoard, i + 4, j + 4);
                gridPaneLayout.getChildren().add(labelStrategiesOnGameBoard);
            }
        }
    }
}
