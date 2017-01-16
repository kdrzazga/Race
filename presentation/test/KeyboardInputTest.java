package presentation.test;

import presentation.KeyboardInput;
import static libs.Assert.assertion;
import logic.Board;
import logic.Vehicle;
import logic.VelocityVector;
import miscallenous.Mocks;

import java.awt.Component;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class KeyboardInputTest {

    private static KeyboardInput ki;
    private static Board mockBoard;
    private static Vehicle player1;
    private static Vehicle player2;

    private static final Component MOCK_EVENT_SOURCE = new JFrame();
     //<editor-fold defaultstate="collapsed" desc=" Assign keys to KeyEvents ">
    private static final KeyEvent PLAYER_1_ACCELERATE = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, (int) 'w', 'w');
    private static final KeyEvent PLAYER_1_SLOW_DOWN = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, (int) 's', 's');
    private static final KeyEvent PLAYER_1_TURN_LEFT = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, (int) 'a', 'a');
    private static final KeyEvent PLAYER_1_TURN_RIGHT = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, (int) 'a', 'a');

    private static final KeyEvent PLAYER_2_ACCELERATE = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, KeyEvent.VK_UP);
    private static final KeyEvent PLAYER_2_SLOW_DOWN = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, KeyEvent.VK_DOWN);
    private static final KeyEvent PLAYER_2_TURN_LEFT = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, KeyEvent.VK_LEFT);
    private static final KeyEvent PLAYER_2_TURN_RIGHT = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, KeyEvent.VK_RIGHT);

    private static final KeyEvent NO_ACTION_KEY_1 = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, KeyEvent.VK_DEAD_TILDE);
    private static final KeyEvent NO_ACTION_KEY_2 = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, (int) 'p', 'p');
    private static final KeyEvent NO_ACTION_KEY_3 = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, (int) 'W', 'W');
    //</editor-fold>

    public static void main(String args[]) {
        testForPlayer1();
        testForBothPlayers();

        System.out.println("No exception occured. All tests PASSED");
    }
    
    private static void testForPlayer1() {
        init();
        givenAccelerateKeyPressed_ShouldPlayer1AccelerateToMaxSpeed(player1, PLAYER_1_ACCELERATE);
        givenSlowDownKeyPressed_ShouldPlayer1SlowDownToStop(player1, PLAYER_1_SLOW_DOWN);
    }

    private static void testForBothPlayers() {
        init();

        ki.keyPressed(PLAYER_1_ACCELERATE);
        assertion(player1.v.value, 1, "testForBothPlayers");
        ki.keyPressed(PLAYER_2_ACCELERATE);
        assertion(player2.v.value, 1, "testForBothPlayers");
        ki.keyPressed(NO_ACTION_KEY_1);
        assertion(player1.v.value, 1, "testForBothPlayers");
        assertion(player2.v.value, 1, "testForBothPlayers");

        for (int i = 2; i < 10; i++) {
            ki.keyPressed(NO_ACTION_KEY_2);
            ki.keyPressed(PLAYER_1_SLOW_DOWN);
            ki.keyPressed(PLAYER_2_ACCELERATE);
        }

        ki.keyPressed(NO_ACTION_KEY_3);
        assertion(player1.v.value, VelocityVector.V_MIN, "testForBothPlayers");
        assertion(player2.v.value, VelocityVector.V_MAX, "testForBothPlayers");
    }

    
    private static void init() {
        mockBoard = Mocks.createBoardWithNVehiclesOnTrack(2, Mocks.TrackType.RECTANGULAR_1);
        ki = new KeyboardInput(mockBoard);
        player1 = mockBoard.vehicles.get(0);
        player2 = mockBoard.vehicles.get(1);
    }

    private static void givenAccelerateKeyPressed_ShouldPlayer1AccelerateToMaxSpeed(Vehicle player, KeyEvent accelerate) {
        ki.keyPressed(accelerate);
        assertion(player.v.value, 1, "accelerateTillMaxSpeed");
        ki.keyPressed(NO_ACTION_KEY_1);
        ki.keyPressed(accelerate);
        assertion(player.v.value, 2, "accelerateTillMaxSpeed");
        ki.keyPressed(accelerate);
        ki.keyPressed(NO_ACTION_KEY_3);
        for (int exp = 3; exp <= 5; exp++)
        {
        assertion(player.v.value, exp, "accelerateTillMaxSpeed");
        ki.keyPressed(accelerate);
        }
        
        ki.keyPressed(accelerate);
        assertion(player.v.value, 5, "accelerateTillMaxSpeed");

        System.out.println("accelerateTillMaxSpeed passed for " + player.toString());
    }

    private static void givenSlowDownKeyPressed_ShouldPlayer1SlowDownToStop(Vehicle player, KeyEvent slowDown) {
        ki.keyPressed(slowDown);
        ki.keyPressed(slowDown);
        assertion(player.v.value, 3, "testForPlayer0");
        ki.keyPressed(slowDown);
        assertion(player.v.value, 2, "testForPlayer0");
        ki.keyPressed(slowDown);
        assertion(player.v.value, 1, "testForPlayer0");
        ki.keyPressed(slowDown);
        assertion(player.v.value, 0, "testForPlayer0");
        ki.keyPressed(slowDown);
        assertion(player.v.value, 0, "testForPlayer0");
        System.out.println("slowDownToStop passed for " + player.toString());
    }
   
}
