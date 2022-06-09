package UNIT;

import TILE.Tile;

import java.util.LinkedList;

public class Enemy extends Unit  {
    protected int experience;
    protected int visionRange;
    public Enemy(int x, int y, char tile,String name, int healthPool, int healthAmount, int attack, int defense,int experience,int visionRange) {
        super(x, y, tile,name, healthPool, healthAmount, attack, defense);
        this.visionRange=visionRange;
    }

    public int getExperience(){return experience;}
    public String description() {
        return this.name+" HealthAmount:"+ this.health.getHealthAmount()+"/"+ this.health.getHealthPool()+" AttackPower:"+ this.attack+" DefencePower:"+ this.defense+"     ExperienceValue:"+ this.experience;
    }
    public String attack(Player p){
        //todo;
        return "";
    }
}
