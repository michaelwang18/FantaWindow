import javax.swing.*;
import java.awt.*;

public class MainFrame implements Runnable{

    private Game panel;

    public MainFrame(String name){
        JFrame frame = new JFrame("Untitled FG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,600);
        System.out.println("set size");
        frame.setLocationRelativeTo(null);
        System.out.println("HUH");

        panel = new Game();
        frame.add(panel);

    //    frame.add(panel);
        frame.setVisible(true);

        Thread thread = new Thread(this);
        thread.start();

       // panel.start();


    }


    @Override
    public void run() {
      while (true){
          panel.repaint(); // finish later
      }
    }
}
