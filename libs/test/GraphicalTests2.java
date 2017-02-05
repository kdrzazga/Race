package libs.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
                .addContainerGap(412, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 476, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLineTests)
                    .addComponent(btnPolygonTest)
                    .addComponent(btnScaledPolygons)))
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
        PointAG[] bigTrianglePts = {new PointAG(110, 50), new PointAG(200, 200), new PointAG(30, 240)};
        PolygonAG bigTrangle = new PolygonAG(bigTrianglePts);
        drawPolygonAG(bigTrangle, Color.MAGENTA);

        PointAG[] smallTrianglePts = {new PointAG(130, 98), new PointAG(170, 180), new PointAG(60, 190)};
        PolygonAG smallTriangle = new PolygonAG(smallTrianglePts);
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
        PointAG[] baseRectanglePts = {new PointAG(350, 250), new PointAG(500, 250),
            new PointAG(500, 350), new PointAG(350, 350)};
        PolygonAG baseRectangle = new PolygonAG(baseRectanglePts);

        drawPolygonAG(baseRectangle, Color.RED);
        System.out.println("Red - original");

        drawScaledRectangle(baseRectanglePts, Color.BLUE, 0.5f);
        drawScaledRectangle(baseRectanglePts, Color.GREEN, 2f);
        drawScaledRectangle(baseRectanglePts, Color.MAGENTA, 3f);
    }//GEN-LAST:event_btnScaledPolygonsActionPerformed

    private void drawScaledRectangle(PointAG[] baseRectanglePts, Color color, float scale) {
        PolygonAG scaledRectangle1 = new PolygonAG(baseRectanglePts);
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
    private javax.swing.JButton btnLineTests;
    private javax.swing.JButton btnPolygonTest;
    private javax.swing.JButton btnScaledPolygons;
    // End of variables declaration//GEN-END:variables
    private Graphics g;
    private LineSection ray;
    private PointAG center;
}
