package JumperButtons;

import JumperCanvases.JumperGameCanvas;
import JumperCanvases.JumperRecordsCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static JumperConstants.Constants.BUTTON_HEIGHT;
import static JumperConstants.Constants.BUTTON_WIDTH;

public class RecordsButton extends JButton {


    public RecordsButton(Container container){
        super(new ImageIcon("src/main/resources/Pictures/Buttons/recordsButton.png"));
        setBounds(100, 230, BUTTON_WIDTH, BUTTON_HEIGHT);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                container.removeAll();
                JumperRecordsCanvas jumperRecordsCanvas = new JumperRecordsCanvas(container);
                container.add(jumperRecordsCanvas);
                container.revalidate();
                jumperRecordsCanvas.requestFocus();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(new ImageIcon("src/main/resources/Pictures/Buttons/recordsButtonSelected.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(new ImageIcon("src/main/resources/Pictures/Buttons/recordsButton.png"));
            }
        });
    }
}
