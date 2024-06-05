import java.awt.image.BufferedImage;

public class Player {

    private String name;
    private int health;
    private int currentHealth;
    private int attack;
    private Animation idleAnim;
    private Animation atkAnim;
    private Skill_Set skillSet;
    private boolean alive;

    private boolean blocking;

    private int blockStack = 0;

    private int x;
    private int y;




    public Player(String n, int hp, int atk, BufferedImage[] idleFrames, BufferedImage[] atkFrames, int x, int y, int delay, Skill_Set skillSet){
        health = hp; attack = atk; currentHealth = hp;
        name = n;
        this.x = x;
        this.y = y;
        idleAnim = new Animation(idleFrames, atkFrames, delay);
        this.skillSet = skillSet;
        alive = true;
        blocking = false;
        blockStack = 0;
    }

    public Player(String n, int hp, int atk, BufferedImage[] idleFrames, BufferedImage[] atkFrames, int x, int y, int delay){
        health = hp; attack = atk; currentHealth = hp;
        name = n;
        this.x = x;
        this.y = y;
        idleAnim = new Animation(idleFrames, atkFrames, delay);
    }



    public void takeDamage(int dmg){
        currentHealth -= dmg;
        if (currentHealth >= 0){
            currentHealth = 0;
            alive = false;
        }
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public void setBlocking(boolean blocking) {
        this.blocking = blocking;
    }

    public boolean isBlocking() {
        return blocking;
    }

    public void setBlockStack(int blockStack) {
        this.blockStack = blockStack;
    }

    public Skill getCurrentSkill() {
        return skillSet.getCurrentSkill();
    }

    public void changeSkill(){
        skillSet.changeSkill();
        if (this.getCurrentSkill().getDamageMultiplier() == 0){
            blocking = true;
        }
    }


    public Skill_Set getSkillSet(){ return skillSet;}

    public int getX() {return x;}
    public int getY() {return y;}

    public void attackAnimation() { idleAnim.attack();}

    public BufferedImage getFrame(){
        return idleAnim.getActiveFrame();
    }





}
