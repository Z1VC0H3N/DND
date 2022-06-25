package backend.TILE;

public class Wall extends Tile {
    public Wall(int x, int y) {
        super((char) 35, x, y,"Wall");
        this.description="Wall, blocked, no characters may step over";
    }
    public String toString(){
        return "#";
    }
}
