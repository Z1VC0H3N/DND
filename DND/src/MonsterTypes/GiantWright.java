package MonsterTypes;

import EnemyTypes.Monster;

public class GiantWright extends Monster {
    public GiantWright(int x,int y){
        super(x,y,1500,1500,100,40,500,5);
        this.tile='g';
        this.name ="Gaint-Wright";
    }
}
