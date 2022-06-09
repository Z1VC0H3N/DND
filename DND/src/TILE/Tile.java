package TILE;

import UTILITY.Position;

public class Tile {
    protected char tile;
    protected Position position;
    protected String name;
    public String description;

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
}
