package backend.EnemyTypes;

import backend.GAME.Board;
import backend.Interfaces.Visited;
import backend.Interfaces.Visitor;
import backend.UNIT.Tile;
import backend.UNIT.Enemy;
import backend.UNIT.Player;
import backend.UTILITY.Graph;
import backend.UTILITY.Position;
import backend.UTILITY.QueueAsLinkedList;


import java.util.*;

public class Monster extends Enemy implements Visited {
    protected int visionRange;
    public Monster(int x, int y, int healthPool ,int attack, int defense,int exp,int visionRange,char tile,String name) {
        super(x, y, tile, name, healthPool, healthPool, attack, defense,exp);
        this.visionRange=visionRange;
    }

    @Override
    public String accept(Visitor v, LinkedList<Tile> board) {
        return v.visit(this,board);
    }

    @Override
    public void gameTick() {

    }
    public Position preformMovement(Player p,LinkedList<Tile> board, Board game){
      if(this.range(p)<visionRange){
          Position pos =close(p,board);
          if(pos!=null){
              return pos;
          }
          setShortPaths(this,board);// evaluated shortest path
          if(!p.getPosition().getShortestPath().isEmpty()){
              p.getPosition().addDistance(p.getPosition());
              Position out =p.getPosition().getShortestPath().get(1);
              for(Tile t:board){
                  if(range(t)<visionRange){
                      t.getPosition().clearPath();
                  }
              }
              return out;
          }
          }

        int x = new Random().nextInt(4 + 1);
        switch (x){
            case 0:
                return this.getPosition();
            case 1://up
                if (!game.getStringOfTile(this.getX() , this.getY()-1).equals("#"))
                    return new Position(this.getX() , this.getY()-1);
            case 2://right
                if(!game.getStringOfTile(this.getX()+1 , this.getY()).equals("#"))
                    return new Position(this.getX() +1, this.getY());
            case 3://down
                if(!game.getStringOfTile(this.getX(), this.getY()+1).equals("#"))
                    return new Position(this.getX(), this.getY()+1);
            case 4://left
                if(!game.getStringOfTile(this.getX() -1, this.getY()).equals("#"))
                    return new Position(this.getX()-1 , this.getY());
        }
      return this.position;
    }

    private Position close(Player p, LinkedList<Tile> board) {
        for(Tile t: board){
            if(this.range(t)<=1 & t.getPosition().equals(p.getPosition())){
                return t.getPosition();
            }
        }
        return null;
    }

    public void setShortPaths(Monster monster, LinkedList<Tile> board) {
        Set<Position> set = new HashSet<>();
        QueueAsLinkedList<Tile> queue = new QueueAsLinkedList<Tile>();
        LinkedList<Tile> inRange = new LinkedList<>();
        for(Tile t: board){
            if(t.toString()!="#"){// make sure is not a wall
                if(monster.range(t)<visionRange){
                    inRange.add(t);
                }
            }
        }
        queue.enqueue(monster);
        while(!queue.isEmpty()){
            Tile tile = queue.dequeue();
            for(Tile t : inRange){
                if( tile.range(t)==1){
                    if(!t.getPosition().isVisited()) {// not pass on same tile twice
                        tile.getPosition().addDestination(t.getPosition(), 1);
                        t.getPosition().setVisited(true);
                        queue.enqueue(t);
                    }
                }
            }
        }
        for(Tile t: inRange){
            set.add(t.getPosition());
        }
        Graph g = new Graph(set);
        g.calculateShortestPathFromSource(g,monster.position);
    }
}
