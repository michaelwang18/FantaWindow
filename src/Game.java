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
    private MainFrame mainFrame;
    private BufferedImage crosshair;
    private Skill_Set testIcon1;
    private Skill_Set testIcon2;
    private Skill_Set testIcon3;

    private Player testPlayer1;
    private Player testPlayer2;
    private Player testPlayer3;

    private Enemy skeleton;
    private Enemy mushroom;
    private Enemy goblins;


    private Enemy testEnemy1;
    private Enemy testEnemy2;
    private Enemy testEnemy3;
    private Player currentPlayer;
    private Skill currentSkill;
    private Enemy[] enemyTeam;
    private Player[] playerTeam;
    private BufferedImage uiBox;
    private Player[] enemies;
    private BufferedImage blackScreen;
    private int rounds = 0;

    private boolean choosePhase;


    private boolean[] pressedKeys;
    private int scaling = 1;
    Scanner scan = new Scanner(System.in);


    public Game(){
        System.out.println("Beginning");



        try { //Process images into "BufferedImage" here
            background = ImageIO.read(new File("src/Assets/backgrounds/bg1.png")); //add temp one
            uiBox = ImageIO.read(new File("src/Assets/uiBox.png")); //add temp one
            BufferedImage s1c =  ImageIO.read(new File("src/Assets/skill_icons/sword_strike.png"));
            BufferedImage s2c = ImageIO.read(new File("src/Assets/skill_icons/sword_sweep.png"));
            BufferedImage s3c = ImageIO.read(new File("src/Assets/skill_icons/block.png"));
            blackScreen = ImageIO.read(new File("src/Assets/black-screen.png"));
            crosshair =  ImageIO.read(new File("src/Assets/skill_icons/crosshair.png"));
            testIcon1 = new Skill_Set(new Skill("Sword Strike", "Quick Strike", 2, false, s1c),new Skill("Sword Sweep", "Aoe Skill", 1, true, s2c),new Skill("Block", "Protec", 0, false, s3c));
            testIcon2 = new Skill_Set(new Skill("Arrow Shot", "Shoots 1 target", 2,  false, s1c),new Skill("Arrow Volley", "Aoe Skill", 1,  true, s2c),new Skill("Block", "Protec", 0,  false, s3c));
            testIcon3 = new Skill_Set(new Skill("Sword Strike", "Quick Strike",2,  false, s1c),new Skill("Sword Sweep", "Aoe Skill", 1, true, s2c),new Skill("Block", "Protec", 0, false, s3c));

        } catch (IOException e){
            System.out.println(e.getMessage());
        }


        BufferedImage[] fireKnightIdle = Utility.processAnimFrames("src/Assets/fire_knight/idle/idle_",8);
        BufferedImage[] fireKnightRun = Utility.processAnimFrames("src/Assets/fire_knight/run/run_",8);
        BufferedImage[] fireKnightAttack = Utility.processAnimFrames("src/Assets/fire_knight/attack/1_atk_",8);

        BufferedImage[] rangerIdle = Utility.processAnimFrames("src/Assets/leaf_ranger/idle/idle_",12);
        BufferedImage[] rangerAttack = Utility.processAnimFrames("src/Assets/leaf_ranger/attack/2_atk_",12);

        BufferedImage[] skeletonIdle = Utility.processAnimFrames("src/Assets/skeleton/idle/idle_",4);
        BufferedImage[] mushroomIdle = Utility.processAnimFrames("src/Assets/goblin/idle_",4);
        BufferedImage[] goblinIdle = Utility.processAnimFrames("src/Assets/mushroom/idle_",4);

        testPlayer1 = new Player("Knight",15,2,fireKnightIdle,fireKnightAttack, 200, 175,66, testIcon1);
        testPlayer2 = new Player("Ranger",10,2,rangerIdle,rangerAttack, 120 , 275,66, testIcon2);
        testPlayer3 = new Player("Greg",10,2,fireKnightIdle,fireKnightAttack, 200, 375,66, testIcon3);
        playerTeam =  new Player[3]; playerTeam[0] = testPlayer1; playerTeam[1] = testPlayer2; playerTeam[2] = testPlayer3;
        currentPlayer = testPlayer1;

        enemies =  new Enemy[3]; enemies[0] = skeleton; enemies[1] = mushroom; enemies[2] = goblins;
        skeleton = new Enemy("Skelly Boy",15,1,skeletonIdle,fireKnightRun,600,175,132);
        mushroom = new Enemy("Shroomy",20,1,mushroomIdle,fireKnightRun,600,175,132);
        goblins = new Enemy("Gobby Jr.",10,1,goblinIdle,fireKnightRun,600,175,132);
        enemies =  new Enemy[3]; enemies[0] = skeleton; enemies[1] = mushroom; enemies[2] = goblins;

        testEnemy1 = new Enemy("Skelly Boy",10,1,skeletonIdle,fireKnightRun,600,175,132);
        testEnemy2 = new Enemy("Skelly Boy",10,1,skeletonIdle,fireKnightRun,720,275,132);
        testEnemy3 = new Enemy("Skelly Boy",10,1,skeletonIdle,fireKnightRun,600,375,132);
        enemyTeam =  new Enemy[3]; enemyTeam[0] = testEnemy1; enemyTeam[1] = testEnemy2; enemyTeam[2] = testEnemy3;
        for (Player enemy: enemyTeam){
            enemy.exchange(enemies[(int)(Math.random() * 3)]);
        }







        pressedKeys = new boolean[128]; //all keys
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();
        rounds++;
        //start();


    }



    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(background,0,0,null); //Temp rn



        g.drawImage(uiBox,45,450,null); //Temp rn
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.BOLD, 25));
        g.drawString(currentPlayer.getName(), 420, 490);
        g.setFont(new Font("Courier New", Font.BOLD, 15));
        g.drawString(currentPlayer.getCurrentSkill().getName(), 400, 510);
        if (currentPlayer.getCurrentSkill().isAoe()){
            g.drawString((currentPlayer.getCurrentSkill().getDamageMultiplier() * currentPlayer.getAttack()) + " (AOE)", 400, 530);
        } else {
            g.drawString((currentPlayer.getCurrentSkill().getDamageMultiplier() * currentPlayer.getAttack()) + " (Single Target)", 400, 530);
        }

        g.setFont(new Font("Courier New", Font.BOLD, 40));
        g.setColor(Color.PINK);
        g.drawString("Round " + rounds, 340, 40);

        g.setFont(new Font("Courier New", Font.BOLD, 12));
        g.setColor(Color.BLUE);
        g.drawString("Selected", currentPlayer.getX(), currentPlayer.getY() - 2);

        boolean pAlive = false;
        boolean eAlive = false;


        for (Player player: playerTeam){
            if (player.isAlive()) {
                pAlive = true;
                g.drawImage(player.getSkillSet().getCurrentIMG(), player.getX(), player.getY() - 40, null);
                g.drawImage(player.getFrame(), player.getX() - 20, player.getY(), null); //the offset is just for show right now
                g.setColor(Color.RED);
                g.setFont(new Font("Courier New", Font.BOLD, 15));
                g.drawString(player.getCurrentHealth() + "/" + player.getHealth(), player.getX(), player.getY()+75);

            } else {
                g.setFont(new Font("Courier New", Font.BOLD, 30));
                g.setColor(Color.RED);
                g.drawString("KO", player.getX(), player.getY()+45);
            }


        }
        for (Enemy enemy: enemyTeam){
            if (enemy.isAlive()){
                eAlive = true;
            }
        }


        for (Enemy enemy: enemyTeam){
            if (enemy.isAlive()){
                g.drawImage(enemy.getFrame(), enemy.getX(), enemy.getY(), null); //the offset is just for show right now
                g.setColor(Color.RED);
                g.setFont(new Font("Courier New", Font.BOLD, 15));
                g.drawString(enemy.getCurrentHealth() + "/" + enemy.getHealth(), enemy.getX(), enemy.getY()+75);
            } else {
                g.setFont(new Font("Courier New", Font.BOLD, 30));
                g.setColor(Color.RED);
                g.drawString("KO", enemy.getX(), enemy.getY()+45);
            }
        }

        if (currentPlayer.getCurrentSkill().isAoe()){
            for (Enemy player: enemyTeam){
                g.drawImage(crosshair,player.getX()-10,player.getY()-10,null);
            }

        }

        else if (currentPlayer.getCurrentSkill().getDamageMultiplier() != 0){
            Player target = enemyTeam[currentPlayer.getCurrentSkill().getTarget()];
            g.drawImage(crosshair,target.getX()-10, target.getY()-10, null);

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



        for (int i = 0; i < playerTeam.length; i++){
            Skill c = playerTeam[i].getCurrentSkill();

            if (c.isAoe()) {
                for (Enemy player: enemyTeam) {
                    g.setColor(Color.RED);
                    g.setFont(new Font("Courier New", Font.BOLD, 10));
                    g.drawString(playerTeam[i].getName() + ": " + c.getName(), player.getX() + 50, player.getY() + (15 * i));
                        }
            } else if (playerTeam[i].getCurrentSkill().getDamageMultiplier() != 0) {
                Player target = enemyTeam[playerTeam[i].getCurrentSkill().getTarget()];
                g.setColor(Color.RED);
                g.setFont(new Font("Courier New", Font.BOLD, 10));
                g.drawString(playerTeam[i].getName() + ": " + c.getName(), target.getX() + 50, target.getY() + (15 * i));
                    }
        }


        if (!pAlive){
                g.drawImage(blackScreen,0,0,null);
                g.setColor(Color.RED);
                g.setFont(new Font("Courier New", Font.BOLD, 90));
                g.drawString("YOU LOST!",200,300);
        }
        if (!eAlive){
            g.drawImage(blackScreen,0,0,null);
            g.setColor(Color.RED);
            g.setFont(new Font("Courier New", Font.BOLD, 90));
            g.drawString("YOU WIN!",200,300);

        }






    }

    public void nextWave(){
        try {
           background =  ImageIO.read(new File("src/Assets/backgrounds/bg" + ((int)(Math.random()*6)+1) + ".png"));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        BufferedImage[] skeletonIdle = Utility.processAnimFrames("src/Assets/skeleton/idle/idle_",4);
        testEnemy1 = new Enemy("Skelly Boy",10,1,skeletonIdle,skeletonIdle,600,175,132);
        testEnemy2 = new Enemy("Skelly Boy",10,1,skeletonIdle,skeletonIdle,720,275,132);
        testEnemy3 = new Enemy("Skelly Boy",10,1,skeletonIdle,skeletonIdle,600,375,132);
        enemyTeam =  new Enemy[3]; enemyTeam[0] = testEnemy1; enemyTeam[1] = testEnemy2; enemyTeam[2] = testEnemy3;

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

        if (pressedKeys[49] && testPlayer1.isAlive()){
            System.out.println("1");
            if (currentPlayer != testPlayer1){
                currentPlayer = testPlayer1;
            } else {
                testPlayer1.changeSkill();
            }
        }
        if (pressedKeys[50] && testPlayer2.isAlive()){
            System.out.println("2");
            if (currentPlayer != testPlayer2){
                currentPlayer = testPlayer2;
            } else {

                testPlayer2.changeSkill();
                }
            }
        if (pressedKeys[51] && testPlayer3.isAlive()){
            System.out.println("3");
            if (currentPlayer != testPlayer3){
                currentPlayer = testPlayer3;
            } else {
                testPlayer3.changeSkill();
                System.out.println(currentPlayer.getCurrentSkill().getName());
            }
        }
        if (pressedKeys[32]){
            System.out.println("Bop");
            currentPlayer.getCurrentSkill().changeTargets();
        }
        if (pressedKeys[10]) {
            System.out.println("Enter");
            boolean alive = false;
            for (Enemy enemy : enemyTeam) {
                if (enemy.isAlive()) {
                    alive = true;
                }
            }
            if (alive) {
                for (int i = 2; i >= 0 ;i--){
                    Player player = playerTeam[i];
                    if (player.isAlive()) {
                        Skill c = player.getCurrentSkill();
                        JFrame frame = new JFrame(player.getName() + "'s Turn!");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(900, 600);
                        frame.setLocationRelativeTo(null);
                        // auto-centers frame in screen
                        if (c.isAoe()) {
                            ActionPanel panel = new ActionPanel(frame, player, enemyTeam);
                            frame.add(panel);
                            frame.setVisible(true);
                            for (Enemy enemy : enemyTeam) {
                                enemy.takeDamage((int)(player.getAttack() * player.getCurrentSkill().getDamageMultiplier()));
                            }
                        } else {
                            if (player.getCurrentSkill().getDamageMultiplier() != 0) {
                                Player target = enemyTeam[c.getTarget()];
                                ActionPanel panel = new ActionPanel(frame, player, target);
                                frame.add(panel);
                                frame.setVisible(true);

                                target.takeDamage((int) (player.getAttack() * player.getCurrentSkill().getDamageMultiplier()));
                            }
                        }
                    }

                }
                for (Enemy enemy : enemyTeam) {
                    if (enemy.isAlive()) {
                        int attack = (int) (Math.random() * 2);
                        if (attack == 0) {
                            Player victim =  playerTeam[(int) (Math.random() * 3)];
                            if (victim.getCurrentSkill().getDamageMultiplier() != 0){
                                playerTeam[(int) (Math.random() * 3)].takeDamage(enemy.getAttack() * 2);
                            }
                        }
                        if (attack == 1) {
                            for (Player player : playerTeam) {
                                if (player.getCurrentSkill().getDamageMultiplier() != 0){
                                    player.takeDamage(enemy.getAttack());
                                }
                            }
                        }
                    }
                }
                rounds++;
            } else {
                nextWave();
            }
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
        for (int i = 0; i < playerTeam.length; i++) {
            if (playerTeam[i].rect().contains(e.getPoint()) && playerTeam[i].isAlive()) {
                System.out.println("clicked on " + playerTeam[i].getName() + i);
                if (playerTeam[i] == currentPlayer){
                    currentPlayer.changeSkill();
                } else {
                    currentPlayer = playerTeam[i];
                }


            }
        }
        for (int i = 0; i < enemyTeam.length; i++) {
            if (enemyTeam[i].rect().contains(e.getPoint())) {
                System.out.println("clicked on " + enemyTeam[i].getName() + i);
                currentPlayer.getCurrentSkill().setTarget(i);
            }
        }


    }


    @Override
    public void mouseEntered(MouseEvent e) { //try not to use

    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse Clicked");

    }

}
