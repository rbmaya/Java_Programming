package JumperCanvases;

import JumperButtons.BackToMenuButton;
import JumperButtons.RestartButton;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.util.Properties;

import static JumperConstants.Constants.*;

public class JumperRestartMenuCanvas extends JPanel {
    private Image background;

    public JumperRestartMenuCanvas(Container container){

        Properties options = new Properties();
        try {
            options.load(new FileInputStream("src/main/resources/options.properties"));
        } catch (Exception ignored) {}

        background = new ImageIcon("src/main/resources/Pictures/Backgrounds/backRestartMenu_" + options.get("sex") + ".png").getImage();
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setLayout(null);

        Properties records = new Properties();
        try {
            records.load(new FileInputStream("src/main/resources/records.properties"));
        } catch (Exception ignored) {}

        JLabel score = new JLabel();
        score.setBounds(210, 418, 400, 60);
        score.setForeground(Color.white);
        score.setFont(new Font("Ink Free", Font.BOLD, 40));
        score.setText(records.getProperty("0"));
        add(score);

        JButton restartGame = new RestartButton(container);
        JButton backToMenu = new BackToMenuButton(container);

        add(backToMenu);
        add(restartGame);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
    }
}
