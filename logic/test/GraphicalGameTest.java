package logic.test;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import libs.math2.LineSection;

import static miscallenous.JFrameCommons.setNimbusLookAndFeel;
import logic.Board;
import logic.Track;
import logic.Vehicle;
import miscallenous.BoardBuilder;

public class GraphicalGameTest extends JFrame {

    public GraphicalGameTest() {
        initComponents();
        initComponents2();
        
    }

    public static void main(String args[]) {
        setNimbusLookAndFeel(GraphicalGameTest.class.getName());
        
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
        btnDrawSineTrack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1200, 1200));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setPreferredSize(new java.awt.Dimension(700, 600));

        boardPanel.setBackground(new java.awt.Color(204, 255, 204));
        boardPanel.setMaximumSize(new java.awt.Dimension(850, 600));
        boardPanel.setMinimumSize(new java.awt.Dimension(850, 600));
        boardPanel.setPreferredSize(new java.awt.Dimension(850, 600));

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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

        btnDrawSineTrack.setText("Draw Sine Track");
        btnDrawSineTrack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrawSineTrackActionPerformed(evt);
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
                    .addComponent(btnDrawTestTrack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDrawSineTrack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnDrawBoard)
                .addGap(18, 18, 18)
                .addComponent(btnDrawRectTrack)
                .addGap(18, 18, 18)
                .addComponent(btnDrawTestTrack)
                .addGap(18, 18, 18)
                .addComponent(btnDrawSineTrack)
                .addContainerGap(651, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

      private void initComponents2() {
          boardG = this.boardPanel.getGraphics();
      }
    private void mockDrawTrack(Track track) {
        //this is required as class Draw2d is in upper layer
        boardG.drawPolygon(track.innerBound.convertToPolygon());
        boardG.drawPolygon(track.outerBound.convertToPolygon());
    }

    private void mockDrawBoard(Board board) {
        mockDrawTrack(board.track);

        for (Vehicle vehicle : board.vehicles) {
            int x = vehicle.v.position.convertToPoint().x;
            int y = vehicle.v.position.convertToPoint().y;

            boardG.drawOval(x - 2, y - 2, 4, 4);
            LineSection startLine = board.track.computeVerticalStartLine();
            Point p1 = startLine.p1.convertToPoint();
            Point p2 = startLine.p2.convertToPoint();
            boardG.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    private void btnDrawBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawBoardActionPerformed
        Board board = BoardBuilder.createBoardWithTrack(3, BoardBuilder.TrackType.DONUT);
        mockDrawBoard(board);
    }//GEN-LAST:event_btnDrawBoardActionPerformed

    private void btnDrawRectTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawRectTrackActionPerformed
        Board board = BoardBuilder.createBoardWithTrack(3, BoardBuilder.TrackType.RECTANGULAR_1);
        mockDrawBoard(board);
    }//GEN-LAST:event_btnDrawRectTrackActionPerformed

    private void btnDrawTestTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawTestTrackActionPerformed
        Board board = BoardBuilder.createBoardWithTrack(3, BoardBuilder.TrackType.TEST_RECTANGULAR);
        mockDrawBoard(board);
    }//GEN-LAST:event_btnDrawTestTrackActionPerformed

    private void btnDrawSineTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawSineTrackActionPerformed
        Board board = BoardBuilder.createBoardWithTrack(3, BoardBuilder.TrackType.SINE);
        mockDrawBoard(board);
    }//GEN-LAST:event_btnDrawSineTrackActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPanel;
    private javax.swing.JButton btnDrawBoard;
    private javax.swing.JButton btnDrawRectTrack;
    private javax.swing.JButton btnDrawSineTrack;
    private javax.swing.JButton btnDrawTestTrack;
    // End of variables declaration//GEN-END:variables
    private Graphics boardG;

}
