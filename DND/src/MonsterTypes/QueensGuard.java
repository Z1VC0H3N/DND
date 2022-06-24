package MonsterTypes;

import EnemyTypes.Monster;

public class QueensGuard extends Monster {
    public QueensGuard(int x, int y) {
        super(x, y, 400, 400, 20,15, 100, 5);
        this.tile='q';
        this.name ="Queen’s Guard";
    }
}
