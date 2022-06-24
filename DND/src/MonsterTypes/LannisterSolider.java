package MonsterTypes;

import EnemyTypes.Monster;

public class LannisterSolider extends Monster {

    public LannisterSolider(int x, int y) {
        super(x, y, 80, 80, 8, 3,25, 3);
        this.tile='s';
        this.name="Lannister Solider";
    }

}
