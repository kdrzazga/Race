package presentation;

import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import logic.Board;
import logic.Game;
import logic.IGraphicalOutput;
import logic.BoardBuilder;
import logic.BoardBuilder.TrackType;

public class IntroFrame extends JFrame {

    protected GameScreen gameScreen;
    protected Game game;

    public IntroFrame() {
        initComponents();
        initComponents2();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lapRadioButtonsGroup = new javax.swing.ButtonGroup();
        pnlMain = new javax.swing.JPanel();
        lblTrack = new javax.swing.JLabel();
        cbTrack = new javax.swing.JComboBox<>();
        lblGraphics = new javax.swing.JLabel();
        cbGraphics = new javax.swing.JComboBox<>();
        lblPlayers = new javax.swing.JLabel();
        cbPlayers = new javax.swing.JComboBox<>();
        lblPlayers1 = new javax.swing.JLabel();
        lblPlayers2 = new javax.swing.JLabel();
        lblLaps = new javax.swing.JLabel();
        radio3Laps = new javax.swing.JRadioButton();
        radio5Laps = new javax.swing.JRadioButton();
        radio7Laps = new javax.swing.JRadioButton();
        cbComputerAlgorithm = new javax.swing.JComboBox<>();
        lblComputerAlgorithm1 = new javax.swing.JLabel();
        lblComputerAlgorithm2 = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTrack.setText("Track");

        lblGraphics.setText("Graphics");

        cbGraphics.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2D", "3D (not implemented)" }));

        lblPlayers.setText("Players");

        lblPlayers1.setText("Player1 control: arrow keys");

        lblPlayers2.setText("Player2 control: WSAD");

        lblLaps.setText("Laps");

        lapRadioButtonsGroup.add(radio3Laps);
        radio3Laps.setSelected(true);
        radio3Laps.setText("3");

        lapRadioButtonsGroup.add(radio5Laps);
        radio5Laps.setText("5");

        lapRadioButtonsGroup.add(radio7Laps);
        radio7Laps.setText("7");

        lblComputerAlgorithm1.setText("Computer");

        lblComputerAlgorithm2.setText("Algorithm");
        lblComputerAlgorithm2.setAlignmentY(0.0F);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                        .addComponent(lblTrack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbTrack, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPlayers1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPlayers2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(radio3Laps)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio5Laps)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radio7Laps)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGraphics)
                                    .addComponent(lblPlayers))
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblComputerAlgorithm1)
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblLaps)
                                        .addComponent(lblComputerAlgorithm2)))
                                .addGap(18, 18, 18)))
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbComputerAlgorithm, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbGraphics, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblComputerAlgorithm1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblComputerAlgorithm2))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(cbComputerAlgorithm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLaps)
                    .addComponent(radio3Laps)
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radio5Laps)
                        .addComponent(radio7Laps)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPlayers1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPlayers2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnStart.setText("Start");
        btnStart.addActionListener(evt -> btnStartActionPerformed(evt));

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
                        .addGap(99, 99, 99)
                        .addComponent(btnStart)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnStart)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected void initComponents2() {

        cbTrack.setModel(new DefaultComboBoxModel<>(new String[]{
            rectTrackType.toString(),
            circularTrackType.toString(),
            kidneyTrackType.toString(),
            sineTrackType.toString(),
            triangleTrackType.toString(),
            pentagonTrackType.toString(), //weraTrackType.toString()
        }));

        for (int i = Game.MIN_VEHICLES; i < Game.MAX_VEHICLES; i++) {
            cbPlayers.addItem(Integer.toString(i));
        }

        this.game = new Game();
    }
    protected void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        game.board = createBoardBasedOnSelectedUiItems();
        gameScreen = new GameScreen(this, game);
        startGame(gameScreen);
    }//GEN-LAST:event_btnStartActionPerformed

    public void startGame(GameScreen gameScreen) {

        IGraphicalOutput graphicalOutput = createGraphicalOutputBasedOnSelectedGraphics(gameScreen.getPnlBoard());

        game.setGraphicalOutput(graphicalOutput);
        gameScreen.setVisible(true);
        game.setGameRunning(true);
    }

    private IGraphicalOutput createGraphicalOutputBasedOnSelectedGraphics(JPanel drawablePanel) {
        String selectedGraphics = (String) this.cbGraphics.getSelectedItem();

        if (selectedGraphics.equals("2D")) {
            return new Draw2d(drawablePanel);
        } else {
            return new Draw3d(drawablePanel);
        }
    }

    protected Board createBoardBasedOnSelectedUiItems() {
        int numberOfVehicles = readSelectedNumberOfVehicles();

        int lapsToWin = Integer.parseInt(getSelectedButtonText(this.lapRadioButtonsGroup));

        this.game.reset(lapsToWin);
        Board board;
        String selectedTrack = (String) this.cbTrack.getSelectedItem();

        if (selectedTrack.equals(rectTrackType.toString())) {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.RECTANGULAR);
        } else if (selectedTrack.equals(circularTrackType.toString())) {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.DONUT);
        } else if (selectedTrack.equals(kidneyTrackType.toString())) {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.KIDNEY);
        } else if (selectedTrack.equals(triangleTrackType.toString())) {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.TRIANGLE);
        } else if (selectedTrack.equals(pentagonTrackType.toString())) {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.PENTAGON);
        } else// if (selectedTrack.equals(sineTrackType.toString()))
        {
            board = BoardBuilder.createBoardWithTrack(numberOfVehicles, BoardBuilder.TrackType.SINE);
        }
        return board;
    }

    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return "";
    }

    protected int readSelectedNumberOfVehicles() throws NumberFormatException {
        String vehCount = this.cbPlayers.getSelectedItem().toString();
        return Integer.parseInt(vehCount);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JComboBox<String> cbComputerAlgorithm;
    private javax.swing.JComboBox<String> cbGraphics;
    private javax.swing.JComboBox<String> cbPlayers;
    protected javax.swing.JComboBox<String> cbTrack;
    private javax.swing.ButtonGroup lapRadioButtonsGroup;
    private javax.swing.JLabel lblComputerAlgorithm1;
    private javax.swing.JLabel lblComputerAlgorithm2;
    private javax.swing.JLabel lblGraphics;
    private javax.swing.JLabel lblLaps;
    private javax.swing.JLabel lblPlayers;
    private javax.swing.JLabel lblPlayers1;
    private javax.swing.JLabel lblPlayers2;
    private javax.swing.JLabel lblTrack;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JRadioButton radio3Laps;
    private javax.swing.JRadioButton radio5Laps;
    private javax.swing.JRadioButton radio7Laps;
    // End of variables declaration//GEN-END:variables
    private final TrackType rectTrackType = BoardBuilder.TrackType.RECTANGULAR;
    private final TrackType circularTrackType = BoardBuilder.TrackType.DONUT;
    private final TrackType kidneyTrackType = BoardBuilder.TrackType.KIDNEY;
    private final TrackType sineTrackType = BoardBuilder.TrackType.SINE;
    private final TrackType triangleTrackType = BoardBuilder.TrackType.TRIANGLE;
    private final TrackType pentagonTrackType = BoardBuilder.TrackType.PENTAGON;

}
