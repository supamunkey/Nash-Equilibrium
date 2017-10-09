import game.NashGame;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Controller.GridPaneController;
import util.NashGameUtil;

public class NashRunner extends Application {
    private Button getNashButton = new Button("Get Nash");
    private Label labelStrategiesOnGameBoardLabel;

    private NashGame game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        game = new NashGame("Tom", "Bob", 2);
        NashGameUtil.generateBoard(game);
        NashGameUtil.displayBoard(game);

        labelStrategiesOnGameBoardLabel = new Label();
        GridPaneController.guiController(primaryStage, getNashButton, labelStrategiesOnGameBoardLabel, game);

        primaryStage.setTitle("Nash Equilibrium");

        primaryStage.show();
    }
}
