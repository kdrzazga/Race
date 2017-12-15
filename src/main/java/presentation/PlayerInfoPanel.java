package presentation;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Game;
import logic.IPlayerInfoPanel;

public class PlayerInfoPanel extends JPanel implements IPlayerInfoPanel {

    public class PlayerInfo {

        public int id;
        public final JLabel lblPlayer;
        public final JLabel lblPlayerSpeed;
        public final JLabel lblPlayerLap;
        private static final String FONT_NAME = "Lucida Trans Typewriter";

        public final Font playerFont = new Font(FONT_NAME, Font.BOLD, 12);
        public final Font defaultFont = new Font(FONT_NAME, Font.PLAIN, 12);
        public static final int LABELS_PER_PLAYER_COUNT = 4;

        private static final  String GAP_BIG = "           ";
        private static final String GAP_SMALL = "    ";
        public static final String SPEED_TEXT = GAP_SMALL + "Speed: ";
        public static final String LAP_TEXT = GAP_SMALL + "Lap: ";

        public PlayerInfo(int id) {
            this.id = id;

            lblPlayer = new JLabel(GAP_SMALL + "Player " + (id + 1) + GAP_BIG);
            //in code players are numbered starting from 0, on screen Player0 is displayedas Player 1 etc.
            initLabel(lblPlayer, playerFont);

            lblPlayerSpeed = new JLabel(SPEED_TEXT);
            initLabel(lblPlayerSpeed, defaultFont);

            lblPlayerLap = new JLabel(LAP_TEXT);
            initLabel(lblPlayerLap, defaultFont);
        }

        private void initLabel(JLabel label, Font font) {
            label.setFont(font);
            label.setForeground(ColorSettings.getVehicleColorById(id));
        }
    }

    private PlayerInfo[] playerInfos;

    private final int numberOfPlayers;

    public PlayerInfoPanel(int numberOfVehicles) {
        super(new GridLayout(Game.MAX_VEHICLES * PlayerInfo.LABELS_PER_PLAYER_COUNT, 1));
        this.numberOfPlayers = numberOfVehicles;
        initComponents();
    }

    private void initComponents() {
        this.setBackground(ColorSettings.INFO_PANEL_COLOR);
        this.setBorder(BorderFactory.createEtchedBorder());

        playerInfos = new PlayerInfo[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            playerInfos[i] = new PlayerInfo(i);

            this.add(playerInfos[i].lblPlayer);
            this.add(playerInfos[i].lblPlayerSpeed);
            this.add(playerInfos[i].lblPlayerLap);
            this.add(new Label(""));
        }
    }

    @Override
    public void setRunningPlayerInfo(int id, int speed, int laps) {
        this.playerInfos[id].lblPlayerLap.setText(PlayerInfo.LAP_TEXT + laps);
        this.playerInfos[id].lblPlayerSpeed.setText(PlayerInfo.SPEED_TEXT + speed);
    }

    @Override
    public void setPlayerWinnerInfo(int id, int finalPlace) {
        this.playerInfos[id].lblPlayerLap.setText(PlayerInfo.GAP_BIG);
        this.playerInfos[id].lblPlayerSpeed.setText(PlayerInfo.GAP_SMALL + "Final place " + finalPlace);
    }
}
