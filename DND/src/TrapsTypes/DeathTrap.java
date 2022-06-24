package TrapsTypes;

import EnemyTypes.Trap;

public class DeathTrap extends Trap {
    public DeathTrap(int x, int y) {
        super(x, y, 500,100,20,250,1,10);
        this.tile='D';
        this.name ="Death Trap";
    }
}
