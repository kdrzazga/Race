package libs.test;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.GroupLayout.Group;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import libs.math2.CircleAG;
import libs.math2.PointAG;
import libs.math2.PolygonAG;
import static miscallenous.JFrameCommons.setNimbusLookAndFeel;

public class GraphicalTests extends JFrame {

    private JButton btnDrawHex;
    private JButton btnDrawCircle;
    private JPanel drawingPanel;

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
    // <editor-fold defaultstate="collapsed" desc="Components initialization">
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initBtnDrawCircle();
        initDrawingPanel();
        initBtnDrawHex();
        initContentPaneLayout();
        pack();
    }

    private void initBtnDrawCircle() {
        btnDrawCircle = new JButton();

        btnDrawCircle.setText("Draw Circle");
        btnDrawCircle.addActionListener((ActionEvent evt) -> {
            btnDrawCircleActionPerformed();
        });
    }

    private void initDrawingPanel() {
        drawingPanel = new JPanel();
        drawingPanel.setBackground(new Color(204, 255, 204));

        GroupLayout drawingPanelLayout = new GroupLayout(drawingPanel);
        initDrawingPanelLayout(drawingPanelLayout);
    }

    private void initBtnDrawHex() {
        btnDrawHex = new JButton();
        btnDrawHex.setText("Draw Hexagon");
        btnDrawHex.addActionListener((ActionEvent evt) -> {
            btnDrawHexActionPerformed();
        });
    }

    private void initContentPaneLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        Group horizontalGroup = createHorizontalGroup(layout);
        layout.setHorizontalGroup(horizontalGroup);

        Group verticalGroup = createVerticalGroup(layout);
        layout.setVerticalGroup(verticalGroup);
    }

    private ParallelGroup createVerticalGroup(GroupLayout layout) {
        ParallelGroup drawButtonsGroup = createDrawButtonsGroup(layout);
        SequentialGroup verticalSubGroup = createVerticalSubGroup(layout, drawButtonsGroup);
       
        return layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, verticalSubGroup);
    }

    private SequentialGroup createVerticalSubGroup(GroupLayout layout, ParallelGroup drawButtonsGroup) {
        SequentialGroup verticalSubGroup = layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(drawingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(drawButtonsGroup)
                .addContainerGap();
        return verticalSubGroup;
    }

    private ParallelGroup createDrawButtonsGroup(GroupLayout layout) {
        ParallelGroup drawButtonsGroup = layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnDrawCircle)
                .addComponent(btnDrawHex);
        return drawButtonsGroup;
    }

    private void initDrawingPanelLayout(GroupLayout drawingPanelLayout) {
        drawingPanel.setLayout(drawingPanelLayout);
        drawingPanelLayout.setHorizontalGroup(drawingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 376, Short.MAX_VALUE)
        );
        drawingPanelLayout.setVerticalGroup(drawingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 242, Short.MAX_VALUE)
        );
    }

    private Group createHorizontalGroup(GroupLayout layout) {
        Group horizontalSubGroup1 = createHorizontalSubGroup1(layout);
        Group horizontalSubGroup2 = createHorizontalSubGroup2(layout);

        return layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(horizontalSubGroup1)
                .addGroup(horizontalSubGroup2);
    }

    private Group createHorizontalSubGroup1(GroupLayout layout) {
        return layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(drawingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap();
    }

    private Group createHorizontalSubGroup2(GroupLayout layout) {
        return layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btnDrawCircle)
                .addGap(57, 57, 57)
                .addComponent(btnDrawHex)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
    }
	//GEN-BEGIN:initComponents
	// </editor-fold>//GEN-END:initComponents
   
    private void btnDrawCircleActionPerformed() {
        PointAG center2D = new PointAG(100, 100);
        CircleAG circle = new CircleAG(center2D, 100, 100);

        Graphics g = this.drawingPanel.getGraphics();
        g.drawPolygon(circle.getPoints().convertToPolygon());
        Point center = circle.getCenter().convertToPoint();
        g.drawOval(center.x - 2, center.y - 2, 4, 4);
    }

    private void btnDrawHexActionPerformed() {
        PolygonAG hexagon = new PolygonAG();

        PointAG hexPoints[] = {new PointAG(100, 100), new PointAG(300, 100), new PointAG(400, 200),
            new PointAG(300, 300), new PointAG(100, 300), new PointAG(0, 200)};

        hexagon.points.addAll(Arrays.asList(hexPoints));
        Graphics g = this.drawingPanel.getGraphics();
        g.drawPolygon(hexagon.convertToPolygon());
    }

	////GEN-BEGIN:variables
    ////GEN-END:variables
}
