import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class skill_Icon {

    private int current = 3;
    private BufferedImage currentIMG;
    private BufferedImage block;
    private BufferedImage basic;
    private BufferedImage heavy;


    public skill_Icon(){
        try {
          this.basic = ImageIO.read(new File("src/Assets/background_layer_2.png"));
          this.heavy = ImageIO.read(new File("src/Assets/shield.png"));
          this.block = ImageIO.read(new File("src/Assets/sword.png"));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        currentIMG = this.block;
        current = 0;


    }

    public BufferedImage getCurrentIMG() {
        return currentIMG;
    }

    public int getCurrent() {
        return current;
    }

    public void changeIcon(){
        if (current == 0){current++; currentIMG = basic;}
        if (current == 1){current++; currentIMG = heavy;}
        if (current == 2){current = 0; currentIMG = block;}
    }



}
