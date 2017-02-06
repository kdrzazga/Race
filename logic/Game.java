package logic;

import java.util.logging.Level;
import java.util.logging.Logger;
import logic.drive_algorithms.HumanDriveNullObject;

public class Game extends Thread {

    public static final int MIN_VEHICLES = 2;
    public static final int MAX_VEHICLES = 10;
    public static final int NUMBER_OF_HUMAN_CONTROLLED_VEHICLES = 2;
    public static final int GAME_FRAME_MS = 70;

    public int lapsToWin;
    public Board board;

    private int currentPlace;
    private boolean gameRunning;
    private final Object gameRunningLock = new Object();
    private IGraphicalOutput graphicalOutput;
    private IPlayerInfoPanel pnlInfo;

    public Game(int lapsToWin) {
        currentPlace = 1;
        this.lapsToWin = lapsToWin;
        this.gameInit2();
    }

    public void findWinner() {
        for (int i = 0; i < board.vehicles.size(); i++) {
            if (board.vehicles.get(i).laps == this.lapsToWin) {
                board.vehicles.get(i).stop();
                board.vehicles.get(i).active = false;

                board.vehicles.get(i).finalPlace = currentPlace;
                currentPlace++;
            }
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
                    analyzeComputerPlayers();
                    this.board.moveAllVehicles();
                    updateGraphicalOutput();
                    updateInfoPanel();
                    findWinner();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setGraphicalOutput(IGraphicalOutput graphicalOutput) {
        this.graphicalOutput = graphicalOutput;
    }

    private void analyzeComputerPlayers() {
        this.board.vehicles.forEach((vehicle) -> {
            if (!(vehicle.driveAlgorithm instanceof HumanDriveNullObject)) {
                vehicle.driveAlgorithm.computeVelocityVector();
            }
        });
    }

    private void updateGraphicalOutput() {
        this.graphicalOutput.eraseVehicles(this.board);
        this.graphicalOutput.drawStartLine(this.board.track);
        this.graphicalOutput.drawTrackBorder(this.board.track);
        this.graphicalOutput.drawAllVehicles(this.board);
    }

    private void updateInfoPanel() {
        if (this.pnlInfo != null) {
            this.board.vehicles.forEach(vehicle -> {
                this.pnlInfo.setPlayerInfo(vehicle.getId(), vehicle.v.value, vehicle.laps);
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
