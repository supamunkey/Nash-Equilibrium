import game.NashGame;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Controller.GridPaneController;
import util.NashGameUtil;

public class NashRunner extends Application {

    private Button getNashButton;

    private NashGame game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        getNashButton = new Button("Get Nash");
        game = new NashGame("Tom", "Bob", 2);

        NashGameUtil.generateBoard(game);
        NashGameUtil.displayBoard(game);

        GridPaneController.guiController(primaryStage, getNashButton, game);

        primaryStage.setTitle("Nash Equilibrium");
        primaryStage.show();
    }
}
