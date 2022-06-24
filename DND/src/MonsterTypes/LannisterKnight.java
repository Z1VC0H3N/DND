package MonsterTypes;

import EnemyTypes.Monster;

public class LannisterKnight extends Monster {

    public LannisterKnight(int x, int y) {
        super(x, y, 200, 200, 14, 8, 50, 4);
        this.tile='k';
        this.name="Lannister Knight";
    }
}
