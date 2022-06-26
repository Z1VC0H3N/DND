package backend.PlayerTypes;

import backend.TILE.EmptyTile;
import backend.UNIT.Tile;
import backend.UNIT.Enemy;
import backend.UNIT.Player;

import java.util.LinkedList;

/**
 * Special ability: Blizzard, randomly hit enemies within range for an amount equals to the mage’s
 * spell power at the cost of mana.
 *  The mage cannot cast the ability if current mana < cost
 */
public class Mage extends Player {
    private int manaPool;//: Integer, holds the maximal value of mana
    private int currentMana;
    private int manaCost;//: Integer, ability cost
    private int spellPower;//ability scale factor
    private int hitsCount;//maximal number of times a single cast of the ability can hit
    private int abilityRange;

    public Mage(int x, int y,String name, int healthPool, int healthAmount, int attack, int defense,int manaPool,int manaCost,int spellPower,int hitsCount,int abilityRange) {
        super(x, y,name, healthPool, healthAmount, attack, defense, "Blizzard");
        this.manaPool=manaPool;
        this.currentMana=manaPool/4;
        this.manaCost=manaCost;
        this.hitsCount=hitsCount;
        this.abilityRange=abilityRange;
        this.spellPower=spellPower;
    }
    public String levelUp(){
        if (exp >= 50*this.playerLevel) {
            super.levelUp();
            manaPool=manaPool+25*playerLevel;
            currentMana=Math.min(currentMana+manaPool/4, manaPool);
            spellPower=spellPower+10*playerLevel;
            return this.name+" reached level: "+this.playerLevel+"and has :+"+health.toString()+"and has :+"+attack+" Attack "+"+and has "+defense+" Defence" +"and has "+manaPool+ " manaPool"+"and has "+spellPower +"spell Power";
        }
        return "";
    }

    public String cast(Enemy e , LinkedList<Tile> board) {
        int attPoints=this.spellPower;
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
                castSwap(this,e,board);
                // we dont swap in special ability
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

    public String cast(LinkedList<Enemy> enemies, LinkedList<Tile> board) {
        return onAbilityCastAttempt(enemies,board);
    }

    protected void castSwap(Player player, Enemy e, LinkedList<Tile> board) {
            int enemyPos = board.indexOf(e);
            board.remove(e);
            board.add(enemyPos, new EmptyTile(player.getX(), player.getY()));
    }

    public String onAbilityCastAttempt(LinkedList<Enemy> enemies , LinkedList<Tile> board) {
        if(this.manaCost> currentMana){
            return this.name+" tried to use "+this.specialAbility+" but there is a cooldown: "+(manaCost-manaPool);
        }
        String ans ="";
        LinkedList<Enemy> inRange = new LinkedList<>();
        for(Enemy e :enemies){
         if(this.range(e)<abilityRange){
             inRange.addLast(e);
         }
        }
        currentMana =currentMana -manaCost;
        int hits =0;
        while(hits < hitsCount & inRange.size()!=0){
            int chosen=(int)(Math.random()*inRange.size()+1)-1;
            ans += cast(inRange.get(chosen), board)+",";
            hits++;
        }
        return ans.substring(0,ans.length()-1);
        //todo;
    }

    public void gameTick() {
       currentMana=Math.min(manaPool,currentMana+playerLevel);
    }
    public String description(){
        return super.description()+" Mana :" +this.currentMana+"/"+this.manaPool+" Spell Power :"+spellPower;    }
}
