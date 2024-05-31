import java.awt.image.BufferedImage;

public class Player {

    private String name;
    private int health;
    private int attack;
    private Animation idleAnim;
    private Animation atkAnim;




    public Player(String n, int hp, int atk, BufferedImage[] idleFrames, BufferedImage[] atkFrames){
        health = hp; attack = atk;
        idleAnim = new Animation(idleFrames, 66);
        atkAnim = new Animation(atkFrames, 66);

    }

    public BufferedImage getFrame





}
