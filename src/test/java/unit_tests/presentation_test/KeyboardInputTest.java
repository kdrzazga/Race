package unit_tests.presentation_test;

import logic.Board;
import logic.BoardBuilder;
import logic.Vehicle;
import logic.VelocityVector;
import org.testng.annotations.Test;
import presentation.KeyboardControlsAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static org.testng.Assert.assertEquals;

public class KeyboardInputTest {

    private static KeyboardControlsAdapter ki;
    private static Board mockBoard;
    private static Vehicle player1;
    private static Vehicle player2;

    private static final Component MOCK_EVENT_SOURCE = new JFrame();
    //<editor-fold defaultstate="collapsed" desc=" Assign keys to KeyEvents ">
    private static final KeyEvent PLAYER_1_ACCELERATE = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, (int) 'W', 'W');
    private static final KeyEvent PLAYER_1_SLOW_DOWN = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, (int) 'S', 'S');
    private static final KeyEvent PLAYER_1_TURN_LEFT = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, (int) 'A', 'A');
    private static final KeyEvent PLAYER_1_TURN_RIGHT = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, (int) 'D', 'D');

    private static final KeyEvent PLAYER_2_ACCELERATE = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, KeyEvent.VK_UP);
    private static final KeyEvent PLAYER_2_SLOW_DOWN = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, KeyEvent.VK_DOWN);
    private static final KeyEvent PLAYER_2_TURN_LEFT = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, KeyEvent.VK_LEFT);
    private static final KeyEvent PLAYER_2_TURN_RIGHT = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, KeyEvent.VK_RIGHT);

    private static final KeyEvent NO_ACTION_KEY_1 = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, KeyEvent.VK_DEAD_TILDE);
    private static final KeyEvent NO_ACTION_KEY_2 = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, (int) 'P', 'P');
    private static final KeyEvent NO_ACTION_KEY_3 = new KeyEvent(MOCK_EVENT_SOURCE, 0, 0, 0, (int) 'w', 'w');
    //</editor-fold>

    @Test
    public static void testForPlayer1() {
        init();
        givenAccelerateKeyPressed_ShouldPlayer1AccelerateToMaxSpeed(player1, PLAYER_1_ACCELERATE);
        testSlowDownToStopOnKeyPressed(player1, PLAYER_1_SLOW_DOWN);
    }

    @Test
    public static void testForBothPlayers() {
        init();

        ki.keyPressed(PLAYER_1_ACCELERATE);
        assertEquals(player1.v.value, VelocityVector.V_MIN + 1, "testForBothPlayers");
        ki.keyPressed(PLAYER_2_ACCELERATE);
        assertEquals(player2.v.value, VelocityVector.V_MIN + 1, "testForBothPlayers");
        ki.keyPressed(NO_ACTION_KEY_1);
        assertEquals(player1.v.value, VelocityVector.V_MIN + 1, "testForBothPlayers");
        assertEquals(player2.v.value, VelocityVector.V_MIN + 1, "testForBothPlayers");

        for (int i = VelocityVector.V_MIN + 1; i < VelocityVector.V_MAX + 3; i++) {
            ki.keyPressed(NO_ACTION_KEY_2);
            ki.keyPressed(PLAYER_1_SLOW_DOWN);
            ki.keyPressed(PLAYER_2_ACCELERATE);
        }

        ki.keyPressed(NO_ACTION_KEY_3);
        assertEquals(player1.v.value, VelocityVector.V_MIN, "testForBothPlayers");
        assertEquals(player2.v.value, VelocityVector.V_MAX, "testForBothPlayers");
    }

    private static void init() {
        mockBoard = BoardBuilder.createBoardWithTrack(2, BoardBuilder.TrackType.RECTANGULAR);
        ki = new KeyboardControlsAdapter(mockBoard);
        player1 = mockBoard.vehicles.get(0);
        player2 = mockBoard.vehicles.get(1);
    }

    private static void givenAccelerateKeyPressed_ShouldPlayer1AccelerateToMaxSpeed(Vehicle player, KeyEvent accelerate) {
        ki.keyPressed(accelerate);
        assertEquals(player.v.value, VelocityVector.V_MIN + 1, "accelerateTillMaxSpeed");
        ki.keyPressed(NO_ACTION_KEY_1);
        ki.keyPressed(accelerate);
        assertEquals(player.v.value, VelocityVector.V_MIN + 2, "accelerateTillMaxSpeed");
        ki.keyPressed(accelerate);
        ki.keyPressed(NO_ACTION_KEY_3);
        for (int exp = VelocityVector.V_MIN + 3; exp <= VelocityVector.V_MAX; exp++) {
            assertEquals(player.v.value, exp, "accelerateTillMaxSpeed");
            ki.keyPressed(accelerate);
        }

        ki.keyPressed(accelerate);
        assertEquals(player.v.value, VelocityVector.V_MAX, "accelerateTillMaxSpeed");

        System.out.println("accelerateTillMaxSpeed passed for " + player.toString());
    }

    private static void testSlowDownToStopOnKeyPressed(Vehicle player, KeyEvent slowDown) {
        GIVEN:
        ki.keyPressed(slowDown);
        ki.keyPressed(slowDown);
        
        WHEN_PLAYER_SLOWS_DOWN_TO_STOP:
        for (int expectedSpeed = VelocityVector.V_MAX - 2; expectedSpeed >= VelocityVector.V_MIN; expectedSpeed--) {
            assertEquals(player.v.value, expectedSpeed, "testForPlayer0");
            ki.keyPressed(slowDown);
        }

        ki.keyPressed(slowDown);
        
        THEN_PLAYER_STOPPED:
        assertEquals(player.v.value, VelocityVector.V_MIN, "testForPlayer0");
        System.out.println("slowDownToStop passed for " + player.toString());
    }

}
