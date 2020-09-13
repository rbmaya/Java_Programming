package JumperButtons;

import JumperCanvases.JumperGameCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static JumperConstants.Constants.*;

public class RestartButton extends JButton {
    public RestartButton(Container container){
        super(new ImageIcon("src/main/resources/Pictures/Buttons/restartButton.png"));
        setBounds(40, 530, BUTTON_WIDTH, BUTTON_HEIGHT);

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
                setIcon(new ImageIcon("src/main/resources/Pictures/Buttons/restartButtonSelected.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(new ImageIcon("src/main/resources/Pictures/Buttons/restartButton.png"));
            }
        });
    }
}
