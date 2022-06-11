package MonsterTypes;

import EnemyTypes.Monster;

public class NightsKing extends Monster {
    public NightsKing(int x,int y){
        super(x,y,5000,5000,300,150,5000,8);
        this.tile='K';
        this.name="Night’s King";
    }
}
