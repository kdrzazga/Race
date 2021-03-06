package org.kd.presentation;

import org.kd.logic.Board;
import org.kd.logic.Game;
import org.kd.logic.IGraphicalOutput;

import javax.swing.*;
import java.awt.event.ComponentAdapter;

public class GameScreen extends JDialog {

    private final IntroFrame introFrame;
    protected IGraphicalOutput drawOutput;
    public final Game game;

    protected PlayerInfoPanel pnlInfo;
    protected JPanel pnlBoard;

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
        
        var pnlBoardLayout = new GroupLayout(pnlBoard);
        pnlBoard.setLayout(pnlBoardLayout);
        pnlBoardLayout.setHorizontalGroup(
                pnlBoardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 568, Short.MAX_VALUE)
        );
        pnlBoardLayout.setVerticalGroup(
                pnlBoardLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        var layout = new GroupLayout(getContentPane());
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

        var ki = new KeyboardControlsAdapter(game.board);
        this.addKeyListener(ki);

        this.addWindowListener(new GameScreenAdapter(introFrame, this.game));
    }

    public JPanel getPnlBoard() {
        return pnlBoard;
    }
}
