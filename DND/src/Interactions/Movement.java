package Interactions;

import Interfaces.Visited;
import Interfaces.Visitor;
import TILE.Tile;

import java.util.LinkedList;

public abstract class Movement implements Visitor {
    public String move(Visited v, LinkedList<Tile> board){
        return v.accept(this,board);
    }
}
