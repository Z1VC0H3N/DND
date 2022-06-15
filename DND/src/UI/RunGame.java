package UI;

import EnemyTypes.Monster;
import EnemyTypes.Trap;
import GAME.Board;
import GAME.MoveOrder;
import Hunters.Ygritte;
import Interactions.PlayerMovement;
import Interactions.Visited;
import Mages.Melisandre;
import Mages.ThorosOfMyr;
import Rogues.AryaStark;
import Rogues.Bronn;
import TILE.Tile;
import TILE.Wall;
import UNIT.Enemy;
import UNIT.Player;
import UTILITY.Position;
import Warriors.JonSnow;
import Warriors.TheHound;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class RunGame {
  private MoveOrder moveOrder =new MoveOrder();
  private File levelsPath = new File("./levels_dir");
  private File[] levels;
  private File myLevel;
  private Player p;
  private Board b;
  private LinkedList<Monster> monsters;
  private LinkedList<Trap> traps;
  private LinkedList<Enemy> totalEnemies;
  private int numOfEnemies;
  private boolean endGame =false;

  public void startGame(String[] args) throws IOException {
      levelsPath = new File(args[0]);
      myLevel =new File(String.valueOf(levelsPath.getCanonicalFile()));
      levels = myLevel.listFiles(); // gets all levels from folder levels
      boolean makeMistake=false;
      Player[] playerSet = {new JonSnow(0,0), new TheHound(0,0), new Melisandre(0,0), new ThorosOfMyr(0,0), new AryaStark(0,0), new Bronn(0,0), new Ygritte(0,0)};
      int levelIdx = 0;
      int x=1;
      System.out.println("Select Player: ");
      for (Player p : playerSet) {
          System.out.println("\t"+x+". " +p.description());
          x++;
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
          startGame(args);
      }
      if(choice<1 | choice>7){
          System.out.println("you must enter 1 to 7");
          startGame(args);
      }
      System.out.println("You have selected:");
      System.out.println(playerSet[choice-1].getName());
      b =new Board(levels[0],choice-1);
      System.out.println(b.toString());
      p=b.getPlayer();
      endGame =b.isAlive();// need to be changed
      monsters =b.getMonsters();
      traps =b.getTraps();
      totalEnemies = new LinkedList<>();
      totalEnemies.addAll(monsters);
      totalEnemies.addAll(traps);
      moveOrder.addObservers(p);
      for(Trap t :traps){
          moveOrder.addObservers(t);
      }
      for(Monster m : monsters){
          moveOrder.addObservers(m);
      }
      numOfEnemies =b.getNumOfEnemies();
      PlayerMovement pm =new PlayerMovement(p);
      String move ="";
      while(numOfEnemies>0 & b.isAlive()){
          System.out.println(p.description());
          System.out.println(b.toString());
          Scanner in = new Scanner(System.in);
          try{
              move = in.nextLine();
              Visited v;
              switch (move){
                  case "w":
                       v =b.getTile(p.getX(),p.getY()-1);
                      if(!b.getTile(p.getX(),p.getY()-1).description().equals("")){
                          System.out.println(p.getName()+" engaged in combat with "+b.getTile(p.getX(),p.getY()-1).getName()+".");
                          System.out.println(p.description());
                          System.out.println(b.getTile(p.getX(),p.getY()-1).description());
                      }
                      move =moveOrder.move(pm,v,b.getBoard());
                      break;
                  case "s":
                       v =b.getTile(p.getX(),p.getY()+1);
                       if(!b.getTile(p.getX(),p.getY()+1).description().equals("")){
                           System.out.println(p.getName()+" engaged in combat with "+b.getTile(p.getX(),p.getY()+1).getName()+".");
                           System.out.println(p.description());
                           System.out.println(b.getTile(p.getX(),p.getY()+1).description());
                       }
                       move = moveOrder.move(pm,v,b.getBoard());
                       break;
                  case "a":
                      v =b.getTile(p.getX()-1,p.getY());
                      if(!b.getTile(p.getX()-1,p.getY()).description().equals("")){
                          System.out.println(p.getName()+" engaged in combat with "+b.getTile(p.getX()-1,p.getY()).getName()+".");
                          System.out.println(p.description());
                          System.out.println(b.getTile(p.getX()-1,p.getY()).description());
                      }
                      move = moveOrder.move(pm,v,b.getBoard());
                      break;
                  case "d":
                      v =b.getTile(p.getX()+1,p.getY());
                      if(!b.getTile(p.getX()+1,p.getY()).description().equals("")){
                          System.out.println(p.getName()+" engaged in combat with "+b.getTile(p.getX()+1,p.getY()).getName()+".");
                          System.out.println(p.description());
                          System.out.println(b.getTile(p.getX(),p.getY()+1).description());
                      }
                      move = moveOrder.move(pm,v,b.getBoard());
                      break;
                  case "e":
                      move=moveOrder.cast(pm,totalEnemies, b.getBoard());
                      break;
                  case "q":
                      //do nothing
                      break;
                  default:
                      System.out.println("u must peek one character :");
                      System.out.println("w - Move up \n ");
                      System.out.println("s - Move down \n");
                      System.out.println("a - Move left \n");
                      System.out.println("d - Move right \n");
                      System.out.println("e - Cast special ability \n");
                      System.out.println("q - Do nothing \n");
                      break;
              }
          }catch (Exception e){
              System.out.println("something went wrong");
              // go back
          }

      }

//      public void printErrorMainArg()
//      {
//          System.out.println("Please run the code as follows:");
//          System.out.println("java -jar hw3.jar <Path to directory of files>");
//      }
//      public void printEndGame(boolean playerDeath) {
//          if (playerDeath){
//              System.out.println("Game over!!!");
//          }
//          else {
//              System.out.println("You won!!!");
//          }
//      }
//      public void printEndLevel(int level)
//      {
//          System.out.println("Finish level: " + (level + 1));
//          System.out.println("-----------------------------------------------------------");
//          System.out.println("██╗     ███████╗██╗   ██╗███████╗██╗         ██╗   ██╗██████╗ ██╗\n" +
//                  "██║     ██╔════╝██║   ██║██╔════╝██║         ██║   ██║██╔══██╗██║\n" +
//                  "██║     █████╗  ██║   ██║█████╗  ██║         ██║   ██║██████╔╝██║\n" +
//                  "██║     ██╔══╝  ╚██╗ ██╔╝██╔══╝  ██║         ██║   ██║██╔═══╝ ╚═╝\n" +
//                  "███████╗███████╗ ╚████╔╝ ███████╗███████╗    ╚██████╔╝██║     ██╗\n" +
//                  "╚══════╝╚══════╝  ╚═══╝  ╚══════╝╚══════╝     ╚═════╝ ╚═╝     ╚═╝\n" );
//      }
//      public void printLevel(GameLevel level)
//      {
//          System.out.println(level.toString());
//      }
//      public void printLoseGame()
//      {
//          System.out.println("Player is Dead End Game");
//          System.out.println(
//                  " __     __           _                    _ \n" +
//                          " \\ \\   / /          | |                  | |\n" +
//                          "  \\ \\_/ /__  _   _  | |     ___  ___  ___| |\n" +
//                          "   \\   / _ \\| | | | | |    / _ \\/ __|/ _ \\ |\n" +
//                          "    | | (_) | |_| | | |___| (_) \\__ \\  __/_|\n" +
//                          "    |_|\\___/ \\__,_| |______\\___/|___/\\___(_)");
//      }


//      try {
//          while(b.isAlive())
//          {
//                try {
//                    choiceStr = scanner.next();
//                } catch (Exception e) {
//                    System.out.println("you must enter only 1 digit number");
//
//                }
//                if(choiceStr=="w" |choiceStr=="a" |choiceStr=="s" |choiceStr=="d" |choiceStr=="q" )
//                {
//                 b.movePlayer(choiceStr);
//                }
//                else if(choiceStr=="e")
//                    {
//                     b.cast();
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
//                    if(b.hasNoEnemiesLeft())
//                    {
//                        levelIdx++;
//                        //board = new Board(levels[levelIdx],board.getPlayer());
//                    }
//                }
//
//          }
//      } catch (Exception e){
//          System.out.println("something went wrong");
//          //startGame();
//      }
//
//
//      LinkedList<Tile> board = new LinkedList<>();
//      for(int i=0;i<10;i++){
//          for(int j=0;j<10;j++){
//              board.add(new Tile('.', i, j,""));
//          }
//      }
//      Monster m=new Monster(2,1,10,10,10,10,3,3);
//      Player p = new Player(2,3,"ziv",10,10,1,1,"");
//      Wall w2 =new Wall(2,2);
//      Wall w1 =new Wall(2,1);
//      Wall w0 =new Wall(2,0);
//      Wall w3 =new Wall(2,3);
//      Wall w4 =new Wall(2,4);
//      board.set(11,m);
//      //board.set(2,w0);
//      board.set(7,w1);
//      board.set(12,w2);
//      board.set(17,w3);
//      board.set(22,w4);
//      board.set(13,p);
//      Set<Position> set =new HashSet<>();
//      int i=11;
//      for(Tile t : board){
//          set.add(t.getPosition());
//          System.out.print(t.toString());
//          if(i%10==0){
//              System.out.println('\n');
//              i=11;
//          }
//          i++;
//      }
//
//      m.setShortPaths(m,board);
//      System.out.println(p.getPosition().getShortestPath());
  }
}
