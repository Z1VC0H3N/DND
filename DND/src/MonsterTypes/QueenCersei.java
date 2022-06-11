package MonsterTypes;

import EnemyTypes.Monster;
import UTILITY.QueueAsLinkedList;

public class QueenCersei extends Monster {
    public QueenCersei(int x,int y){
        super(x,y,100,100,10,10,1000,1);
        this.tile='C';
        this.name = "Queen Cersei";
    }
}
