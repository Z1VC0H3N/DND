package PlayerTypes;

import TILE.Tile;
import UNIT.Enemy;
import UNIT.Player;

/**
 * Special ability: Avenger’s Shield, randomly hits one enemy withing range < 3 for an amount
 * equals to 10% of the warrior’s max health and heals the warrior for amount equals to (10×defense)
 * (but will not exceed the total amount of health pool).
 * The warrior’s ability has a cooldown, meaning it can only be used once every ability cooldown
 * game ticks.
 * The warrior cannot cast the ability if remaining cooldown > 0.
 */
public class Warrior extends Player {
    private int abilityCooldown ;// the number of game ticks required to cast ability again
    private int remainingCooldown;
    public Warrior(int x, int y, String name, int healthPool, int healthAmount, int attack, int defense,int abilityCooldown) {
        super(x, y, name, healthPool, healthAmount, attack, defense,"Avenger’s Shield");
        this.abilityCooldown=abilityCooldown;
        remainingCooldown=0;
    }
    public String onAbilityCastAttempt(Enemy[] enemies , Tile[][] board) {
            //todo;
            return "";
    }
    public String cast(Enemy e , Tile[][] board) {
        remainingCooldown=abilityCooldown;
        health.setHealthAmount(Math.min(health.getHealthAmount()+10*defense,health.getHealthPool()));
        //todo;
        // need to add randomly hits point and the ability of the player
        return "";
    }

    @Override
    public String levelUp() {
        if (exp > 50*this.playerLevel) {
            super.levelUp();
            remainingCooldown = 0;
            health.setHealthPool(health.getHealthPool() + 5 * playerLevel);
            attack = attack + 2 * playerLevel;
            defense = defense + playerLevel;
            return this.name+" reached level: "+this.playerLevel+"and has :+"+health.toString()+"and has :+"+attack+" Attack "+"+and has "+defense+" Defence";
        }
        return "";
    }
    public void gameTick() {
        if(this.remainingCooldown>0)
            this.remainingCooldown=remainingCooldown-1;
    }
    public String attack(){
        //todo;
        return "";
    }
}
