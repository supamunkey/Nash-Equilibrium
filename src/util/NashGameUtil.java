package util;

import game.NashGame;
import model.Strategy;

import java.util.concurrent.ThreadLocalRandom;

public class NashGameUtil {

    /**
     * Adds random strategies to a NashGames' board
     *
     * @param nashGame  The game who's board is to be updated
     */
    public static void generateBoard(NashGame nashGame) {
        for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
            for (int i = 0; i < nashGame.boardLength(); i++) {
                for (int j = 0; j < nashGame.boardLength(); j++) {
                    nashGame.addStrategyToBoard(playerIndex, i, j,
                            ThreadLocalRandom.current().nextInt(-5, 5 + 1));
                }
            }
        }
    }

    /**
     * Displays the board of a NashGame
     *
     * @param nashGame  The game who's board is to be displayed
     */
    public static void displayBoard(NashGame nashGame) {
        Strategy[][] board = nashGame.getBoard();

        for (int i = 0; i < nashGame.boardLength(); i++) {
            for (int j = 0; j < nashGame.boardLength(); j++) {
                System.out.print(board[i][j].toString());
            }
            System.out.println("\n");
        }
    }
}
