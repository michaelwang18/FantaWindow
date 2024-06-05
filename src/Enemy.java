import java.awt.image.BufferedImage;

public class Enemy extends Player{

    public Enemy(String n, int hp, int atk, BufferedImage[] idleFrames, BufferedImage[] atkFrames, int x, int y, int delay){
        super(n,hp,atk,idleFrames,atkFrames,x,y,delay);

    }


}
