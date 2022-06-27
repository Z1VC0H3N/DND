package backend.EnemyTypes;

import backend.GAME.Board;
import backend.Interfaces.Visited;
import backend.Interfaces.Visitor;
import backend.UNIT.Tile;
import backend.UNIT.Enemy;
import backend.UNIT.Player;
import backend.UTILITY.Position;

import java.util.LinkedList;

public class Trap extends Enemy implements Visited {
    protected int visibilityTime;
    protected int inVisibilityTime;
    protected int ticksCount=0;
    protected boolean visible=true;
    public Trap(int x,int y,int healthPool, int attack, int defence, int expValue,int visibilityTime,int inVisibilityTime,char tile,String name ) {
        super(x,y,tile,name,healthPool,healthPool,attack,defence,expValue);
        this.visibilityTime=visibilityTime;
        this.inVisibilityTime=inVisibilityTime;
    }

    @Override
    public String accept(Visitor v, LinkedList<Tile> board) {
        String s=v.visit(this, board);
        return s;
    }

    public void gameTick() {
        ticksCount++;
        if(ticksCount>=visibilityTime) {
            visible=false;
            ticksCount=-1*inVisibilityTime;
        }

        if(ticksCount==0)
            visible=true;
    }

    @Override
    public char getSign() {
        if(!visible)
            return '.';
        return tile;
    }

    @Override
    public String attack(Player p) {
        String ans="";
        if(this.range(p)<2)
            ans=super.attack(p)+",";
        return ans;
    }




    @Override
    public String description() {
        String s=this.name+"     HealthAmount:"+this.getHealthAmount()+"     AttackPower:"+this.getAttack()+"     DefencePower:"+this.getDefense()+"     ExperienceValue:"+this.getExperience()
                +"     VisibilityTime:"+this.visibilityTime+"     InVisibilityTime:"+this.inVisibilityTime;
        return s;
    }
    public int getTicksCount(){
        return ticksCount;
    }
    public int getVisibilityTime(){
        return visibilityTime;
    }
    public int getInVisibilityTime(){
        return inVisibilityTime;
    }

    @Override
    public Position preformMovement(Player p, LinkedList<Tile> board, Board b) {
        return this.getPosition();
    }

    public Object getVisible() {
        return visible;

    }
}
