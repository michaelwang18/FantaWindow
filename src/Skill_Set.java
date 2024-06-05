import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Skill_Set {

    private int current;
    private BufferedImage currentIMG;
    private BufferedImage block;
    private BufferedImage basic;
    private BufferedImage heavy;

    private Skill skill1;
    private Skill skill2;
    private Skill skill3;
    private Skill currentSkill;


    public Skill_Set(){ //default
        try {
          this.basic = ImageIO.read(new File("src/Assets/skill_icons/block.png"));
          this.heavy = ImageIO.read(new File("src/Assets/skill_icons/sword_strike.png"));
          this.block = ImageIO.read(new File("src/Assets/skill_icons/sword_sweep.png"));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        currentIMG = this.block;
        current = 0;


    }

    public Skill_Set(boolean test){ //default
        try {
            this.basic = ImageIO.read(new File("src/Assets/skill_icons/block.png"));
            this.heavy = ImageIO.read(new File("src/Assets/skill_icons/sword_strike.png"));
            this.block = ImageIO.read(new File("src/Assets/skill_icons/sword_sweep.png"));
            Skill s1 = new Skill("Sword Strike", "Quick Strike", 1, 2, .5, false, basic);
            Skill s2 = new Skill("Sword Sweep", "Aoe Skill", 1, 2, .5, true, heavy);
            Skill s3 = new Skill("Block", "Protec", 0, 2, 0, false, block);


        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        currentIMG = this.block;
        current = 0;


    }

    public Skill_Set(Skill s1, Skill s2, Skill s3){
        try {
//            this.basic = ImageIO.read(new File("src/Assets/skill_icons/block.png"));
//            this.heavy = ImageIO.read(new File("src/Assets/skill_icons/sword_strike.png"));
//            this.block = ImageIO.read(new File("src/Assets/skill_icons/sword_sweep.png"));
            this.basic = s1.getIcon();
            this.heavy = s2.getIcon();
            this.block = s3.getIcon();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        skill1 = s1; skill2 = s2; skill3 = s3;
        currentIMG = this.block;
        currentSkill = s3;
        current = 0;


    }

    public BufferedImage getCurrentIMG() {
        return currentIMG;
    }

    public int getCurrent() {
        return current;}
    public Skill getCurrentSkill() { return currentSkill;}

    public void changeSkill(){
        if (current == 0 ){
            current = 1; currentIMG = basic;}
        else if (current == 1 ){
            current = 2; currentIMG = heavy; }
        else if (current == 2 ){
            current = 0; currentIMG = block; }

        System.out.println("Change icon");
        System.out.println(current);
    }



}
