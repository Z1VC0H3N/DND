package GAME;

import TILE.EmptyTile;
import TILE.Tile;
import TILE.Wall;
import UNIT.Enemy;
import UNIT.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Board {
    private LinkedList<Tile> units;
    private int length;
    private int height;
    private LinkedList<Enemy> enemies;
    private Player hero;
    private File level;
    private ArrayList<Character> tile;// dont know why
    private int numOfHero;
    private int numOfEnemy;

    public Board(File f, int numOfPlayer) throws IOException {
        this.level = f;
        parseFile(level);
        this.numOfHero = numOfPlayer;
    }
    public Board(File f,Player p){
        //todo;
    }

    public Player chooseHero(int numOfPlayer, int x, int y) {
        switch (numOfPlayer) {
            case (1):
                hero = new JonSnow();
            case (2):
                hero = new TheHound();
            case (3):
                hero = new Melisandre();
            case (4):
                hero = new ThorosOfMyr();
            case (5):
                hero = new AryaStark();
            case (6):
                hero = new Bronn();
            case (7):
                hero = new Ygritte();
                // needs to add to the hero the location;
        }
        return null;
    }

    public void parseFile(File f) throws IOException {
        if (level.isFile()) {//other condition like name ends in html
            BufferedReader first = new BufferedReader(new FileReader(level));
            String str;
            int rows = 0;
            int columns = 0;
            while ((str = first.readLine()) != null) {
                rows++;
                columns = str.length();
            }
            this.length = columns;
            this.height = rows;
            units = new LinkedList<>();
            enemies = new LinkedList<>();
            BufferedReader sec = new BufferedReader(new FileReader(level));
            str = "";
            int idy = 0;
            while ((str = sec.readLine()) != null) {
                for (int i = 0; i < str.length(); i++) {
                    // i is the x parameter and idy is the y parameter in Position
                    units.add(getTile(str.charAt(i), i, idy));
                }
                idy++;
            }
        }
    }

    private Tile getTile(char charAt, int idx, int idy) {
        switch (charAt) {
            case ('.'):
                return new EmptyTile(idx, idy);
            case ('#'):
                return new Wall(idx, idy);
            case ('@'):
                return chooseHero(numOfHero, idx, idy);
            case ('s'):
                numOfEnemy++;
                enemies.add(new LannisterSolider());// dont know why maybe we will know
                return new LannisterSolider();
            case ('k'):
                return new LannisterKnight();
            case ('q'):
                return new QueensGuard();
            case ('z'):
                return new Wright();
            case ('b'):
                return new BearWright();
            case ('g'):
                return new GiantWright();
            case ('w'):
                return new WhiteWalker();
            case ('M'):
                return new TheMountain();
            case ('C'):
                return new QueenCersei();
            case ('K'):
                return new NightsKing();
            case ('B'):
                return new BonusTrap();
            case ('Q'):
                return new QueensTrap();
            case ('D'):
                return new DeathTrap();
        }
        return null;
    }
	public String getBoard(int i) {
		StringBuilder s= new StringBuilder();
        for (Tile unit : units) s.append(unit.getSign());
		return s.toString();
	}

	public LinkedList<Tile> getBoard(){
		return units;
	}

	public LinkedList<Enemy> getEnemies(){
		return enemies;
	}

//	public Stack<Trap> getTraps(){
//		return traps;
//	}

	public Player getPlayer() {
		return hero;
	}
	public boolean isAlive(){
        return hero.getHealthAmount()>0;
    }

	public int getNumOfEnemies() {
		return numOfEnemy;
	}


}
