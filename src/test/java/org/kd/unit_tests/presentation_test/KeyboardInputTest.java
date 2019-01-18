package org.kd.unit_tests.presentation_test;

import org.kd.logic.Board;
import org.kd.logic.Vehicle;
import org.kd.logic.VelocityVector;
import org.kd.logic.creation.BoardBuilder;
import org.kd.presentation.KeyboardControlsAdapter;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.stream.IntStream;

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

        final var methodName = "testForBothPlayers";

        ki.keyPressed(PLAYER_1_ACCELERATE);
        assertEquals(player1.v.value, VelocityVector.V_MIN + 1, methodName);
        ki.keyPressed(PLAYER_2_ACCELERATE);
        assertEquals(player2.v.value, VelocityVector.V_MIN + 1, methodName);
        ki.keyPressed(NO_ACTION_KEY_1);
        assertEquals(player1.v.value, VelocityVector.V_MIN + 1, methodName);
        assertEquals(player2.v.value, VelocityVector.V_MIN + 1, methodName);

        IntStream.range(VelocityVector.V_MIN + 1, VelocityVector.V_MAX + 3)
                .forEach(i -> {
            ki.keyPressed(NO_ACTION_KEY_2);
            ki.keyPressed(PLAYER_1_SLOW_DOWN);
            ki.keyPressed(PLAYER_2_ACCELERATE);
        });

        ki.keyPressed(NO_ACTION_KEY_3);
        assertEquals(player1.v.value, VelocityVector.V_MIN, methodName);
        assertEquals(player2.v.value, VelocityVector.V_MAX, methodName);
    }

    private static void init() {
        mockBoard = new BoardBuilder()
                .createBoard()
                .withTrack(BoardBuilder.TrackType.RECTANGULAR)
                .withVehicles(2)
                .build();

        ki = new KeyboardControlsAdapter(mockBoard);
        player1 = mockBoard.vehicles.get(0);
        player2 = mockBoard.vehicles.get(1);
    }

    private static void givenAccelerateKeyPressed_ShouldPlayer1AccelerateToMaxSpeed(Vehicle player, KeyEvent accelerate) {
        ki.keyPressed(accelerate);
        var methodName = "accelerateTillMaxSpeed";
        assertEquals(player.v.value, VelocityVector.V_MIN + 1, methodName);

        ki.keyPressed(NO_ACTION_KEY_1);
        ki.keyPressed(accelerate);
        assertEquals(player.v.value, VelocityVector.V_MIN + 2, methodName);

        ki.keyPressed(accelerate);
        ki.keyPressed(NO_ACTION_KEY_3);

        IntStream.range(VelocityVector.V_MIN + 3, VelocityVector.V_MAX).forEach(exp->{
            assertEquals(player.v.value, exp, methodName);
            ki.keyPressed(accelerate);
        });

        ki.keyPressed(accelerate);
        assertEquals(player.v.value, VelocityVector.V_MAX, methodName);

        System.out.println("accelerateTillMaxSpeed passed for " + player.toString());
    }

    private static void testSlowDownToStopOnKeyPressed(Vehicle player, KeyEvent slowDown) {
        final var methodName = "testForPlayer0";

        GIVEN:
        ki.keyPressed(slowDown);
        ki.keyPressed(slowDown);

        WHEN_PLAYER_SLOWS_DOWN_TO_STOP:

        for (int expectedSpeed = VelocityVector.V_MAX - 2; expectedSpeed >= VelocityVector.V_MIN; expectedSpeed--) {
            assertEquals(player.v.value, expectedSpeed, methodName);
            ki.keyPressed(slowDown);
        }

        ki.keyPressed(slowDown);

        THEN_PLAYER_STOPPED:
        assertEquals(player.v.value, VelocityVector.V_MIN, methodName);
        System.out.println("slowDownToStop passed for " + player.toString());
    }

}
