package backend.UNIT;

import backend.GAME.Board;
import backend.Interfaces.DeathCallBack;
import backend.UTILITY.Position;

import java.util.LinkedList;

public abstract class Enemy extends Unit  {
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
        String[]ans=new String[7];
        for(int i=0;i<ans.length;i++)
            ans[i]="";
        int attPoints=(int)(Math.random()*this.attack+1)-1;
        int defPoints=(int)(Math.random()*e.getDefense()+1)-1;
        ans[0] = this.name + " engaged in combat with " + e.getName() + ".";
        ans[1] = this.description();
        ans[2] =e.description();
        ans[3]=this.name+" rolled "+attPoints+" attack points";
        ans[4]=e.getName()+" rolled "+defPoints+" defence points";
        int damage=attPoints-defPoints;
        if(damage<0)
            damage=0;
        ans[5]=this.name+" did "+damage+" damage to "+e.getName();
        e.decreaseHealth(damage);
        if(!e.isAlive()) {
            ans[6]="Game Over!";
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

    public Position preformMovement(Player p, LinkedList<Tile> board) {
        return null;
    }
}
