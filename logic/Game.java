package logic;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends Thread {

    public static final int MIN_VEHICLES = 2;
    public static final int MAX_VEHICLES = 10;
    public static final int GAME_FRAME_MS = 70;
    public Board board;

    private boolean gameRunning;
    private final Object gameRunningLock = new Object();
    private IGraphicalOutput graphicalOutput;
    private IPlayerInfoPanel pnlInfo;

    public Game(int numberOfVehicles, Track track) {

        this.board = new Board(numberOfVehicles, track);
        this.gameInit(numberOfVehicles);
        this.gameInit2();
    }

    public Game(Board board) {
        this.board = board;
        this.gameInit(board.vehicles.size());
        this.gameInit2();
    }

    public Game() {
        this.gameInit2();
    }

    public void findWinner() {
        System.out.println(" findWinner() - Not implemented yet. "
                + "Won't be implemented until travelledWay works correctly");
    }

    private void gameInit(int numberOfVehicles) {
        if ((numberOfVehicles < MIN_VEHICLES) || (numberOfVehicles > MAX_VEHICLES)) {
            throw new RuntimeException("Creating a game with wrong number of players (vehicles) + "
                    + numberOfVehicles + ". Only " + MIN_VEHICLES + " to "
                    + MAX_VEHICLES + " available");
        }
    }

    private void gameInit2() {
        this.gameRunning = false;
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(GAME_FRAME_MS);

                if (this.gameRunning) {
                    this.board.moveAllVehicles();
                    updateGraphicalOutput();
                    updateInfoPanel();
                    this.findWinner();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setGraphicalOutput(IGraphicalOutput graphicalOutput) {
        this.graphicalOutput = graphicalOutput;
    }

    private void updateGraphicalOutput() {
        this.graphicalOutput.eraseVehicles(this.board);
        this.graphicalOutput.drawAllVehicles(this.board);
        this.graphicalOutput.drawTrackBorder(this.board.track);
    }

    private void updateInfoPanel() {
        if (this.pnlInfo != null) {
            this.board.vehicles.forEach(vehicle -> {
                this.pnlInfo.setPlayerInfo(vehicle.getId(), vehicle.v.value, vehicle.travelledWayAngle);
            });
        } else {
            System.out.println("Info panel not created - update not possible");
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

    public void setPnlInfo(IPlayerInfoPanel pnlInfo) {
        this.pnlInfo = pnlInfo;
    }
}
