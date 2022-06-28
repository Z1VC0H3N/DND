package backend.Interactions;
import backend.Interfaces.Visited;
import backend.Interfaces.Visitor;
import backend.UNIT.Tile;

import java.util.LinkedList;

public abstract class Movement implements Visitor {
    public String move(Visited v, LinkedList<Tile> board){
        return v.accept(this,board);
    }
}
