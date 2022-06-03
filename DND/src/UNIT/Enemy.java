package UNIT;

public class Enemy extends Unit  {
    protected int experience;

    public Enemy(int x, int y, String name, int healthPool, int healthAmount, int attack, int defense) {
        super(x, y, name, healthPool, healthAmount, attack, defense);
    }

    public int getExperience(){return experience;}
    public String description() {
        return this.name+" HealthAmount:"+ this.health.getHealthAmount()+"/"+ this.health.getHealthPool()+" AttackPower:"+ this.attack+" DefencePower:"+ this.defense+"     ExperienceValue:"+ this.experience;
    }
    public String attack(){
        //todo;
        return "";
    }
}
