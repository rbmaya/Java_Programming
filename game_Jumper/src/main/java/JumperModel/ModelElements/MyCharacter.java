package JumperModel.ModelElements;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static JumperConstants.Constants.*;

public class MyCharacter {
    private int x, y;
    private int xSpeed, ySpeed;
    private boolean moveRight, moveLeft;

    public void toStartPosition(){
        this.x = FRAME_WIDTH / 2 - CHARACTER_WIDTH / 2 + BLOCK_WIDTH / 2;
        this.y = FRAME_HEIGHT - CHARACTER_HEIGHT;
        this.xSpeed = DEFAULT_X_SPEED;
        this.ySpeed = DEFAULT_Y_SPEED;
        this.moveLeft = false;
        this.moveRight = false;
    }

    private boolean isOnBlock(ArrayList<Block> blocks) {
        if (ySpeed <= 0) {
            for (Block block : blocks) {
                if ((x + CHARACTER_WIDTH >= block.getX()) && (x <= block.getX() + BLOCK_WIDTH)) {
                    if ((y + CHARACTER_HEIGHT <= block.getY() + BLOCK_HEIGHT + INACCURACY) && (y + CHARACTER_HEIGHT >= block.getY() - INACCURACY)) {
                        block.changeState();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void move(ArrayList<Block> blocks) {
        if (isOnBlock(blocks)){
            ySpeed = DEFAULT_Y_SPEED;
        }

        if (x > FRAME_WIDTH) {
            x = 0;
        }

        if (x < -CHARACTER_WIDTH) {
            x = FRAME_WIDTH - CHARACTER_WIDTH;
        }

        if (moveRight) {
           x += xSpeed;
        }

        if (moveLeft) {
            x -= xSpeed;
        }

        if (y >= FRAME_HEIGHT - CHARACTER_HEIGHT) {
            ySpeed = DEFAULT_Y_SPEED;
        }

        y -= ySpeed;
        ySpeed -= ACCELERATION;
    }

    public boolean isOnBottom(){
        return y >= FRAME_HEIGHT - CHARACTER_HEIGHT;
    }

    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case (KeyEvent.VK_RIGHT): {
                moveRight = true;
                break;
            }
            case (KeyEvent.VK_LEFT): {
                moveLeft = true;
                break;
            }
        }
    }

    public void keyReleased(KeyEvent event) {
        switch (event.getKeyCode()) {
            case (KeyEvent.VK_RIGHT): {
                moveRight = false;
                break;
            }
            case (KeyEvent.VK_LEFT): {
                moveLeft = false;
                break;
            }
        }
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getXSpeed(){
        return xSpeed;
    }

    public int getYSpeed(){
        return ySpeed;
    }
}
