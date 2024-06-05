import java.awt.image.BufferedImage;

public class Skill {

    private BufferedImage icon;
    private double damageMultiplier;
    private boolean aoe;

    private double coinPower;
    private int coinCount;



    private int target;


    private String name;
    private String detail;

    public Skill(String name, String detail, double dmgMult, int coinCount, double coinPower, boolean AOE, BufferedImage icon){
        this.name = name;
        this.detail = detail;
        this.coinCount = coinCount;
        this.coinPower =coinPower;
        damageMultiplier = dmgMult;
        this.icon = icon;
    }

    public Skill(String name, double dmgMult, int coinCount, int coinPower, BufferedImage icon){
        this.name = name;
        this.detail = "Description not given yet LOSER!!!";
        this.coinCount = coinCount;
        this.coinPower =coinPower;
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

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public boolean isAoe() {
        return aoe;
    }
}
