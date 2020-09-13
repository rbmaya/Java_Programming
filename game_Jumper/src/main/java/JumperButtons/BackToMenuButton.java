package JumperButtons;

import JumperCanvases.JumperMainMenuCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static JumperConstants.Constants.BUTTON_HEIGHT;
import static JumperConstants.Constants.BUTTON_WIDTH;

public class BackToMenuButton extends JButton {
    public BackToMenuButton(Container container){
        super(new ImageIcon("src/main/resources/Pictures/Buttons/homeButton.png"));
        setBounds(175, 530, BUTTON_WIDTH, BUTTON_HEIGHT);
        setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                container.removeAll();
                JumperMainMenuCanvas jumperMainMenuCanvas = new JumperMainMenuCanvas(container);
                container.add(jumperMainMenuCanvas);
                container.revalidate();
                jumperMainMenuCanvas.requestFocus();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(new ImageIcon("src/main/resources/Pictures/Buttons/homeButtonSelected.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(new ImageIcon("src/main/resources/Pictures/Buttons/homeButton.png"));
            }
        });
    }
}
