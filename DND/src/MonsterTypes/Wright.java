package MonsterTypes;

import EnemyTypes.Monster;

public class Wright extends Monster {
    public Wright(int x,int y){
        super(x,y, 600,600,20,15,100,3);
        this.tile='z';
        this.name ="Wright";
    }
}
