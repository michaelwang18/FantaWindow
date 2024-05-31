import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.module.ModuleFinder;
import java.util.Scanner;

public class Game extends JPanel implements KeyListener, MouseListener, ActionListener {
    private int battles = 0;
    private BufferedImage background;
    private BufferedImage bigBG;
    private skill_Icon testIcon;
    private Character player1;
    private Character player2;
    private Character player3;

    private Player testPlayer;
    private boolean[] pressedKeys;
    private int scaling = 1;
    Scanner scan = new Scanner(System.in);

    private boolean fail = false;

    public Game(){
        System.out.println("Beginning");
        testIcon = new skill_Icon();
        BufferedImage[] testPlayerBI = Utility.processAnimFrames("src/fire_knight/idle/idle_",8);
        testPlayer = new Player("Bob",10,10,testPlayerBI,testPlayerBI); //now port
        try { //Process images into "BufferedImage" here
            background = ImageIO.read(new File("src/Assets/background_layer_2.png")); //add temp one
            bigBG = new BufferedImage(400,400,background.getType());
            bigBG.getData();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

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
        g.drawImage(background,100,100,null); //Temp rn
        g.drawImage(testIcon.getCurrentIMG(),100,200,null);
        g.drawImage(testPlayer.)
        g.drawImage(bigBG,0,0,null);


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








        System.out.println("Your starting team is: ");
        //lo
        player1 = genPlayer();
        player2 = genPlayer();
        player3 = genPlayer();
        while (player1.getName().equals(player2.getName())){
            player2 = genPlayer();
        }

        while (player3.getName().equals(player2.getName()) || player3.getName().equals(player1.getName()) ){
            player3 = genPlayer();
        }
        startInfo();
        System.out.println("Press Enter To Start Your First Battle!\n");



        for (double i = 1; battles < 5 ;i += .125){ //"gameplay" loop
            Battle battle =  new Battle(player1,player2,player3,genStoryEnemy(i),genStoryEnemy(i),genStoryEnemy(i));
            if (battle.start()){
                battles++;
                System.out.println( "\n\n" + Utility.color(5 - battles + Utility.plural(" Battle",5 - battles), Color.WHITE_BOLD_BRIGHT) + " Away From Final Boss- Prepare Carefully! \n");
                System.out.println(Utility.color("CURRENT STATUS",Color.WHITE_BOLD_BRIGHT));
                afterBattle();

                System.out.println(Utility.color("Press enter to start next battle!",Color.WHITE_BOLD_BRIGHT));
                scan.nextLine();

            } else {
                failure();
            }
        }
        if (!fail) {
            Battle battle = new Battle(player1, player2, player3, Entity.hydraHead(1), Entity.hydraHead(1), Entity.hydraHead(1));
            if(battle.start()){
                System.out.println(Utility.color("CONGRATS, You Beat The Hydra!",Color.YELLOW_BOLD_BRIGHT));
                System.out.print("Would You Like to keep going? \n(1) Yes \n(2) No");
                if (Utility.tryInput(scan.nextLine(),2) == 2){
                    System.out.println("Thanks For Playing!");
                } else {
                    InfiniteMode();
                }
            } else {
                failure();
            }
        }

    }


    private void InfiniteMode(){
        System.out.println("You Will Be Entered Infinite Mode!\nGo As Far As You Can! (There is a Hydra every 10 fights BTW)");
        boolean lose = false;
        double infScale = 2;
        double infScaling = .10;
        Scenario.upgrade(player1);
        Scenario.upgrade(player2);
        Scenario.upgrade(player3);
        while (!lose){
            Battle inf =  new Battle(player1,player2,player3,genEnemy(infScale),genEnemy(infScale),genEnemy(infScale));
            if (inf.start()){
                battles++;
                infScale += infScaling;
                System.out.println( "\n\n" + Utility.color(battles + Utility.plural(" Battle",battles), Color.WHITE_BOLD_BRIGHT) + " Total, Keep it up!");
                System.out.println( Utility.color(battles + " Battles", Color.WHITE_BOLD_BRIGHT));
                System.out.println(Utility.color("\n\nCURRENT STATUS",Color.WHITE_BOLD_BRIGHT));
                afterBattle();

                System.out.println(Utility.color("Press enter to start next battle!",Color.WHITE_BOLD_BRIGHT));
                scan.nextLine();

                if (battles % 5 == 0){
                    Battle hydra = new Battle(player1,player2,player3,Entity.hydraHead(infScale),Entity.hydraHead(infScale),Entity.hydraHead(infScale));
                    if (hydra.start()){
                        afterBattle();
                        infScaling += .10;
                        System.out.println(Utility.color("Press enter to start next battle!",Color.WHITE_BOLD_BRIGHT));
                        scan.nextLine();
                    } else{failure();}
                }


            } else {
                System.out.println(" \n\n You Lose, Bye Bye! \nYou Survived: " + Utility.color(battles + Utility.plural(" Battle",battles), Color.WHITE_BOLD_BRIGHT));
                lose = true;
            }
        }
    }

    public Character genEnemy(double scaling){
        return Entity.allEnemies(scaling)[(int) (Math.random()*32)];
    }
    public Character genStoryEnemy(double scaling){
        return Entity.storyEnemy(scaling)[(int) (Math.random()*10)];
    }
    public Character genPlayer(){
        return Entity.startingChar(1)[(int) (Math.random()*3)];
    }


    private void failure(){
        System.out.println(" \n\n You Lose, Bye Bye!");
        fail = true;
        System.out.println("Try Again? \n(1) Yes \n(2) No");
        if (Utility.tryInput(scan.nextLine(),2) == 1){
            fail = false;
            battles = 0;
            start();
        } else {
            System.out.println("See You Next Time!\n\n\n\n          On Dragon Ball Z");
        }
    }

    private void printInfo(){
        System.out.println(player1.info() + "\n" + Utility.healthBar(player1.getHP(),player1.getMaxHealth()) + "\n" + player2.info() + "\n" + Utility.healthBar(player2.getHP(),player2.getMaxHealth()) + "\n" + player3.info() + "\n" + Utility.healthBar(player3.getHP(),player3.getMaxHealth()) + "\n" +"\n");
    }

    private void afterBattle(){
        printInfo();
        Scenario.event(player1,player2,player3);
        printInfo();
        Scenario.afterBattle(player1,player2,player3);
        printInfo();

    }


    public void startInfo(){
        System.out.println();
        System.out.println(player1.info());
        player1.printSkillInfo();
        System.out.println();
        System.out.println(player2.info()); // GENERATES PLAYER TEAM
        player2.printSkillInfo();
        System.out.println();
        System.out.println(player3.info());
        player3.printSkillInfo();
        System.out.println("\n PRESS TO BEGIN YOUR JOURNEY");
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

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
        testIcon.changeIcon();

    }

    @Override
    public void mouseEntered(MouseEvent e) { //try not to use

    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse Clicked");

    }
}
