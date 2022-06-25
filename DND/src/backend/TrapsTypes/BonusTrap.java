package backend.TrapsTypes;

import backend.EnemyTypes.Trap;

public class BonusTrap extends Trap {
    public BonusTrap(int x, int y) {
        super(x, y, 1,1,1,250,1, 5);
        this.tile ='B';
        this.name="Bonus Trap";
    }
}
