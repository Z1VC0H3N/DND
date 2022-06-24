package PlayerTypes;

import TILE.Tile;
import UNIT.Enemy;
import UNIT.Player;

import java.util.LinkedList;

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
    public String cast(Enemy e , LinkedList<Tile> board){
        int attPoints=(int)(this.attack);
        int defPoints=(int)(Math.random()*e.getDefense()+1)-1;
        String[]ans=new String[5];
        for(int i=0;i<ans.length;i++)
            ans[i]="";
        ans[0]=this.name+ " used "+this.specialAbility;
        ans[1]=e.getName()+" rolled "+defPoints+" defence points";
        int[] information=new int[3];
        int damage = attPoints - defPoints;
        if(damage>0){
            e.decreaseHealth(damage);
            if(!e.isAlive()) {
                exp += e.getExperience();
                swap(this,e,board);
                information = e.death();
                ans[2] = e.getName() + " died " + this.name + " gained " + information[0] + " experience points";
                e.onDeath();
            }
            else{
                ans[2] ="";
            }
        }
        else {
            damage = 0;
        }
        ans[3]=this.name+" did "+damage+" damage to "+e.getName();
        while(this.exp>=50*this.playerLevel) {
            ans[4] = ans[4] + this.levelUp() +",";
        }
        String out ="";
        for(int x =0;x<5;x++){
            out=out+ans[x]+",";
        }
        return out.substring(0, out.length()-1);
    }
    public String onAbilityCastAttempt(LinkedList<Enemy> enemies,LinkedList<Tile> board) {
        if(cost > this.energy){
            return "";
        }
        String ans ="";
        LinkedList<Enemy> inRange = new LinkedList<>();
        for(Enemy e : enemies){
            if(this.range(e) <2){
                inRange.addLast(e);
            }
        }
        for(Enemy e : inRange){
            ans += cast(e,board) +",";
        }
        return ans.substring(0,ans.length()-1);
    }

    public String cast(LinkedList<Enemy> enemies, LinkedList<Tile> board) {
        return onAbilityCastAttempt(enemies,board);
    }
    public void gameTick(){
        energy=Math.min(energy+90,100);
    }
    public String description(){
        return super.description()+ " Energy: "+energy+"/100";
    }
}
