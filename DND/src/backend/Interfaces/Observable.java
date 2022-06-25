package backend.Interfaces;

import backend.Interactions.Movement;
import backend.UNIT.Enemy;
import backend.TILE.Tile;

import java.util.LinkedList;

public interface Observable {
    public String move(Movement m, Visited v , LinkedList<Tile> board);
    public String cast(Movement m, LinkedList<Enemy> enemies,LinkedList<Tile> board);
    public void addObservers(Visited v);
    public void notifyGameTick();
}
