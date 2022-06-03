package PlayerTypes;

import UNIT.Enemy;
import UNIT.Player;

/**
 * Special ability: Fan of Knives, hits everyone around the rogue for an amount equals to the
 * rogue’s attack points at the cost of energy.
 * Using energy as resource. Starting energy equals to the rogue’s maximum energy which is 100
 */
public class Rogue extends Player {
    private int cost;
    private int energy;
    public Rogue(int x, int y, String name, int healthPool, int healthAmount, int attack, int defense,int cost) {
        super(x, y, name, healthPool, healthAmount, attack, defense, "Fan of Knives");
        this.cost=cost;
        this.energy=100;
    }
    public String levelUp(){
        if (exp > 50*this.playerLevel) {
            super.levelUp();
            energy=100;
            attack=attack+3*playerLevel;
            return this.name+" reached level: "+this.playerLevel+"and has :+"+health.toString()+"and has :+"+attack+" Attack "+"+and has "+defense+" Defence" +"and has "+"and has "+energy +"energy";
        }
        return "";
    }
    public String cast(Enemy e , TILE.tile[][] board){
        //todo
        //- current energy ← current energy − cost
        //- For each enemy within range < 2, deal damage (reduce health value) equals to the rogue’s
        //attack points (each enemy will try to defend itself)
        return "";
    }
    public void gameTick(){
        energy=Math.min(energy+90,100);
    }
}
