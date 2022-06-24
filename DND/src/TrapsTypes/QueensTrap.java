package TrapsTypes;

import EnemyTypes.Trap;

public class QueensTrap extends Trap {
    public QueensTrap(int x, int y) {
        super(x, y,250,50,10,100,3,7);
        this.tile ='Q';
        this.name = "Death Trap";
    }
}
