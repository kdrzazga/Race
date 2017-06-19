package graphical_tests;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import libs.Colors;
import libs.math2.LineSection;

import static libs.JFrameDialogCommons.setNimbusLookAndFeel;
import libs.math2.PolygonAG;
import logic.Board;
import logic.Track;
import logic.Vehicle;
import logic.drive_algorithms.FollowRouteForCmpPlr;
import logic.BoardBuilder;
import logic.VelocityVector;

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
        btnDrawKidneyTrack = new javax.swing.JButton();
        btnTriangleTrack = new javax.swing.JButton();
        btnPentagonTrack = new javax.swing.JButton();
        cbTrackCenter = new javax.swing.JCheckBox();
        cbInnerCenter = new javax.swing.JCheckBox();
        cbDesiredRoute = new javax.swing.JCheckBox();
        cbCheckpoints = new javax.swing.JCheckBox();
        btnWeronikaTrack = new javax.swing.JButton();
        btnMoveVehicle0 = new javax.swing.JButton();
        btnVehicleTurnRight = new javax.swing.JButton();
        sliderVelocity = new javax.swing.JSlider();
        lblMousePoint = new javax.swing.JLabel();
        btnDrawnDownFromCenter = new javax.swing.JButton();
        cbTrackCentroid = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1200, 1200));
        setMinimumSize(new java.awt.Dimension(500, 900));
        setPreferredSize(new java.awt.Dimension(850, 700));

        boardPanel.setBackground(new java.awt.Color(204, 255, 204));
        boardPanel.setMaximumSize(new java.awt.Dimension(850, 600));
        boardPanel.setMinimumSize(new java.awt.Dimension(850, 600));
        boardPanel.setPreferredSize(new java.awt.Dimension(850, 600));
        boardPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                boardPanelMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 909, Short.MAX_VALUE)
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 828, Short.MAX_VALUE)
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

        btnDrawKidneyTrack.setText("Draw Kindey Track");
        btnDrawKidneyTrack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrawKidneyTrackActionPerformed(evt);
            }
        });

        btnTriangleTrack.setText("Draw Triangle Track");
        btnTriangleTrack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTriangleTrackActionPerformed(evt);
            }
        });

        btnPentagonTrack.setText("Draw Pentagon Track");
        btnPentagonTrack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPentagonTrackActionPerformed(evt);
            }
        });

        cbTrackCenter.setBackground(new java.awt.Color(0, 0, 0));
        cbTrackCenter.setForeground(new java.awt.Color(255, 0, 51));
        cbTrackCenter.setText("Draw center");

        cbInnerCenter.setBackground(new java.awt.Color(0, 0, 0));
        cbInnerCenter.setForeground(new java.awt.Color(51, 51, 255));
        cbInnerCenter.setText("Draw inner bnd center");

        cbDesiredRoute.setText("Draw desired route");

        cbCheckpoints.setText("Draw checkpoints");

        btnWeronikaTrack.setText("Draw Weronika Track");
        btnWeronikaTrack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWeronikaTrackActionPerformed(evt);
            }
        });

        btnMoveVehicle0.setText("Move vehcile0");
        btnMoveVehicle0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveVehicle0ActionPerformed(evt);
            }
        });

        btnVehicleTurnRight.setText("Vehicle Turn Right");
        btnVehicleTurnRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVehicleTurnRightActionPerformed(evt);
            }
        });

        sliderVelocity.setMaximum(15);

        lblMousePoint.setText("()");

        btnDrawnDownFromCenter.setText("Drawn Down From Center");
        btnDrawnDownFromCenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrawnDownFromCenterActionPerformed(evt);
            }
        });

        cbTrackCentroid.setBackground(new java.awt.Color(0, 0, 0));
        cbTrackCentroid.setForeground(new java.awt.Color(204, 255, 255));
        cbTrackCentroid.setText("Draw centroid");
        cbTrackCentroid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTrackCentroidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDrawRectTrack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDrawTestTrack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDrawSineTrack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDrawKidneyTrack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTriangleTrack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPentagonTrack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbTrackCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbInnerCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDrawBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbDesiredRoute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbCheckpoints, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnWeronikaTrack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMoveVehicle0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVehicleTurnRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sliderVelocity, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lblMousePoint, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDrawnDownFromCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cbTrackCentroid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnDrawBoard)
                        .addGap(18, 18, 18)
                        .addComponent(btnDrawRectTrack)
                        .addGap(18, 18, 18)
                        .addComponent(btnDrawTestTrack)
                        .addGap(18, 18, 18)
                        .addComponent(btnDrawSineTrack)
                        .addGap(18, 18, 18)
                        .addComponent(btnDrawKidneyTrack)
                        .addGap(18, 18, 18)
                        .addComponent(btnTriangleTrack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPentagonTrack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnWeronikaTrack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMoveVehicle0)
                        .addGap(18, 18, 18)
                        .addComponent(sliderVelocity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVehicleTurnRight)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDrawnDownFromCenter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMousePoint)
                        .addGap(61, 61, 61)
                        .addComponent(cbTrackCentroid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTrackCenter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbInnerCenter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbDesiredRoute)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbCheckpoints))
                    .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initComponents2() {
        boardG = this.boardPanel.getGraphics();
        this.sliderVelocity.setExtent(VelocityVector.V_MAX);
    }

    private void clearBoard() {
        this.boardG.setColor(this.getBackground());
        this.boardG.fillRect(0, 0, this.boardPanel.getWidth(), this.boardPanel.getHeight());
        this.boardG.setColor(Color.BLACK);
    }

    private void mockDrawTrack(Track track) {
        //this is required as class Draw2d is in upper layer
        boardG.drawPolygon(track.innerBound.convertToPolygon());
        boardG.drawPolygon(track.outerBound.convertToPolygon());
        drawStartLine(track);
    }

    private void mockDrawBoard(BoardWrapper board) {
        clearBoard();

        if (cbTrackCenter.isSelected()) {
            drawTrackCenter(board);
        }

        if (cbTrackCentroid.isSelected()) {
            drawCentroid(board);
        }
        
        if (cbInnerCenter.isSelected()) {
            drawInnerBoundCenter(board);
        }

        if (cbDesiredRoute.isSelected()) {
            drawDesiredRoute(board);
        }

        if (cbCheckpoints.isSelected()) {

            int colorIndex = 0;
            for (PolygonAG checkpoint : board.track.checkpoints) {
                Color color = Colors.getDarkColor(colorIndex);
                boardG.setColor(color);
                System.out.print("Color " + color + " index " + colorIndex + "\t");
                boardG.drawPolygon(checkpoint.convertToPolygon());
                colorIndex++;
            }
        }
        boardG.setColor(Color.BLACK);
        mockDrawTrack(board.track);

        drawVehicles(board);
    }

    private void drawVehicles(Board board) {
        board.vehicles.forEach((vehicle) -> {
            int x = vehicle.v.position.convertToPoint().x;
            int y = vehicle.v.position.convertToPoint().y;

            boardG.drawOval(x - 2, y - 2, 4, 4);
        });
    }

    private void drawStartLine(Track track) {
        LineSection startLine = track.computeVerticalStartLine();
        Point p1 = startLine.p1.convertToPoint();
        Point p2 = startLine.p2.convertToPoint();
        boardG.drawLine(p1.x, p1.y, p2.x, p2.y);
    }

    private void drawInnerBoundCenter(Board board) {
        Point innerBoundCenter = board.track.innerBound.computeCenter().convertToPoint();
        boardG.setColor(Color.BLUE);
        boardG.fillRect(innerBoundCenter.x - 2, innerBoundCenter.y - 2, 4, 4);
    }
    private void drawCentroid(Board board) {
        Point innerBoundCenter = board.track.innerBound.computeCentroid().convertToPoint();
        boardG.setColor(Color.CYAN);
        boardG.fillRect(innerBoundCenter.x - 2, innerBoundCenter.y - 2, 4, 4);
    }

    private void drawTrackCenter(Board board) {
        Point trackCenter = board.track.computeCenter().convertToPoint();
        boardG.setColor(Color.RED);
        boardG.fillRect(trackCenter.x - 2, trackCenter.y - 2, 4, 4);
    }

    private void drawDesiredRoute(Board board) {

        PolygonAG route = board.track.routeForCompPlyr;

        boardG.setColor(Color.red);

        for (int i = 0; i < route.points.size() - 2; i++) {
            LineSection side = new LineSection(route.points.get(i), route.points.get(i + 1));
            Point p1 = side.p1.convertToPoint();
            Point p2 = side.p2.convertToPoint();
            boardG.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
        boardG.setColor(Color.BLACK);
    }

    private void btnDrawBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawBoardActionPerformed
        board = new BoardWrapper(BoardBuilder.createBoardWithTrack(3, BoardBuilder.TrackType.DONUT));
        mockDrawBoard(board);
    }//GEN-LAST:event_btnDrawBoardActionPerformed

    private void btnDrawRectTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawRectTrackActionPerformed
        board = new BoardWrapper(BoardBuilder.createBoardWithTrack(3, BoardBuilder.TrackType.RECTANGULAR));
        mockDrawBoard((BoardWrapper) board);
    }//GEN-LAST:event_btnDrawRectTrackActionPerformed

    private void btnDrawTestTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawTestTrackActionPerformed
        board = new BoardWrapper(BoardBuilder.createBoardWithTrack(3, BoardBuilder.TrackType.TEST_RECTANGULAR));
        mockDrawBoard(board);
    }//GEN-LAST:event_btnDrawTestTrackActionPerformed

    private void btnDrawSineTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawSineTrackActionPerformed
        board = new BoardWrapper(BoardBuilder.createBoardWithTrack(3, BoardBuilder.TrackType.SINE));
        mockDrawBoard(board);
    }//GEN-LAST:event_btnDrawSineTrackActionPerformed

    private void btnDrawKidneyTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawKidneyTrackActionPerformed
        board = new BoardWrapper(BoardBuilder.createBoardWithTrack(3, BoardBuilder.TrackType.KIDNEY));
        mockDrawBoard(board);
    }//GEN-LAST:event_btnDrawKidneyTrackActionPerformed

    private void btnTriangleTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTriangleTrackActionPerformed
        board = new BoardWrapper(BoardBuilder.createBoardWithTrack(3, BoardBuilder.TrackType.TRIANGLE));
        mockDrawBoard(board);
    }//GEN-LAST:event_btnTriangleTrackActionPerformed

    private void btnPentagonTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPentagonTrackActionPerformed
        board = new BoardWrapper(BoardBuilder.createBoardWithTrack(3, BoardBuilder.TrackType.PENTAGON));
        mockDrawBoard(board);
    }//GEN-LAST:event_btnPentagonTrackActionPerformed

    private void btnWeronikaTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWeronikaTrackActionPerformed
        board = new BoardWrapper(BoardBuilder.createBoardWithTrack(3, BoardBuilder.TrackType.WERONIKA));
        mockDrawBoard(board);
    }//GEN-LAST:event_btnWeronikaTrackActionPerformed

    private void btnMoveVehicle0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveVehicle0ActionPerformed
        if (board == null) {
            initVehiceOnWeronikaTrack();
        }
        board.moveAllVehicles();
        board.vehicles.get(0).v.value = this.sliderVelocity.getValue();
        mockDrawBoard(board);

        drawDownFromCenterLineSection();
    }//GEN-LAST:event_btnMoveVehicle0ActionPerformed

    private void drawDownFromCenterLineSection() {
        if (board == null) {
            initVehiceOnWeronikaTrack();
        }

        board.mockTrack.computeVerticalStartLine();
        LineSection line = board.mockTrack.getDownFromCenterLineSection();
        Point p1 = line.p1.convertToPoint();
        Point p2 = line.p2.convertToPoint();
        boardG.setColor(Color.MAGENTA);
        boardG.drawLine(p1.x, p1.y, p2.x, p2.y);
        boardG.setColor(Color.BLACK);
    }

    private void initVehiceOnWeronikaTrack() {

        board = new BoardWrapper(BoardBuilder.createBoardWithTrack(1, BoardBuilder.TrackType.WERONIKA));
        mockDrawBoard(board);
        final Vehicle vehicle = board.vehicles.get(0);
        vehicle.v.value = sliderVelocity.getValue();
        vehicle.v.angle = 0;
        vehicle.active = true;

    }

    private void btnVehicleTurnRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehicleTurnRightActionPerformed
        if (board == null) {
            initVehiceOnWeronikaTrack();
        }
        board.vehicles.get(0).turnRight();
    }//GEN-LAST:event_btnVehicleTurnRightActionPerformed

    private void boardPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardPanelMouseMoved
        String location = "(" + evt.getX() + " ," + evt.getY() + ")";
        this.lblMousePoint.setText(location);
    }//GEN-LAST:event_boardPanelMouseMoved

    private void btnDrawnDownFromCenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawnDownFromCenterActionPerformed
        drawDownFromCenterLineSection();
    }//GEN-LAST:event_btnDrawnDownFromCenterActionPerformed

    private void cbTrackCentroidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTrackCentroidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTrackCentroidActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPanel;
    private javax.swing.JButton btnDrawBoard;
    private javax.swing.JButton btnDrawKidneyTrack;
    private javax.swing.JButton btnDrawRectTrack;
    private javax.swing.JButton btnDrawSineTrack;
    private javax.swing.JButton btnDrawTestTrack;
    private javax.swing.JButton btnDrawnDownFromCenter;
    private javax.swing.JButton btnMoveVehicle0;
    private javax.swing.JButton btnPentagonTrack;
    private javax.swing.JButton btnTriangleTrack;
    private javax.swing.JButton btnVehicleTurnRight;
    private javax.swing.JButton btnWeronikaTrack;
    private javax.swing.JCheckBox cbCheckpoints;
    private javax.swing.JCheckBox cbDesiredRoute;
    private javax.swing.JCheckBox cbInnerCenter;
    private javax.swing.JCheckBox cbTrackCenter;
    private javax.swing.JCheckBox cbTrackCentroid;
    private javax.swing.JLabel lblMousePoint;
    private javax.swing.JSlider sliderVelocity;
    // End of variables declaration//GEN-END:variables
    private Graphics boardG;
    private BoardWrapper board;
}
