package presentation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeMap;

import logic.Board;
import logic.Vehicle;

public class KeyboardInput implements KeyListener {

    private enum Player {
        Player1, Player2
    }

    private enum VehicleAction {
        Accelerate, SlowDown, TurnLeft, TurnRight
    }

    
    private final TreeMap<Integer, VehicleAction> keyVehActionMap;
    private final TreeMap<Integer, Player> keyPlayerMap;

    private final Board board;

    private final int player1Keys[] = {'w', 's', 'a', 'd'};
    private final int player2Keys[] = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};

    private final VehicleAction[] vehAction = {VehicleAction.Accelerate, VehicleAction.SlowDown,
        VehicleAction.TurnLeft, VehicleAction.TurnRight};

    
    public KeyboardInput(Board board) {
        this.board = board;

        this.keyVehActionMap = new TreeMap<>();
        this.assignKeysToVehicleActions();

        this.keyPlayerMap = new TreeMap<>();
        this.assignKeysToPlayers();
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        Vehicle veh = this.translateKeyToVehicle(e.getKeyCode());

        if (veh != null) {
            VehicleAction action = this.keyVehActionMap.get(e.getKeyCode());
            executeActionOnVehicle(veh, action);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //no need to implement
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //no need to implement
    }

    
    private void assignKeysToVehicleActions() {
        for (int i = 0; i < this.player1Keys.length; i++) {
            this.keyVehActionMap.put(this.player1Keys[i], this.vehAction[i]);
        }

        for (int i = 0; i < this.player2Keys.length; i++) {
            this.keyVehActionMap.put(this.player2Keys[i], this.vehAction[i]);
        }
    }

    private void assignKeysToPlayers() {
        for (int i = 0; i < this.player1Keys.length; i++) {
            this.keyPlayerMap.put(this.player1Keys[i], Player.Player1);
        }

        for (int i = 0; i < this.player2Keys.length; i++) {
            this.keyPlayerMap.put(this.player2Keys[i], Player.Player2);
        }
    }

    private Vehicle translateKeyToVehicle(int key) {
        if (this.keyPlayerMap.containsKey(key)) {
            switch (this.keyPlayerMap.get(key)) {
                case Player1:
                    return this.board.vehicles.get(0);
                case Player2:
                    return this.board.vehicles.get(1);
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    private void executeActionOnVehicle(Vehicle veh, VehicleAction vehicleAction) {
        switch (vehicleAction) {
            case Accelerate:
                veh.accelerate();
                break;
            case SlowDown:
                veh.slowDown();
                break;
            case TurnLeft:
                veh.turnLeft();
                break;
            case TurnRight:
                veh.turnRight();
                break;
            default:
                break;
        }
    }

}
