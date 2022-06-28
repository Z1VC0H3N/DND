package frontend;

import backend.EnemyTypes.*;
import backend.GAME.Board;
import backend.Interactions.EnemyMovement;
import backend.Interactions.MoveOrder;
import backend.Interactions.PlayerMovement;
import backend.Interfaces.Visited;
import backend.PlayerTypes.Hunter;
import backend.PlayerTypes.Mage;
import backend.PlayerTypes.Rogue;
import backend.PlayerTypes.Warrior;
import backend.UNIT.Enemy;
import backend.UNIT.Player;
import backend.UTILITY.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class RunGame {
    private MoveOrder moveOrder = new MoveOrder();
    private File levelsPath = new File("./levels_dir");
    private File[] levels;
    private File myLevel;
    private Player p;
    private Board b;
    private LinkedList<Enemy> totalEnemies;
    private int numOfEnemies;
    private PlayerMovement pm;
    int time = 0;
    int currHealth = 0;
    int currPool = 0;
    int currDamage = 0;
    boolean cheater = false;
    int levelIdx = 0;
    public boolean finish =false;


    public void startGame(String[] args) throws IOException {
        levelsPath = new File(args[0]);
        myLevel = new File(String.valueOf(levelsPath.getCanonicalFile()));
        levels = myLevel.listFiles(); // gets all levels from folder levels
        Player[] playerSet =
                {
                        new Warrior(0, 0, "Jon Snow", 300, 300, 30, 4, 3)
                        , new Warrior(0, 0, "The Hound", 400, 400, 20, 6, 5)
                        , new Mage(0, 0, "Melisandre", 100, 100, 5, 1, 300, 30, 15, 5, 6)
                        , new Mage(0, 0, "Thoros of Myr", 250, 250, 25, 4, 150, 20, 20, 3, 4)
                        , new Rogue(0, 0, "Arya Stark", 250, 250, 40, 2, 20)
                        , new Rogue(0, 0, "Bronn", 250, 250, 35, 3, 50)
                        , new Hunter(0, 0, "Ygritte", 250, 250, 30, 2, 6)
                };

        int x = 1;
        System.out.println("Select Player: ");
        for (Player p : playerSet) {
            System.out.println("\t" + x + ". " + p.description());
            x++;
        }
        Scanner scanner = new Scanner(System.in);
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
        int num =0;
        System.out.println(b.toString());
        initialData(b);
        String move = "";
        boolean finish = false;
        while (numOfEnemies > 0 & b.isAlive() & !finish) {
            if(num!=0) {
                System.out.println(b.toString());
            }
            num++;
            System.out.println(p.description());
            Scanner in = new Scanner(System.in);
            move = in.nextLine();
            Visited v;
            switch (move) {
                case "w":
                    v = b.getTile(p.getX(), p.getY() - 1);
                    move = moveOrder.move(pm, v, b.getBoard());
                    break;
                case "s":
                    v = b.getTile(p.getX(), p.getY() + 1);
                    move = moveOrder.move(pm, v, b.getBoard());
                    break;
                case "a":
                    v = b.getTile(p.getX() - 1, p.getY());
                    move = moveOrder.move(pm, v, b.getBoard());
                    break;
                case "d":
                    v = b.getTile(p.getX() + 1, p.getY());
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
                    while (!totalEnemies.isEmpty()) {
                        p.setExp(p.getExp() + totalEnemies.getFirst().getExperience());
                        totalEnemies.getFirst().onDeath();
                        p.levelUp();
                    }
                    cheater = true;
                    break;
                case "jump":
                    System.out.println("enter location u want to go : example -> 4,5 ");
                    String jump = in.nextLine();
                    try {
                        int X = Integer.parseInt(jump.substring(0, jump.indexOf(',')));
                        int Y = Integer.parseInt(jump.substring(jump.indexOf(',') + 1));
                        if (X < 0 | Y < 0) {
                            System.out.println("U cant go there");
                            break;
                        } else if (X > b.getLength() | Y >= b.getHeight()) {
                            System.out.println("U cant go there");
                            break;
                        } else if (!b.getStringOfTile(X, Y).equals(".")) {
                            System.out.println("U cant go there");
                            break;
                        } else {
                            System.out.println(b.swap(X, Y));
                            cheater = true;
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("U didnt enter a proper input");
                    }
                case "ultimate power!":
                    time = 15;
                    currHealth = p.getHealthAmount();
                    currDamage = p.getAttack();
                    currPool = p.getHealthPool();
                    p.setHealthAmount(Integer.MAX_VALUE);
                    p.setHealthPool(Integer.MAX_VALUE);
                    p.setAttack(Integer.MAX_VALUE);
                    cheater = true;
                    System.out.println("U activated ultimate power for 15 game ticks");
                    break;
                case "level up!":
                    p.setExp(50 * p.getLevel());
                    p.levelUp();
                    cheater = true;
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

            if (!move.equals("")) {//prints the scenerio that happened
                int count = 0;
                for (int i = 0; i < move.length(); i++)
                    if (move.charAt(i) == ',') {
                        if (!move.substring(count, i).equals(""))
                            if (!move.substring(count, i).equals(" "))
                                System.out.println(move.substring(count, i));
                        count = i + 1;
                    }

            }
            //totalEnemies =b.getEnemies();
            // now we preform an enemy movement
            for (Enemy e : totalEnemies) {
                EnemyMovement em = new EnemyMovement(e);
                Position pos = e.preformMovement(p, b.getBoard()); // using dijkstra
                v = b.getTile(pos.getX(), pos.getY());
                move = moveOrder.move(em, v, b.getBoard());
                if (!move.equals("")) {
                    int count = 0;
                    for (int i = 0; i < move.length(); i++)
                        if (move.charAt(i) == ',') {
                            if (!move.substring(count, i).equals(""))
                                System.out.println(move.substring(count, i));
                            count = i + 1;
                        }
                }
            }
            onEveryGameTick();
        }
    }

 private void onEveryGameTick() {
    numOfEnemies = b.getNumOfEnemies();
    moveOrder.notifyGameTick();
    time--;
    if (time == 0) {
        p.setAttack(currDamage);
        p.setHealthAmount(currHealth);
        p.setHealthPool(currPool);
    }
    if (numOfEnemies == 0 & p.isAlive()) {// change the board
        levelIdx++;
        if (levelIdx != 4) {
            b = new Board(levels[levelIdx], p);
            initialData(b);
        } else {
            int width = 200;
            int height = 30;
            String toPrint = "VICTORY!!!";
            if (cheater) {
                toPrint = "CHEATER!!";
            }
            //BufferedImage image = ImageIO.read(new File("/Users/mkyong/Desktop/logo.jpg"));
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            g.setFont(new Font("SansSerif", Font.BOLD, 24));

            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics.drawString(toPrint, 10, 20);
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
            finish = true;
        }
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
        totalEnemies =b.getEnemies();
        moveOrder.addObservers(p);
        for (Enemy e : totalEnemies) {
            moveOrder.addObservers(e);
        }
        numOfEnemies = b.getNumOfEnemies();
        pm = new PlayerMovement(p);
    }

}

