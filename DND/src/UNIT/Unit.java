package UNIT;

import TILE.tile;

public class Unit extends TILE.tile {
    private int healthPool;
    private int healthAmount;
    private int attack;
    private int defense;

    public Unit(int x, int y, String name,int healthPool,int healthAmount,int attack,int defense) {
        super((char)65, x, y, name);
        this.healthPool=healthPool;
        this.healthAmount=healthAmount;
        this.attack=attack;
        this.defense=defense;
    }
    public double range(Unit other){
        return Math.sqrt(Math.pow((this.getX()-other.getX()), 2)+Math.pow((this.getY()-other.getY()), 2));
    }
    public int getHealthPool(){
        return healthPool;
    }
    public int getHealthAmount(){
        return healthAmount;
    }
    public int getAttack(){
        return attack;
    }
    public int getDefense(){
        return defense;
    }
    public void decreaseHealth(int damage){
        healthAmount=healthAmount-damage;
    }

}
