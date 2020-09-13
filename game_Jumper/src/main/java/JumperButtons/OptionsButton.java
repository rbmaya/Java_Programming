package JumperButtons;

import JumperCanvases.JumperMainMenuCanvas;
import JumperCanvases.JumperOptionsCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static JumperConstants.Constants.BUTTON_HEIGHT;
import static JumperConstants.Constants.BUTTON_WIDTH;

public class OptionsButton extends JButton {
    public OptionsButton(Container container){
        super(new ImageIcon("src/main/resources/Pictures/Buttons/optionsButton.png"));
        setBounds(100, 310, BUTTON_WIDTH, BUTTON_HEIGHT);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                container.removeAll();
                container.add(new JumperOptionsCanvas(container));
                container.revalidate();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(new ImageIcon("src/main/resources/Pictures/Buttons/optionsButtonSelected.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(new ImageIcon("src/main/resources/Pictures/Buttons/optionsButton.png"));
            }
        });
    }
}
