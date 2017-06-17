package libs.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import libs.Colors;
import libs.math2.Numbers;
import libs.math2.LineSection;
import libs.math2.PointAG;
import libs.math2.PolygonAG;

public class GraphicalTests2 extends javax.swing.JDialog {

    public GraphicalTests2() {
        initComponents();
        initComponents2();
    }

    private void initComponents2() {
        this.g = this.getGraphics();
        this.center = new PointAG(150, 150);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLineTests = new javax.swing.JButton();
        btnPolygonTest = new javax.swing.JButton();
        btnScaledPolygons = new javax.swing.JButton();
        btnColorsTest = new javax.swing.JButton();
        lblMousePoint = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        btnLineTests.setText("Line Test");
        btnLineTests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLineTestsActionPerformed(evt);
            }
        });

        btnPolygonTest.setText("PolygonTest");
        btnPolygonTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPolygonTestActionPerformed(evt);
            }
        });

        btnScaledPolygons.setText("Scaled Polygons");
        btnScaledPolygons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScaledPolygonsActionPerformed(evt);
            }
        });

        btnColorsTest.setText("Test Colors");
        btnColorsTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorsTestActionPerformed(evt);
            }
        });

        lblMousePoint.setText("()");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLineTests)
                .addGap(18, 18, 18)
                .addComponent(btnPolygonTest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnScaledPolygons)
                .addGap(18, 18, 18)
                .addComponent(btnColorsTest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                .addComponent(lblMousePoint, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 446, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLineTests)
                    .addComponent(btnPolygonTest)
                    .addComponent(btnScaledPolygons)
                    .addComponent(btnColorsTest)
                    .addComponent(lblMousePoint)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLineTestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLineTestsActionPerformed
        final int length = 70;
        for (float angle = -Numbers.roundToFloat(Math.PI); angle < Math.PI; angle += 0.1) {
            this.ray = new LineSection(center, angle, length);

            drawLine(ray, Color.red);
        }
    }//GEN-LAST:event_btnLineTestsActionPerformed

    private void btnPolygonTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPolygonTestActionPerformed
       
        PolygonAG bigTrangle = TestPolygonsBuilder.createPolygon(TestPolygonsBuilder.PolygonType.BIG_TRIANGLE);
        drawPolygonAG(bigTrangle, Color.MAGENTA);
        
        PolygonAG smallTriangle = TestPolygonsBuilder.createPolygon(TestPolygonsBuilder.PolygonType.SMALL_TRIANGLE_2);
        drawPolygonAG(smallTriangle, Color.LIGHT_GRAY);

        final float pi = Numbers.roundToFloat(Math.PI);
        final int length = 250;
        for (float angle = -pi; angle < pi; angle += 0.1) {
            this.ray = new LineSection(center, angle, length);
            LineSection crossedSmallTrnglLine = smallTriangle.getLineSectionCrossedBy(ray);
            LineSection crossedBiglTrnglLine = bigTrangle.getLineSectionCrossedBy(ray);

            if (crossedSmallTrnglLine != null && crossedBiglTrnglLine != null) {
                PointAG p1 = ray.computeIntersection(crossedBiglTrnglLine);
                PointAG p2 = ray.computeIntersection(crossedSmallTrnglLine);

                if (p1 != null && p2 != null) {
                    drawLine(new LineSection(p1, p2), Color.blue);
                }
            }
        }
    }//GEN-LAST:event_btnPolygonTestActionPerformed

    private void drawLine(LineSection line, Color color) {
        this.g.setColor(color);
        this.g.drawLine(line.p1.convertToPoint().x, line.p1.convertToPoint().y,
                line.p2.convertToPoint().x, line.p2.convertToPoint().y);
    }

    private void btnScaledPolygonsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScaledPolygonsActionPerformed
        
        PolygonAG baseRectangle 
                = TestPolygonsBuilder.createPolygon(TestPolygonsBuilder.PolygonType.BASE_RECTANGLE);

        drawPolygonAG(baseRectangle, Color.RED);
        System.out.println("Red - original");

        drawScaledRectangle(baseRectangle, Color.BLUE, 0.5f);
        drawScaledRectangle(baseRectangle, Color.GREEN, 2f);
        drawScaledRectangle(baseRectangle, Color.MAGENTA, 3f);
    }//GEN-LAST:event_btnScaledPolygonsActionPerformed

    private void btnColorsTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorsTestActionPerformed
        for (int i = 0; i < 30; i++) {
            this.g.setColor(Colors.getColor(i));
            g.fillRect(0, 10 + 15 * i, 80, 15);
        }
        for (int i = 0; i < 30; i++) {
            this.g.setColor(Colors.getDarkColor(i));
            g.fillRect(120, 10 + 15 * i, 80, 15);
        }
        for (int i = 0; i < 30; i++) {
            this.g.setColor(Colors.getLightColor(i));
            g.fillRect(240, 10 + 15 * i, 80, 15);
        }
    }//GEN-LAST:event_btnColorsTestActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        String location = "(" + evt.getX() + " ," + evt.getY() + ")";
        this.lblMousePoint.setText(location);
    }//GEN-LAST:event_formMouseMoved

    private void drawScaledRectangle(PolygonAG baseRectangle, Color color, float scale) {
        PolygonAG scaledRectangle1 = baseRectangle.clone();
        scaledRectangle1.scale(scale);
        drawPolygonAG(scaledRectangle1, color);
        System.out.println("Scale " + scale + ", color=" + color);
    }

    private void drawPolygonAG(PolygonAG polygonAG, Color color) {
        this.g.setColor(color);
        Polygon polygon = polygonAG.convertToPolygon();
        this.g.drawPolygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
        this.g.setColor(Color.BLACK);

    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new GraphicalTests2().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnColorsTest;
    private javax.swing.JButton btnLineTests;
    private javax.swing.JButton btnPolygonTest;
    private javax.swing.JButton btnScaledPolygons;
    private javax.swing.JLabel lblMousePoint;
    // End of variables declaration//GEN-END:variables
    private Graphics g;
    private LineSection ray;
    private PointAG center;
}