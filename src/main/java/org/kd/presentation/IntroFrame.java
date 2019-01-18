package org.kd.presentation;

import org.kd.logic.Game;
import org.kd.logic.IGraphicalOutput;
import org.kd.logic.creation.BoardBuilder;
import org.kd.logic.creation.BoardBuilder.TrackType;

import javax.swing.*;
import java.util.stream.IntStream;

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

        lapRadioButtonsGroup = new ButtonGroup();
        pnlMain = new JPanel();
        lblTrack = new JLabel();
        cbTrack = new JComboBox<>();
        lblGraphics = new JLabel();
        cbGraphics = new JComboBox<>();
        lblPlayers = new JLabel();
        cbPlayers = new JComboBox<>();
        lblPlayers1 = new JLabel();
        lblPlayers2 = new JLabel();
        lblLaps = new JLabel();
        radio3Laps = new JRadioButton();
        radio5Laps = new JRadioButton();
        radio7Laps = new JRadioButton();
        cbComputerAlgorithm = new JComboBox<>();
        lblComputerAlgorithm1 = new JLabel();
        lblComputerAlgorithm2 = new JLabel();
        btnStart = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBorder(BorderFactory.createEtchedBorder());

        lblTrack.setText("Track");

        lblGraphics.setText("Graphics");

        cbGraphics.setModel(new DefaultComboBoxModel<>(new String[] { "2D"}));

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

        GroupLayout pnlMainLayout = new GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addGroup(GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                        .addComponent(lblTrack)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbTrack, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPlayers1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPlayers2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(radio3Laps)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio5Laps)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radio7Laps)
                        .addGap(23, 23, 23))
                    .addGroup(GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(lblGraphics)
                                    .addComponent(lblPlayers))
                                .addGap(25, 25, 25))
                            .addGroup(GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblComputerAlgorithm1)
                                    .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(lblLaps)
                                        .addComponent(lblComputerAlgorithm2)))
                                .addGap(18, 18, 18)))
                        .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(cbPlayers, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbComputerAlgorithm, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbGraphics, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrack)
                    .addComponent(cbTrack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGraphics)
                    .addComponent(cbGraphics, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPlayers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPlayers))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblComputerAlgorithm1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblComputerAlgorithm2))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(cbComputerAlgorithm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblLaps)
                    .addComponent(radio3Laps)
                    .addGroup(pnlMainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(radio5Laps)
                        .addComponent(radio7Laps)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPlayers1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPlayers2)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnStart.setText("Start");
        btnStart.addActionListener(this::btnStartActionPerformed);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlMain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(btnStart)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlMain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnStart)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        IntStream.range(Game.MIN_VEHICLES, Game.MAX_VEHICLES).forEach (
            i -> {cbPlayers.addItem(Integer.toString(i));}
        );

        this.game = new Game();
    }
    protected void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        game.board = IntroFrameInitializer.createBoardBasedOnSelectedUiItems(this);
        gameScreen = new GameScreen(this, game);
        startGame(gameScreen);
    }//GEN-LAST:event_btnStartActionPerformed

    public void startGame(GameScreen gameScreen) {

        IGraphicalOutput graphicalOutput = IntroFrameInitializer.createGraphicalOutputBasedOnSelectedGraphics(gameScreen.getPnlBoard(), this);

        game.setGraphicalOutput(graphicalOutput);
        gameScreen.setVisible(true);
        game.setGameRunning(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnStart;
    private JComboBox<String> cbComputerAlgorithm;
    JComboBox<String> cbGraphics;
    JComboBox<String> cbPlayers;
    JComboBox<String> cbTrack;
    ButtonGroup lapRadioButtonsGroup;
    private JLabel lblComputerAlgorithm1;
    private JLabel lblComputerAlgorithm2;
    private JLabel lblGraphics;
    private JLabel lblLaps;
    private JLabel lblPlayers;
    private JLabel lblPlayers1;
    private JLabel lblPlayers2;
    private JLabel lblTrack;
    private JPanel pnlMain;
    private JRadioButton radio3Laps;
    private JRadioButton radio5Laps;
    private JRadioButton radio7Laps;
    // End of variables declaration//GEN-END:variables
    final TrackType rectTrackType = BoardBuilder.TrackType.RECTANGULAR;
    final TrackType circularTrackType = BoardBuilder.TrackType.DONUT;
    final TrackType kidneyTrackType = BoardBuilder.TrackType.KIDNEY;
    final TrackType sineTrackType = BoardBuilder.TrackType.SINE;
    final TrackType triangleTrackType = BoardBuilder.TrackType.TRIANGLE;
    final TrackType pentagonTrackType = BoardBuilder.TrackType.PENTAGON;

}
