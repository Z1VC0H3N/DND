package il.co.test;

import EnemyTypes.*;
import GAME.Board;
import Rogues.*;
import TILE.*;
import UNIT.*;
import UTILITY.*;
import Hunters.*;
import Mages.*;
import Warriors.*;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        startGame();

    }

    public static void startGame() {
        boolean makeMistake=false;
        Player[] playerSet = {new JonSnow(0,0), new TheHound(0,0), new Melisandre(0,0), new ThorosOfMyr(0,0), new AryaStark(0,0), new Bronn(0,0), new Ygritte(0,0)};
         File[] levels = {new File("\"C:\\Users\\Owner\\BGU\\oop\\hw3\\levels_dir\\level1.txt\""), new File("\"C:\\Users\\Owner\\BGU\\oop\\hw3\\levels_dir\\level2.txt\""), new File("\"C:\\Users\\Owner\\BGU\\oop\\hw3\\levels_dir\\level3.txt\""), new File("\"C:\\Users\\Owner\\BGU\\oop\\hw3\\levels_dir\\level4.txt\"")};
        int levelIdx = 0;
        System.out.println("Select Player: ");
        for (Player p : playerSet) {
            System.out.println(p.description());
        }
        Scanner scanner = new Scanner(System.in);
        String str = "";
        String choiceStr = "";
        int choice = 0;
        try {
            choiceStr = scanner.next();
            choice = Integer.parseInt(choiceStr);
        } catch (Exception e) {
            System.out.println("you must enter only 1 digit number");
            startGame();
        }
        try {
            Board board = new Board(levels[levelIdx], choice);
            board.startGame();
            while(board.isAlive())
            {
//                try {
//                    choiceStr = scanner.next();
//                } catch (Exception e) {
//                    System.out.println("you must enter only 1 digit number");
//
//                }
//                if(choiceStr=="w" |choiceStr=="a" |choiceStr=="s" |choiceStr=="d" |choiceStr=="q" )
//                {
//                 board.movePlayer(choiceStr);
//                }
//                else if(choiceStr=="e")
//                    {
//                     board.cast();
//                    }
//                else {
//                    System.out.println("u must peek one character :");
//                    System.out.println("w - Move up \n ");
//                    System.out.println("s - Move down \n");
//                    System.out.println("a - Move left \n");
//                    System.out.println("d - Move right \n :");
//                    System.out.println("e - Cast special ability \n");
//                    System.out.println("q - Do nothing \n");
//                    makeMistake=true;
//                }
//                if(!makeMistake)
//                {
//                    if(board.hasNoEnemysLeft())
//                    {
//                        levelIdx++;
//                        board = new Board(levels[levelIdx],board.getPlayer());
//                    }
//                }

            }
        } catch (Exception e){
            System.out.println("something went wrong");
            startGame();
        }


        LinkedList<Tile> board = new LinkedList<>();
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                board.add(new Tile('.', i, j,""));
            }
        }
        Monster m=new Monster(2,1,10,10,10,10,3,3);
        Player p = new Player(2,3,"ziv",10,10,1,1,"");
        Wall w2 =new Wall(2,2);
        Wall w1 =new Wall(2,1);
        Wall w0 =new Wall(2,0);
        Wall w3 =new Wall(2,3);
        Wall w4 =new Wall(2,4);
        board.set(11,m);
        board.set(2,w0);
        board.set(7,w1);
        board.set(12,w2);
        board.set(17,w3);
        board.set(22,w4);
        board.set(13,p);
        Set<Position> set =new HashSet<>();
        int i=6;
        for(Tile t : board){
            set.add(t.getPosition());
            System.out.print(t.toString());
            if(i%5==0){
                System.out.println('\n');
                i=5;
            }
            i++;
        }

        m.setShortPaths(m,board);
        System.out.println(p.getPosition().getShortestPath());
    }

}