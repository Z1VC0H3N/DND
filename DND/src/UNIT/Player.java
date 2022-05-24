package UNIT;

public class Player extends Unit{
    private int exp;
    private int playerLevel;
    private String specialAbility;

    public Player(int x, int y, String name, int healthPool, int healthAmount, int attack, int defense,String specialAbility) {
        super(x, y, name, healthPool, healthAmount, attack, defense);
        this.exp=0;
        this.playerLevel=1;
        this.specialAbility=specialAbility;
    }
    public void levelUp(){//should return string to notify that action succeed

    }

}
