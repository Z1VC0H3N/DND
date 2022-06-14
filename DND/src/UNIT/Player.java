package UNIT;

import EnemyTypes.Trap;
import TILE.Tile;

import java.util.LinkedList;

public class Player extends Unit{
    protected int exp;
    protected int playerLevel;
    protected String specialAbility;

    public Player(int x, int y,String name, int healthPool, int healthAmount, int attack, int defense,String specialAbility) {
        super(x, y,'@', name, healthPool, healthAmount, attack, defense);
        this.exp=0;
        this.playerLevel=1;
        this.specialAbility=specialAbility;
    }

    public String levelUp(){//should return string to notify that action success
        if (exp>=50*playerLevel) {
            exp = exp - (50 * playerLevel);
            playerLevel = playerLevel + 1;
            this.health.setHealthPool(this.health.getHealthPool()+10 * playerLevel);
            this.health.setHealthAmount(health.getHealthPool());
            this.attack += 4 * playerLevel;
            this.defense = +playerLevel;
            return this.name+" reached level "+this.playerLevel+": "+"+"+10*playerLevel+" Health "+"+"+4*playerLevel+" Attack "+"+"+playerLevel+" Defence";
        }
        return "";
    }
    public String description() {
        return "HealthAmount:"+this.health.getHealthAmount()+" AttackPower:"+this.attack+" DefencePower:"+this.defense+" Experience:"+this.exp;
    }
    public String attack(Enemy e, LinkedList<Tile> board){
        //todo;
        return "";
    }
    public String attack(Trap t, LinkedList<Tile> board){
        //todo;
        return "";
    }




}
