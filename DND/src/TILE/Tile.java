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

}
