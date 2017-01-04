package presentation.test;

import java.awt.Component;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import presentation.KeyboardInput;
import static libs.Assert.assertion;
import logic.Board;
import logic.Vehicle;
import logic.VelocityVector;
import logic.Mocks;

public class KeyboardInputTest {

    private static KeyboardInput ki;
    private static Board mockBoard;
    private static Vehicle player1;
    private static Vehicle player2;

    private static final Component mockEventSource = new JFrame();
    private static final KeyEvent player1Accelerate = new KeyEvent(mockEventSource, 0, 0, 0, (int) 'w', 'w');
    private static final KeyEvent player1SlowDown = new KeyEvent(mockEventSource, 0, 0, 0, (int) 's', 's');
    private static final KeyEvent player1TurnLeft = new KeyEvent(mockEventSource, 0, 0, 0, (int) 'a', 'a');
    private static final KeyEvent player1TurnRight = new KeyEvent(mockEventSource, 0, 0, 0, (int) 'a', 'a');

    private static final KeyEvent player2Accelerate = new KeyEvent(mockEventSource, 0, 0, 0, KeyEvent.VK_UP);
    private static final KeyEvent player2SlowDown = new KeyEvent(mockEventSource, 0, 0, 0, KeyEvent.VK_DOWN);
    private static final KeyEvent player2TurnLeft = new KeyEvent(mockEventSource, 0, 0, 0, KeyEvent.VK_LEFT);
    private static final KeyEvent player2TurnRight = new KeyEvent(mockEventSource, 0, 0, 0, KeyEvent.VK_RIGHT);

    private static final KeyEvent noActionKey1 = new KeyEvent(mockEventSource, 0, 0, 0, KeyEvent.VK_DEAD_TILDE);
    private static final KeyEvent noActionKey2 = new KeyEvent(mockEventSource, 0, 0, 0, (int) 'p', 'p');
    private static final KeyEvent noActionKey3 = new KeyEvent(mockEventSource, 0, 0, 0, (int) 'W', 'W');

    private static void init() {
        mockBoard = Mocks.createBoardWith2VehiclesOnTrack(Mocks.TrackType.RECTANGULAR_1);
        ki = new KeyboardInput(mockBoard);
        player1 = mockBoard.vehicles.get(0);
        player2 = mockBoard.vehicles.get(1);
    }

    private static void testForPlayer0() {
        init();

        ki.keyPressed(player1Accelerate);
        assertion(player1.v.value, 2, "testForPlayer0");
        ki.keyPressed(player1Accelerate);
        assertion(player1.v.value, 3, "testForPlayer0");
        ki.keyPressed(player1Accelerate);
        assertion(player1.v.value, 4, "testForPlayer0");
        ki.keyPressed(player1Accelerate);
        assertion(player1.v.value, 5, "testForPlayer0");
        ki.keyPressed(player1Accelerate);
        assertion(player1.v.value, 5, "testForPlayer0");

        ki.keyPressed(player1SlowDown);
        ki.keyPressed(player1SlowDown);
        assertion(player1.v.value, 3, "testForPlayer0");
        ki.keyPressed(player1SlowDown);
        assertion(player1.v.value, 2, "testForPlayer0");
        ki.keyPressed(player1SlowDown);
        assertion(player1.v.value, 1, "testForPlayer0");
        ki.keyPressed(player1SlowDown);
        assertion(player1.v.value, 0, "testForPlayer0");
        ki.keyPressed(player1SlowDown);
        assertion(player1.v.value, 0, "testForPlayer0");
    }

    private static void testForBothPlayers() {
        init();

        ki.keyPressed(player1Accelerate);
        assertion(player1.v.value, 2, "testForBothPlayers");
        ki.keyPressed(player2Accelerate);
        assertion(player2.v.value, 2, "testForBothPlayers");
        ki.keyPressed(noActionKey1);
        assertion(player1.v.value, 2, "testForBothPlayers");
        assertion(player2.v.value, 2, "testForBothPlayers");

        for (int i = 2; i < 10; i++) {
            ki.keyPressed(noActionKey2);
            ki.keyPressed(player1SlowDown);
            ki.keyPressed(player2Accelerate);
        }

        ki.keyPressed(noActionKey3);
        assertion(player1.v.value, VelocityVector.V_MIN, "testForBothPlayers");
        assertion(player2.v.value, VelocityVector.V_MAX, "testForBothPlayers");
    }

    public static void main(String args[]) {
        testForPlayer0();
        testForBothPlayers();

        System.out.println("No exception occured. All tests PASSED");
    }
}
