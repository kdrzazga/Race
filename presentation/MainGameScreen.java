package presentation;

import logic.Board;
import logic.Game;
import logic.IGraphicalOutput;

public final class MainGameScreen extends javax.swing.JFrame {

    public Game game;
    private IntroFrame introFrame;
    private IGraphicalOutput drawOutput;
    private Board board;
    private MainGameScreenAdapter mainGameScreenAdapter;

    public MainGameScreen(IntroFrame introFrame) {
        init(introFrame);
    }

    public MainGameScreen(IntroFrame introFrame, IGraphicalOutput drawOutput) {
        this.drawOutput = drawOutput;
        init(introFrame);
    }

    public MainGameScreen(IntroFrame introFrame, Board board) {
        this.board = board;
        init(introFrame);
    }

    public void init(IntroFrame introFrame) {

        this.introFrame = introFrame;
        introFrame.setVisible(false);
        initComponents();
        initComponents2();

        ColorSettings.setBOARD_COLOR(this.pnlBoard.getBackground());
        this.mainGameScreenAdapter = new MainGameScreenAdapter(introFrame);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInfo = new javax.swing.JPanel();
        pnlBoard = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
        });

        pnlInfo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnlInfoLayout = new javax.swing.GroupLayout(pnlInfo);
        pnlInfo.setLayout(pnlInfoLayout);
        pnlInfoLayout.setHorizontalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 146, Short.MAX_VALUE)
        );
        pnlInfoLayout.setVerticalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );

        pnlBoard.setBackground(new java.awt.Color(204, 255, 204));
        pnlBoard.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnlBoardLayout = new javax.swing.GroupLayout(pnlBoard);
        pnlBoard.setLayout(pnlBoardLayout);
        pnlBoardLayout.setHorizontalGroup(
            pnlBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );
        pnlBoardLayout.setVerticalGroup(
            pnlBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void draw() {
        drawOutput.draw(board.track);
    }

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
        draw();
    }//GEN-LAST:event_formComponentMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlBoard;
    private javax.swing.JPanel pnlInfo;
    // End of variables declaration//GEN-END:variables

    private void initComponents2() {
        //this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        KeyboardInput ki = new KeyboardInput(board);
        this.addKeyListener(ki);

        this.addWindowListener(mainGameScreenAdapter);
        
    }
    // <editor-fold defaultstate="collapsed" desc="getters and setters">                          

    public javax.swing.JPanel getPnlBoard() {
        return pnlBoard;
    }

    public void setDrawOutput(IGraphicalOutput drawOutput) {
        this.drawOutput = drawOutput;
    }

    public MainGameScreenAdapter getMainGameScreenAdapter() {
        return mainGameScreenAdapter;
    }
    // </editor-fold>
}
