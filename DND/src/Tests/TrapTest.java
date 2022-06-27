package Tests;

import backend.EnemyTypes.Trap;
import backend.GAME.Board;
import backend.Interactions.EnemyMovement;
import backend.UNIT.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TrapTest {
    private File levelsPath = new File("./src/level2");
    private Trap trap =new Trap(11,19,250,50,10,100,3,7,'Q',"Queen’s Trap");
    private Board b = new Board(levelsPath,3);
    private Player p = b.getPlayer();//12,19
    @Before
    public void setUp() throws Exception {
        b = new Board(levelsPath,3);
        trap =new Trap(11,19,250,50,10,100,3,7,'Q',"Queen’s Trap");
        p =b.getPlayer();
    }

    @Test
    public void accept() {
        while(p.isAlive()) {
            int health = p.getHealthAmount();
            EnemyMovement v = new EnemyMovement(trap);
            String ans = p.accept(v,b.getBoard());
            assertTrue(p.getHealthAmount() <= health);
            String con =trap.getName() + " engaged in combat with " + p.getName() + ".";
            assertTrue(ans.contains(con));
        }
        EnemyMovement v = new EnemyMovement(trap);
        String ans = p.accept(v,b.getBoard());
    }

    @Test
    public void gameTick() {
        int tick = trap.getTicksCount();
        int visible = trap.getVisibilityTime(); // 3
        int inVisible = trap.getInVisibilityTime();//7
        trap.gameTick();
        tick=tick+1;
        assertEquals(tick,trap.getTicksCount());
        trap.gameTick();// tick - 2
        trap.gameTick();// tick - 3
        assertEquals(false,trap.getVisible());
        assertEquals(-7,trap.getTicksCount());
    }

    @Test
    public void attack() {
        while(p.isAlive()) {
            int health = p.getHealthAmount();
            trap.attack(p);
            assertTrue(p.getHealthAmount() <= health);
        }
    }
}