package JumperCanvases;

import javax.swing.*;
import java.awt.*;

import JumperButtons.*;

import static JumperConstants.Constants.*;

public class JumperMainMenuCanvas extends JPanel {
    private Image image = new ImageIcon("src/main/resources/Pictures/Backgrounds/bkMainMenuJumper.png").getImage();

    public JumperMainMenuCanvas(Container container) {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setLayout(null);
        add(new StartButton(container));
        add(new RecordsButton(container));
        add(new OptionsButton(container));
        add(new ExitButton(container));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
    }
}
