package TILE;

import Interactions.Visited;
import Interactions.Visitor;
import UTILITY.Position;

import java.util.LinkedList;

public class Tile implements Visited {
    protected char tile;
    protected Position position;
    protected String name;
    protected String description;

    public Tile(char tile, int x, int y , String name){
        this.tile=tile;
        this.position=new Position(x,y);
        this.name=name;
    }
    public char getSign(){
        return tile;
    }
    public String getName(){
        return name;
    }
    public int getX(){
        return position.getX();
    }
    public int getY(){
        return position.getY();
    }
    public void setX(int newX){
        position.setX(newX);
    }
    public void setY(int newY){
       position.setY(newY);
    }
    public void setSign(char sign){
        this.tile=sign;
    }
    public String toString(){
        return String.valueOf((char) tile);
    }
    public Position getPosition(){
        return position;
    }
   public void setPosition(Position p){
        this.position=p;
   }
   public double range(Tile other){
       return Math.sqrt(Math.pow((this.getX()-other.getX()), 2)+Math.pow((this.getY()-other.getY()), 2));
   }

    @Override
    public String accept(Visitor v, LinkedList<Tile> board) {
        return "";
    }

    @Override
    public void gameTick() {
        return;
    }
    public String description(){
        return "";
    }
}
