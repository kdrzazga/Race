package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import libs.math2.Numbers;
import logic.Game;
import logic.IPlayerInfoPanel;

public class PlayerInfoPanel extends JPanel implements IPlayerInfoPanel {

    public class PlayerInfo {

        public int id;
        public JLabel lblPlayer;
        public JLabel lblPlayerSpeed;
        public JLabel lblPlayerLap;
        private static final String FONT_NAME = "Lucida Trans Typewriter";

        public final Font playerFont = new Font(FONT_NAME, Font.BOLD, 12);
        public final Font defaultFont = new Font(FONT_NAME, Font.PLAIN, 12);
        public final static int LABELS_PER_PLAYER_COUNT = 4;

        private final static String GAP_BIG = "           ";
        private final static String GAP_SMALL = "    ";
        public final static String SPEED_TEXT = GAP_SMALL + "Speed: ";
        public final static String LAP_TEXT = GAP_SMALL + "Lap: ";

        public PlayerInfo(int id) {
            this.id = id;

            lblPlayer = new JLabel(GAP_SMALL + "Player " + id + GAP_BIG);
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
    public void setPlayerInfo(int id, int speed, double travelledWay) {
        this.playerInfos[id].lblPlayerLap.setText(PlayerInfo.LAP_TEXT + Numbers.roundToFloat(travelledWay, 2));
        this.playerInfos[id].lblPlayerSpeed.setText(PlayerInfo.SPEED_TEXT + speed);
    }
}
