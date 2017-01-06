package logic;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends Thread {

    public static final int MIN_VEHICLES = 2;
    public static final int MAX_VEHICLES = 5;
    public static final int GAME_FRAME_MS = 500;

    private boolean gameRunning;
    private final Object gameRunningLock = new Object();

    public Game(int numberOfVehicles, Track track) {

        this.board = new Board(numberOfVehicles, track);
        this.gameInit(numberOfVehicles);
    }

    public Game(Board board) {
        this.board = board;
        this.gameInit(board.vehicles.size());
    }

    public Board board;

    public void findWinner() {
        System.out.println (" findWinner() - Not implemented yet");
    }

    private void gameInit(int numberOfVehicles) {
        if (numberOfVehicles > MAX_VEHICLES) {
            throw new RuntimeException("Creating a game with wrong number of players (vehicles) + "
                    + numberOfVehicles + ". Only " + MIN_VEHICLES + " to "
                    + MAX_VEHICLES + " available");
        }

        this.gameRunning = false;

        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(GAME_FRAME_MS);

                System.out.print("_" + board.vehicles.size());

                if (this.gameRunning) {
                    for (int i = 0; i < board.vehicles.size(); i++) {
                        board.moveVehicle(i);
                        System.out.println(board.vehicles.get(0).v.position);
                    }

                    this.findWinner();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isGameRunning() {
        synchronized (gameRunningLock) {
            return gameRunning;
        }
    }

    public void setGameRunning(boolean gameRunning) {
        synchronized (gameRunningLock) {
            this.gameRunning = gameRunning;
        }
    }

}
