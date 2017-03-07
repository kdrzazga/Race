package libs.test;

import java.awt.Graphics;
import java.util.List;
import libs.ClassFinder;
import libs.UnitTest;
import libs.math2.UnitTestFieldsDraw;

public class UnitTestFieldsDrawGraphicalTest extends javax.swing.JFrame {

    public UnitTestFieldsDrawGraphicalTest() {
        initComponents();
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new UnitTestFieldsDrawGraphicalTest().setVisible(true);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDrawAllShapesFromGetters = new javax.swing.JButton();
        drawPanel = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnDrawAllShapesFromGetters.setText("Draw All Shapes From Getters");
        btnDrawAllShapesFromGetters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDrawAllShapesFromGettersActionPerformed(evt);
            }
        });

        drawPanel.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout drawPanelLayout = new javax.swing.GroupLayout(drawPanel);
        drawPanel.setLayout(drawPanelLayout);
        drawPanelLayout.setHorizontalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        drawPanelLayout.setVerticalGroup(
            drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 535, Short.MAX_VALUE)
        );

        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDrawAllShapesFromGetters)
                        .addGap(53, 53, 53)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 192, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDrawAllShapesFromGetters)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDrawAllShapesFromGettersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDrawAllShapesFromGettersActionPerformed
        LineSectionTests tests = new LineSectionTests();
        UnitTestFieldsDraw draw = new UnitTestFieldsDraw(tests);
        LineSectionTests.main(new String[]{""});
        Graphics g = this.drawPanel.getGraphics();
        draw.drawAllShapesFromGetters(g);
    }//GEN-LAST:event_btnDrawAllShapesFromGettersActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        String packageName = UnitTestFieldsDrawGraphicalTest.class.getPackage().getName();
        throw new RuntimeException("Not finished");
    }//GEN-LAST:event_formWindowOpened

    private String getPackageName() {
        String fullClassName = UnitTestFieldsDrawGraphicalTest.class.getName();
        int packageNameEnd = fullClassName.lastIndexOf(".") - 1;
        return fullClassName.substring(0, packageNameEnd);
    }

    private UnitTest createTestBasedOnUi()
    {
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDrawAllShapesFromGetters;
    private javax.swing.JPanel drawPanel;
    private javax.swing.JComboBox<String> jComboBox1;
    // End of variables declaration//GEN-END:variables
}
