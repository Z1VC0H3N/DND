package Hunters;

import Interactions.PlayerMovement;
import PlayerTypes.Hunter;
import TILE.EmptyTile;
import TILE.Tile;
import UNIT.*;

import java.util.LinkedList;

public class Ygritte extends Hunter {
    public Ygritte(int x, int y) {
        super(x, y,"Ygritte", 250,250, 30, 2,6);
    }

    public String levelUp(){
        String ans ="";
        if(this.exp>=50*this.playerLevel){
            super.levelUp();
            this.arrows = arrows+10*playerLevel;
            this.attack = attack +2*playerLevel;
            this.defense = defense +playerLevel;
            ans =this.name +" reached level " + this.playerLevel +" and has Health:" +health.toString() +" attack : "+attack + " defence : "+defense +" arrows: "+arrows;
        }
        return ans;
    }
    public String description(){
        String s=this.name+" HealthAmount:"+this.health.getHealthAmount()+"/"+this.health.getHealthPool()+" Attack:"+this.attack+" Defence:"+this.defense+" Level:"+this.playerLevel+" Experience:"
                +this.exp+"/"+(50*this.playerLevel)+" arrows:"+this.arrows;
        return s;
    }
    public void gameTick(){
        if(ticks == 10){
            arrows =arrows +playerLevel;
            ticks=0;
        }
        ticks++;
    }
    public String castAttempt(LinkedList<Enemy> enemies,LinkedList<Tile> board){
         String ans ="";
         if(arrows == 0){
             ans = this.name + "tried to use" +this.specialAbility +" but she doesn't have any arrows to use";
         }
         else{
             for(Enemy e :enemies){
                 if(this.range(e)<=abilityRange){
                     if(e!=null){
                        ans =this.name +" used "+ this.specialAbility +", "+this.cast(e,board);
                        this.arrows--;
                     }
                 }
             }
         }
         return ans;
    }

    private String cast(Enemy e, LinkedList<Tile> board) {
        int attackPoints =this.attack;
        int defensePoints = (int)(Math.random()*e.getDefense()+1)-1;
        String[] ans = new String[3];
        ans[0] =e.getName() + " rolled "+defensePoints +"defense points";
        int[] info =new int[3];
        int damage = attackPoints-defensePoints;
        if(damage>0 & e.getHealthAmount()>0){
            e.decreaseHealth(damage);
            if(e.getHealthAmount()<=0){
                info =e.death();
            }
        }
        else{
            damage=0;
            this.exp+=info[0];
            if(info[0] >0){
                ans[2] =e.getName() +" died. " +this.name +" gained " +info[0] +"exp points";
                PlayerMovement pm =new PlayerMovement(this);
                int x =info[1];
                int y =info[2];
                EmptyTile et =new EmptyTile(this.getX(),this.getY());
                for(Tile t : board){

                }
            }
        }
        return "";
    }
}

