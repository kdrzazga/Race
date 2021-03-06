package org.kd.logic;

import org.kd.logic.drive_algorithms.HumanDriveNullObject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends Thread {

    public static final int MIN_VEHICLES = 2;
    public static final int MAX_VEHICLES = 10;
    public static final int NUMBER_OF_HUMAN_CONTROLLED_VEHICLES = 2;
    public static final int GAME_FRAME_MS = 70;

    public static int lapsToWin;
    public static Board board;

    private int currentPlace;
    private boolean gameRunning;
    private final Object gameRunningLock = new Object();
    private IGraphicalOutput graphicalOutput;
    private IPlayerInfoPanel pnlInfo;

    public Game() {
        this.gameInit2();
    }

    public void findWinner() {
        board.vehicles.stream()
                .filter(Vehicle::isActive)
                .forEach(vehicle -> {
                    if ( vehicle.getLaps() == this.lapsToWin + 1) {//laps counted from 1 not from 0
                        vehicle.stop();
                        vehicle.setActive(false);

                        vehicle.finalPlace = currentPlace;
                        currentPlace++;
                    }
                }
        );
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
        this.board.vehicles.forEach(vehicle -> {
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
                if (vehicle.finalPlace <= 0) {//no final place = race not finished yet
                    this.pnlInfo.setRunningPlayerInfo(vehicle.getId(), vehicle.v.value, vehicle.getLaps());
                } else {
                    this.pnlInfo.setPlayerWinnerInfo(vehicle.getId(), vehicle.finalPlace);
                }
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
            this.reset(lapsToWin);
        }
    }

    public void reset(int lapsToWin) {
        currentPlace = 1;
        this.lapsToWin = lapsToWin;
    }

    public void setPnlInfo(IPlayerInfoPanel pnlInfo) {
        this.pnlInfo = pnlInfo;
    }
}
