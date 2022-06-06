package il.co.test;

import GAME.Board;
import TILE.EmptyTile;
import TILE.Wall;
import UNIT.Player;

import java.io.File;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        startGame();

    }
    public static void startGame() {
        boolean makeMistake=false;
        Player[] playerSet = {new JonSnow(), new TheHound(), new Melisandre(), new ThorosOfMyr(), new AryaStark(), new Bronn(), new Ygritte()};
        File[] levels = {new File("\"C:\\Users\\Owner\\BGU\\oop\\hw3\\levels_dir\\level1.txt\""), new File("\"C:\\Users\\Owner\\BGU\\oop\\hw3\\levels_dir\\level2.txt\""), new File("\"C:\\Users\\Owner\\BGU\\oop\\hw3\\levels_dir\\level3.txt\""), new File("\"C:\\Users\\Owner\\BGU\\oop\\hw3\\levels_dir\\level4.txt\"")};
        int levelIdx = 0;
        System.out.println("Select Player: ");
        for (Player p : playerSet) {
            System.out.println(p.data());
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
                try {
                    choiceStr = scanner.next();
                } catch (Exception e) {
                    System.out.println("you must enter only 1 digit number");

                }
                if(choiceStr=="w" |choiceStr=="a" |choiceStr=="s" |choiceStr=="d" |choiceStr=="q" )
                {
                 board.movePlayer(choiceStr);
                }
                else if(choiceStr=="e")
                    {
                     board.cast();
                    }
                else {
                    System.out.println("u must peek one character :");
                    System.out.println("w - Move up \n ");
                    System.out.println("s - Move down \n");
                    System.out.println("a - Move left \n");
                    System.out.println("d - Move right \n :");
                    System.out.println("e - Cast special ability \n");
                    System.out.println("q - Do nothing \n");
                    makeMistake=true;
                }
                if(makeMistake)
                {
                    if(board.hasNoEnemysLeft())
                    {
                        levelIdx++;
                        board = new Board(levels[levelIdx],board.getPlayer());
                    }
                }

            }
        } catch (Exception e){
            System.out.println("something went wrong");
            startGame();
        }
    }
}
