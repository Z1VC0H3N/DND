package Tests;

import backend.EnemyTypes.Monster;
import backend.Hunters.Ygritte;
import backend.TILE.EmptyTile;
import backend.TILE.Tile;
import backend.UNIT.Enemy;
import backend.UNIT.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class EnemyTest {
    private Player p = new Ygritte(1,2);
    private Monster s =new Monster(2,2,80,8,3,25,3,'s',"Lannister Solider");
    private LinkedList<Enemy> enemies =new LinkedList<>();

    private LinkedList<Tile> board =new LinkedList<>();
    @Before
    public void setUp() throws Exception {
        p = new Ygritte(5,5);
        s =new Monster(2,2,80,8,3,25,3,'s',"Lannister Solider");
        s.setDeathCallBack(()->board.remove(s));
        for(int y =0;y<5;y++) {
            for (int x = 0; x < 5; x++) {
                board.add(x+y*5, new EmptyTile(x,y));
            }
        }
        board.remove(11);
        board.add(11,p);
        board.remove(12);
        board.add(12,s);
        enemies.add(s);
    }

    @Test
    public void attack() {
        while(p.isAlive()) {
            int health = p.getHealthAmount();
            s.attack(p);
            assertTrue(p.getHealthAmount() <= health);
        }
    }
}