package MonsterTypes;

import EnemyTypes.Monster;

public class BearWright extends Monster {
    public BearWright(int x,int y){
        super(x,y,1000,1000,75,30,250,4);
        this.tile='b';
        this.name="Bear-Wright";
    }
}
