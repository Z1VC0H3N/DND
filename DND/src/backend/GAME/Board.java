package backend.GAME;

import backend.EnemyTypes.Monster;
import backend.TILE.*;
import backend.UNIT.*;
import backend.UTILITY.Position;
import backend.Warriors.*;
import backend.Mages.*;
import backend.Hunters.*;
import backend.Rogues.*;
import backend.TrapsTypes.*;

import java.io.*;
import java.util.LinkedList;

public class Board {
    private LinkedList<Tile> units;
    private int length;
    private int height;
    private LinkedList<Enemy> enemies;
    private Player hero =null ;
    private File level;
    private int numOfHero;
    private Object lock =new Object();
    public Board(File f,int numOfPlayer){
        this.level=f;
        this.numOfHero=numOfPlayer;
        try {
            parseFile(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Board(File f,Player p){
        this.level=f;
        this.hero = p;
        try {
            parseFile(f);
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

    public void parseFile(File f) throws IOException {
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
            BufferedReader sec = new BufferedReader(new FileReader(f));
            str = "";
            int idy = 0;
            while ((str = sec.readLine()) != null) {
                for (int i = 0; i < str.length(); i++) {
                    // i is the x parameter and idy is the y parameter in Position
                    if(str.charAt(i)=='@'){
                        if(hero==null) {
                            hero = chooseHero(numOfHero, i, idy);
                        }
                        else {
                            hero.setPosition(new Position(i,idy));
                        }
                    }
                    Tile t =getTile(str.charAt(i), i, idy);
                    units.addLast(t);
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
                return hero;
            case ('s'):
                Monster l = new Monster(x,y,80,8,3,25,3,'s',"Lannister Solider");
                l.setDeathCallBack(()->{enemies.remove(l);
                });
                enemies.add(l);// dont know why maybe we will know
                return l;
            case ('k'):
                Monster k =new Monster(x,y,200,14,8,50,4,'k',"Lannister Knight");
                k.setDeathCallBack(()->enemies.remove(k));
                enemies.add(k);
                return k;
            case ('q'):
                Monster qg =new Monster(x,y,400,20,15,100,5,'q',"Queen’s Guard");
                qg.setDeathCallBack(()->enemies.remove(qg));
                enemies.add(qg);
                return qg;
            case ('z'):
                Monster z =new Monster(x,y,600,20,15,100,3,'z',"Wright");
                z.setDeathCallBack(()->enemies.remove(z));
                enemies.add(z);
                return z;
            case ('b'):
                Monster b =new Monster(x,y,1000,75,30,250,4,'b',"Bear-Wright");
                b.setDeathCallBack(()->enemies.remove(b));
                enemies.add(b);
                return b;
            case ('g'):
                Monster g =new Monster(x,y,1500,100,40,500,5,'g',"Gaint-Wright");
                g.setDeathCallBack(()->enemies.remove(g));
                enemies.add(g);
                return g;
            case ('w'):
                Monster w = new Monster(x,y,2000,150,50,1000,6, 'w',"White Walker");
                w.setDeathCallBack(()->enemies.remove(w));
                enemies.add(w);
                return w;
            case ('M'):
                Monster m = new Monster(x,y,1000,60,25,500,6,'M',"The Mountain");
                m.setDeathCallBack(()->enemies.remove(m));
                enemies.add(m);
                return m;
            case ('C'):
                Monster c =new Monster(x,y,100,10,10,1000,1,'C',"Queen Cersei");
                c.setDeathCallBack(()->enemies.remove(c));
                enemies.add(c);
                return c;
            case ('K'):
                Monster K =new Monster(x,y,5000,300,150,5000,8,'K',"Night’s King");
                K.setDeathCallBack(()->enemies.remove(K));
                enemies.add(K);
                return K;
            case ('B'):
                BonusTrap B =new BonusTrap(x,y);
                B.setDeathCallBack(()->enemies.remove(B));
                enemies.add(B);
                return B;
            case ('Q'):
                QueensTrap q =new QueensTrap(x,y);
                q.setDeathCallBack(()->enemies.remove(q));
                enemies.add(q);
                return q;
            case ('D'):
                DeathTrap d =new DeathTrap(x,y);
                d.setDeathCallBack(()-> enemies.remove(d));
                enemies.add(d);
                return d;
        }
        return null;
    }

    public LinkedList<Tile> getBoard(){
		return units;
	}

	public LinkedList<Enemy> getMonsters(){
		return enemies;
	}


	public Player getPlayer() {
		return hero;
	}
	public boolean isAlive(){
        return hero.getHealthAmount()>0;
    }

	public int getNumOfEnemies() {
		return enemies.size();
	}
    public String getStringOfTile(int x,int y){
        return units.get(length*y+x).toString();
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


    public Tile getTile(int x, int y) {
        return units.get(length*y+x);
    }

    public String swap(int x, int y) {
        if(x > this.length| y>=this.height){
            return "bad location";
        }
        int playerPos = length*hero.getY()+hero.getX();
        units.remove(hero);
        units.add(playerPos, new EmptyTile(hero.getX(), hero.getY()));
        int enemyPos = length*y+x;
        units.remove(enemyPos);
        units.add(enemyPos, hero);
        Position p =new Position(x,y);
        hero.setPosition(p);
        return "nice jump G";
    }

    public LinkedList<Enemy> getEnemies() {
        return enemies;
    }
}
