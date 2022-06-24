package Interactions;

import Interfaces.Observable;
import UNIT.Enemy;
import Interfaces.Visited;
import TILE.Tile;

import java.util.LinkedList;

public class MoveOrder implements Observable {
    LinkedList<Visited> observers;

    public MoveOrder(){
        this.observers =new LinkedList<>();
    }

    @Override
    public String move(Movement m, Visited v, LinkedList<Tile> board) {
        return m.move(v,board);

    }

    @Override
    public String cast(Movement m, LinkedList<Enemy> enemies, LinkedList<Tile> board) {
        return m.visit(enemies,board);
    }

    @Override
    public void addObservers(Visited v) {
       observers.addLast(v);
    }

    @Override
    public void notifyGameTick() {
      for(Visited v : observers){
          v.gameTick();
        }
    }
}
