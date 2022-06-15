package PlayerTypes;

import UNIT.Player;

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
        return super.description()+" Arrows :"+this.arrows+" Range:"+this.abilityRange;
    }
    public void gameTick(){}
}
