package logic.test;

import java.awt.Graphics;
import logic.Board;
import logic.Mocks;
import logic.Track;
import logic.Vehicle;
import static miscallenous.JFrameCommons.setNimbusLookAndFeel;

public class GraphicalGameTest extends javax.swing.JFrame {

    public GraphicalGameTest() {
        initComponents();
        boardG = this.boardPanel.getGraphics();
    }

    public static void main(String args[]) {
        System.out.println("Start line will notbe drawn in this class - check presentation.test.GameTest");
        
        setNimbusLookAndFeel(GraphicalGameTest.class.getName());

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GraphicalGameTest().setVisible(true);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boardPanel = new javax.swing.JPanel();
        btnDrawBoard = new javax.swing.JButton();
        btnDrawRectTrack = new javax.swing.JButton();
        btnDrawTestTrack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 600));

        boardPanel.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        btnDrawBoard.setText("Draw Donut Track");
        btnDrawBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrawBoardActionPerformed(evt);
            }
        });

        btnDrawRectTrack.setText("Draw Rect Track");
        btnDrawRectTrack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrawRectTrackActionPerformed(evt);
            }
        });

        btnDrawTestTrack.setText("Draw Test Track");
        btnDrawTestTrack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrawTestTrackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDrawBoard))
                    .addComponent(btnDrawRectTrack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDrawTestTrack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boardPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnDrawBoard)
                .addGap(38, 38, 38)
                .addComponent(btnDrawRectTrack)
                .addGap(44, 44, 44)
                .addComponent(btnDrawTestTrack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mockDrawTrack(Track track) {
        //this isrequired as class Draw2d is in upper layer

        boardG.drawPolygon(track.innerBound.convertToPolygon());
        boardG.drawPolygon(track.outerBound.convertToPolygon());
    }

    private void mockDrawBoard(Board board) {
        mockDrawTrack(board.track);

        for (Vehicle vehicle : board.vehicles) {
            int x = vehicle.v.position.convertToPoint().x;
            int y = vehicle.v.position.convertToPoint().y;

            boardG.drawOval(x - 2, y - 2, 4, 4);
        }
    }

    private void btnDrawBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawBoardActionPerformed
        Board board = new Board();
        board.track = Mocks.create_50_50__550_550_DonutTrack();

        mockDrawTrack(board.track);
    }//GEN-LAST:event_btnDrawBoardActionPerformed

    private void btnDrawRectTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawRectTrackActionPerformed
        Board board = new Board();
        board.track = Mocks.create_50_50__350_250_RectangularTrack();
        mockDrawTrack(board.track);

    }//GEN-LAST:event_btnDrawRectTrackActionPerformed

    private void btnDrawTestTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawTestTrackActionPerformed
        Board board = new Board();
        board.track = Mocks.create_0_0__30_30_TestRectangularTrack();

        for (int id = 0; id <= 1; id++) {
            board.vehicles.add(Mocks.createVehicleAtPosition(id, 10 + 10 * id, 5));
        }
        mockDrawBoard(board);
    }//GEN-LAST:event_btnDrawTestTrackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPanel;
    private javax.swing.JButton btnDrawBoard;
    private javax.swing.JButton btnDrawRectTrack;
    private javax.swing.JButton btnDrawTestTrack;
    // End of variables declaration//GEN-END:variables
    private final Graphics boardG;

}
