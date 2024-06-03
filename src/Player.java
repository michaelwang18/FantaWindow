import java.awt.image.BufferedImage;

public class Player {

    private String name;
    private int health;
    private int attack;
    private Animation idleAnim;
    private Animation atkAnim;

    private int x;
    private int y;




    public Player(String n, int hp, int atk, BufferedImage[] idleFrames, BufferedImage[] atkFrames, int x, int y){
        health = hp; attack = atk;
        this.x = x;
        this.y = y;
        idleAnim = new Animation(idleFrames, atkFrames, 66);


    }

    public int getX() {return x;}
    public int getY() {return y;}

    public void attackAnimation() { idleAnim.attack();}

    public BufferedImage getFrame(){
        return idleAnim.getActiveFrame();
    }





}
