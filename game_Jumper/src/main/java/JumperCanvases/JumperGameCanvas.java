package JumperCanvases;

import JumperModel.JumperModel;
import JumperModel.ModelElements.Block;
import JumperServices.FileRecordsService;
import Observers.Observable;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import static JumperConstants.Constants.*;


public class JumperGameCanvas extends JPanel implements Observer, ActionListener {
    private Container container;
    private JumperModel jumperModel;
    private Image imageCharacter, background, imageStandardBlock, imageIceBlock, imageIceBrokenBlock, imageSnowBlock;
    private Timer timer = new Timer(DELAY, this);
    private JLabel score;

    public JumperGameCanvas(Container container) {
        this.container = container;
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        jumperModel = new JumperModel();
        jumperModel.addObserver(this);
        jumperModel.addObserver(new FileRecordsService());

        Properties options = new Properties();
        try {
            options.load(new FileInputStream("src/main/resources/options.properties"));
        } catch (Exception ignored) {
        }

        imageCharacter = new ImageIcon("src/main/resources/Pictures/Players/" + options.get("sex") + "_character.png").getImage();
        background = new ImageIcon("src/main/resources/Pictures/Backgrounds/backGr_Game.png").getImage();
        imageStandardBlock = new ImageIcon("src/main/resources/Pictures/Environments/ground_" + options.get("block") + ".png").getImage();
        imageIceBlock = new ImageIcon("src/main/resources/Pictures/Environments/ground_ice.png").getImage();
        imageIceBrokenBlock = new ImageIcon("src/main/resources/Pictures/Environments/ground_ice_broken.png").getImage();
        imageSnowBlock = new ImageIcon("src/main/resources/Pictures/Environments/ground_snow.png").getImage();
        score = new JLabel();
        score.setBounds(0, 0, 30, 30);
        score.setForeground(Color.white);
        score.setFont(new Font("Ink Free", Font.BOLD, 40));
        add(score);
        timer.start();

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                jumperModel.keyPressed(event);
            }

            @Override
            public void keyReleased(KeyEvent event) {
                jumperModel.keyReleased(event);
            }
        });

        jumperModel.startGame();
    }


    @Override
    public void update(Observable.Event<?> event) {
        if (event instanceof Observable.TicEvent){
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        score.setText(jumperModel.getScore().toString());
        ArrayList<Block> blocks = jumperModel.getBlocks();
        for (Block block : blocks) {
            switch (block.getType()) {
                case "standard":
                    g.drawImage(imageStandardBlock, block.getX(), block.getY(), BLOCK_WIDTH, BLOCK_HEIGHT, null);
                    break;
                case "ice":
                    if (block.getDurability() == 2) {
                        g.drawImage(imageIceBlock, block.getX(), block.getY(), BLOCK_WIDTH, BLOCK_HEIGHT, null);
                    } else if (block.getDurability() == 1) {
                        g.drawImage(imageIceBrokenBlock, block.getX(), block.getY(), BLOCK_WIDTH, BLOCK_HEIGHT, null);
                    }
                    break;
                case "snow":
                    if (block.getDurability() == 1) {
                        g.drawImage(imageSnowBlock, block.getX(), block.getY(), BLOCK_WIDTH, BLOCK_HEIGHT, null);
                    }
                    break;
            }
        }
        g.drawImage(imageCharacter, jumperModel.getCharacter().getX(), jumperModel.getCharacter().getY(),
                CHARACTER_WIDTH, CHARACTER_HEIGHT, null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jumperModel.makeGameStep()) {
            timer.stop();
            container.removeAll();
            container.add(new JumperRestartMenuCanvas(container));
            container.revalidate();
        }
    }
}
