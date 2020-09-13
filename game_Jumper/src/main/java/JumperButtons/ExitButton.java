package JumperButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static JumperConstants.Constants.BUTTON_HEIGHT;
import static JumperConstants.Constants.BUTTON_WIDTH;

public class ExitButton extends JButton {
    public ExitButton(Container container){
        super(new ImageIcon("src/main/resources/Pictures/Buttons/exitButton.png"));
        setBounds(100, 390, BUTTON_WIDTH, BUTTON_HEIGHT);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(new ImageIcon("src/main/resources/Pictures/Buttons/exitButtonSelected.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(new ImageIcon("src/main/resources/Pictures/Buttons/exitButton.png"));
            }
        });
    }
}
