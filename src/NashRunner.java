import game.NashGame;
import util.NashGameUtil;

public class NashRunner {
    public static void main(String[] args) {

        NashGame game = new NashGame("Tom", "Bob", 4);
        NashGameUtil.generateBoard(game);

        NashGameUtil.displayBoard(game);

        game.findNashEqualities();
    }
}
