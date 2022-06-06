package PlayerTypes;

import TILE.Tile;
import UNIT.Enemy;
import UNIT.Player;

/**
 * Special ability: Blizzard, randomly hit enemies within range for an amount equals to the mage’s
 * spell power at the cost of mana.
 *  The mage cannot cast the ability if current mana < cost
 */
public class Mage extends Player {
    private int abilityCooldown ;// the number of game ticks required to cast ability again
    private int remainingCooldown;
    private int manaPool;//: Integer, holds the maximal value of mana
    private int currentMana;
    private int manaCost;//: Integer, ability cost
    private int spellPower;//ability scale factor
    private int hitsCount;//maximal number of times a single cast of the ability can hit
    private int abilityRange;

    public Mage(int x, int y, String name, int healthPool, int healthAmount, int attack, int defense,int abilityCooldown,int manaPool,int manaCost,int hitsCount,int abilityRange) {
        super(x, y, name, healthPool, healthAmount, attack, defense, "Blizzard");
        this.manaPool=manaPool;
        this.currentMana=manaPool/4;
        this.abilityCooldown=abilityCooldown;
        this.manaCost=manaCost;
        this.hitsCount=hitsCount;
        this.abilityRange=abilityRange;
    }
    public String levelUp(){
        if (exp > 50*this.playerLevel) {
            super.levelUp();
            manaPool=manaPool+25*playerLevel;
            currentMana=Math.min(currentMana+manaPool/4, manaPool);
            spellPower=spellPower+10*playerLevel;
            return this.name+" reached level: "+this.playerLevel+"and has :+"+health.toString()+"and has :+"+attack+" Attack "+"+and has "+defense+" Defence" +"and has "+manaPool+ " manaPool"+"and has "+spellPower +"spell Power";
        }
        return "";
    }

    public int getAbilityCooldown() {
        return abilityCooldown;
    }

    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    public int getManaPool() {
        return manaPool;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getSpellPower() {
        return spellPower;
    }

    public int getHitsCount() {
        return hitsCount;
    }

    public int getAbilityRange() {
        return abilityRange;
    }

    public String cast(Enemy e , Tile[][] board) {
        //todo;
        //hits ← 0
        //while (hits < hits count) ∧ (∃ living enemy s.t. range(enemy, player) < ability range) do
        //- Select random enemy within ability range.
        //- Deal damage (reduce health value) to the chosen enemy for an amount equal to spell power
        //(each enemy may try to defend itself).
        //- hits ← hits + 1
        return "";
    }
    public String onAbilityCastAttempt(Enemy[] enemies , Tile[][] board) {
        return "";
        //todo;
    }

    public void gameTick() {
       currentMana=Math.min(manaPool,currentMana+playerLevel);
    }
}
