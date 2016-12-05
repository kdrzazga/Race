package logic;

public class Game {

    private static Game instance = null;

    public Board board;

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void findWinner() {

    }

    public void start() {

    }

}
