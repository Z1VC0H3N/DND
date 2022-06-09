package Interactions;

import TILE.Tile;

import java.util.LinkedList;

public interface Visited {
    String accept(Visitor v, LinkedList<Tile> board);
    void gameTick();
}
