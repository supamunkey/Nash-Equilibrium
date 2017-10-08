import model.Strategy;

import java.util.concurrent.ThreadLocalRandom;

public class NashRunner {
    public static void main(String[] args) {

        NashGame game = new NashGame("Tom", "Bob", 4);

        for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
            for (int i = 0; i < game.boardLength(); i++) {
                for (int j = 0; j < game.boardLength(); j++) {
                    game.addStrategyToBoard(playerIndex, i, j, ThreadLocalRandom.current().nextInt(-5, 5 + 1));
                }
            }
        }

        Strategy[][] board = game.getBoard();
        for (int i = 0; i < game.boardLength(); i++) {
            for (int j = 0; j < game.boardLength(); j++) {
                System.out.print(board[i][j].toString());
            }
            System.out.println("\n");
        }

        game.findNashEqualities();
    }
}
