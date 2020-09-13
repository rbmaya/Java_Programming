package JumperCanvases;

import JumperButtons.BackToMenuButton;
import JumperButtons.SaveToPDFButton;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.util.Properties;

import static JumperConstants.Constants.FRAME_HEIGHT;
import static JumperConstants.Constants.FRAME_WIDTH;

public class JumperRecordsCanvas extends JPanel {
    private Image background;

    public JumperRecordsCanvas(Container container){
        Properties records = new Properties();
        try {
            records.load(new FileInputStream("src/main/resources/records.properties"));
        } catch (Exception ignored) {}

        background = new ImageIcon("src/main/resources/Pictures/Backgrounds/backRecords.png").getImage();
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setLayout(null);

        int shift = 170;
        for (int i = 0; i < 10; ++i){
            JLabel score = new JLabel();
            JLabel number = new JLabel();

            score.setBounds(98, shift, 400, 20);
            number.setBounds(70, shift, 400, 20);

            score.setForeground(Color.white);
            number.setForeground(Color.blue);

            score.setFont(new Font("Ink Free", Font.BOLD, 20));
            number.setFont(new Font("Ink Free", Font.BOLD, 20));

            number.setText(i + 1 + ". ");
            score.setText(records.getProperty(Integer.valueOf(i + 1).toString()));

            add(score);
            add(number);
            shift += 35;
        }

        BackToMenuButton backToMenu = new BackToMenuButton(container);
        SaveToPDFButton saveToPDFButton = new SaveToPDFButton();
        add(saveToPDFButton);
        add(backToMenu);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
    }

}
