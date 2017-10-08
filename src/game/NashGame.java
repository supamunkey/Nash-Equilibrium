import model.Player;
import model.Strategy;

public class NashGame {

    private Strategy[][] strategies;

    private Player playerOne;
    private Player playerTwo;

    NashGame(String playerOne, String playerTwo, int boardSize) {
        initializeGameBoard(boardSize);
        this.playerOne = new Player(playerOne, 1);
        this.playerTwo = new Player(playerTwo, 2);
    }

    /**
     * Initializes the games strategies board for an n*n game board
     *
     * @param boardSize The size of the game board e.g an n*n board where
     *                  n is the boardSize
     */
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
     *
     * @exception RuntimeException Throws exception if the playerIndex
     *                             exceeds the supported amount of players
     */
    public void addStrategyToBoard(int playerIndex, int row, int column, int value) {
        if (playerIndex > 1) {
            throw new RuntimeException("This game only supports 2 players");
        }

        strategies[row][column].addStrategy(playerIndex, value);
    }

    public String getPlayerName(int player) {
        if (player > 2 || player < 1) {
            throw new RuntimeException("Unable to find player " + player);
        }

        if (player == 1) {
            return playerOne.getPlayerName();
        }
        return playerTwo.getPlayerName();
    }

    public int boardLength() {
        return strategies.length;
    }

    public Strategy[][] getBoard() {
        return strategies;
    }

    /**
     * Traverses the strategies of a given player and sets their
     * ideal strategies, as possible nash candidate, flag to true
     *
     * @param player    The player who's strategies are to be examined.
     */
    private void findBestStrategiesForPlayer(Player player) {

        int playerNumber = player.getPlayerNumber();

        for (int i = 0; i < boardLength(); i++) {
            int max = -5; // Note: this needs to be updated if the min is changed

            /* Run through the players strategies to find the ideal choice*/
            for (int j = 0; j < boardLength(); j++) {
                int strategyWeight = getStrategy(playerNumber, i, j)
                        .getStrategyWeight(playerNumber);

                if (strategyWeight > max) {
                    max = strategyWeight;
                }
            }

            /* If a players strategy was found to be the ideal choice, set the candidate flag to true*/
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

    /**
     * Returns a strategy object.
     *
     * @param playerNumber      The player who's strategy is inspected
     *
     * @param opposingPlayer    The players who's strategy is inspected
     *                          relative to the selectedPlayer
     *
     * @param selectedPlayer    The players who's strategy is inspected
     *
     * @return                  The strategy object containing the weighted
     *                          strategies for both players
     *
     * @exception RuntimeException Throws a runtime exception in the event the opposingPlayer and/or
     *                             selectedPlayer is outside of the bounds of the strategies array
     */
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

    /**
     * Checks to see if a game board contains a nash equilibrium
     * and displays all, if found
     */
    public void findNashEqualities() {
        findBestStrategiesForPlayer(playerOne);
        findBestStrategiesForPlayer(playerTwo);
        printNashEqualities();
    }
}