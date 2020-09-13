package JumperModel;

import JumperModel.ModelElements.*;
import Observers.Observable;
import Observers.Observer;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import static JumperConstants.Constants.*;

public class JumperModel implements Observable {
    private MyCharacter character;
    private ArrayList<Block> blocks;
    boolean lost;
    Integer score = 0;

    public JumperModel() {
        character = new MyCharacter();
        blocks = new ArrayList<>();
        lost = false;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void startGame() {
        character.toStartPosition();
        blocks.add(new StandardBlock(FRAME_WIDTH / 2, FRAME_HEIGHT - 50));
        for (int i = 0; i < MAX_AMOUNT_OF_BLOCKS; ++i) {
            addBlock();
        }
    }

    public boolean makeGameStep() {
        notifyObservers(new TicEvent(new Object()));
        character.move(blocks);
        moveBlocks();
        replaceBlocks();
        if (character.isOnBottom()) {
            lost = true;
            notifyObservers(new RecordEvent(this.getScore()));
        }
        return (!lost);
    }

    public Integer getScore(){
        return score / 100;
    }

    public void addBlock() {
        Random random = new Random();
        int x = random.nextInt(FRAME_WIDTH - BLOCK_WIDTH - 2 * BORDER_DISTANCE) + BORDER_DISTANCE;
        int y = (blocks.get(blocks.size() - 1).getY() - MIN_DISTANCE - random.nextInt(MAX_JUMP - MIN_DISTANCE));
        int typeOfBlock = random.nextInt(TYPE_RANGE);

        if (typeOfBlock <= STANDARD_RANGE) {
            blocks.add(new StandardBlock(x, y));
        } else if (typeOfBlock <= ICE_RANGE) {
            blocks.add(new IceBlock(x, y));
        } else {
            blocks.add(new SnowBlock(x, y));
        }
    }

    private void moveBlocks() {
        if ((character.getY() < FRAME_HEIGHT / 2) && (character.getYSpeed() > 0)) {
            character.setY(FRAME_HEIGHT / 2);
            score += character.getYSpeed();
            for (Block block : blocks) {
                block.setY(block.getY() + character.getYSpeed());
            }
        }
    }

    private void replaceBlocks() {
        blocks.removeIf(block -> block.getY() > FRAME_HEIGHT);
        blocks.removeIf(block -> block.getDurability() == 0);
        while (blocks.size() < MAX_AMOUNT_OF_BLOCKS) {
            addBlock();
        }
    }

    public MyCharacter getCharacter() {
        return character;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void keyPressed(KeyEvent event){
        character.keyPressed(event);
    }

    public void keyReleased(KeyEvent event){
        character.keyReleased(event);
    }
}
