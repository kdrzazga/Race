package presentation;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import java.awt.event.ComponentAdapter;

import logic.Board;
import logic.Game;
import logic.IGraphicalOutput;
import logic.InfoPanel;

public class GameScreen extends JFrame {

    private final IntroFrame introFrame;
    private final IGraphicalOutput drawOutput;
    public final Game game;

    private InfoPanel pnlInfo;
    private JPanel pnlBoard;

    public GameScreen(IntroFrame introFrame, Game game) {

        this.introFrame = introFrame;
        this.game = game;
        initComponents();
        this.game.setPnlInfo(this.pnlInfo);
        introFrame.setVisible(false);
        initComponents2();

        this.drawOutput = new Draw2d(this.pnlBoard);
        ColorSettings.setBOARD_COLOR(this.pnlBoard.getBackground());
        draw();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
        });
        
        this.pnlInfo = new InfoPanel(this.game.board.vehicles.size());

        this.pnlBoard = new JPanel();

        GroupLayout pnlBoardLayout = new GroupLayout(pnlBoard);
        pnlBoard.setLayout(pnlBoardLayout);
        pnlBoardLayout.setHorizontalGroup(
                pnlBoardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 568, Short.MAX_VALUE)
        );
        pnlBoardLayout.setVerticalGroup(
                pnlBoardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(pnlInfo, GroupLayout.PREFERRED_SIZE + 50, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnlBoard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pnlInfo, GroupLayout.DEFAULT_SIZE + 50, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlBoard, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        this.pnlBoard.setBackground(new Color(204, 255, 204));

        this.pack();
        this.setVisible(true);
    }

    private void draw() {
        drawOutput.draw(game.board);
    }

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {
        draw();
    }

    private void initComponents2() {
        Board brd = game.board;

        KeyboardInput ki = new KeyboardInput(brd);
        this.addKeyListener(ki);

        this.addWindowListener(new GameScreenAdapter(introFrame, this.game));
    }

    public JPanel getPnlBoard() {
        return pnlBoard;
    }
}
