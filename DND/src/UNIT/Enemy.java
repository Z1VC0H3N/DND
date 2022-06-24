package UNIT;

import Interfaces.DeathCallBack;
import TILE.Tile;

import java.util.LinkedList;

public class Enemy extends Unit  {
    protected int experience;
    private DeathCallBack deathCallBack;

    public Enemy(int x, int y, char tile,String name, int healthPool, int healthAmount, int attack, int defense,int experience) {
        super(x, y, tile,name, healthPool, healthAmount, attack, defense);
        this.experience=experience;
    }
    public void setDeathCallBack(DeathCallBack d){
        this.deathCallBack=d;
    }
    public int getExperience(){return experience;}
    public String description() {
        return this.name+" HealthAmount:"+ this.health.getHealthAmount()+"/"+ this.health.getHealthPool()+" AttackPower:"+ this.attack+" DefencePower:"+ this.defense+"     ExperienceValue:"+ this.experience;
    }
    public String attack(Player e) {
        String[]ans=new String[4];
        for(int i=0;i<ans.length;i++)
            ans[i]="";
        int attPoints=(int)(Math.random()*this.attack+1)-1;
        int defPoints=(int)(Math.random()*e.getDefense()+1)-1;
        ans[0]=this.name+" rolled "+attPoints+" attack points";
        ans[1]=e.getName()+" rolled "+defPoints+" defence points";
        int damage=attPoints-defPoints;
        if(damage<0)
            damage=0;
        ans[2]=this.name+" did "+damage+" damage to "+e.getName();
        e.decreaseHealth(damage);
        if(!e.isAlive()) {
            ans[3]="Game Over!";
            e.setSign('X');
        }
        String out ="";
        for(int x=0;x<ans.length;x++){
            out+=ans[x]+",";
        }
        return out.substring(0,out.length()-1);
    }
    public boolean isAlive(){
        return getHealthAmount()>0;
    }

    public int[] death() {
        return new int[]{experience,getX(),getY()};
    }
    public void onDeath(){
        deathCallBack.call();
    }
    public String makeMove(LinkedList<Tile> board){
        return "";
    }

}
