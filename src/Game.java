import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Game extends JPanel implements KeyListener, MouseListener, ActionListener {
    private int battles = 0;
    private BufferedImage background;
    private BufferedImage bigBG;
    private skill_set testIcon1;
    private skill_set testIcon2;
    private skill_set testIcon3;

    private Player testPlayer1;
    private Player testPlayer2;
    private Player testPlayer3;
    private boolean[] pressedKeys;
    private int scaling = 1;
    Scanner scan = new Scanner(System.in);

    private boolean fail = false;

    public Game(){
        System.out.println("Beginning");
        testIcon1 = new skill_set();
        testIcon2 = new skill_set();
        testIcon3 = new skill_set();
        BufferedImage[] fireKnightIdle = Utility.processAnimFrames("src/Assets/fire_knight/idle/idle_",8);
        BufferedImage[] fireKnightRun = Utility.processAnimFrames("src/Assets/fire_knight/run/run_",8);
        testPlayer1 = new Player("Bob",10,10,fireKnightIdle,fireKnightRun, 200, 185);
        testPlayer2 = new Player("Wil",10,10,fireKnightIdle,fireKnightRun, 120 , 285);
        testPlayer3 = new Player("Fred",10,10,fireKnightIdle,fireKnightRun, 200, 385);//now port
        try { //Process images into "BufferedImage" here
            background = ImageIO.read(new File("src/Assets/grass_Background.png")); //add temp one
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
        g.drawImage(background,0,-20,null); //Temp rn
        g.drawImage(testIcon1.getCurrentIMG(), testPlayer1.getX(), testPlayer1.getY(), null);
        g.drawImage(testPlayer1.getFrame(),testPlayer1.getX(), testPlayer1.getY(), null);

        g.drawImage(testIcon2.getCurrentIMG(), testPlayer2.getX(), testPlayer2.getY(), null);
        g.drawImage(testPlayer2.getFrame(),testPlayer2.getX(), testPlayer2.getY(), null);

        g.drawImage(testIcon3.getCurrentIMG(), testPlayer3.getX(), testPlayer3.getY(), null);
        g.drawImage(testPlayer3.getFrame(),testPlayer3.getX(), testPlayer3.getY(), null);
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
            System.out.println("One");
            testPlayer1.attackAnimation();
            testIcon1.changeIcon();
        }
        if (pressedKeys[50]){
            System.out.println("One");
            testPlayer2.attackAnimation();
            testIcon2.changeIcon();
        }
        if (pressedKeys[51]){
            System.out.println("One");
            testPlayer3.attackAnimation();
            testIcon3.changeIcon();
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
