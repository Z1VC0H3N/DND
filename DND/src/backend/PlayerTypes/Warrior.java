package backend.PlayerTypes;

import backend.TILE.EmptyTile;
import backend.TILE.Tile;
import backend.UNIT.Enemy;
import backend.UNIT.Player;

import java.util.LinkedList;

/**
 * Special ability: Avenger’s Shield, randomly hits one enemy withing range < 3 for an amount
 * equals to 10% of the warrior’s max health and heals the warrior for amount equals to (10×defense)
 * (but will not exceed the total amount of health pool).
 * The warrior’s ability has a cooldown, meaning it can only be used once every ability cooldown
 * game ticks.
 * The warrior cannot cast the ability if remaining cooldown > 0.
 */
public class Warrior extends Player {
    private int abilityCooldown ;// the number of game ticks required to cast ability again
    private int remainingCooldown;
    public Warrior(int x, int y, String name, int healthPool, int healthAmount, int attack, int defense,int abilityCooldown) {
        super(x, y, name, healthPool, healthAmount, attack, defense,"Avenger’s Shield");
        this.abilityCooldown=abilityCooldown;
        remainingCooldown=0;
    }
    public String onAbilityCastAttempt(LinkedList<Enemy> enemies,LinkedList<Tile> board) {
        String ans="";
        boolean found=false;
        if(remainingCooldown>0)
            ans=this.name+" tried to use "+this.specialAbility+" but there is a cooldown: "+remainingCooldown+"/"+abilityCooldown+",";
        else {
            this.setHealthAmount((this.getHealthAmount()+10*this.defense));
            if(this.getHealthAmount()>=this.getHealthPool())
                this.setHealthAmount(this.getHealthPool());
            ans=this.name+" casted special abillity healing for "+10*this.defense+",";
            for(int i=0;i<enemies.size()&!found;i++) {
                if (this.range(enemies.get(i)) < 3) {
                    found = true;
                    ans = ans + this.cast(enemies.get(i), board) + ",";
                }
            }
            remainingCooldown=abilityCooldown;
        }
        return ans;

    }

    @Override
    public String cast(LinkedList<Enemy> enemies, LinkedList<Tile> board) {
        return onAbilityCastAttempt(enemies,board);
    }

    public String cast(Enemy e, LinkedList<Tile> board) {
        int attPoints=(int)(this.getHealthPool()/10);
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

    @Override
    public String levelUp() {
        if (exp >= 50*this.playerLevel) {
            super.levelUp();
            remainingCooldown = 0;
            health.setHealthPool(health.getHealthPool() + 5 * playerLevel);
            attack = attack + 2 * playerLevel;
            defense = defense + playerLevel;
            return this.name+" reached level: "+this.playerLevel+"and has :+"+health.toString()+"and has :+"+attack+" Attack "+"+and has "+defense+" Defence";
        }
        return "";
    }
    public void gameTick() {
        if(this.remainingCooldown>0)
            this.remainingCooldown=remainingCooldown-1;
    }
    public String attack(){
        //todo;
        return "";
    }

    @Override
    protected void swap(Player player, Enemy e, LinkedList<Tile> board) {
        int enemyPos = board.indexOf(e);
        board.remove(e);
        board.add(enemyPos, new EmptyTile(player.getX(), player.getY()));
    }

    public String description(){
        return super.description() + " Cooldown : "+remainingCooldown+"/"+abilityCooldown;
    }
}
