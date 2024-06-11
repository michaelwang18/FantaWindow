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

    private BufferedImage background;
    private BufferedImage goomba;

    private Player attacker;
    private Player victim;

    private Player[] victims;
    private Timer timer;

    public ActionPanel(JFrame frame, Player attack, Player victim) {
        attacker = attack;
        timer = new Timer(10, this);
        timer.start();
        this.victim = victim;
        enclosingFrame = frame;
        try {
            goomba = ImageIO.read(new File("src/Assets/skill_icons/Crosshair.png"));
            background = ImageIO.read(new File("src/Assets/action_background.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        submitButton = new JButton("Next");
        add(submitButton);
        submitButton.addActionListener(this);
    }

    public ActionPanel(JFrame frame, Player attack, Player[] victims) {
        attacker = attack;
        timer = new Timer(10, this);
        timer.start();
        this.victims = victims;
        enclosingFrame = frame;
        try {
            goomba = ImageIO.read(new File("src/Assets/skill_icons/Crosshair.png"));
            background = ImageIO.read(new File("src/Assets/action_background.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        submitButton = new JButton("Next");
        add(submitButton);
        submitButton.addActionListener(this);
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background,0,0,null);
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
            g.drawString(dmg + " Damage!",250,30);
        } else {
            int aliveCount = 0;
            for (Player p: victims){
                if (p.isAlive()){
                    g.drawImage(p.getFrame(),p.getX(),p.getY(),null);
                    aliveCount++;
                }
            }
            g.setFont(new Font("Arial", Font.ITALIC, 25));
            g.drawString((dmg * aliveCount) + " Damage!",250,30);
        }



        //g.drawImage(goomba, 200, 100, null);

        submitButton.setLocation(800, 500);
        submitButton.setBackground(Color.red);
    }

    // ACTIONLISTENER INTERFACE METHODS
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            enclosingFrame.dispose();
        }
        if (e.getSource() instanceof Timer) {
            repaint();
        }
    }


}
