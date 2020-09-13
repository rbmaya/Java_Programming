package JumperModel.ModelElements;

public class StandardBlock extends Block {

    public StandardBlock(int x, int y) {
        super(x, y);
        this.type = "standard";
        durability = 1;
    }
}
