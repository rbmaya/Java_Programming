package JumperButtons;

import JumperServices.PdfFileRecordsService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static JumperConstants.Constants.BUTTON_HEIGHT;
import static JumperConstants.Constants.BUTTON_WIDTH;

public class SaveToPDFButton extends JButton {
    private PdfFileRecordsService recordsService = new PdfFileRecordsService();

    public SaveToPDFButton() {
        super(new ImageIcon("src/main/resources/Pictures/Buttons/pdfButton.png"));
        setBounds(28, 530, BUTTON_WIDTH, BUTTON_HEIGHT);
        setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                saveScoreToPDF();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(new ImageIcon("src/main/resources/Pictures/Buttons/pdfButtonSelected.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(new ImageIcon("src/main/resources/Pictures/Buttons/pdfButton.png"));
            }
        });
    }

    public void saveScoreToPDF() {
        recordsService.save(null);
    }
}
