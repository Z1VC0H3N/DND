package TILE;

public class tile {
    protected char tile;
    protected int x;
    protected int y;
    protected String name;
    public String description;

    public tile(char tile,int x,int y , String name){
        this.tile=tile;
        this.x=x;
        this.y=y;
        this.name=name;
    }
    public char getSign(){
        return tile;
    }
    public String getName(){
        return name;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int newX){
        this.x=newX;
    }
    public void setY(int newY){
        this.y=newY;
    }
    public void setSign(char sign){
        this.tile=sign;
    }

}
