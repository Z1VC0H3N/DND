package GAME;

import EnemyTypes.Monster;
import EnemyTypes.Trap;
import Interactions.Visited;
import TILE.*;
import UNIT.*;
import Warriors.*;
import Mages.*;
import Hunters.*;
import Rogues.*;
import MonsterTypes.*;
import TrapsTypes.*;
import jdk.jshell.SourceCodeAnalysis;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TransferQueue;

public class Board {
    private LinkedList<Tile> units;
    private int length;
    private int height;
    private LinkedList<Monster> enemies;
    private LinkedList<Trap> traps;
    private Player hero ;
    private File level;
    private ArrayList<Character> tile;// dont know why
    private int numOfHero;

//    public Board(File f, int numOfPlayer) throws IOException {
//        this.level = f;
//        parseFile(level);
//        this.numOfHero = numOfPlayer;
//    }
    public Board(File f,int numOfPlayer){
        this.level=f;
        //this.hero=p;
        try {
            parseFile(f,numOfPlayer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player chooseHero(int numOfPlayer, int x, int y) {
        switch (numOfPlayer) {
            case (1):
                return new JonSnow(x,y);
            case (2):
                return new TheHound(x,y);
            case (3):
                return new Melisandre(x,y);
            case (4):
                return new ThorosOfMyr(x,y);
            case (5):
                return new AryaStark(x,y);
            case (6):
                return new Bronn(x,y);
            case (7):
                return new Ygritte(x,y);
                // needs to add to the hero the location;
        }
        return null;
    }

    public void parseFile(File f,int numOfHero) throws IOException {
        if (f.isFile()) {//other condition like name ends in html
            BufferedReader first = new BufferedReader(new FileReader(f));
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
            traps =new LinkedList<>();
            BufferedReader sec = new BufferedReader(new FileReader(f));
            str = "";
            int idy = 0;
            while ((str = sec.readLine()) != null) {
                for (int i = 0; i < str.length(); i++) {
                    // i is the x parameter and idy is the y parameter in Position
                    if(str.charAt(i)=='@'){
                        hero = chooseHero(numOfHero,i,idy);
                    }
                    units.addLast(getTile(str.charAt(i), i, idy));
                }
                idy++;
            }
        }
    }

    private Tile getTile(char charAt, int x, int y) {
        switch (charAt) {
            case ('.'):
                return new EmptyTile(x, y);
            case ('#'):
                return new Wall(x, y);
            case ('@'):
                //return chooseHero(numOfHero, x, y);
                return hero;
            case ('s'):
                LannisterSolider l =new LannisterSolider(x,y);
                enemies.add(l);// dont know why maybe we will know
                return l;
            case ('k'):
                LannisterKnight k =new LannisterKnight(x,y);
                enemies.add(k);
                return k;
            case ('q'):
                QueensGuard qg =new QueensGuard(x,y);
                enemies.add(qg);
                return qg;
            case ('z'):
                Wright z =new Wright(x,y);
                enemies.add(z);
                return z;
            case ('b'):
                BearWright b =new BearWright(x,y);
                enemies.add(b);
                return b;
            case ('g'):
                GiantWright g =new GiantWright(x,y);
                enemies.add(g);
                return g;
            case ('w'):
                WhiteWalker w = new WhiteWalker(x,y);
                enemies.add(w);
                return w;
            case ('M'):
                TheMountain m = new TheMountain(x,y);
                enemies.add(m);
                return m;
            case ('C'):
                QueenCersei c =new QueenCersei(x,y);
                enemies.add(c);
                return c;
            case ('K'):
                NightsKing K =new NightsKing(x,y);
                enemies.add(K);
                return K;
            case ('B'):
                BonusTrap B =new BonusTrap(x,y);
                traps.add(B);
                return B;
            case ('Q'):
                QueensTrap q =new QueensTrap(x,y);
                traps.add(q);
                return q;
            case ('D'):
                DeathTrap d =new DeathTrap(x,y);
                traps.add(d);
                return d;
        }
        return null;
    }

	public LinkedList<Tile> getBoard(){
		return units;
	}

	public LinkedList<Monster> getMonsters(){
		return enemies;
	}

	public LinkedList<Trap> getTraps(){
		return traps;
	}

	public Player getPlayer() {
		return hero;
	}
	public boolean isAlive(){
        return hero.getHealthAmount()>0;
    }

	public int getNumOfEnemies() {
		return enemies.size() +traps.size();
	}
	public int getNumOfMonsters(){
        return enemies.size();
    }
	public int getNumOfTraps(){
        return traps.size();
    }
    public String getStringOfTile(int x,int y){
        return units.get(length*x+y).toString();
    }

    public String toString(){
            String board = "";
            for(int i = 0; i < height; i += 1)
            {
                for(int j = 0; j < length; j += 1)
                {
                    board += units.get(i*length+j).toString();
                }
                board += "\n";
            }
            return board;
        }

    public boolean hasNoMonsterLeft() {
        return enemies.size()==0;
    }
    public boolean hasNoTrapsLeft(){
        return traps.size()==0;
    }

    public Tile getTile(int x, int y) {
        return units.get(length*x+y);
    }
}
