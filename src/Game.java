import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Game extends JPanel implements KeyListener, MouseListener, ActionListener {
    private int battles = 0;
    private BufferedImage background;
    private BufferedImage crosshair;
    private Skill_Set testIcon1;
    private Skill_Set testIcon2;
    private Skill_Set testIcon3;

    private Player testPlayer1;
    private Player testPlayer2;
    private Player testPlayer3;

    private Enemy testEnemy1;
    private Enemy testEnemy2;
    private Enemy testEnemy3;
    private Player currentPlayer;
    private Skill currentSkill;
    private Enemy[] enemyTeam;
    private Player[] playerTeam;


    private boolean[] pressedKeys;
    private int scaling = 1;
    Scanner scan = new Scanner(System.in);


    public Game(){
        System.out.println("Beginning");
        try { //Process images into "BufferedImage" here
            background = ImageIO.read(new File("src/Assets/grass_Background.png")); //add temp one
            BufferedImage s1c =  ImageIO.read(new File("src/Assets/skill_icons/sword_strike.png"));
            BufferedImage s2c = ImageIO.read(new File("src/Assets/skill_icons/sword_sweep.png"));
            BufferedImage s3c = ImageIO.read(new File("src/Assets/skill_icons/block.png"));
            crosshair =  ImageIO.read(new File("src/Assets/skill_icons/crosshair.png"));

            Skill s1 = new Skill("Sword Strike", "Quick Strike", 1, 2, .5, false, s1c);

            Skill s2 = new Skill("Sword Sweep", "Aoe Skill", 1, 2, .5, true, s2c);

            Skill s3 = new Skill("Block", "Protec", 0, 2, 0, false, s3c);
//
            testIcon1 = new Skill_Set(new Skill("Sword Strike", "Quick Strike", 1, 2, .5, false, s1c),new Skill("Sword Sweep", "Aoe Skill", 1, 2, .5, true, s2c),new Skill("Block", "Protec", 0, 2, 0, false, s3c));
            testIcon2 = new Skill_Set(new Skill("Sword Strike", "Quick Strike", 1, 2, .5, false, s1c),new Skill("Sword Sweep", "Aoe Skill", 1, 2, .5, true, s2c),new Skill("Block", "Protec", 0, 2, 0, false, s3c));
            testIcon3 = new Skill_Set(new Skill("Sword Strike", "Quick Strike", 1, 2, .5, false, s1c),new Skill("Sword Sweep", "Aoe Skill", 1, 2, .5, true, s2c),new Skill("Block", "Protec", 0, 2, 0, false, s3c));

        } catch (IOException e){
            System.out.println(e.getMessage());
        }


        BufferedImage[] fireKnightIdle = Utility.processAnimFrames("src/Assets/fire_knight/idle/idle_",8);
        BufferedImage[] fireKnightRun = Utility.processAnimFrames("src/Assets/fire_knight/run/run_",8);
        BufferedImage[] skeletonIdle = Utility.processAnimFrames("src/Assets/skeleton/idle/idle_",4);

        testPlayer1 = new Player("Bob",10,10,fireKnightIdle,fireKnightRun, 200, 155,66, testIcon1);
        testPlayer2 = new Player("Wil",10,10,fireKnightIdle,fireKnightRun, 120 , 255,66, testIcon2);
        testPlayer3 = new Player("Fred",10,10,fireKnightIdle,fireKnightRun, 200, 355,66, testIcon3);
        playerTeam =  new Player[3]; playerTeam[0] = testPlayer1; playerTeam[1] = testPlayer2; playerTeam[2] = testPlayer3;
        currentPlayer = testPlayer1;



        testEnemy1 = new Enemy("Skelly Boy",10,10,skeletonIdle,fireKnightRun,600,155,132);
        testEnemy2 = new Enemy("Skelly Boy",10,10,skeletonIdle,fireKnightRun,720,255,132);
        testEnemy3 = new Enemy("Skelly Boy",10,10,skeletonIdle,fireKnightRun,600,355,132);
        enemyTeam =  new Enemy[3]; enemyTeam[0] = testEnemy1; enemyTeam[1] = testEnemy2; enemyTeam[2] = testEnemy3;






        pressedKeys = new boolean[128]; //all keys
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();
        //start();


    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(background,0,-40,null); //Temp rn

        g.drawImage(testIcon1.getCurrentIMG(),testPlayer1.getX(), testPlayer1.getY()-40, null);
        g.drawImage(testPlayer1.getFrame(),testPlayer1.getX()-125, testPlayer1.getY()-75, null); //the offset is just for show right now


        g.drawImage(testIcon2.getCurrentIMG(), testPlayer2.getX(), testPlayer2.getY()-40, null);
        g.drawImage(testPlayer2.getFrame(),testPlayer2.getX()-125, testPlayer2.getY()-75, null);

        g.drawImage(testIcon3.getCurrentIMG(), testPlayer3.getX(), testPlayer3.getY()-40, null);
        g.drawImage(testPlayer3.getFrame(),testPlayer3.getX()-125, testPlayer3.getY()-75, null);


        g.drawImage(testEnemy1.getFrame(),testEnemy1.getX(),testEnemy1.getY(),null);
        g.drawImage(testEnemy2.getFrame(),testEnemy2.getX(),testEnemy2.getY(),null);
        g.drawImage(testEnemy3.getFrame(),testEnemy3.getX(),testEnemy3.getY(),null);

        if (currentPlayer.getCurrentSkill().isAoe()){
            for (Enemy player: enemyTeam){ g.drawImage(crosshair,player.getX(),player.getY(),null);}}

        else { Player target = enemyTeam[currentPlayer.getCurrentSkill().getTarget()];
            g.drawImage(crosshair,target.getX(), target.getY(), null);

            }


        for (Player player: playerTeam){
            g.setColor(Color.RED);
            g.setFont(new Font("Courier New", Font.BOLD, 15));
            g.drawString(player.getCurrentHealth() + "/" + player.getHealth(), player.getX(), player.getY()+75);

        }
        for (Enemy player: enemyTeam){
            g.setColor(Color.RED);
            g.setFont(new Font("Courier New", Font.BOLD, 15));
            g.drawString(player.getCurrentHealth() + "/" + player.getHealth(), player.getX(), player.getY()+75);
        }


        g.setColor(Color.BLUE);
        g.setFont(new Font("Courier New", Font.BOLD, 18));
        g.drawString(currentPlayer.getName(), currentPlayer.getX()-30, currentPlayer.getY()+10);






    }
    public void start(){
        //start throwing in things








        /*make player class
        - Models
        -Assets prob do it with seperate asset folders

        ASSETS NEEDED
        -CHARACTERS (fill in OK)
        -ENEMIES (fill in OK)
        -Coin flip (Maybe)
        - VFX (prolly on itch)
        - Ui (prolly needed custom)
        - Skill iCONS (also prolly needa custom)
        - needed for the



        - idle anim cycle
        - attack anim, tie to keybutton first for testings sake
        - start on THURSDAY night

        simplify into PHASES
        - CHOOSING
        ^ highlights maybe?
         active listening until enter
         enter would be regulated either that or double taped with 1 second (using timers)


        - PROCESS BATTLE




        - add targetting crosshair to targetted enemies
        3 on 3, fixed positions for ease of effects
        - 1 2 3 KEY to swap between heros
        arrow keys to swap between skills
        enter (either check for chosen skill or have all on guard without chosen input

         remove crits and defense calc too much effort for low impact





        Bring in battle and not from seperate class cuz it needs too much back and forth for it to be worth seperate


         */



    }





    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // see this for all keycodes: https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
        // A = 65, D = 68, S = 83, W = 87, left = 37, up = 38, right = 39, down = 40, space = 32, enter = 10
        int key = e.getKeyCode();
        pressedKeys[key] = true;

        if (pressedKeys[49]){
            System.out.println("1");
            if (currentPlayer != testPlayer1){
                currentPlayer = testPlayer1;
            } else {
                testPlayer1.attackAnimation();
                testPlayer1.changeSkill();
            }
        }
        if (pressedKeys[50]){
            System.out.println("2");
            if (currentPlayer != testPlayer2){
                currentPlayer = testPlayer2;
            } else {
                testPlayer2.attackAnimation();
                testPlayer2.changeSkill();
                }
            }
        if (pressedKeys[51]){
            System.out.println("3");
            if (currentPlayer != testPlayer3){
                currentPlayer = testPlayer3;
            } else {
                testPlayer3.attackAnimation();
                testPlayer3.changeSkill();
                System.out.println(currentPlayer.getCurrentSkill().getName());
            }
        }
        if (pressedKeys[32]){
            System.out.println("Bop");
            currentPlayer.getCurrentSkill().changeTargets();


        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys[key] = false;


    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("released");
        //testIcon.changeIcon();

    }

    @Override
    public void mouseEntered(MouseEvent e) { //try not to use

    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse Clicked");

    }
}
