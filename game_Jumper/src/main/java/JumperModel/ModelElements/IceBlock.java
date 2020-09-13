package JumperModel.ModelElements;

import javax.swing.*;
import java.awt.*;

public class IceBlock extends Block {

    public IceBlock(int x, int y) {
        super(x, y);
        this.type = "ice";
        durability = 2;
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
