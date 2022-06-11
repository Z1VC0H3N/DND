package EnemyTypes;

import GAME.Board;
import Interactions.Visited;
import Interactions.Visitor;
import TILE.Tile;
import EnemyTypes.UNIT.Enemy;
import EnemyTypes.UNIT.Player;
import UTILITY.Graph;
import UTILITY.Position;
import UTILITY.QueueAsLinkedList;


import java.util.*;

public class Monster extends Enemy implements Visited {
    public Monster(int x, int y, int healthPool, int healthAmount, int attack, int defense,int exp,int visionRange) {
        super(x, y, 'W', "monster", healthPool, healthAmount, attack, defense,exp,visionRange);
    }

    @Override
    public String accept(Visitor v, LinkedList<Tile> board) {
        return v.visit(this,board);
    }

    @Override
    public void gameTick() {

    }
    public Position preformMovement(Player p,LinkedList<Tile> board, Board game){
      if(range(p)<visionRange){
          setShortPaths(this,board);// evaluated shortest path
          if(p.getPosition().getShortestPath().isEmpty()){
              return this.position;
          }
          return p.getPosition().getShortestPath().get(1);
      }
        int x = new Random().nextInt(4 + 1);
        switch (x){
            case 0:
                return this.getPosition();
            case 1://up

                if (!game.getStringOfTile(this.getX() - 1, this.getY()).equals("#"))
                    return new Position(this.getX() - 1, this.getY());
            case 2://right
                if(!game.getStringOfTile(this.getX() , this.getY()+1).equals("#"))
                    return new Position(this.getX() , this.getY()+1);
            case 3://down
                if(!game.getStringOfTile(this.getX()+1, this.getY()+1).equals("#"))
                    return new Position(this.getX()+1, this.getY());
            case 4://left
                if(!game.getStringOfTile(this.getX() , this.getY()-1).equals("#"))
                    return new Position(this.getX() , this.getY()-1);
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
