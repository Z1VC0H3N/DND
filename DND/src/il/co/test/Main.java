package il.co.test;

import TILE.EmptyTile;
import TILE.Wall;
import UNIT.Player;

public class Main {

    public static void main(String[] args) {
        System.out.println((char)46);
        EmptyTile tile =new EmptyTile(5,6);
        System.out.println(tile.toString());
        Wall wall =new Wall(5,6);
        System.out.println(wall);
        Player p =new Player(5,6,"ziv",50,50,50,50,"");
        System.out.println(p.toString());


    }
}
