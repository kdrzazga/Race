package presentation;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import logic.Board;
import logic.Game;
import logic.IGraphicalOutput;
import miscallenous.BoardBuilder;
import miscallenous.BoardBuilder.TrackType;

public class IntroFrame extends JFrame {

    private GameScreen gameScreen;
    private Game game;

    public IntroFrame() {
        initComponents();
        initComponents2();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblTrack = new javax.swing.JLabel();
        cbTrack = new javax.swing.JComboBox<>();
        lblGraphics = new javax.swing.JLabel();
        cbGraphics = new javax.swing.JComboBox<>();
        lblPlayers = new javax.swing.JLabel();
        cbPlayers = new javax.swing.JComboBox<>();
        lblPlayers1 = new javax.swing.JLabel();
        lblPlayers2 = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTrack.setText("Track");

        lblGraphics.setText("Graphics");

        cbGraphics.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2D", "3D (not implemented)" }));

        lblPlayers.setText("Players");

        lblPlayers1.setText("Player1 control: arrow keys");

        lblPlayers2.setText("Player2 control: WSAD");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblPlayers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                        .addComponent(lblGraphics)
                        .addGap(18, 18, 18)
                        .addComponent(cbGraphics, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                        .addComponent(lblTrack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbTrack, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPlayers1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPlayers2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrack)
                    .addComponent(cbTrack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGraphics)
                    .addComponent(cbGraphics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPlayers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPlayers1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPlayers2)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(btnStart)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStart)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initComponents2() {

        cbTrack.setModel(new DefaultComboBoxModel<>(new String[]{
            rectTrackType.toString(),
            circularTrackType.toString(),
            kidneyTrackType.toString(),
            sineTrackType.toString(),
            triangleTrackType.toString(),
            pentagonTrackType.toString(),
            weraTrackType.toString()
        }));

        for (int i = Game.MIN_VEHICLES; i < Game.MAX_VEHICLES; i++) {
            cbPlayers.addItem(Integer.toString(i));
        }

        this.game = new Game();
    }
    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        game.board = createBoardBasedOnSelectedUiItems();

        gameScreen = new GameScreen(this, game);
        IGraphicalOutput graphicalOutput = createGraphicalOutputBasedOnSelectedGraphics(gameScreen.getPnlBoard());

        game.setGraphicalOutput(graphicalOutput);
        gameScreen.setVisible(true);
        game.setGameRunning(true);
    }//GEN-LAST:event_btnStartActionPerformed

    private IGraphicalOutput createGraphicalOutputBasedOnSelectedGraphics(JPanel drawablePanel) {
        String selectedGraphics = (String) this.cbGraphics.getSelectedItem();

        if (selectedGraphics.equals("2D")) {
            return new Draw2d(drawablePanel);
        } else {
            return new Draw3d(drawablePanel);
        }
    }

    private Board createBoardBasedOnSelectedUiItems() {
        String vehCount = this.cbPlayers.getSelectedItem().toString();
        int numberOfVehicles = new Integer(vehCount);
        Board board;
        String selectedTrack = (String) this.cbTrack.getSelectedItem();
        if (selectedTrack.equals(rectTrackType.toString())) {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, 2, BoardBuilder.TrackType.RECTANGULAR_1);

        } else if (selectedTrack.equals(circularTrackType.toString()))
        {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, 2, BoardBuilder.TrackType.DONUT);
        }
        else if (selectedTrack.equals(kidneyTrackType.toString()))
        {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, 2, BoardBuilder.TrackType.KIDNEY);
        }
        else if (selectedTrack.equals(triangleTrackType.toString()))
        {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, 2, BoardBuilder.TrackType.TRIANGLE);
        }
        else if (selectedTrack.equals(pentagonTrackType.toString()))
        {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, 2, BoardBuilder.TrackType.PENTAGON);
        }
        else if (selectedTrack.equals(weraTrackType.toString()))
        {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, 2, BoardBuilder.TrackType.WERONIKA);
        }
        else// if (selectedTrack.equals(sineTrackType.toString()))
        {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, 2, BoardBuilder.TrackType.SINE);
        }        
        return board;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JComboBox<String> cbGraphics;
    private javax.swing.JComboBox<String> cbPlayers;
    private javax.swing.JComboBox<String> cbTrack;
    private javax.swing.JLabel lblGraphics;
    private javax.swing.JLabel lblPlayers;
    private javax.swing.JLabel lblPlayers1;
    private javax.swing.JLabel lblPlayers2;
    private javax.swing.JLabel lblTrack;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
    private final TrackType rectTrackType = BoardBuilder.TrackType.RECTANGULAR_1;
    private final TrackType circularTrackType = BoardBuilder.TrackType.DONUT;
    private final TrackType kidneyTrackType = BoardBuilder.TrackType.KIDNEY;
    private final TrackType sineTrackType = BoardBuilder.TrackType.SINE;
    private final TrackType triangleTrackType = BoardBuilder.TrackType.TRIANGLE;
    private final TrackType pentagonTrackType = BoardBuilder.TrackType.PENTAGON;
    private final TrackType weraTrackType = BoardBuilder.TrackType.WERONIKA;
    
}
