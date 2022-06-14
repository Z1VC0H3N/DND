package UNIT;

import TILE.Tile;
import UTILITY.Health;

public class Unit extends Tile {
    protected Health health;
    protected int attack;
    protected int defense;

    public Unit(int x, int y,char tile, String name,int healthPool,int healthAmount,int attack,int defense) {
        super(tile, x, y, name);
        this.health=new Health(healthAmount, healthPool);
        this.attack=attack;
        this.defense=defense;
    }
    public double range(Tile other){
        return Math.sqrt(Math.pow((this.getX()-other.getX()), 2)+Math.pow((this.getY()-other.getY()), 2));
    }
    public int getHealthPool(){
        return health.getHealthPool();
    }
    public int getHealthAmount(){
        return health.getHealthAmount();
    }
    public int getAttack(){
        return attack;
    }
    public int getDefense(){
        return defense;
    }
    public void decreaseHealth(int damage){
        health.setHealthAmount(health.getHealthAmount()-damage);

    }
    public void setHealthPool(int health){
       this.health.setHealthPool(health);
    }
    public  void setHealthAmount(int health){
        this.health.setHealthAmount(health);
    }
    public void setAttack(int attack){
        this.attack=attack;
    }
    public void setDefense(int defence){
        this.defense=defence;
    }
}
