package UTILITY;

public class Position {
    private int x;
    private int y;

    public Position(int z,int t){
        x=z;
        y=t;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int newX ){
        this.x=newX;
    }
    public void setY(int newY){
        this.y=newY;
    }
    public void swap(int newX,int newY){
        this.x=newX;
        this.y=newY;
    }


}
