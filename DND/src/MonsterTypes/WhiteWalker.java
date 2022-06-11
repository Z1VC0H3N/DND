package MonsterTypes;

import EnemyTypes.Monster;

public class WhiteWalker extends Monster {
    public WhiteWalker(int x,int y){
        super(x,y,2000,2000,150,50,1000,6);
        this.tile='w';
        this.name = "White Walker";
    }
}
