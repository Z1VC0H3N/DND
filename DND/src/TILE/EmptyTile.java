package TILE;

import Interactions.Visited;
import Interactions.Visitor;

import java.util.LinkedList;

public class EmptyTile extends Tile implements Visited {
    public EmptyTile(int x, int y) {
        super((char) 46, x, y, "Empty");
        this.description="Free, characters can step over";
    }
    public String accept(Visitor v, LinkedList<Tile> board) {
        String s=v.visit(this, board);
        return s;

    }
}
