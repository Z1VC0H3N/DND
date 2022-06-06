package TILE;

public class EmptyTile extends Tile {
    public EmptyTile(int x, int y) {
        super((char) 46, x, y, "Empty");
        this.description="Free, characters can step over";
    }
}
