package backend.UNIT;

public class Health {
    private int healthPool;
    private int healthAmount;

    public Health(int a,int p){
        this.healthAmount=a;
        this.healthPool=p;
    }
    public void setHealthPool(int hp){
        this.healthPool=hp;
    }
    public  void setHealthAmount(int hp){
        this.healthAmount=hp;
    }
    public int getHealthPool(){
        return healthPool;
    }
    public int getHealthAmount(){
        return healthAmount;
    }
    public String toString(){
        return " healthAmount: "+healthAmount +" healthPool: "+healthPool;
    }
}