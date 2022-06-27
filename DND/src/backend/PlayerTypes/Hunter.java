package backend.PlayerTypes;

import backend.TILE.EmptyTile;
import backend.UNIT.Tile;
import backend.UNIT.Enemy;
import backend.UNIT.Player;

import java.util.LinkedList;

public class Hunter extends Player {
    protected int abilityRange;
    protected int arrows;
    protected int ticks;


    public Hunter(int x, int y, String name, int healthPool, int healthAmount, int attack, int defense,int abilityRange) {
        super(x, y, name, healthPool, healthAmount, attack, defense, "Shoot");
        this.abilityRange=abilityRange;
        this.arrows =10*this.playerLevel;
        this.ticks=0;
    }
    public String description(){
        return super.description() + " arrows:"+this.arrows;
    }
    public void gameTick(){
        if(ticks == 10)
        {
        arrows =arrows +playerLevel;
        ticks=0;
        }
        ticks++;
    }
    public String levelUp(){
        String ans ="";
        if(this.exp>=50*this.playerLevel){
            super.levelUp();
            this.arrows = arrows+10*playerLevel;
            this.attack = attack +2*playerLevel;
            this.defense = defense +playerLevel;
            ans =this.name +" reached level " + this.playerLevel +" and has Health : " +health.toString() +" attack : "+attack + " defence : "+defense +"  arrows: "+arrows;
        }
        return ans;
    }
    private String cast(Enemy e, LinkedList<Tile> board) {
        String[]ans=new String[5];
        for(int i=0;i<ans.length;i++){
            ans[i] ="";
        }
        int[] info = new int[3];
        int attPoints=this.attack;
        int defPoints=(int)(int)(Math.random()*e.getDefense()+1)-1;
        ans[1]=e.getName()+" rolled "+defPoints+" defence points";
        int damage = attPoints - defPoints;
        if(damage>0){
            e.decreaseHealth(damage);
            if(!e.isAlive()) {
                exp += e.getExperience();
                castSwap(this,e,board);
                // we dont swap in special ability, just
                info = e.death();
                e.onDeath();
                ans[2] = e.getName() + " died " + this.name + " gained " + info[0] + " experience points";

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

        protected void castSwap(Player player, Enemy e, LinkedList<Tile> board) {
            int enemyPos = board.indexOf(e);
            board.remove(e);
            board.add(enemyPos, new EmptyTile(player.getX(), player.getY()));
    }

    public String cast(LinkedList<Enemy> enemies, LinkedList<Tile> board) {
        return onAbilityCastAttempt(enemies,board);
    }
    public String onAbilityCastAttempt(LinkedList<Enemy> enemies,LinkedList<Tile> board){
        String ans ="";
        double range =board.size();
        int idx=0;
        if(arrows == 0){
           return this.name + "tried to use" +this.specialAbility +" but she doesn't have any arrows to use";
        }
        else{
            //finding the closet enemy
            for(Enemy e :enemies){
                if(this.range(e)<=range){
                    range = this.range(e);
                    idx = enemies.indexOf(e);
                }
            }
            if(this.range(enemies.get(idx))<abilityRange){
                ans =this.name +" used "+ this.specialAbility +", "+this.cast(enemies.get(idx),board);
                this.arrows--;
            }
        }
        return ans;
    }

}
