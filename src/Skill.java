import java.awt.image.BufferedImage;

public class Skill {

    private BufferedImage icon;
    private double damageMultiplier;
    private boolean aoe;





    private int target;


    private String name;
    private String detail;

    public Skill(String name, String detail, double dmgMult, boolean AOE, BufferedImage icon){
        this.name = name;
        this.detail = detail;

        aoe = AOE;
        damageMultiplier = dmgMult;
        this.icon = icon;
    }

    public Skill(String name, double dmgMult, BufferedImage icon){
        this.name = name;
        this.detail = "Description not given yet LOSER!!!";

        damageMultiplier = dmgMult;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public BufferedImage getIcon() {
        return icon;
    }

     public void changeTargets(){
        if (target < 2){
            target++;
        } else {
            target = 0;
        }
     }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getTarget() {
        return target;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public boolean isAoe() {
        return aoe;
    }
}
