package JumperModel.ModelElements;

import javax.swing.*;
import java.awt.*;

public class SnowBlock extends Block {
    private Image image;
    private int durability;

    public SnowBlock(int x, int y) {
        super(x, y);
        this.type = "snow";
        durability = 1;
    }

    public int getDurability() {
        return durability;
    }

    @Override
    public void changeState() {
        --durability;
    }

    @Override
    public boolean isBroken() {
        return durability == 0;
    }
}
