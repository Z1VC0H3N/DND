package MonsterTypes;

import EnemyTypes.Monster;

public class TheMountain extends Monster {
    public TheMountain(int x,int y){
        super(x,y,100,1000,60,25,500,6);
        this.tile='M';
        this.name ="The Mountain";
    }
}
