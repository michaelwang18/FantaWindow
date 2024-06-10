import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WelcomePanel extends JPanel implements ActionListener {

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

    public WelcomePanel(JFrame frame, Player attack, Player victim) {
        attacker = attack;
        timer = new Timer(10, this);
        timer.start();
        this.victim = victim;
        enclosingFrame = frame;
        try {
            goomba = ImageIO.read(new File("src/Assets/skill_icons/Crosshair.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        textField = new JTextField(10);
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");
        add(textField);  // textField doesn't need a listener since nothing needs to happen when we type in text
        add(submitButton);
        add(clearButton);
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    public WelcomePanel(JFrame frame, Player attack, Player[] victims) {
        attacker = attack;
        this.victims = victims;
        enclosingFrame = frame;
        try {
            goomba = ImageIO.read(new File("src/Assets/skill_icons/Crosshair.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        textField = new JTextField(10);
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");
        add(textField);  // textField doesn't need a listener since nothing needs to happen when we type in text
        add(submitButton);
        add(clearButton);
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.RED);
        g.drawString(attacker.getCurrentSkill().getName(), 50, 30);
        g.drawImage(attacker.getFrame(),300,300,null);
        if (victim != null){
            g.drawImage(victim.getFrame(),350,300,null);
        } else {
            for (Player p: victims){
                g.drawImage(p.getFrame(),p.getX(),p.getY(),null);
            }
        }


        System.out.println("test");


        g.drawImage(goomba, 200, 100, null);

        textField.setLocation(50, 50);
        submitButton.setLocation(50, 100);
        clearButton.setLocation(150, 100);
    }

    // ACTIONLISTENER INTERFACE METHODS
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            repaint();
        }
    }
}
