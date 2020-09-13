package JumperButtons;

import JumperCanvases.JumperGameCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static JumperConstants.Constants.*;

public class StartButton extends JButton {
    public StartButton(Container container){
        super(new ImageIcon("src/main/resources/Pictures/Buttons/startButton.png"));
        setBounds(100, 150, BUTTON_WIDTH, BUTTON_HEIGHT);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                container.removeAll();
                JumperGameCanvas jumperGameCanvas = new JumperGameCanvas(container);
                container.add(jumperGameCanvas);
                container.revalidate();
                jumperGameCanvas.requestFocus();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(new ImageIcon("src/main/resources/Pictures/Buttons/startButtonPressed.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(new ImageIcon("src/main/resources/Pictures/Buttons/startButton.png"));
            }
        });
    }
}
