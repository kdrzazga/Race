package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import java.awt.event.ComponentAdapter;

import logic.Board;
import logic.Game;
import logic.IGraphicalOutput;

public class GameScreen extends JFrame {

    private final IntroFrame introFrame;
    private final IGraphicalOutput drawOutput;
    public final Game game;

    private PlayerInfoPanel pnlInfo;
    private JPanel pnlBoard;

    public GameScreen(IntroFrame introFrame, Game game) {
        this.introFrame = introFrame;
        this.game = game;
        initComponents();
        this.game.setPnlInfo(this.pnlInfo);  
        this.drawOutput = new Draw2d(this.pnlBoard);
        draw();
    }

    private void initComponents() {
        this.initPanels();
        this.initFrames();         
    }

    private void initFrames() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponentMovedAction();
        initKeyboardAdapter();
        
        this.pack();
        this.setVisible(true);        
        this.introFrame.setVisible(false);
    }

    private void initComponentMovedAction() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
        });
    }

    private void initPanels() {
        this.pnlInfo = new PlayerInfoPanel(this.game.board.vehicles.size());
        
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

        this.pnlBoard.setBackground(ColorSettings.BOARD_COLOR);
    }

    private void draw() {
        drawOutput.draw(game.board);
    }

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {
        draw();
    }

    private void initKeyboardAdapter() {
        Board brd = game.board;

        KeyboardControlsAdapter ki = new KeyboardControlsAdapter(brd);
        this.addKeyListener(ki);

        this.addWindowListener(new GameScreenAdapter(introFrame, this.game));
    }

    public JPanel getPnlBoard() {
        return pnlBoard;
    }
}
