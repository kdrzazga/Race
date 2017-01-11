package presentation.test;

import static org.lwjgl.opengl.GL11.*;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.glColor3f;

// original author Brian Matzon <brian@matzon.dk>
public class Test3d {

    Canvas displayCanvas;
    Thread gameThread;
    boolean running;

    private JFrame frame;
    private final JPanel infoPanel = new JPanel();
    private final JPanel activePanel = new JPanel();
    private final JTextPane infoPane = new JTextPane();

    public static void main(String[] args) {
        Test3d test3d = new Test3d();
    }

    public Test3d() {
        this.initializeFrame();
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
                    //Display.setVSyncEnabled(true);
                    initGL();
                    setupProjectionMatrix();
                } catch (LWJGLException e) {
                    e.printStackTrace();
                }
                performMainGameAction();
            }
        };
        gameThread.start();
    }

    protected void performMainGameAction() {
        int i = 0;
        while (running) {
            System.out.println(i++);

            Display.update();

            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                draw();
                 glClearColor(1f, 0f, 1f, 0f);
            } else {
                glClearColor(1f, 1f, 1f, 0f);
            }
            glClear(GL_COLOR_BUFFER_BIT);

            Display.sync(10);
        }
        Display.destroy();
    }

    private void draw() {
        
        glColor3f(1f, 0, 1f);
        glBegin(GL_TRIANGLES);
            glVertex2f(80, 30);
            glVertex2f(130, 130);
            glVertex2f(10, 130);
        glEnd();
        Display.sync(10);
        //glClearColor(1f, 0, 0, 0);
        System.out.println("magenta");
    }

    private void initGL() throws LWJGLException {
        Display.setParent(displayCanvas);
        Display.setDisplayMode(new DisplayMode(640, 480));
        Display.create();
    }
    private static void setupProjectionMatrix() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity(); // Resets any previous projection matrices
        final double nearValue = 100;
        final double farValue = -10;
        glOrtho(0, 640, 480, 0, nearValue, farValue);
        glMatrixMode(GL_MODELVIEW);
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

    private void initializeFrame() {
        frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.remove(displayCanvas);
                frame.dispose();
            }
        });
        frame.setBounds(100, 100, 450, 300);
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
