import model.Player;
import model.Strategy;

import java.util.InputMismatchException;

public class NashGame {

    private Strategy[][] strategies;

    private Player playerOne;
    private Player playerTwo;

    NashGame(String playerOne, String playerTwo, int boardSize) {
        initializeGameBoard(boardSize);
        this.playerOne = new Player(playerOne, 1);
        this.playerTwo = new Player(playerTwo, 2);
    }

    private void initializeGameBoard(int boardSize) {
        strategies = new Strategy[boardSize][boardSize];

        for (int i = 0; i < boardLength(); i++) {
            for (int j = 0; j < boardLength(); j++) {
                strategies[i][j] = new Strategy();
            }
        }
    }

    /**
     * @param playerIndex The index of a player within the Nash Board, Value is either 0 or 1
     * @param row         The row for a strategy placement
     * @param column      The column for a strategy placement within the board
     * @param value       The value of a strategy
     */
    void addStrategyToBoard(int playerIndex, int row, int column, int value) {
        if (playerIndex > 1) {
            throw new InputMismatchException("This game only supports 2 players");
        }

        strategies[row][column].addStrategy(playerIndex, value);
    }

    public String getPlayerName(int player) {
        if (player > 2 || player < 1) {
            throw new InputMismatchException("Unable to find player " + player);
        }

        if (player == 1) {
            return playerOne.getPlayerName();
        }
        return playerTwo.getPlayerName();
    }

    int boardLength() {
        return strategies.length;
    }

    Strategy[][] getBoard() {
        return strategies;
    }

    private void findBestStrategiesForPlayer(Player player) {

        int playerNumber = player.getPlayerNumber();

        for (int i = 0; i < boardLength(); i++) {
            int max = -5; // Note: this needs to be updated if the min is changed

            for (int j = 0; j < boardLength(); j++) {

                int strategyWeight = getStrategy(playerNumber, i, j)
                        .getStrategyWeight(playerNumber);

                if (strategyWeight > max) {
                    max = strategyWeight;
                }
            }

            for (int k = 0; k < boardLength(); k++) {
                int strategyWeight = getStrategy(playerNumber, i, k)
                        .getStrategyWeight(playerNumber);

                if (strategyWeight == max) {
                    getStrategy(playerNumber, i, k)
                            .setNashCandidateToTrue(playerNumber);
                }
            }
        }
    }

    private Strategy getStrategy(int playerNumber, int opposingPlayer, int selectedPlayer) {
        switch (playerNumber) {
            case 0:
                return strategies[selectedPlayer][opposingPlayer];
            case 1:
                return strategies[opposingPlayer][selectedPlayer];
            default:
                throw new RuntimeException("Something very wrong is happening here. . .you're pretty much screwed");
        }
    }

    private void printNashEqualities() {
        for (int row = 0; row < boardLength(); row++) {
            for (int col = 0; col < boardLength(); col++) {
                if (strategies[row][col].isNashEquilibrium()) {
                    System.out.println("Nash Equilibrium : (" + row + ", " + col + ")");
                }
            }
        }
    }

    void findNashEqualities() {
        findBestStrategiesForPlayer(playerOne);
        findBestStrategiesForPlayer(playerTwo);
        printNashEqualities();
    }
}