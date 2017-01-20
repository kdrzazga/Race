package libs.test;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;

import libs.math2.CircleAG;
import libs.math2.PointAG;
import libs.math2.PolygonAG;
import static miscallenous.JFrameCommons.setNimbusLookAndFeel;

public class GraphicalTests extends JFrame {

    public GraphicalTests() {
        initComponents();
    }

    public static void main(String args[]) {        
        setNimbusLookAndFeel(GraphicalTests.class.getName());    

        java.awt.EventQueue.invokeLater(() -> {
            new GraphicalTests().setVisible(true);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnDrawHex = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Draw Circle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 242, Short.MAX_VALUE)
        );

        btnDrawHex.setText("Draw Hexagon");
        btnDrawHex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrawHexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButton1)
                .addGap(57, 57, 57)
                .addComponent(btnDrawHex)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnDrawHex))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PointAG center2D = new PointAG(100, 100);
        CircleAG circle = new CircleAG(center2D, 100, 100);

        Graphics g = this.jPanel1.getGraphics();
        g.drawPolygon(circle.getPoints().convertToPolygon());
        Point center = circle.getCenter().convertToPoint();
        g.drawOval(center.x - 2, center.y - 2, 4, 4);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDrawHexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawHexActionPerformed
        PolygonAG hexagon = new PolygonAG();

        PointAG hexPoints[] = {new PointAG(100, 100), new PointAG(300, 100), new PointAG(400, 200),
            new PointAG(300, 300), new PointAG(100, 300), new PointAG(0, 200)};

        hexagon.points.addAll(Arrays.asList(hexPoints));
        Graphics g = this.jPanel1.getGraphics();
        g.drawPolygon(hexagon.convertToPolygon());
    }//GEN-LAST:event_btnDrawHexActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDrawHex;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
