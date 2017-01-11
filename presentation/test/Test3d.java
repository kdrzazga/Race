package presentation.test;

//<editor-fold defaultstate="collapsed" desc=" import OpenGL libraries">
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc=" import AWT + Swing libs">
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
// <editor-fold>
import miscallenous.Mocks;
import logic.Vehicle;
import presentation.Draw3d;

// original author Brian Matzon <brian@matzon.dk>
public class Test3d {

    Canvas displayCanvas;
    Thread gameThread;
    boolean running;

    private JFrame frame;
    private JPanel infoPanel;
    private JPanel activePanel;
    private JTextPane infoPane;
    private Vehicle vehicleToDraw;
    private Draw3d draw3d;

    public static void main(String[] args) {
        Test3d test3d = new Test3d();
    }

    public Test3d() {
        this.initializeFrame();
        this.initializeGlObjects();
    }

    /**
     * Once the Canvas is created its add notify method will call this method to
     * start the LWJGL Display and game loop in another thread.
     */
    public void startMainThread() {
        gameThread = new Thread() {
            @Override
            public void run() {
                running = true;
                try {
                    Display.setParent(displayCanvas);
                    //Display.setVSyncEnabled(true);
                    Display.create();
                } catch (LWJGLException e) {
                    e.printStackTrace();
                }
                performMainGameAction();
            }
        };
        gameThread.start();
    }

    private void performMainGameAction() {
        while (running) {
            System.out.println(".");

            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                System.out.print("_");
                glClearColor(0f, 0f, 0.2f, 0f);
                glColor3f(1f, 0, 0);
               
                glBegin(GL_TRIANGLES);
                    glVertex2f(80, 30);
                    glVertex2f(130, 130);
                    glVertex2f(10, 130);
                glEnd();

                this.draw3d.draw(vehicleToDraw);
                Display.update();

            } else {
                glClearColor(1f, 1f, 1f, 0f);
            }
            glClear(GL_COLOR_BUFFER_BIT);

            Display.sync(10);
        }
        Display.destroy();
    }

    /**
     * Tell game loop to stop running, after which the LWJGL Display will be
     * destoryed. The main thread will wait for the Display.destroy() to
     * complete
     */
    private void stopMainGameActionLoop() {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initializeGlObjects() {
        vehicleToDraw = Mocks.createVehicleAtPosition(0, 4, 4);
        draw3d = new Draw3d(activePanel);
    }

    private void initializeFrame() {
        infoPanel = new JPanel();
        activePanel = new JPanel();
        infoPane = new JTextPane();

        frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.remove(displayCanvas);
                frame.dispose();
            }
        });
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
        frame.getContentPane().add(infoPanel);
        infoPanel.setLayout(null);
        infoPane.setBounds(10, 5, 124, 20);

        infoPanel.add(infoPane);
        frame.getContentPane().add(activePanel);
        activePanel.setLayout(new BorderLayout(0, 0));

        displayCanvas = new Canvas() {
            @Override
            public void addNotify() {
                super.addNotify();
                startMainThread();
            }

            @Override
            public void removeNotify() {
                stopMainGameActionLoop();
                super.removeNotify();
            }
        };
        displayCanvas.setFocusable(true);
        displayCanvas.requestFocus();
        displayCanvas.setIgnoreRepaint(true);
        activePanel.add(displayCanvas);
        frame.setVisible(true);
    }

}
