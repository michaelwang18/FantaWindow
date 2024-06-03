import java.awt.image.BufferedImage;

public class Player {

    private String name;
    private int health;
    private int attack;
    private Animation idleAnim;
    private Animation atkAnim;




    public Player(String n, int hp, int atk, BufferedImage[] idleFrames, BufferedImage[] atkFrames){
        health = hp; attack = atk;
        idleAnim = new Animation(idleFrames, atkFrames, 66);


    }

    public void attackAnimation() { idleAnim.attack();}

    public BufferedImage getFrame(){
        return idleAnim.getActiveFrame();
    }





}
