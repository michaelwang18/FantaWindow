import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ActionPanel extends JPanel implements ActionListener {

    private JTextField textField;
    private JButton submitButton;
    private JButton clearButton;
    private JFrame enclosingFrame;
    private BufferedImage currentBG;
    private int timerCount;

    private BufferedImage background1;
    private BufferedImage background2;
    private BufferedImage background3;

    private Player attacker;
    private Player victim;

    private Player[] victims;
    private Timer timer;

    public ActionPanel(JFrame frame, Player attack, Player victim) {
        attacker = attack;
        timer = new Timer(15, this);
        timer.start();
        this.victim = victim;
        enclosingFrame = frame;
        try {
            background1 = ImageIO.read(new File("src/Assets/backgrounds/action_background1.jpg"));
            background2 = ImageIO.read(new File("src/Assets/backgrounds/action_background2.jpg"));
            background3 = ImageIO.read(new File("src/Assets/backgrounds/action_background3.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("ACTION PANEL");
        }

        submitButton = new JButton("Next");
        add(submitButton);
        submitButton.addActionListener(this);
        currentBG = background1;
        timerCount = 0;
    }

    public ActionPanel(JFrame frame, Player attack, Player[] victims) {
        attacker = attack;
        timer = new Timer(10, this);
        timer.start();
        this.victims = victims;
        enclosingFrame = frame;
        try {
            background1 = ImageIO.read(new File("src/Assets/backgrounds/action_background1.jpg"));
            background2 = ImageIO.read(new File("src/Assets/backgrounds/action_background2.jpg"));
            background3 = ImageIO.read(new File("src/Assets/backgrounds/action_background3.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("ACTION PANEL");
        }
        submitButton = new JButton("Next");
        add(submitButton);
        submitButton.addActionListener(this);
        currentBG = background1;
        timerCount = 0;
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (timerCount == 0){
            currentBG = background1;
        }
        if (timerCount == 5){
            currentBG = background2;
        }
        if (timerCount == 10){
            currentBG = background3;
            timerCount = -1;
        }
        g.drawImage(currentBG,0,0,null);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.setColor(Color.RED);
        g.drawString(attacker.getCurrentSkill().getName(), 400, 50);
        int dmg  = (int) (attacker.getAttack() * attacker.getCurrentSkill().getDamageMultiplier());

        if (attacker.getIdleAnim().getCurrentFrame() == 0){
            attacker.attackAnimation();
        }

        g.drawImage(attacker.getFrame(),300,300,null);
        if (victim != null){
            g.drawImage(victim.getFrame(),350,300,null);
            g.setFont(new Font("Arial", Font.ITALIC, 25));
            g.drawString(dmg + " Damage!",10,70);
        } else {
            int aliveCount = 0;
            for (Player p: victims){
                if (p.isAlive()){
                    g.drawImage(p.getFrame(),p.getX(),p.getY(),null);
                    aliveCount++;
                }
            }
            g.setFont(new Font("Arial", Font.ITALIC, 25));
            g.drawString((dmg * aliveCount) + " Damage!",10,70);
        }



        //g.drawImage(goomba, 200, 100, null);

        submitButton.setLocation(800, 500);
        submitButton.setBackground(Color.red);
    }

    // ACTIONLISTENER INTERFACE METHODS
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            attacker.getIdleAnim().setAtk(false);
            enclosingFrame.dispose();
        }
        if (e.getSource() instanceof Timer) {
            timerCount++;
            repaint();
        }
    }


}
