package backend.UNIT;

import backend.EnemyTypes.Trap;
import backend.Interfaces.Visited;
import backend.Interfaces.Visitor;
import backend.TILE.EmptyTile;

import java.util.LinkedList;

public abstract class Player extends Unit implements Visited {
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
            return this.name+" reached level "+this.playerLevel+": "+"+"+health.toString()+" Health "+"+"+attack+" Attack "+"+"+defense+" Defence";
        }
        return "";
    }
    public String description() {
        return this.name+"  "+"Health:"+this.health.getHealthAmount()+"/"+ getHealthPool()+" Attack : "+this.attack+" Defence : "+this.defense+ " Level : "+this.playerLevel+" Experience : "+this.exp +"/"+50*playerLevel;
    }
    public String attack(Enemy e, LinkedList<Tile> board){
        String[]ans=new String[8];
        for(int i=0;i<ans.length;i++){
            ans[i] ="";
        }
        int[] info = new int[3];
        int attPoints=(int)(int)(Math.random()*this.attack+1)-1;
        int defPoints=(int)(int)(Math.random()*e.getDefense()+1)-1;
        ans[0] = this.getName() + " engaged in combat with " +e.getName();
        ans[1] = this.description();
        ans[2] = e.description();
        ans[3]=this.name+" rolled "+attPoints+" attack points";
        ans[4]=e.getName()+" rolled "+defPoints+" defence points";
        int damage = attPoints - defPoints;
        if(damage>0){
          e.decreaseHealth(damage);
          if(!e.isAlive()) {
              exp += e.getExperience();
              swap(this,e,board);
              info = e.death();
              e.onDeath();
              ans[5] = e.getName() + " died " + this.name + " gained " + info[0] + " experience points";

          }
          else{
              ans[5] ="";
          }
        }
        else {
            damage = 0;
        }
        ans[6]=this.name+" did "+damage+" damage to "+e.getName();
        while(this.exp>=50*this.playerLevel) {
            ans[7] = ans[7] + this.levelUp() +",";
        }
        String out ="";
        for(int x =0;x<8;x++){
            out=out+ans[x]+",";
        }
        return out.substring(0, out.length()-1);
    }

    protected void swap(Player player, Enemy e, LinkedList<Tile> board) {
        int playerPos = board.indexOf(player);
        board.remove(player);
        board.add(playerPos, new EmptyTile(player.getX(), player.getY()));
        int enemyPos = board.indexOf(e);
        board.remove(e);
        board.add(enemyPos, player);
        player.setPosition(e.getPosition());
    }

    public String attack(Trap t, LinkedList<Tile> board){
        String[]ans=new String[5];
        for(int i=0;i<ans.length;i++){
            ans[i] ="";
        }
        int[] info = new int[3];
        int attPoints=(int)(int)(Math.random()*this.attack+1)-1;
        int defPoints=(int)(int)(Math.random()*t.getDefense()+1)-1;
        ans[0]=this.name+" rolled "+attPoints+" attack points";
        ans[1]=t.getName()+" rolled "+defPoints+" defence points";
        int damage = attPoints - defPoints;
        if(damage>0){
            t.decreaseHealth(damage);
            if(!t.isAlive()) {
                exp += t.getExperience();
                swap(this,t,board);
                info = t.death();
                ans[2] = t.getName() + " died " + this.name + " gained " + info[0] + " experience points";
            }
            else{
                ans[2] ="";
            }
        }
        else {
            damage = 0;
        }
        ans[3]=this.name+" did "+damage+" damage to "+t.getName();
        while(this.exp>=50*this.playerLevel) {
            ans[4] = ans[4] + this.levelUp();
        }
        String out ="";
        for(int x =0;x<5;x++){
            out=out+ans[x]+",";
        }
        return out.substring(0, out.length()-1);
    }


    @Override
    public String accept(Visitor v, LinkedList<Tile> board) {
        return v.visit(this, board);
    }

    @Override
    public void gameTick() {

    }
    public boolean isAlive(){
        return this.getHealthAmount()>0;
    }
    public void setExp(int exp){
        this.exp=exp;
    }
    public int getExp(){
        return exp;
    }
    public String onAbilityCastAttempt(LinkedList<Enemy> enemies, LinkedList<Tile> board) {
        return "";
    }

    public String cast(LinkedList<Enemy> enemies,LinkedList<Tile> board) {
        return "";
    }


    public int getLevel() {
        return playerLevel;
    }
}
