package JumperCanvases;

import JumperButtons.BackToMenuButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import static JumperConstants.Constants.*;

public class JumperOptionsCanvas extends JPanel {
    private Image image = new ImageIcon("src/main/resources/Pictures/Backgrounds/backGr_Game.png").getImage();
    private Properties properties = new Properties();

    public JumperOptionsCanvas(Container container){
        try {
            properties.load(new FileInputStream("src/main/resources/options.properties"));
        } catch (Exception ignore){}

        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setLayout(null);

        JRadioButton radioMale = new JRadioButton("male");
        radioMale.setBounds(50, 200, 80, 20);
        radioMale.setFont(new Font("Ink Free", Font.BOLD, 15));
        radioMale.setOpaque(false);

        JRadioButton radioFemale = new JRadioButton("female");
        radioFemale.setBounds(150, 200, 80, 20);
        radioFemale.setFont(new Font("Ink Free", Font.BOLD, 15));
        radioFemale.setOpaque(false);

        if (properties.get("sex").equals("male")){
            radioMale.setSelected(true);
        } else {
            radioFemale.setSelected(true);
        }

        ButtonGroup sex = new ButtonGroup();
        sex.add(radioFemale);
        sex.add(radioMale);
        JLabel characterIcon = new JLabel(new ImageIcon("src/main/resources/Pictures/Icons/" + properties.get("sex") + "_icon.png"));
        characterIcon.setBounds(280, 154, 40, 66);

        radioMale.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                characterIcon.setIcon(new ImageIcon("src/main/resources/Pictures/Icons/male_icon.png"));
                properties.setProperty("sex", "male");
                try {
                    properties.store(new FileOutputStream("src/main/resources/options.properties"), null);
                } catch (Exception ignored){}
            }
        });

        radioFemale.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                characterIcon.setIcon(new ImageIcon("src/main/resources/Pictures/Icons/female_icon.png"));
                properties.setProperty("sex", "female");
                try {
                    properties.store(new FileOutputStream("src/main/resources/options.properties"), null);
                } catch (Exception ignored){}
            }
        });

        add(radioMale);
        add(radioFemale);
        add(characterIcon);

        JRadioButton radioGrass = new JRadioButton("grass");
        radioGrass.setBounds(50, 300, 80, 20);
        radioGrass.setFont(new Font("Ink Free", Font.BOLD, 15));
        radioGrass.setOpaque(false);

        JRadioButton radioCake = new JRadioButton("cake");
        radioCake.setFont(new Font("Ink Free", Font.BOLD, 15));
        radioCake.setBounds(150, 300, 80, 20);
        radioCake.setOpaque(false);
        ButtonGroup blocks = new ButtonGroup();
        blocks.add(radioCake);
        blocks.add(radioGrass);
        JLabel blockIcon = new JLabel(new ImageIcon("src/main/resources/Pictures/Icons/" + properties.get("block") + "_icon.png"));
        blockIcon.setBounds(260, 300, 80, 20);

        if (properties.get("block").equals("grass")){
            radioGrass.setSelected(true);
        } else {
            radioCake.setSelected(true);
        }

        radioGrass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                blockIcon.setIcon(new ImageIcon("src/main/resources/Pictures/Icons/grass_icon.png"));
                properties.setProperty("block", "grass");
                try {
                    properties.store(new FileOutputStream("src/main/resources/options.properties"), null);
                } catch (Exception ignored){}
            }
        });

        radioCake.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                blockIcon.setIcon(new ImageIcon("src/main/resources/Pictures/Icons/cake_icon.png"));
                properties.setProperty("block", "cake");
                try {
                    properties.store(new FileOutputStream("src/main/resources/options.properties"), null);
                } catch (Exception ignored){}
            }
        });

        add(radioGrass);
        add(radioCake);
        add(blockIcon);

        BackToMenuButton backToMenu = new BackToMenuButton(container);
        backToMenu.setBounds(180, 550, BUTTON_WIDTH, BUTTON_HEIGHT);
        add(backToMenu);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
    }

}
