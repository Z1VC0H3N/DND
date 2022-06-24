package UI;

import EnemyTypes.*;
import GAME.Board;
import Interactions.EnemyMovement;
import Interactions.MoveOrder;
import Hunters.Ygritte;
import Interactions.PlayerMovement;
import Interfaces.Visited;
import Mages.Melisandre;
import Mages.ThorosOfMyr;
import Rogues.AryaStark;
import Rogues.Bronn;
import UNIT.Enemy;
import UNIT.Player;
import UTILITY.Position;
import Warriors.JonSnow;
import Warriors.TheHound;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.PrivilegedExceptionAction;
import java.util.LinkedList;
import java.util.Scanner;

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
  private PlayerMovement pm;

    public void startGame(String[] args) throws IOException {
      levelsPath = new File(args[0]);
      myLevel = new File(String.valueOf(levelsPath.getCanonicalFile()));
      levels = myLevel.listFiles(); // gets all levels from folder levels
      Player[] playerSet = {new JonSnow(0, 0), new TheHound(0, 0), new Melisandre(0, 0), new ThorosOfMyr(0, 0), new AryaStark(0, 0), new Bronn(0, 0), new Ygritte(0, 0)};
      int levelIdx = 0;
      int x = 1;
      System.out.println("Select Player: ");
      for (Player p : playerSet) {
          System.out.println("\t" + x + ". " + p.description());
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
      if (choice < 1 | choice > 7) {
          System.out.println("you must enter 1 to 7");
          startGame(args);
      }
      System.out.println("You have selected:");
      System.out.println(playerSet[choice - 1].getName());
      b = new Board(levels[levelIdx], choice);
      p = b.getPlayer();
      System.out.println(b.toString());
      initialData(b);
      String move = "";
      boolean finish =false;
          while (numOfEnemies > 0 & b.isAlive() & !finish) {
              System.out.println(b.toString());
              System.out.println(p.description());
              Scanner in = new Scanner(System.in);
              try {
                  move = in.nextLine();
                  Visited v;
                  switch (move) {
                      case "w":
                          v = b.getTile(p.getX(), p.getY() - 1);
                          if (!b.getTile(p.getX(), p.getY() - 1).description().equals("")) {
                              System.out.println(p.getName() + " engaged in combat with " + b.getTile(p.getX(), p.getY() - 1).getName() + ".");
                              System.out.println(p.description());
                              System.out.println(b.getTile(p.getX(), p.getY() - 1).description());
                          }
                          move = moveOrder.move(pm, v, b.getBoard());
                          break;
                      case "s":
                          v = b.getTile(p.getX(), p.getY() + 1);
                          if (!b.getTile(p.getX(), p.getY() + 1).description().equals("")) {
                              System.out.println(p.getName() + " engaged in combat with " + b.getTile(p.getX(), p.getY() + 1).getName() + ".");
                              System.out.println(p.description());
                              System.out.println(b.getTile(p.getX(), p.getY() + 1).description());
                          }
                          move = moveOrder.move(pm, v, b.getBoard());
                          break;
                      case "a":
                          v = b.getTile(p.getX() - 1, p.getY());
                          if (!b.getTile(p.getX() - 1, p.getY()).description().equals("")) {
                              System.out.println(p.getName() + " engaged in combat with " + b.getTile(p.getX() - 1, p.getY()).getName() + ".");
                              System.out.println(p.description());
                              System.out.println(b.getTile(p.getX() - 1, p.getY()).description());
                          }
                          move = moveOrder.move(pm, v, b.getBoard());
                          break;
                      case "d":
                          v = b.getTile(p.getX() + 1, p.getY());
                          if (!b.getTile(p.getX() + 1, p.getY()).description().equals("")) {
                              System.out.println(p.getName() + " engaged in combat with " + b.getTile(p.getX() + 1, p.getY()).getName() + ".");
                              System.out.println(p.description());
                              System.out.println(b.getTile(p.getX() + 1, p.getY()).description());
                          }
                          move = moveOrder.move(pm, v, b.getBoard());
                          break;
                      case "e":
                          move = moveOrder.cast(pm, totalEnemies, b.getBoard());
                          break;
                      case "q":
                          //do nothing
                          break;

                          // lets add some chits

                      case "killAll!":
                          for (Enemy e:totalEnemies){
                              e.onDeath();
                              p.setExp(p.getExp()+e.getExperience());
                              p.levelUp();
                          }
                          break;
                      case "jump":
                          System.out.println("enter location u want to go : example -> 4,5 ");
                          String jump = in.nextLine();
                          try {
                              int X =Integer.parseInt(jump.substring(0,jump.indexOf(',')));
                              int Y =Integer.parseInt(jump.substring(jump.indexOf(',')+1));
                              if(!b.getStringOfTile(X, Y).equals(".")){
                                  System.out.println("U cant go there");
                              }
                              else{
                                  move = b.swap(X,Y);
                                  break;
                              }
                          }catch (Exception e){
                              System.out.println("U didnt enter a proper input");
                          }
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
              } catch (Exception e) {
                  System.out.println("something went wrong");
                  // go back
              }
              if (!move.equals("")) {//prints the scenerio that happened
                  int count = 0;
                  for (int i = 0; i < move.length(); i++)
                      if (move.charAt(i) == ',') {
                          if (!move.substring(count, i).equals(""))
                              System.out.println(move.substring(count, i));
                          count = i + 1;
                      }

              }
              // now we preform an monster movement
              // monster can attack only player
              for (Monster e : monsters) {
                  EnemyMovement em = new EnemyMovement(e);
                  Visited v;
                  Position pos = e.preformMovement(p, b.getBoard(), b); // using dijkstra
                  v = b.getTile(pos.getX(), pos.getY());
                  if (b.getTile(pos.getX(), pos.getY()).getSign() == '@') {
                      System.out.println(e.getName() + " engaged in combat with " + p.getName() + ".");
                      System.out.println(e.description());
                      System.out.println(p.description());
                  }
                  move = moveOrder.move(em, v, b.getBoard());
                  //prints the scenerio that happened
                  if (!move.equals("")) {
                      int count = 0;
                      for (int i = 0; i < move.length(); i++)
                          if (move.charAt(i) == ',') {
                              if (!move.substring(count, i).equals(""))
                                  System.out.println(move.substring(count, i));
                              count = i + 1;
                          }
                  } else {

                  }
              }
                  for (Trap e : traps) {
                      EnemyMovement em = new EnemyMovement(e);
                      Visited v;
                      v = b.getTile(p.getX(), p.getY());
                      if (e.range(p)<2) {
                          System.out.println(e.getName() + " engaged in combat with " + p.getName()+ ".");
                          System.out.println(e.description());
                          System.out.println(p.description());
                      }
                      move = moveOrder.move(em, v, b.getBoard());
                      //prints the scenario that happened
                      if (!move.equals("")) {
                          int count = 0;
                          for (int i = 0; i < move.length(); i++)
                              if (move.charAt(i) == ',') {
                                  if (!move.substring(count, i).equals(""))
                                      System.out.println(move.substring(count, i));
                                  count = i + 1;
                              }
                      } else {

                      }
                  }
                  numOfEnemies =b.getNumOfEnemies();
                  moveOrder.notifyGameTick();
                  if(numOfEnemies==0& p.isAlive()){// change the board
                      levelIdx++;
                      if(levelIdx!=4) {
                          b = new Board(levels[levelIdx], p);
                          initialData(b);
                      }
                      else{
                          int width = 300;
                          int height = 30;

                          //BufferedImage image = ImageIO.read(new File("/Users/mkyong/Desktop/logo.jpg"));
                          BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                          Graphics g = image.getGraphics();
                          g.setFont(new Font("SansSerif", Font.BOLD, 24));

                          Graphics2D graphics = (Graphics2D) g;
                          graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                                  RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                          graphics.drawString("VICTORY!!! U ROCK!!", 10, 20);
                          for (int y = 0; y < height; y++) {
                              StringBuilder sb = new StringBuilder();
                              for (int xs = 0; xs < width; xs++) {

                                  sb.append(image.getRGB(xs, y) == -16777216 ? " " : "$");

                              }

                              if (sb.toString().trim().isEmpty()) {
                                  continue;
                              }

                              System.out.println(sb);
                          }
                          finish =true;
                          }
                  }
      }
          if(!finish) {
              System.out.println(b.toString());
          }
          if(!p.isAlive())
          {
              System.out.println("Player is Dead End Game");
              System.out.println(
                      " __     __           _                    _ \n" +
                              " \\ \\   / /          | |                  | |\n" +
                              "  \\ \\_/ /__  _   _  | |     ___  ___  ___| |\n" +
                              "   \\   / _ \\| | | | | |    / _ \\/ __|/ _ \\ |\n" +
                              "    | | (_) | |_| | | |___| (_) \\__ \\  __/_|\n" +
                              "    |_|\\___/ \\__,_| |______\\___/|___/\\___(_)");
          }

          }

    private void initialData(Board b) {
        monsters = b.getMonsters();
        traps = b.getTraps();
        totalEnemies = new LinkedList<>();
        for (Monster m : monsters) {
            totalEnemies.addLast(m);
        }
        for (Trap t : traps) {
            totalEnemies.addLast(t);
        }
        moveOrder.addObservers(p);
        for (Trap t : traps) {
            moveOrder.addObservers(t);
        }
        for (Monster m : monsters) {
            moveOrder.addObservers(m);
        }
        numOfEnemies = b.getNumOfEnemies();
        pm = new PlayerMovement(p);
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


  }

