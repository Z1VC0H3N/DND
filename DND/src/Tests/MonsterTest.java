package Tests;

import backend.EnemyTypes.Monster;
import backend.GAME.Board;
import backend.Hunters.Ygritte;
import backend.Interactions.EnemyMovement;
import backend.UNIT.Enemy;
import backend.UNIT.Player;
import backend.UTILITY.Position;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class MonsterTest {
    private Monster m =new Monster(4,9,80,8,3,25,3,'s',"Lannister Solider");
    private File levelsPath = new File("./src/level1");
    private Board b = new Board(levelsPath,3);
    private Player p = b.getPlayer();
    @Before
    public void setUp() throws Exception {
        Monster m =new Monster(4,9,80,8,3,25,3,'s',"Lannister Solider");
        File levelsPath = new File("./src/level1");
        Board b = new Board(levelsPath,p);
        Player p = b.getPlayer();
        b.swap(2,9);
    }


    @Test
    public void preformMovement() {
        //once using dijkstra
        Position pos =m.preformMovement(p,b.getBoard());
        assertEquals(3,pos.getX());
        assertEquals(9,pos.getY());
        b.swap(4,10);
        // once find is he very close - close method
        pos =m.preformMovement(p,b.getBoard());
        assertEquals(4,pos.getX());
        assertEquals(10,pos.getY());
    }
    @Test
    public void accept(){
        while(p.isAlive()) {
            int health = p.getHealthAmount();
            EnemyMovement v = new EnemyMovement(m);
            String ans = p.accept(v,b.getBoard());
            assertTrue(p.getHealthAmount() <= health);
            String con =m.getName() + " engaged in combat with " + p.getName() + ".";
            assertTrue(ans.contains(con));
        }
        EnemyMovement v = new EnemyMovement(m);
        String ans = p.accept(v,b.getBoard());
    }

}