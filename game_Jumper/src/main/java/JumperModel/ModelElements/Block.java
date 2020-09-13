package JumperModel.ModelElements;

public abstract class Block {
    protected int x, y;
    int durability;
    protected String type;

    public Block(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void changeState(){}

    public boolean isBroken(){
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDurability() {
        return durability;
    }

    public String getType(){
        return type;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
